package equifax.automation.dataupload;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;
import equifax.automation.model.LoadingInformation;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;

import java.nio.file.Files;
import java.nio.file.Paths;

public class UploadFileToGCPBucket implements IUploadDataStrategy {

    LoadingInformation loadingInformation;

    @SneakyThrows
    @Override
    public String uploadData(LoadingInformation loadingInformation) {
        this.loadingInformation = loadingInformation;
        Storage storage = StorageOptions.newBuilder().setProjectId(loadingInformation.getProjectId()).build().getService();
        BlobId blobId = BlobId.of(loadingInformation.getBucketName(), String.format(loadingInformation.getBucketPath(), loadingInformation.getFileName()));
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        storage.create(blobInfo, Files.readAllBytes(Paths.get(loadingInformation.getObjectPath())));

        Thread.sleep(5_000L);

        System.out.println(
                "File " + loadingInformation.getObjectPath() + " uploaded to bucket " + loadingInformation.getBucketName() + " as " + loadingInformation.getFileName());
        return getCorrelationID();
    }


    @Override
    public String getCorrelationID() {
        int correlationIdPosition = 3;
        boolean found = false;
        String correlationId = "";

        Storage storage = StorageOptions.newBuilder().setProjectId(loadingInformation.getProjectId()).build().getService();
        Page<Blob> blobs = storage.list(loadingInformation.getBucketName(),
                Storage.BlobListOption.prefix(loadingInformation.getPrefix()),
                Storage.BlobListOption.currentDirectory());


        for (Blob blob : blobs.iterateAll()) {
            Page<Blob> files = storage.list(
                    loadingInformation.getBucketName(),
                    Storage.BlobListOption.prefix(blob.getName()),
                    Storage.BlobListOption.currentDirectory());

            for (Blob file : files.iterateAll()) {
                if (file.getName().contains(loadingInformation.getFileName())) {
                    found = true;
                    correlationId = blob.getName().split("/")[correlationIdPosition];
                    Serenity.setSessionVariable("ci").to(correlationId);
                }
                if (found) break;
            }
            if (found) break;
        }

        return correlationId;
    }

    @Override
    public void createFileName() {

    }
}
