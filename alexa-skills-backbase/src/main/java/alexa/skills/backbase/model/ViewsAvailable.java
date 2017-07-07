
package alexa.skills.backbase.model;

import java.util.HashMap;
import java.util.Map;

public class ViewsAvailable {

    private String id;
    private String shortName;
    private String description;
    private Boolean isPublic;
    private String alias;
    private Boolean hideMetadataIfAliasUsed;
    private Boolean canAddComment;
    private Boolean canAddCorporateLocation;
    private Boolean canAddImage;
    private Boolean canAddImageUrl;
    private Boolean canAddMoreInfo;
    private Boolean canAddOpenCorporatesUrl;
    private Boolean canAddPhysicalLocation;
    private Boolean canAddPrivateAlias;
    private Boolean canAddPublicAlias;
    private Boolean canAddTag;
    private Boolean canAddUrl;
    private Boolean canAddWhereTag;
    private Boolean canDeleteComment;
    private Boolean canDeleteCorporateLocation;
    private Boolean canDeleteImage;
    private Boolean canDeletePhysicalLocation;
    private Boolean canDeleteTag;
    private Boolean canDeleteWhereTag;
    private Boolean canEditOwnerComment;
    private Boolean canSeeBankAccountBalance;
    private Boolean canSeeBankAccountBankName;
    private Boolean canSeeBankAccountCurrency;
    private Boolean canSeeBankAccountIban;
    private Boolean canSeeBankAccountLabel;
    private Boolean canSeeBankAccountNationalIdentifier;
    private Boolean canSeeBankAccountNumber;
    private Boolean canSeeBankAccountOwners;
    private Boolean canSeeBankAccountSwiftBic;
    private Boolean canSeeBankAccountType;
    private Boolean canSeeComments;
    private Boolean canSeeCorporateLocation;
    private Boolean canSeeImageUrl;
    private Boolean canSeeImages;
    private Boolean canSeeMoreInfo;
    private Boolean canSeeOpenCorporatesUrl;
    private Boolean canSeeOtherAccountBankName;
    private Boolean canSeeOtherAccountIban;
    private Boolean canSeeOtherAccountKind;
    private Boolean canSeeOtherAccountMetadata;
    private Boolean canSeeOtherAccountNationalIdentifier;
    private Boolean canSeeOtherAccountNumber;
    private Boolean canSeeOtherAccountSwiftBic;
    private Boolean canSeeOwnerComment;
    private Boolean canSeePhysicalLocation;
    private Boolean canSeePrivateAlias;
    private Boolean canSeePublicAlias;
    private Boolean canSeeTags;
    private Boolean canSeeTransactionAmount;
    private Boolean canSeeTransactionBalance;
    private Boolean canSeeTransactionCurrency;
    private Boolean canSeeTransactionDescription;
    private Boolean canSeeTransactionFinishDate;
    private Boolean canSeeTransactionMetadata;
    private Boolean canSeeTransactionOtherBankAccount;
    private Boolean canSeeTransactionStartDate;
    private Boolean canSeeTransactionThisBankAccount;
    private Boolean canSeeTransactionType;
    private Boolean canSeeUrl;
    private Boolean canSeeWhereTag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Boolean getHideMetadataIfAliasUsed() {
        return hideMetadataIfAliasUsed;
    }

    public void setHideMetadataIfAliasUsed(Boolean hideMetadataIfAliasUsed) {
        this.hideMetadataIfAliasUsed = hideMetadataIfAliasUsed;
    }

    public Boolean getCanAddComment() {
        return canAddComment;
    }

    public void setCanAddComment(Boolean canAddComment) {
        this.canAddComment = canAddComment;
    }

    public Boolean getCanAddCorporateLocation() {
        return canAddCorporateLocation;
    }

    public void setCanAddCorporateLocation(Boolean canAddCorporateLocation) {
        this.canAddCorporateLocation = canAddCorporateLocation;
    }

    public Boolean getCanAddImage() {
        return canAddImage;
    }

    public void setCanAddImage(Boolean canAddImage) {
        this.canAddImage = canAddImage;
    }

    public Boolean getCanAddImageUrl() {
        return canAddImageUrl;
    }

    public void setCanAddImageUrl(Boolean canAddImageUrl) {
        this.canAddImageUrl = canAddImageUrl;
    }

    public Boolean getCanAddMoreInfo() {
        return canAddMoreInfo;
    }

    public void setCanAddMoreInfo(Boolean canAddMoreInfo) {
        this.canAddMoreInfo = canAddMoreInfo;
    }

    public Boolean getCanAddOpenCorporatesUrl() {
        return canAddOpenCorporatesUrl;
    }

