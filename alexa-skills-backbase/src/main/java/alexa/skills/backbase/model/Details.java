
package alexa.skills.backbase.model;


public class Details {

    private String type;
    private String description;
    private String posted;
    private String completed;
    private NewBalance newBalance;
    private Value value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public NewBalance getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(NewBalance newBalance) {
        this.newBalance = newBalance;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

}
