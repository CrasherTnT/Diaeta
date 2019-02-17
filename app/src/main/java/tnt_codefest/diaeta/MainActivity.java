package tnt_codefest.diaeta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tnt_codefest.diaeta.Account.Login;
import tnt_codefest.diaeta.Account.Signup;
import tnt_codefest.diaeta.Database.SQLiteHelper;

import tnt_codefest.diaeta.BMI_Calculator.MainBMICalculator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(getApplicationContext(), Login.class);
        startActivity(intent);
        finish();

    }
}
