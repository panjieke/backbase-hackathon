
package alexa.skills.backbase.model;

import java.util.HashMap;
import java.util.Map;

public class AccountRouting {

    private Object scheme;
    private Object address;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Object getScheme() {
        return scheme;
    }

    public void setScheme(Object scheme) {
        this.scheme = scheme;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
