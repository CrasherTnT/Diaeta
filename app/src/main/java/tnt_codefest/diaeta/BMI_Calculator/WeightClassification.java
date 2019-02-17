package tnt_codefest.diaeta.BMI_Calculator;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.DietPlan.DietPlan;
import tnt_codefest.diaeta.R;

public class WeightClassification extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private TextView label_bmi_category, label_yourname;
    private Button button_diet_plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_classification);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        Intent intent = getIntent();
        double result = intent.getDoubleExtra("result_bmi", 0.00);

        // TEMPORARY CODE
        int USER_ID = intent.getIntExtra("USER_ID", 0);
        label_yourname = findViewById(R.id.label_yourname);
        Cursor user_data = sqLiteHelper.findUser(USER_ID);
        while(user_data.moveToNext()){
            label_yourname.setText(user_data.getString(3) + " " + user_data.getString(4));
        }user_data.close();
        //TEMPORARY CODE

        label_bmi_category = findViewById(R.id.label_bmi_category);

        if (result < 18.5){
            label_bmi_category.setText("UNDER WEIGHT: " + String.valueOf(result));

        }
        else if (result >= 18.5 && result <= 24.9){
            label_bmi_category.setText("NORMAL WEIGHT: " + String.valueOf(result));

        }

        else if (result >= 25 && result <= 29.9){
            label_bmi_category.setText("OVER WEIGHT: " + String.valueOf(result));

        }
        else if (result >= 30){
            label_bmi_category.setText("OBESE: " + String.valueOf(result));

        }

        button_diet_plan = findViewById(R.id.button_diet_plan);

        button_diet_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DietPlan.class);
                startActivity(intent);
            }
        });

    }
}
