package equifax.automation.apiclient;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class PostFedssoEquifaxOauth {

    private static final String TOKEN_NAME = "int-token";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CALLBACK_URL = "https://localhost/df_us_platform/accesstoken";
    private static final String DATA_FABRIC_API_BASE_URL = "https://fedssoqa.equifax.com/as/authorization.oauth2?";  //Es la misma Auth URL?
    private static final String COMPLETED = "https://fedssoqa.equifax.com/as/authorization.oauth2?response_type=token%20id_token&nonce=adk23409sdfkdfDAF2";
    //GET https://fedssoqa.equifax.com/as/authorization.oauth2?response_type=token%20id_token&nonce=adk23409sdfkdfDAF2&response_type=token&client_id=df_us_platform&state=adk23409sdfkdfDAF2&scope=openid%20profile%20groups&redirect_uri=https%3A%2F%2Flocalhost%2Fdf_us_platform%2Faccesstoken
    //GET https://fedssoqa.equifax.com/as/authorization.oauth2?response_type=token%20id_token&nonce=adk23409sdfkdfDAF2&response_type=token&client_id=df_us_platform&state=adk23409sdfkdfDAF2&scope=openid%20profile%20groups&redirect_uri=https%3A%2F%2Flocalhost%2Fdf_us_platform%2Faccesstoken
    //enviar mis credenciales y luego hay que autorizar por celular (libreria que genere otp para evitar eso?)
    //https://fedssoqa.equifax.com/as/authorization.oauth2
    // ?response_type=token%20id_token
    // &nonce=adk23409sdfkdfDAF2
    // &response_type=token
    // &client_id=df_us_platform
    // &state=adk23409sdfkdfDAF2
    // &scope=openid%20profile%20groups
    // &redirect_uri=https%3A%2F%2Flocalhost%2Fdf_us_platform%2Faccesstoken

    private static final String SCOPE = "scope";
    private static final String RESPONSE_TYPE = "response_type";
    private static final String NONCE = "adk23409sdfkdfDAF2";
    private static final String STATE = "adk23409sdfkdfDAF2";
    private static final String CLIENT_ID = "client_id";
    private static final String REDIRECT_URI = "redirect_uri";

    @Step("Request authentication Oauth")
    public void postFedssoOauth() {
        RestAssured.baseURI = "https://fedssoqa.equifax.com/as/authorization.oauth2?response_type=token%20id_token&nonce=adk23409sdfkdfDAF2&response_type=token&client_id=df_us_platform&state=adk23409sdfkdfDAF2&scope=openid%20profile%20groups&redirect_uri=https%3A%2F%2Flocalhost%2Fdf_us_platform%2Faccesstoken";
        //String fedsoOauthPath= String.format()
        //que  debe ir en el header/params
        SerenityRest
                .given()
                .contentType("?")
                .relaxedHTTPSValidation()
                .header(SCOPE,"openid profile groups")
                .header("CLIENT_ID","df_us_platform")
                .pathParams(GRANT_TYPE, "implicit")
                .post(DATA_FABRIC_API_BASE_URL);//de donde obtengo el response_type
    }

    //Please sign on using your desktop credentials
    //EIS ID:mxg560
    //Password:fdds
}
