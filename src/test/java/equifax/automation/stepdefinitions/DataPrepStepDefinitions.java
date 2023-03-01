package equifax.automation.stepdefinitions;

import equifax.automation.dataupload.UploadFileSteps;
import io.cucumber.java.en.Given;
import net.thucydides.core.annotations.Steps;

import java.util.EmptyStackException;

public class DataPrepStepDefinitions {
    @Steps
    UploadFileSteps uploadFileSteps;

    @Given("the data is uploaded to an {} bucket named {} and contains {int} records")
    public void theDataIsUploadedToAnGCPBucketNamedBucketNameAndContainsRecords(String cloudServiceProviders, String bucketName, int recordsNumber) {
        switch (cloudServiceProviders) {
            case "GCP":
                uploadFileSteps.uploadingFileToGCPBucket(bucketName);
                System.out.println("subiendo archivo GCP");
                break;
            case "AWS":
                //uploadFileSteps.uploadingFileToAWSBucket(bucketName);
                System.out.println("subiendo archivo aws");
                break;
            default:throw new EmptyStackException();
        }
    }
}