    public void setCanAddOpenCorporatesUrl(Boolean canAddOpenCorporatesUrl) {
        this.canAddOpenCorporatesUrl = canAddOpenCorporatesUrl;
    }

    public Boolean getCanAddPhysicalLocation() {
        return canAddPhysicalLocation;
    }

    public void setCanAddPhysicalLocation(Boolean canAddPhysicalLocation) {
        this.canAddPhysicalLocation = canAddPhysicalLocation;
    }

    public Boolean getCanAddPrivateAlias() {
        return canAddPrivateAlias;
    }

    public void setCanAddPrivateAlias(Boolean canAddPrivateAlias) {
        this.canAddPrivateAlias = canAddPrivateAlias;
    }

    public Boolean getCanAddPublicAlias() {
        return canAddPublicAlias;
    }

    public void setCanAddPublicAlias(Boolean canAddPublicAlias) {
        this.canAddPublicAlias = canAddPublicAlias;
    }

    public Boolean getCanAddTag() {
        return canAddTag;
    }

    public void setCanAddTag(Boolean canAddTag) {
        this.canAddTag = canAddTag;
    }

    public Boolean getCanAddUrl() {
        return canAddUrl;
    }

    public void setCanAddUrl(Boolean canAddUrl) {
        this.canAddUrl = canAddUrl;
    }

    public Boolean getCanAddWhereTag() {
        return canAddWhereTag;
    }

    public void setCanAddWhereTag(Boolean canAddWhereTag) {
        this.canAddWhereTag = canAddWhereTag;
    }

    public Boolean getCanDeleteComment() {
        return canDeleteComment;
    }

    public void setCanDeleteComment(Boolean canDeleteComment) {
        this.canDeleteComment = canDeleteComment;
    }

    public Boolean getCanDeleteCorporateLocation() {
        return canDeleteCorporateLocation;
    }

    public void setCanDeleteCorporateLocation(Boolean canDeleteCorporateLocation) {
        this.canDeleteCorporateLocation = canDeleteCorporateLocation;
    }

    public Boolean getCanDeleteImage() {
        return canDeleteImage;
    }

    public void setCanDeleteImage(Boolean canDeleteImage) {
        this.canDeleteImage = canDeleteImage;
    }

    public Boolean getCanDeletePhysicalLocation() {
        return canDeletePhysicalLocation;
    }

    public void setCanDeletePhysicalLocation(Boolean canDeletePhysicalLocation) {
        this.canDeletePhysicalLocation = canDeletePhysicalLocation;
    }

    public Boolean getCanDeleteTag() {
        return canDeleteTag;
    }

    public void setCanDeleteTag(Boolean canDeleteTag) {
        this.canDeleteTag = canDeleteTag;
    }

    public Boolean getCanDeleteWhereTag() {
        return canDeleteWhereTag;
    }

    public void setCanDeleteWhereTag(Boolean canDeleteWhereTag) {
        this.canDeleteWhereTag = canDeleteWhereTag;
    }

    public Boolean getCanEditOwnerComment() {
        return canEditOwnerComment;
    }

    public void setCanEditOwnerComment(Boolean canEditOwnerComment) {
        this.canEditOwnerComment = canEditOwnerComment;
    }

    public Boolean getCanSeeBankAccountBalance() {
        return canSeeBankAccountBalance;
    }

    public void setCanSeeBankAccountBalance(Boolean canSeeBankAccountBalance) {
        this.canSeeBankAccountBalance = canSeeBankAccountBalance;
    }

    public Boolean getCanSeeBankAccountBankName() {
        return canSeeBankAccountBankName;
    }

    public void setCanSeeBankAccountBankName(Boolean canSeeBankAccountBankName) {
        this.canSeeBankAccountBankName = canSeeBankAccountBankName;
    }

    public Boolean getCanSeeBankAccountCurrency() {
        return canSeeBankAccountCurrency;
    }

    public void setCanSeeBankAccountCurrency(Boolean canSeeBankAccountCurrency) {
        this.canSeeBankAccountCurrency = canSeeBankAccountCurrency;
    }

    public Boolean getCanSeeBankAccountIban() {
        return canSeeBankAccountIban;
    }

    public void setCanSeeBankAccountIban(Boolean canSeeBankAccountIban) {
        this.canSeeBankAccountIban = canSeeBankAccountIban;
    }

    public Boolean getCanSeeBankAccountLabel() {
        return canSeeBankAccountLabel;
    }

    public void setCanSeeBankAccountLabel(Boolean canSeeBankAccountLabel) {
        this.canSeeBankAccountLabel = canSeeBankAccountLabel;
    }

    public Boolean getCanSeeBankAccountNationalIdentifier() {
        return canSeeBankAccountNationalIdentifier;
    }

