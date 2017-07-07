
package alexa.skills.backbase.model;


public class Transaction {

    private String id;
    private ThisAccount thisAccount;
    private OtherAccount otherAccount;
    private Details details;
    private Metadata metadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ThisAccount getThisAccount() {
        return thisAccount;
    }

    public void setThisAccount(ThisAccount thisAccount) {
        this.thisAccount = thisAccount;
    }

    public OtherAccount getOtherAccount() {
        return otherAccount;
    }

    public void setOtherAccount(OtherAccount otherAccount) {
        this.otherAccount = otherAccount;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
