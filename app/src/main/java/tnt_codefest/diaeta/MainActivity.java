package tnt_codefest.diaeta;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tnt_codefest.diaeta.Account.Login;
import tnt_codefest.diaeta.Account.Signup;
import tnt_codefest.diaeta.BMI_Calculator.WeightClassification;
import tnt_codefest.diaeta.Database.PreferencesKeys;
import tnt_codefest.diaeta.Database.SQLiteHelper;

import tnt_codefest.diaeta.BMI_Calculator.MainBMICalculator;
import tnt_codefest.diaeta.Profile.Profile;

import static tnt_codefest.diaeta.NotificationService.App.CHANNEL_ID;

public class MainActivity extends AppCompatActivity {
    //yezuz


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO: USE SHARED PREFERENCE TO IDENTIFY IF THE USER IS LOGGED IN

        // Retrieving from shared preferences
        SharedPreferences prefs = getSharedPreferences(PreferencesKeys.MY_PREFS_NAME, MODE_PRIVATE);
        Boolean loggedIn = prefs.getBoolean(PreferencesKeys.USER_LOGGED_IN, false);
        int user_id = prefs.getInt(PreferencesKeys.USER_ID, 0);

        if(loggedIn){
            createNotification("Hello", "HI", "scale");
            Intent intent = new Intent(getApplicationContext(), MainBMICalculator.class);
            intent.putExtra("USER_ID", user_id);
            startActivity(intent);
            finish();
        }
        else{
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }
    }
    public void createNotification(String title, String description, String variable) {
        Intent intent = new Intent(this, Profile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder notifBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(getResources().getIdentifier(variable, "drawable", getPackageName()))
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(variable, "drawable", getPackageName())))
                .setContentTitle(title)
                .setContentText(description)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notifBuilder.build());

    }
}

