package equifax.automation.dataupload;

import equifax.automation.enums.FileNameSubdomainConstans;
import equifax.automation.model.LoadingInformation;
import equifax.automation.utils.RandomUtil;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import static equifax.automation.enums.AWSBucketConstants.AWS_KOUNT_P5_BUCKET;
import static equifax.automation.enums.GCPBucketConstants.BUCKET_NAME_DOMINO;
import static equifax.automation.enums.GCPBucketConstants.BUCKET_NAME_KOUNT;
import static equifax.automation.enums.ProjectConstants.PROJECT_ID_DPREP_C874;

public class UploadFileSteps {
    @Step("uploading file to aws bucket")
    public void uploadingFileToAWSBucket(String bucketName) {
        String RandomFileName = RandomUtil.getRandomFileName();
        String filePath = "src/test/resources/data/kount/payment_risk_evaluation/inputfiles/PAYMENT_RISK_EVALUATION_080223_3Records.json.gpg";
        String keyFileName = String.format(FileNameSubdomainConstans.PAYMENT_RISK_EVALUATION_NAME_FILE.toString(), RandomFileName);
        System.out.println("filename->" + keyFileName);
        LoadingInformation loadingInformation = LoadingInformation.builder()
                .bucketName(String.valueOf(AWS_KOUNT_P5_BUCKET))
                .objectKey(keyFileName)
                .objectPath(filePath)
                .fileName(keyFileName)
                .build();

        Context context = new Context(new UploadFileToS3Bucket());
        Serenity.setSessionVariable("correlationId").to(context.execute(loadingInformation));
        String correlationId = Serenity.sessionVariableCalled("correlationId").toString();
        System.out.println("The file has been uploaded to the AWS bucket with correlationId->" + correlationId);
    }

    @Step("uploading file to GCP bucket")
    public void uploadingFileToGCPBucketInteractions(String bucketName) {
        String RandomFileName = RandomUtil.getRandomFileName();
        String filePath = "src/test/resources/data/kount/payment_risk_evaluation/inputfiles/PAYMENT_RISK_EVALUATION_HIST_P5KOUNT012723.json.gpg";
        String bucketPath = "PAYMENT_RISK_EVALUATION/PAYMENT_RISK_EVALUATION__HIST_CONTRIBUTION/PAYMENT_RISK_EVALUATION_HIST_%s.json.gpg";
        String prefix = "PAYMENT_RISK_EVALUATION/PAYMENT_RISK_EVALUATION__HIST_CONTRIBUTION/_matched/";
        System.out.println("filename->" + String.format(bucketPath,RandomFileName));
        LoadingInformation loadingInformation = LoadingInformation.builder()
                .bucketName(String.valueOf(BUCKET_NAME_KOUNT))
                .objectPath(filePath)
                .fileName(RandomFileName)
                .projectId(String.valueOf(PROJECT_ID_DPREP_C874))
                .bucketPath(bucketPath)
                .prefix(prefix)
                .build();

        Context context = new Context(new UploadFileToGCPBucket());
        Serenity.setSessionVariable("correlationId").to(context.execute(loadingInformation));
        String correlationId = Serenity.sessionVariableCalled("correlationId").toString();
        System.out.println("The file has been uploaded to the GCP bucket with correlationId->" + correlationId);
    }

    @Step("uploading file to GCP bucket")
    public void uploadingFileToGCPBucket(String bucketName) {
        String RandomFileName = RandomUtil.getRandomFileName();
        String filePath = "src/test/resources/data/domino/jce_registration/inputfiles/CONSUMER_IDENTITY_JCE_REGISTRATION_1Record_aut.txt.gpg";
        String bucketPath = "DO_DATA_CREDITO/JCE_REGISTRATION_FL/CONSUMER_IDENTITY_JCE_REGISTRATION_%s.txt.gpg";
        String prefix = "DO_DATA_CREDITO/JCE_REGISTRATION_FL/_matched/";
        System.out.println("filename->" + String.format(bucketPath,RandomFileName));
        LoadingInformation loadingInformation = LoadingInformation.builder()
                .bucketName(String.valueOf(BUCKET_NAME_DOMINO))
                .objectPath(filePath)
                .fileName(RandomFileName)
                .projectId(String.valueOf(PROJECT_ID_DPREP_C874))
                .bucketPath(bucketPath)
                .prefix(prefix)
                .build();

        Context context = new Context(new UploadFileToGCPBucket());
        Serenity.setSessionVariable("correlationId").to(context.execute(loadingInformation));
        String correlationId = Serenity.sessionVariableCalled("correlationId").toString();
        System.out.println("The file has been uploaded to the GCP bucket with correlationId->" + correlationId);
    }
}
