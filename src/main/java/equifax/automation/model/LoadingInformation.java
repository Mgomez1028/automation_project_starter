package equifax.automation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.s3.S3Client;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoadingInformation {
    //common attributes
    private String bucketName;
    private String objectKey;
    private String objectPath;
    private String fileName;
    //aws attributes
    private S3Client s3;
    //gcp attributes
    private String projectId;
    private String bucketPath;
    private String prefix;
}
