
package alexa.skills.backbase.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountDetails {

    private String id;
    private Object label;
    private String number;
    private List<Owner> owners = null;
    private Object type;
    private Balance balance;
    private Object iBAN;
    private Object swiftBic;
    private List<ViewsAvailable> viewsAvailable = null;
    private String bankId;
    private AccountRouting accountRouting;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getLabel() {
        return label;
    }

    public void setLabel(Object label) {
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

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Object getIBAN() {
        return iBAN;
    }

    public void setIBAN(Object iBAN) {
        this.iBAN = iBAN;
    }

    public Object getSwiftBic() {
        return swiftBic;
    }

    public void setSwiftBic(Object swiftBic) {
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
