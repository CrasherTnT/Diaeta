package tnt_codefest.diaeta.BMI_Calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import tnt_codefest.diaeta.R;

public class WeightClassification extends AppCompatActivity {
    private TextView label_bmi_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_classification);

        Intent intent = getIntent();
        double result = intent.getDoubleExtra("result_bmi", 0.00);
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

    }
}
