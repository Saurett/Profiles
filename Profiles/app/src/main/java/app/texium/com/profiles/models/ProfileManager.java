package app.texium.com.profiles.models;

import java.io.Serializable;

/**
 * Created by texiumuser on 02/06/2016.
 */
public class ProfileManager implements Serializable {

    private PersonalProfile personalProfile;
    private ElectoralProfile electoralProfile;
    private AddressProfile addressProfile;
    private ContactProfile contactProfile;
    private ProfessionalProfile professionalProfile;
    private StructureProfile structureProfile;
    private CommentProfile commentProfile;
    private SocialNetworkProfile socialNetworkProfile;
    private Users userProfile;

    public ProfileManager() {
        this.personalProfile = new PersonalProfile();
        this.electoralProfile = new ElectoralProfile();
        this.addressProfile = new AddressProfile();
        this.contactProfile = new ContactProfile();
        this.professionalProfile = new ProfessionalProfile();
        this.structureProfile = new StructureProfile();
        this.commentProfile = new CommentProfile();
        this.socialNetworkProfile = new SocialNetworkProfile();
        this.userProfile = new Users();
    }

    public PersonalProfile getPersonalProfile() {
        return personalProfile;
    }

    public void setPersonalProfile(PersonalProfile personalProfile) {
        this.personalProfile = personalProfile;
    }

    public ElectoralProfile getElectoralProfile() {
        return electoralProfile;
    }

    public void setElectoralProfile(ElectoralProfile electoralProfile) {
        this.electoralProfile = electoralProfile;
    }

    public AddressProfile getAddressProfile() {
        return addressProfile;
    }

    public void setAddressProfile(AddressProfile addressProfile) {
        this.addressProfile = addressProfile;
    }

    public ContactProfile getContactProfile() {
        return contactProfile;
    }

    public void setContactProfile(ContactProfile contactProfile) {
        this.contactProfile = contactProfile;
    }

    public ProfessionalProfile getProfessionalProfile() {
        return professionalProfile;
    }

    public void setProfessionalProfile(ProfessionalProfile professionalProfile) {
        this.professionalProfile = professionalProfile;
    }

    public SocialNetworkProfile getSocialNetworkProfile() {
        return socialNetworkProfile;
    }

    public void setSocialNetworkProfile(SocialNetworkProfile socialNetworkProfile) {
        this.socialNetworkProfile = socialNetworkProfile;
    }

    public StructureProfile getStructureProfile() {
        return structureProfile;
    }

    public void setStructureProfile(StructureProfile structureProfile) {
        this.structureProfile = structureProfile;
    }

    public CommentProfile getCommentProfile() {
        return commentProfile;
    }

    public void setCommentProfile(CommentProfile commentProfile) {
        this.commentProfile = commentProfile;
    }

    public Users getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Users userProfile) {
        this.userProfile = userProfile;
    }
}
