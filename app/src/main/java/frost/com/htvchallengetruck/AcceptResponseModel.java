package frost.com.htvchallengetruck;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chinmayghag on 11/02/18.
 */

public class AcceptResponseModel {

    @SerializedName("lat")
    @Expose
    private String lat;

    @SerializedName("log")
    @Expose
    private String log;


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
