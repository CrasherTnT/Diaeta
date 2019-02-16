package tnt_codefest.dieta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tnt_codefest.dieta.Database.SQLiteHelper;

import tnt_codefest.dieta.BMI_Calculator.MainBMICalculator;

public class MainActivity extends AppCompatActivity {

    // Database
    private SQLiteHelper sqLiteHelper;

    private Button button_go_bmicalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        sqLiteHelper.addUser("SusejParty", "Sus", "Mariajoseph", 69.5, 147, 25.0, 1);



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
