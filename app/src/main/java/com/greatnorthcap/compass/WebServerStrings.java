package com.greatnorthcap.compass;

/**
 * Created by aspiree15 on 14/07/17.
 */

public class WebServerStrings {
    /*
    private static final String RESOURCE_URL = "https://greatnorthcap.000webhostapp.com/";
    // Specifies the limit size of elastic search (its default = 10)
    private static final String SEARCH_URL = "https://greatnorthcap.000webhostapp.com/user/_search?size=1000000";

    private static final String RESOURCE_PHOTO_URL = "https://greatnorthcap.000webhostapp.com/photo/";
    // Specifies the limit size of elastic search (its default = 10)
    private static final String SEARCH_PHOTO_URL = "https://greatnorthcap.000webhostapp.com/photo/_search?size=1000000";

    public WebServerStrings() {
    }
    public String getResourceUrl() {
        return RESOURCE_URL;
    }
    public String getSearchUrl() {
        return SEARCH_URL;
    }
    public String getResourcePhotoUrl() {
        return RESOURCE_PHOTO_URL;
    }
    public String getSearchPhotoUrl() {
        return SEARCH_PHOTO_URL;
    }
    */

    private static final String SERVER_ADDRESS = "http://greatnorthcap.000webhostapp.com/";
    private static final String REGISTER_URL = "https://greatnorthcap.000webhostapp.com/PHP/register.php";
    private static final String LOGIN_URL = "https://greatnorthcap.000webhostapp.com/PHP/login.php";

    public WebServerStrings() {
    }

    public String getRegisterURL() {
        return REGISTER_URL;
    }

}
