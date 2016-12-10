
package mahdiyar.ir.marketpanel.core.api.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("full_name")
    @Expose
    private String fullName;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("n_birth_certificate")
    @Expose
    private Object nBirthCertificate;
    @SerializedName("national_id_card_no")
    @Expose
    private Object nationalIdCardNo;
    @SerializedName("page_name")
    @Expose
    private String pageName;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("neighbourhood")
    @Expose
    private String neighbourhood;
    @SerializedName("work_address")
    @Expose
    private String workAddress;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telegram_id")
    @Expose
    private String telegramId;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("long")
    @Expose
    private Double _long;
    @SerializedName("profile_image")
    @Expose
    private Object profileImage;
    @SerializedName("store_email")
    @Expose
    private String storeEmail;
    @SerializedName("store_phone")
    @Expose
    private String storePhone;
    @SerializedName("store_mobile")
    @Expose
    private String storeMobile;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("verify_code")
    @Expose
    private Object verifyCode;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * 
     * @param fullName
     *     The full_name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * 
     * @return
     *     The phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 
     * @param phoneNumber
     *     The phone_number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 
     * @return
     *     The nBirthCertificate
     */
    public Object getNBirthCertificate() {
        return nBirthCertificate;
    }

    /**
     * 
     * @param nBirthCertificate
     *     The n_birth_certificate
     */
    public void setNBirthCertificate(Object nBirthCertificate) {
        this.nBirthCertificate = nBirthCertificate;
    }

    /**
     * 
     * @return
     *     The nationalIdCardNo
     */
    public Object getNationalIdCardNo() {
        return nationalIdCardNo;
    }

    /**
     * 
     * @param nationalIdCardNo
     *     The national_id_card_no
     */
    public void setNationalIdCardNo(Object nationalIdCardNo) {
        this.nationalIdCardNo = nationalIdCardNo;
    }

    /**
     * 
     * @return
     *     The pageName
     */
    public String getPageName() {
        return pageName;
    }

    /**
     * 
     * @param pageName
     *     The page_name
     */
    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    /**
     * 
     * @return
     *     The mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * 
     * @param mobileNo
     *     The mobile_no
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The state
     */
    public String getState() {
        return state;
    }

    /**
     * 
     * @param state
     *     The state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     * @return
     *     The city
     */
    public String getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 
     * @return
     *     The neighbourhood
     */
    public String getNeighbourhood() {
        return neighbourhood;
    }

    /**
     * 
     * @param neighbourhood
     *     The neighbourhood
     */
    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    /**
     * 
     * @return
     *     The workAddress
     */
    public String getWorkAddress() {
        return workAddress;
    }

    /**
     * 
     * @param workAddress
     *     The work_address
     */
    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The telegramId
     */
    public String getTelegramId() {
        return telegramId;
    }

    /**
     * 
     * @param telegramId
     *     The telegram_id
     */
    public void setTelegramId(String telegramId) {
        this.telegramId = telegramId;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public Double getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(Double lat) {
        this.lat = lat;
    }

    /**
     * 
     * @return
     *     The _long
     */
    public Double getLong() {
        return _long;
    }

    /**
     * 
     * @param _long
     *     The long
     */
    public void setLong(Double _long) {
        this._long = _long;
    }

    /**
     * 
     * @return
     *     The profileImage
     */
    public Object getProfileImage() {
        return profileImage;
    }

    /**
     * 
     * @param profileImage
     *     The profile_image
     */
    public void setProfileImage(Object profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * 
     * @return
     *     The storeEmail
     */
    public String getStoreEmail() {
        return storeEmail;
    }

    /**
     * 
     * @param storeEmail
     *     The store_email
     */
    public void setStoreEmail(String storeEmail) {
        this.storeEmail = storeEmail;
    }

    /**
     * 
     * @return
     *     The storePhone
     */
    public String getStorePhone() {
        return storePhone;
    }

    /**
     * 
     * @param storePhone
     *     The store_phone
     */
    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    /**
     * 
     * @return
     *     The storeMobile
     */
    public String getStoreMobile() {
        return storeMobile;
    }

    /**
     * 
     * @param storeMobile
     *     The store_mobile
     */
    public void setStoreMobile(String storeMobile) {
        this.storeMobile = storeMobile;
    }

    /**
     * 
     * @return
     *     The website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * 
     * @param website
     *     The website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * 
     * @return
     *     The verifyCode
     */
    public Object getVerifyCode() {
        return verifyCode;
    }

    /**
     * 
     * @param verifyCode
     *     The verify_code
     */
    public void setVerifyCode(Object verifyCode) {
        this.verifyCode = verifyCode;
    }

}
