package frost.com.htvchallengetruck.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import frost.com.htvchallengetruck.sharePreferences.AppPreferenceUtils;

/**
 * Created by chinmayghag on 10/02/18.
 */

public class FCMInstanceIdService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String refreshToken = FirebaseInstanceId.getInstance().getToken();

        Log.i("frost", "onTokenRefresh: "+refreshToken);

        storeRegistrationIdInSharedPreference(refreshToken);
        sendRegistrationNumberToServer(refreshToken);


    }

    private void storeRegistrationIdInSharedPreference(String refreshToken) {

        AppPreferenceUtils appPreferenceUtils = new AppPreferenceUtils(getApplicationContext());
        appPreferenceUtils.setRegId(refreshToken);

    }

    private void sendRegistrationNumberToServer(String refreshToken) {



    }
}
