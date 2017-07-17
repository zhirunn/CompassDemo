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

    private static final String SERVER_ADDRESS = "http://greatnorthcap.000webhostapp.com/";
    private static final String REGISTER_URL = "https://greatnorthcap.000webhostapp.com/PHP/register.php";
    private static final String LOGIN_URL = "https://greatnorthcap.000webhostapp.com/PHP/login.php";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_PASSWORD = "Password";
    private static final String LOGIN_SUCCESS = "Success";
    private static final String SHARED_PREF_NAME = "SharedPref";
    private static final String EMAIL_SHARED_PREF = "Email";
    private static final String LOGGEDIN_SHARED_PREF = "LoggedIn";

    public UserSharedPref() {
    }
    public String getServerAddress() {
        return SERVER_ADDRESS;
    }
    public String getRegisterURL() {
        return REGISTER_URL;
    }
    public String getLoginURL() {
        return LOGIN_URL;
    }
    public String getKeyEmail() {
        return KEY_EMAIL;
    }
    public String getKeyPassword() {
        return KEY_PASSWORD;
    }
    public String getLoginSuccess() {
        return LOGIN_SUCCESS;
    }
    public String getSharedPrefName() {
        return SHARED_PREF_NAME;
    }
    public String getEmailSharedPref() {
        return EMAIL_SHARED_PREF;
    }
    public String getLoggedinSharedPref() {
        return LOGGEDIN_SHARED_PREF;
    }

}
