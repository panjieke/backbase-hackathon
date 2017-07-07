
package alexa.skills.backbase.model;

import java.util.HashMap;
import java.util.Map;

public class Balance {

    private String currency;
    private String amount;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
