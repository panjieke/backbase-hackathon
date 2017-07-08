package alexa.skills.backbase.lib;

import alexa.skills.backbase.model.AccountDetails;
import alexa.skills.backbase.model.Balance;
import alexa.skills.backbase.model.TransactionsResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

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
    public static final String CREDENTIALS = "username=\"mobiquitybbhack\", password=\"123456789@Mo\"";
    public static final String CONSUMER = "consumer_key=\"hrfs2sfxnk4fnj4god3ig0fligpgqabflatbkli0\"";
    public static final String COMMA = ", ";
    public static final String CONSUMER_CREDENTIALS = CREDENTIALS + COMMA + CONSUMER;
    public static final String TRANSACTION_ID = "transaction_id";
    private static final Set<String> TRANSACTIONS = new HashSet<>();

    public String login() {
        try {
            HttpResponse<JsonNode> response = UNIREST.post(HOST + "/my/logins/direct")
                    .header(AUTHORIZATION_HEADER, "DirectLogin " + CONSUMER_CREDENTIALS)
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
            AccountDetails accountDetails = gson.fromJson((response.getBody().getObject()).toString(), AccountDetails.class);
            return accountDetails;
        } catch (UnirestException e) {
            log.error("get Account Details request failed: ", e);
            throw new RuntimeException(e);
        }
    }

    public String getAccountDisplayName(String bankId, String accountId, String viewId) {
        try {
            HttpResponse<JsonNode> response = UNIREST.get(HOST + BASE_PATH + "/banks/" + bankId + "/accounts/" + accountId + "/" + viewId + "/account")
                    //.header(AUTHORIZATION, "BEARER " + token)
                    .asJson();

            JSONArray owners = (JSONArray) response.getBody().getObject().get("owners");
            JSONObject mainOwner = ((JSONObject) owners.get(0));

            return (String) mainOwner.get("display_name");
        } catch (UnirestException e) {
            log.error("get Account Display Name request failed: ", e);
            throw new RuntimeException(e);
        }
    }

    public String getAccountBalance(String bankId, String accountId, String viewId) {
        Balance balance = getAccountDetails(bankId, accountId, viewId).getBalance();
        return balance.getAmount() + " " + balance.getCurrency();
    }

    public String transferAmount(String bankId, String accountId, String viewId, String otherBankId, String otherAccountId, double amount) {
        try {

            String token = login();

            String transactionBody = "{  \"bank_id\":\"" + otherBankId + "\",  \"account_id\":\"" + otherAccountId + "\",  \"amount\":\"" + amount + "\"}";

            HttpResponse<JsonNode> response = UNIREST.post(HOST + BASE_PATH + "/banks/" + bankId + "/accounts/" + accountId + "/" + viewId + "/transactions")
                    .header(AUTHORIZATION_HEADER, "DirectLogin " + CONSUMER_CREDENTIALS + COMMA + "token=" + token)
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .body(transactionBody).asJson();

            String transactionId = (String) response.getBody().getObject().get(TRANSACTION_ID);
            //logTransaction(getTransactionDetailsById(bankId, accountId, viewId, transactionId));

            return transactionId;
        } catch (UnirestException e) {
            log.error("Login request failed: ", e);
            throw new RuntimeException(e);
        }
    }


    public TransactionsResponse getAllTransactions(String bankId, String accountId, String viewId) {
        try {
            HttpResponse<JsonNode> response = UNIREST.get(HOST + BASE_PATH + "/banks/" + bankId + "/accounts/" + accountId + "/" + viewId + "/transactions")
                    .asJson();

            Gson gson = new Gson();
            TransactionsResponse transactionsResponse = gson.fromJson(response.getBody().getObject().toString(), TransactionsResponse.class);
            return transactionsResponse;
        } catch (UnirestException e) {
            log.error("get Account Details request failed: ", e);
            throw new RuntimeException(e);
        }
    }

    public String getTransactionDetailsById(String bankId, String accountId, String viewId, String transactionId) {
        try {
            HttpResponse<JsonNode> response = UNIREST.get(HOST + BASE_PATH + "/banks/" + bankId + "/accounts/" + accountId + "/" + viewId + "/transactions/" + transactionId + "/transaction")
                    .asJson();

            return response.getBody().getObject().toString();
        } catch (UnirestException e) {
            log.error("get Account Details request failed: ", e);
            throw new RuntimeException(e);
        }
    }

    private void logTransaction(String transactionBody) {
        try {
            TRANSACTIONS.add(transactionBody);

            StringBuilder sb = new StringBuilder();

            sb.append("{ \"transactions\": [ ");
            for (String t : TRANSACTIONS) {
                sb.append(t).append(COMMA);
            }
            int lastComma = sb.lastIndexOf(COMMA);
            sb.replace(lastComma,lastComma+1, "");
            sb.append("] }");


            UNIREST.put("https://api.myjson.com/bins/1fisnb")
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .body(transactionBody).asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }
}
