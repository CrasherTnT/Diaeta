package tnt_codefest.diaeta;

import android.content.Intent;
import android.content.SharedPreferences;
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
}
