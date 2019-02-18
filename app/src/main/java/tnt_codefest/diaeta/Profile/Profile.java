package tnt_codefest.diaeta.Profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import tnt_codefest.diaeta.BMI_Calculator.WeightClassification;
import tnt_codefest.diaeta.DietPlan.DietPlan;
import tnt_codefest.diaeta.Database.PreferencesKeys;
import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.DietPlan.DietPlan;
import tnt_codefest.diaeta.DietPlan.DietPlanDialog;
import tnt_codefest.diaeta.R;

public class Profile extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    private SQLiteHelper sqLiteHelper;

    private TextView name, label_bmi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        drawerLayout=findViewById(R.id.drawer_layout);

        NavigationView NavView = findViewById(R.id.navigation_view);
        NavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                boolean check = false;

                menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {

                    case R.id.nav_diet:
                        check = true;
                        diet_plan();
                        Toast.makeText(Profile.this, "DIET", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_weight_class:
                        check = true;
                        weight_class();
                        Toast.makeText(Profile.this, "WEIGHT CLASS", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return Profile.super.onOptionsItemSelected(menuItem);
                }
                return check;
            }
        });
       ;
    }
    public void diet_plan() {
        Intent a = new Intent(this, DietPlan.class);
        startActivity(a);

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
    public void weight_class(){
        Intent b = new Intent(this, WeightClassification.class);
        startActivity(b);
    }

}
