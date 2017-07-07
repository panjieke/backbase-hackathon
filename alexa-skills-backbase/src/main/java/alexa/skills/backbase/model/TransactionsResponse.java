
package alexa.skills.backbase.model;

import java.util.List;

public class TransactionsResponse {

    private List<Transaction> transactions = null;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
