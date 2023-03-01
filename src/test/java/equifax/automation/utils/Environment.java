package equifax.automation.utils;

import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.environment.SystemEnvironmentVariables;

public class Environment {

    private Environment() {
    }
    public static final EnvironmentVariables parameters = SystemEnvironmentVariables.createEnvironmentVariables();
    private static final String ENV_VARIABLE = "env";

    private static final String ENVI= System.getenv("env");
    private static final String ENV_KEY = "${env}";
    public static String getSystemEnvVariable(String variable) {
        return parameters.getValue(variable);
    }
    public static String getSerenityEnvVariable(String property) {
        return property.contains(ENV_KEY) ?
                //parameters.getProperty(property.replace(ENV_KEY, getSerenityEnvVariable(ENV_VARIABLE))) :
                parameters.getProperty(property.replace(ENV_KEY,ENVI)) :
                parameters.getProperty(property);
    }
}
