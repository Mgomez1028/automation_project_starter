package equifax.automation.enums;

public enum DomainConstants {
    INTERACTIONS_DOMAIN("INTERACTIONS"),
    DO_CONSUMER_IDENTITY_DOMAIN("DO_CONSUMER_IDENTITY");

    private final String message;

    DomainConstants(String s) {
        message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
