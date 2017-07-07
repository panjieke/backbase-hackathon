
package alexa.skills.backbase.model;

import java.util.HashMap;
import java.util.Map;

public class Owner {

    private String id;
    private String provider;
    private String displayName;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