    public void setCanSeeBankAccountNationalIdentifier(Boolean canSeeBankAccountNationalIdentifier) {
        this.canSeeBankAccountNationalIdentifier = canSeeBankAccountNationalIdentifier;
    }

    public Boolean getCanSeeBankAccountNumber() {
        return canSeeBankAccountNumber;
    }

    public void setCanSeeBankAccountNumber(Boolean canSeeBankAccountNumber) {
        this.canSeeBankAccountNumber = canSeeBankAccountNumber;
    }

    public Boolean getCanSeeBankAccountOwners() {
        return canSeeBankAccountOwners;
    }

    public void setCanSeeBankAccountOwners(Boolean canSeeBankAccountOwners) {
        this.canSeeBankAccountOwners = canSeeBankAccountOwners;
    }

    public Boolean getCanSeeBankAccountSwiftBic() {
        return canSeeBankAccountSwiftBic;
    }

    public void setCanSeeBankAccountSwiftBic(Boolean canSeeBankAccountSwiftBic) {
        this.canSeeBankAccountSwiftBic = canSeeBankAccountSwiftBic;
    }

    public Boolean getCanSeeBankAccountType() {
        return canSeeBankAccountType;
    }

    public void setCanSeeBankAccountType(Boolean canSeeBankAccountType) {
        this.canSeeBankAccountType = canSeeBankAccountType;
    }

    public Boolean getCanSeeComments() {
        return canSeeComments;
    }

    public void setCanSeeComments(Boolean canSeeComments) {
        this.canSeeComments = canSeeComments;
    }

    public Boolean getCanSeeCorporateLocation() {
        return canSeeCorporateLocation;
    }

    public void setCanSeeCorporateLocation(Boolean canSeeCorporateLocation) {
        this.canSeeCorporateLocation = canSeeCorporateLocation;
    }

    public Boolean getCanSeeImageUrl() {
        return canSeeImageUrl;
    }

    public void setCanSeeImageUrl(Boolean canSeeImageUrl) {
        this.canSeeImageUrl = canSeeImageUrl;
    }

    public Boolean getCanSeeImages() {
        return canSeeImages;
    }

    public void setCanSeeImages(Boolean canSeeImages) {
        this.canSeeImages = canSeeImages;
    }

    public Boolean getCanSeeMoreInfo() {
        return canSeeMoreInfo;
    }

    public void setCanSeeMoreInfo(Boolean canSeeMoreInfo) {
        this.canSeeMoreInfo = canSeeMoreInfo;
    }

    public Boolean getCanSeeOpenCorporatesUrl() {
        return canSeeOpenCorporatesUrl;
    }

    public void setCanSeeOpenCorporatesUrl(Boolean canSeeOpenCorporatesUrl) {
        this.canSeeOpenCorporatesUrl = canSeeOpenCorporatesUrl;
    }

    public Boolean getCanSeeOtherAccountBankName() {
        return canSeeOtherAccountBankName;
    }

    public void setCanSeeOtherAccountBankName(Boolean canSeeOtherAccountBankName) {
        this.canSeeOtherAccountBankName = canSeeOtherAccountBankName;
    }

    public Boolean getCanSeeOtherAccountIban() {
        return canSeeOtherAccountIban;
    }

    public void setCanSeeOtherAccountIban(Boolean canSeeOtherAccountIban) {
        this.canSeeOtherAccountIban = canSeeOtherAccountIban;
    }

    public Boolean getCanSeeOtherAccountKind() {
        return canSeeOtherAccountKind;
    }

    public void setCanSeeOtherAccountKind(Boolean canSeeOtherAccountKind) {
        this.canSeeOtherAccountKind = canSeeOtherAccountKind;
    }

    public Boolean getCanSeeOtherAccountMetadata() {
        return canSeeOtherAccountMetadata;
    }

    public void setCanSeeOtherAccountMetadata(Boolean canSeeOtherAccountMetadata) {
        this.canSeeOtherAccountMetadata = canSeeOtherAccountMetadata;
    }

    public Boolean getCanSeeOtherAccountNationalIdentifier() {
        return canSeeOtherAccountNationalIdentifier;
    }

    public void setCanSeeOtherAccountNationalIdentifier(Boolean canSeeOtherAccountNationalIdentifier) {
        this.canSeeOtherAccountNationalIdentifier = canSeeOtherAccountNationalIdentifier;
    }

    public Boolean getCanSeeOtherAccountNumber() {
        return canSeeOtherAccountNumber;
    }

    public void setCanSeeOtherAccountNumber(Boolean canSeeOtherAccountNumber) {
        this.canSeeOtherAccountNumber = canSeeOtherAccountNumber;
    }

    public Boolean getCanSeeOtherAccountSwiftBic() {
        return canSeeOtherAccountSwiftBic;
    }

