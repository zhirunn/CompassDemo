package com.greatnorthcap.compass;

/**
 * Created by aspiree15 on 14/07/17.
 */

public class WebServer {
    private static final String RESOURCE_URL = "https://greatnorthcap.000webhostapp.com/";
    // Specifies the limit size of elastic search (its default = 10)
    private static final String SEARCH_URL = "https://greatnorthcap.000webhostapp.com/user/_search?size=1000000";

    private static final String RESOURCE_PHOTO_URL = "https://greatnorthcap.000webhostapp.com/photo/";
    // Specifies the limit size of elastic search (its default = 10)
    private static final String SEARCH_PHOTO_URL = "https://greatnorthcap.000webhostapp.com/photo/_search?size=1000000";

    public WebServer() {
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
}
