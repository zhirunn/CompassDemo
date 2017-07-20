/**
 * Created by Dan on 7/20/2017.
 */
import com.greatnorthcap.compass.UserSharedPref;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJSON {
    private UserSharedPref UserPref = new UserSharedPref();
    public static String[] userIds;
    public static String[] userEmails;
    public static String[] borrowerTypes;
    public static String[] lenderTypes;
    public final String JSON_Array = UserPref.getJsonArray();
    public final String KEY_ID = UserPref.getUseridSharedPref();

    JSONArray users = null;
    private String JSON;

    public ParseJSON(String json){
        this.JSON = JSON;
    }



}
