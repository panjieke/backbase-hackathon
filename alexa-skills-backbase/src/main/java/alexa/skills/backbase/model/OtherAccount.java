
package alexa.skills.backbase.model;


public class OtherAccount {

    private String id;
    private Holder holder;
    private String number;
    private String kind;
    private String iBAN;
    private String swiftBic;
    private Bank bank;
    private Metadata metadata;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Holder getHolder() {
        return holder;
    }

    public void setHolder(Holder holder) {
        this.holder = holder;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
