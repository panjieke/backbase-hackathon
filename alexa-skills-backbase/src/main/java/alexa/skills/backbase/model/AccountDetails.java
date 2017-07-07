
package alexa.skills.backbase.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDetails {

    private String id;
    private String label;
    private String number;
    private List<Owner> owners = null;
    private String type;
    private Balance balance;
    private String iBAN;
    private String swiftBic;
    private List<ViewsAvailable> viewsAvailable = null;
    private String bankId;
    private AccountRouting accountRouting;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public String getIBAN() {
        return iBAN;
    }

    public void setIBAN(String iBAN) {
        this.iBAN = iBAN;
    }

    public String getSwiftBic() {
        return swiftBic;
    }

    public void setSwiftBic(String swiftBic) {
        this.swiftBic = swiftBic;
    }

    public List<ViewsAvailable> getViewsAvailable() {
        return viewsAvailable;
    }

    public void setViewsAvailable(List<ViewsAvailable> viewsAvailable) {
        this.viewsAvailable = viewsAvailable;
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public AccountRouting getAccountRouting() {
        return accountRouting;
    }

    public void setAccountRouting(AccountRouting accountRouting) {
        this.accountRouting = accountRouting;
    }

}
