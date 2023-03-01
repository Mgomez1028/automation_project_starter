package equifax.automation.dataupload;

import equifax.automation.model.LoadingInformation;

public class Context {
    private IUploadDataStrategy uploadDataStrategy;

    public Context(IUploadDataStrategy uploadDataStrategy){//recibe cualquier imple de esta interfaz
        this.uploadDataStrategy=uploadDataStrategy;
    }

    public String execute(LoadingInformation loadingInformation){
        return this.uploadDataStrategy.uploadData(loadingInformation);
    }
}
