package tnt_codefest.diaeta;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tnt_codefest.diaeta.Database.SQLiteHelper;

import tnt_codefest.diaeta.BMI_Calculator.MainBMICalculator;

public class MainActivity extends AppCompatActivity {

    // Database
    private SQLiteHelper sqLiteHelper;

    private Button button_go_bmicalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        button_go_bmicalculator = findViewById(R.id.button_go_bmicalculator);

        button_go_bmicalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainBMICalculator.class);
                startActivity(intent);

            }
        });


    }
}
