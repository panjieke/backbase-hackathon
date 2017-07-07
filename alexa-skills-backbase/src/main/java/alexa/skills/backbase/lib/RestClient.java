package alexa.skills.backbase.lib;

import alexa.skills.backbase.model.AccountDetails;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestClient {

    private static final String DEFAULT_LOGIN = "";
    private static final Logger log = LoggerFactory.getLogger(RestClient.class);
    private static final Unirest UNIREST = new Unirest();
    private static final String HOST = "https://apisandbox.openbankproject.com";
    private static final String BASE_PATH = "/obp/v1.2.1";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";
    private static final String AUTHORIZATION = "Authorization";
    private static final String ACCESS_TOKEN = "token";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public String login() {
        try {
            HttpResponse<JsonNode> response = UNIREST.post(HOST + "/my/logins/direct")
                    .header(AUTHORIZATION_HEADER, "DirectLogin username=\"mobiquitybbhack\", password=\"123456789@Mo\", consumer_key=\"hrfs2sfxnk4fnj4god3ig0fligpgqabflatbkli0\"")
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .body(DEFAULT_LOGIN).asJson();
            return (String) response.getBody().getObject().get(ACCESS_TOKEN);
        } catch (UnirestException e) {
            log.error("Login request failed: ", e);
            throw new RuntimeException(e);
        }
    }


    public String getUserName(String token) {
        try {
            HttpResponse<JsonNode> response = UNIREST.get(HOST + "/rest/entities/USERS/instances/jannie").header(AUTHORIZATION, "BEARER " + token).asJson();
            return (String) ((JSONObject) response.getBody().getObject().get("entityInstance")).get("firstName");
        } catch (UnirestException e) {
            log.error("Get User request failed: ", e);
            throw new RuntimeException(e);
        }
    }

    public String getNextAppointment(String token) {
        try {
            HttpResponse<JsonNode> response = UNIREST.get(HOST + "/rest/entities/CONTACTPERSONEN/instances").header(AUTHORIZATION, "BEARER " + token).asJson();
            JSONArray entityInstances = (JSONArray) response.getBody().getObject().get("entityInstances");
            for (int i = 0; i < entityInstances.length(); i++) {
                JSONObject entry = ((JSONObject) entityInstances.get(i));

                String self = (String) ((JSONObject) entry.get("gebruiker")).get("__self");
                if (self != null && self.equals("/entities/USERS/instances/jannie")) {
                    return (String) entry.get("nextAppointmentText");
                }
            }

            return null;
        } catch (UnirestException e) {
            log.error("Get Next Appointment request failed: ", e);
            throw new RuntimeException(e);
        }
    }

    public AccountDetails getAccountDetails(String bankId, String accountId, String viewId) {
        try {
            HttpResponse<JsonNode> response = UNIREST.get(HOST + BASE_PATH + "/banks/" + bankId + "/accounts/" + accountId + "/" + viewId + "/account")
                    //.header(AUTHORIZATION, "BEARER " + token)
                    .asJson();

            Gson gson = new Gson();
            AccountDetails accountDetails = gson.fromJson(((JSONObject) response.getBody().getObject()).toString(), AccountDetails.class);
            return accountDetails;
        } catch (UnirestException e) {
            log.error("Get User request failed: ", e);
            throw new RuntimeException(e);
        }
    }
}
