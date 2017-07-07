
package alexa.skills.backbase.model;


public class Metadata {

    private String publicAlias;
    private String privateAlias;
    private String moreInfo;
    private String uRL;
    private String imageURL;
    private String openCorporatesURL;
    private String corporateLocation;
    private String physicalLocation;

    public String getPublicAlias() {
        return publicAlias;
    }

    public void setPublicAlias(String publicAlias) {
        this.publicAlias = publicAlias;
    }

    public String getPrivateAlias() {
        return privateAlias;
    }

    public void setPrivateAlias(String privateAlias) {
        this.privateAlias = privateAlias;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getOpenCorporatesURL() {
        return openCorporatesURL;
    }

    public void setOpenCorporatesURL(String openCorporatesURL) {
        this.openCorporatesURL = openCorporatesURL;
    }

    public String getCorporateLocation() {
        return corporateLocation;
    }

    public void setCorporateLocation(String corporateLocation) {
        this.corporateLocation = corporateLocation;
    }

    public String getPhysicalLocation() {
        return physicalLocation;
    }

    public void setPhysicalLocation(String physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

}