    public void setCanSeeOtherAccountSwiftBic(Boolean canSeeOtherAccountSwiftBic) {
        this.canSeeOtherAccountSwiftBic = canSeeOtherAccountSwiftBic;
    }

    public Boolean getCanSeeOwnerComment() {
        return canSeeOwnerComment;
    }

    public void setCanSeeOwnerComment(Boolean canSeeOwnerComment) {
        this.canSeeOwnerComment = canSeeOwnerComment;
    }

    public Boolean getCanSeePhysicalLocation() {
        return canSeePhysicalLocation;
    }

    public void setCanSeePhysicalLocation(Boolean canSeePhysicalLocation) {
        this.canSeePhysicalLocation = canSeePhysicalLocation;
    }

    public Boolean getCanSeePrivateAlias() {
        return canSeePrivateAlias;
    }

    public void setCanSeePrivateAlias(Boolean canSeePrivateAlias) {
        this.canSeePrivateAlias = canSeePrivateAlias;
    }

    public Boolean getCanSeePublicAlias() {
        return canSeePublicAlias;
    }

    public void setCanSeePublicAlias(Boolean canSeePublicAlias) {
        this.canSeePublicAlias = canSeePublicAlias;
    }

    public Boolean getCanSeeTags() {
        return canSeeTags;
    }

    public void setCanSeeTags(Boolean canSeeTags) {
        this.canSeeTags = canSeeTags;
    }

    public Boolean getCanSeeTransactionAmount() {
        return canSeeTransactionAmount;
    }

    public void setCanSeeTransactionAmount(Boolean canSeeTransactionAmount) {
        this.canSeeTransactionAmount = canSeeTransactionAmount;
    }

    public Boolean getCanSeeTransactionBalance() {
        return canSeeTransactionBalance;
    }

    public void setCanSeeTransactionBalance(Boolean canSeeTransactionBalance) {
        this.canSeeTransactionBalance = canSeeTransactionBalance;
    }

    public Boolean getCanSeeTransactionCurrency() {
        return canSeeTransactionCurrency;
    }

    public void setCanSeeTransactionCurrency(Boolean canSeeTransactionCurrency) {
        this.canSeeTransactionCurrency = canSeeTransactionCurrency;
    }

    public Boolean getCanSeeTransactionDescription() {
        return canSeeTransactionDescription;
    }

    public void setCanSeeTransactionDescription(Boolean canSeeTransactionDescription) {
        this.canSeeTransactionDescription = canSeeTransactionDescription;
    }

    public Boolean getCanSeeTransactionFinishDate() {
        return canSeeTransactionFinishDate;
    }

    public void setCanSeeTransactionFinishDate(Boolean canSeeTransactionFinishDate) {
        this.canSeeTransactionFinishDate = canSeeTransactionFinishDate;
    }

    public Boolean getCanSeeTransactionMetadata() {
        return canSeeTransactionMetadata;
    }

    public void setCanSeeTransactionMetadata(Boolean canSeeTransactionMetadata) {
        this.canSeeTransactionMetadata = canSeeTransactionMetadata;
    }

    public Boolean getCanSeeTransactionOtherBankAccount() {
        return canSeeTransactionOtherBankAccount;
    }

    public void setCanSeeTransactionOtherBankAccount(Boolean canSeeTransactionOtherBankAccount) {
        this.canSeeTransactionOtherBankAccount = canSeeTransactionOtherBankAccount;
    }

    public Boolean getCanSeeTransactionStartDate() {
        return canSeeTransactionStartDate;
    }

    public void setCanSeeTransactionStartDate(Boolean canSeeTransactionStartDate) {
        this.canSeeTransactionStartDate = canSeeTransactionStartDate;
    }

    public Boolean getCanSeeTransactionThisBankAccount() {
        return canSeeTransactionThisBankAccount;
    }

    public void setCanSeeTransactionThisBankAccount(Boolean canSeeTransactionThisBankAccount) {
        this.canSeeTransactionThisBankAccount = canSeeTransactionThisBankAccount;
    }

    public Boolean getCanSeeTransactionType() {
        return canSeeTransactionType;
    }

    public void setCanSeeTransactionType(Boolean canSeeTransactionType) {
        this.canSeeTransactionType = canSeeTransactionType;
    }

    public Boolean getCanSeeUrl() {
        return canSeeUrl;
    }

    public void setCanSeeUrl(Boolean canSeeUrl) {
        this.canSeeUrl = canSeeUrl;
    }

    public Boolean getCanSeeWhereTag() {
        return canSeeWhereTag;
    }

    public void setCanSeeWhereTag(Boolean canSeeWhereTag) {
        this.canSeeWhereTag = canSeeWhereTag;
    }

}
