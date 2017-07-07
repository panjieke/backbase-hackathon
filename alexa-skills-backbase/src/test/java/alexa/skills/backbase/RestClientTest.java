package alexa.skills.backbase;

import alexa.skills.backbase.lib.RestClient;
import alexa.skills.backbase.model.AccountDetails;
import org.junit.Test;

import static org.junit.Assert.*;

public class RestClientTest {



    @Test
    public void login() throws Exception {
        whenRequestLogin();
        thenTokenIsReturned();
    }

    @Test
    public void getAccount() {
        givenAccountDetails();
        whenRequestAccountDetails();
        thenAccountDetailsIsReturned();
        andAccountIdShouldBeTheSame();
    }

    @Test
    public void getDisplayName() {
        givenAccountDetails();
        whenRequestAccountDisplayName();
        thenDisplayNameIsReturned();
    }

    @Test
    public void getAccountBalance() {
        givenAccountDetails();
        whenRequestAccountBalance();
        thenAccountBalanceIsReturned();
    }

    //    @Test
    public void getUser() throws Exception {
        givenAccessToken();
        whenRequestUser();
        thenUserIs("Jannie");
    }

//    @Test
    public void getNextAppointment() throws Exception {
        givenAccessToken();
        whenRequestNextAppointment();
        thenAppointmentMessageIs("You have no upcoming visit scheduled for Ilja");
    }

    private void givenAccessToken() {
        token = restClient.login();
    }

    private void whenRequestLogin() {
        token = restClient.login();
    }

    private void whenRequestUser() {
        userName = restClient.getUserName(token);
    }

    private void whenRequestNextAppointment() {
        message = restClient.getNextAppointment(token);
    }

    private void thenTokenIsReturned() {
        assertNotNull(token);
    }

    private void thenUserIs(String name) {
        assertEquals(name, userName);
    }

    private void thenAppointmentMessageIs(String expectedMessage) {
        assertNotNull(message);
        assertEquals(expectedMessage, message);
    }

    private void givenAccountDetails() {
        bankId = "test-bank";
        accountId = "RR-000001";
        viewId = "owner";
    }

    private void whenRequestAccountDetails() {
        accountDetails = restClient.getAccountDetails(bankId, accountId, viewId);
    }

    private void thenAccountDetailsIsReturned() {
        assertNotNull(accountDetails);
    }

    private void andAccountIdShouldBeTheSame() {
        assertEquals(accountId,accountDetails.getId());
    }

    private void whenRequestAccountDisplayName() {
        displayName = restClient.getAccountDisplayName(bankId, accountId, viewId);
    }
    private void thenDisplayNameIsReturned() {
        assertNotNull(displayName);
    }

    private void whenRequestAccountBalance() {
        balance = restClient.getAccountBalance(bankId, accountId, viewId);
    }
    private void thenAccountBalanceIsReturned() {
        assertNotNull(balance);
    }

    private RestClient restClient = new RestClient();
    private String token;
    private String userName;
    private String message;

    private String bankId;
    private String accountId;
    private String viewId;

    private AccountDetails accountDetails;

    private String displayName;
    private String balance;
}
