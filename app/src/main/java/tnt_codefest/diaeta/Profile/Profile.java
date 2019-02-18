package tnt_codefest.diaeta.Profile;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import tnt_codefest.diaeta.Database.PreferencesKeys;
import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.R;

public class Profile extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private TextView name, label_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        name = findViewById(R.id.label_name);
        label_bmi = findViewById(R.id.label_BMI);

        // Get User ID
        SharedPreferences prefs = getSharedPreferences(PreferencesKeys.MY_PREFS_NAME, MODE_PRIVATE);
        int user_id = prefs.getInt(PreferencesKeys.USER_ID, 0);

        // Adding values to the texviews
        Cursor user_cursor = sqLiteHelper.findUser(user_id);
        while(user_cursor.moveToNext()){
            name.setText(user_cursor.getString(3) + " " + user_cursor.getString(4));
            label_bmi.setText(user_cursor.getDouble(7) + "");
        }
        user_cursor.close();
    }
}
