package equifax.automation.enums;

public enum AWSBucketConstants {
    S3_BUCKET_KOUNT("eqfx-dev-kount-nonprod-payment-risk-evaluation"),
    AWS_KOUNT_P5_BUCKET("kount-p5");

    private final String message;

    AWSBucketConstants(String s) {
        this.message=s;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
