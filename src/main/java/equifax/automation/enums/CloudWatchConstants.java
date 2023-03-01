package equifax.automation.enums;

public enum CloudWatchConstants {
    LOG_GROUP_EVENT_PUBLISHER("/aws/lambda/kount-p5-cloud-event-publisher"),
    LOG_GROUP_KOUNT_P5_PROCESSOR("/aws/lambda/kount-p5-processor"),
    FILTER_PATTERN_LOG_EVENT("Publishing Cloud Event"),
    FILTER_PATTERN_REQUEST_ID("RequestId");

    private final String message;

    CloudWatchConstants(String s) {
        this.message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
