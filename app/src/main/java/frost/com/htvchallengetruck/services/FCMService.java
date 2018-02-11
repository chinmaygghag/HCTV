package frost.com.htvchallengetruck.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import frost.com.htvchallengetruck.MainActivity;
import frost.com.htvchallengetruck.R;

/**
 * Created by chinmayghag on 10/02/18.
 */

public class FCMService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.i("frost", "onMessageReceived: "+remoteMessage.getFrom());
        if(remoteMessage.getData().size() > 0){
            Log.i("frost", "Message data payload : "+remoteMessage.getData().get("issueId"));
        }
        if(remoteMessage.getNotification() !=null){
            Log.i("frost","Notification Body"+remoteMessage.getNotification());
        }

        String message = remoteMessage.getData().get("message");
        sendNotification(message,Integer.parseInt(remoteMessage.getData().get("issueId")));

    }

    private void sendNotification(String message,int issueId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("OpenMain", "true");
        intent.putExtra("issueId",issueId);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());



    }
}
