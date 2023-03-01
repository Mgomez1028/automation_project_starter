package equifax.automation.enums;

public enum GCPBucketConstants {

    BUCKET_NAME_DOMINO("dprep-lz-cat-int-lat-3f"),
    BUCKET_NAME_KOUNT("dprep-lz-cat-int-ga");

    private final String message;

    GCPBucketConstants(String s) {
        message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
