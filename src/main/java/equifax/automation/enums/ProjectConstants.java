package equifax.automation.enums;

public enum ProjectConstants {

    PROJECT_ID_DPREP_C874("df-cat-dprep-dev-npe-c874");

    private final String message;

    ProjectConstants(String s) {
        message = s;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
