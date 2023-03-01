package equifax.automation.enums;

public enum FileNameSubdomainConstans {
    PAYMENT_RISK_EVALUATION_NAME_FILE("PAYMENT_RISK_EVALUATION_%s.json.gpg"),
    JCE_REGISTRATION_NAME_FILE("JCE_REGISTRATION_%s.txt.gpg");

    private final String message;

    FileNameSubdomainConstans(String s) {
        this.message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
