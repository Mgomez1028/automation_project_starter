package equifax.automation.dataupload;

import equifax.automation.model.LoadingInformation;

public interface IUploadDataStrategy {
    String uploadData(LoadingInformation loadingInformation);

    String getCorrelationID();

    void createFileName();
}
