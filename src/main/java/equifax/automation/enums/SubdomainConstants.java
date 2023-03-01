package equifax.automation.enums;

public enum SubdomainConstants {

    PAYMENT_RISK_EVALUATION_SUBDOMAIN("PAYMENT_RISK_EVALUATION"),
    JCE_REGISTRATION_SUBDOMAIN("JCE_REGISTRATION"),
    TABLE_NAME_JCE_JOUR("J_DO_CONSUMER_IDENTITY_JCE_REGISTRATION"),
    TABLE_NAME_JCE_PURP("PURP_DO_CONSUMER_IDENTITY_JCE_REGISTRATION_DFLT");

    private final String message;

    SubdomainConstants(String s){
        this.message=s;
    }

    @Override
    public String toString(){
        return this.message;
    }
}
