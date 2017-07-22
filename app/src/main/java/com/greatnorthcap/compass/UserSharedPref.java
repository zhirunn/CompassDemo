package com.greatnorthcap.compass;

/**
 * Created by aspiree15 on 14/07/17.
 */

public class UserSharedPref {
    /*
    private static final String RESOURCE_URL = "https://greatnorthcap.000webhostapp.com/";
    // Specifies the limit size of elastic search (its default = 10)
    private static final String SEARCH_URL = "https://greatnorthcap.000webhostapp.com/user/_search?size=1000000";
    */
    private static final String ACCOUNTPROFILE_URL= "http://greatnorthcap.000webhostapp.com/PHP/accountprofile.php";
    private static final String UPDATEINFORMATION_URL = "https://greatnorthcap.000webhostapp.com/PHP/information.php";
    private static final String SERVER_ADDRESS = "http://greatnorthcap.000webhostapp.com/";
    private static final String REGISTER_URL = "https://greatnorthcap.000webhostapp.com/PHP/register.php";
    private static final String LOGIN_URL = "https://greatnorthcap.000webhostapp.com/PHP/login.php";
    private static final String DATAUSERID_URL = "https://greatnorthcap.000webhostapp.com/PHP/getuserid.php?Email=";
    private static final String USERSEARCH_URL = "https://greatnorthcap.000webhostapp.com/PHP/usersearch.php";
    private static final String KEY_USER_ID = "UserID";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_NAME = "name";
    private static final String LOGIN_SUCCESS = "Success";
    private static final String SHARED_PREF_NAME = "SharedPref";
    private static final String USERID_SHARED_PREF = "UserID";
    private static final String EMAIL_SHARED_PREF = "Email";
    private static final String FULLNAME_SHARED_PREF = "Full Name*";
    private static final String PHONENUMBER_SHARED_PREF = "Phone Number*";
    private static final String ADDRESS_SHARED_PREF = "Street Address*";
    private static final String EMPLOYMENT_SHARED_PREF = "Place of Employment*";
    private static final String JOBTITLE_SHARED_PREF = "Job Title*";
    private static final String LOGGEDIN_SHARED_PREF = "LoggedIn";
    private static final String JSON_ARRAY = "Result";
    private static final String borrowerType = "BorrowerType";
    private static final String lenderType = "LenderType";
    private static final String SEARCH_USER_ID = "SearchUserID";
    private static final String SEARCH_EMAIL = "SearchEmail";
    private static final String FULL_NAME = "FullName";
    private static final String PHONE_NUMBER= "PhoneNumber";
    private static final String ADDRESS = "Address";
    private static final String EMPLOYMENT = "Employment";
    private static final String JOB_Title = "JobTitle";
    private static final String SEARCHEDID_SHARED_PREF = "SearchedID";
    public UserSharedPref() {
    }
    public String getFullName() { return FULL_NAME;}
    public String getPhoneNumber() { return PHONE_NUMBER;}
    public String getAddress () { return ADDRESS;}
    public String getEmployment() { return EMPLOYMENT;}
    public String getJOB_Title (){return JOB_Title;}
    public String getAccountprofileUrl() { return ACCOUNTPROFILE_URL;}
    public String getUpdateinformationUrl() { return UPDATEINFORMATION_URL;}
    public String getSearchUserID() {return SEARCH_USER_ID;}
    public String getSearchEmail() {return SEARCH_EMAIL;}
    public String getborrowerType() {
        return borrowerType;
    }
    public String getlenderType () { return lenderType;}
    public String getServerAddress() {
        return SERVER_ADDRESS;
    }
    public String getRegisterURL() {
        return REGISTER_URL;
    }
    public String getLoginURL() {
        return LOGIN_URL;
    }
    public String getDatauseridUrl() {
        return DATAUSERID_URL;
    }
    public String getKeyUserId() {
        return KEY_USER_ID;
    }
    public String getKeyEmail() {
        return KEY_EMAIL;
    }
    public String getKeyPassword() {
        return KEY_PASSWORD;
    }
    public String getKeyImage() {
        return KEY_IMAGE;
    }
    public String getKeyName() {
        return KEY_NAME;
    }
    public String getLoginSuccess() {
        return LOGIN_SUCCESS;
    }
    public String getSharedPrefName() {
        return SHARED_PREF_NAME;
    }
    public String getUseridSharedPref() {
        return USERID_SHARED_PREF;
    }
    public String getEmailSharedPref() {
        return EMAIL_SHARED_PREF;
    }
    public String getFullnameSharedPref() {
        return FULLNAME_SHARED_PREF;
    }
    public String getPhonenumberSharedPref() {
        return PHONENUMBER_SHARED_PREF;
    }
    public String getAddressSharedPrefSharedPref() {
        return ADDRESS_SHARED_PREF;
    }
    public String getEmploymentSharedPref() {
        return EMPLOYMENT_SHARED_PREF;
    }
    public String getJobtitleSharedPref() {
        return JOBTITLE_SHARED_PREF;
    }
    public String getLoggedinSharedPref() {
        return LOGGEDIN_SHARED_PREF;
    }
    public String getSearchedIDSharedPref() {return SEARCHEDID_SHARED_PREF;}
    public String getJsonArray() {
        return JSON_ARRAY;
    }
    public String getUserSearchURL() {return USERSEARCH_URL;}

}
