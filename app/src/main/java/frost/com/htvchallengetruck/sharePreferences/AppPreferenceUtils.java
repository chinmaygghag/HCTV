package frost.com.htvchallengetruck.sharePreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by chinmayghag on 10/02/18.
 */

public class AppPreferenceUtils {

    private SharedPreferences mSharedPrefrence;
    private String regId;
    private Context context;
    private String vehicleId;

    public AppPreferenceUtils(Context mContext) {
        super();
        this.context = mContext;
        mSharedPrefrence = mContext.getSharedPreferences("app_shared_preference",
                Context.MODE_PRIVATE);
    }

    public String getRegId() {
        return mSharedPrefrence.getString("regId", regId);
    }

    public void setRegId(String regId) {
        SharedPreferences.Editor editor = mSharedPrefrence.edit();
        this.regId = regId;
        editor.putString("regId", regId).apply();
    }


    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        SharedPreferences.Editor editor = mSharedPrefrence.edit();
        this.vehicleId = vehicleId;
        editor.putString("vehicleId", vehicleId).apply();
    }
}
