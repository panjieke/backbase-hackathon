package alexa.skills.backbase;

import alexa.skills.backbase.lib.RestClient;
import alexa.skills.backbase.model.AccountDetails;
import alexa.skills.backbase.model.TransactionsResponse;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;

import static org.junit.Assert.*;

public class RestClientTest {


//    @Test
    public void login() throws Exception {
        whenRequestLogin();
        thenTokenIsReturned();
    }

//    @Test
    public void getAccount() {
        givenAccountDetails();
        whenRequestAccountDetails();
        thenAccountDetailsIsReturned();
        andAccountIdShouldBeTheSame();
    }

//    @Test
    public void getDisplayName() {
        givenAccountDetails();
        whenRequestAccountDisplayName();
        thenDisplayNameIsReturned();
    }

//    @Test
    public void getAccountBalance() {
        givenAccountDetails();
        whenRequestAccountBalance();
        thenAccountBalanceIsReturned();
    }

    @Test
    public void transferAmount() {
        givenAccountDetails();
        givenOtherAccountDetails();
        givenCurrentAccountBalance();
        whenRequestTransferAmount();
//        thenTransactionIdShouldBeReturned();
//        andBalanceShouldBeUpdated();
    }

//    @Test
    public void getAllTransactions() {
        givenAccountDetails();
        whenRequestAllTransactions();
        thenSomeTransactionsShouldBeReturned();
    }

    private void whenRequestAllTransactions() {
        transactionsResponse = restClient.getAllTransactions(bankId, accountId, viewId);
    }

    private void thenSomeTransactionsShouldBeReturned() {
        assertNotNull(transactionsResponse);
        assertNotNull(transactionsResponse.getTransactions());
        assertNotEquals(0, transactionsResponse.getTransactions().size());

    }

    private void givenCurrentAccountBalance() {
        balance = restClient.getAccountBalance(bankId, accountId, viewId);
    }

    private void andBalanceShouldBeUpdated() {
        String newBalance = restClient.getAccountBalance(bankId, accountId, viewId);
        double oldBalanceNumeric = Double.valueOf(balance.split(" ")[0]);
        double newBalanceNumeric = Double.valueOf(newBalance.split(" ")[0]);
        double DELTA = 1e-15;
        assertEquals(oldBalanceNumeric - transferAmount, newBalanceNumeric, DELTA);
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

    private void givenOtherAccountDetails() {
        otherBankId = "test-bank";
        otherAccountId = "10800111111";
        transferAmount = 5.00;
    }

    private void whenRequestAccountDetails() {
        accountDetails = restClient.getAccountDetails(bankId, accountId, viewId);
    }

    private void thenAccountDetailsIsReturned() {
        assertNotNull(accountDetails);
    }

    private void andAccountIdShouldBeTheSame() {
        assertEquals(accountId, accountDetails.getId());
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

    private void whenRequestTransferAmount() {
        transactionId = restClient.transferAmount(bankId, accountId, viewId, otherBankId, otherAccountId, transferAmount);
    }

    private void thenTransactionIdShouldBeReturned() {
        assertNotNull(transactionId);
    }

    private RestClient restClient = new RestClient();
    private String token;
    private String userName;
    private String message;

    private String bankId;
    private String accountId;
    private String viewId;

    private String otherBankId;
    private String otherAccountId;
    private double transferAmount;

    private AccountDetails accountDetails;

    private String displayName;
    private String balance;

    private String transactionId;
    TransactionsResponse transactionsResponse;
}
