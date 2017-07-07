
package alexa.skills.backbase.model;

import java.util.List;

public class Account {

    private String id;
    private List<Holder> holders = null;
    private String number;
    private String kind;
    private String iBAN;
    private String swiftBic;
    private Bank bank;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Holder> getHolders() {
        return holders;
    }

    public void setHolders(List<Holder> holders) {
        this.holders = holders;
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

}
