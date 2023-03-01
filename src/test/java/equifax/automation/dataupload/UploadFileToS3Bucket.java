package equifax.automation.dataupload;

import equifax.automation.model.LoadingInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static equifax.automation.cloudwatch.GetEvents.filterCWLogEvent;
import static equifax.automation.cloudwatch.GetLogStreams.getLastLogStreamFromLogGroup;
import static equifax.automation.enums.CloudWatchConstants.LOG_GROUP_KOUNT_P5_PROCESSOR;
import static equifax.automation.utils.GetObject.getObjectFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileToS3Bucket implements IUploadDataStrategy {
    private S3Client s3;
    Region region = Region.US_EAST_1;
    ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();

    private Date currentDate;
    private Long milliSeconds;

    @Override
    public String uploadData(LoadingInformation loadingInformation) {
        s3 = S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

        currentDate = new Date();
        System.out.println("current date start ->" + currentDate);
        this.milliSeconds = currentDate.getTime();

        try {
            Map<String, String> metadata = new HashMap<>();
            metadata.put("x-amz-meta-myVal", "test");
            PutObjectRequest putOb = PutObjectRequest.builder()
                    .bucket(loadingInformation.getBucketName())
                    .key(loadingInformation.getObjectKey())
                    .metadata(metadata)
                    .build();

            PutObjectResponse response = s3.putObject(putOb, RequestBody.fromBytes(getObjectFile(loadingInformation.getObjectPath())));

        } catch (S3Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return getCorrelationID();

    }

    @Override
    public String getCorrelationID() {
        int startIndex = 17;
        int endIndex = 53;
        String logGroupName = String.valueOf(LOG_GROUP_KOUNT_P5_PROCESSOR);
        CloudWatchLogsClient cloudWatchLogsClient = CloudWatchLogsClient.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();

        try {
            Thread.sleep(13_000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String lastLogStreamName = getLastLogStreamFromLogGroup(cloudWatchLogsClient, logGroupName);
        System.out.println("last stream name-> " + lastLogStreamName);
        String firstEvent = filterCWLogEvent(cloudWatchLogsClient, logGroupName, lastLogStreamName, milliSeconds);
        return firstEvent.substring(startIndex, endIndex);
    }


    @Override
    public void createFileName() {

    }
}
