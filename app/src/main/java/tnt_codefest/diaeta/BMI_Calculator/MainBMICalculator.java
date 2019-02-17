package tnt_codefest.diaeta.BMI_Calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import tnt_codefest.diaeta.Account.Login;
import tnt_codefest.diaeta.Database.PreferencesKeys;
import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.R;


public class MainBMICalculator extends AppCompatActivity{
    private EditText field_feet, field_weight, field_inches, field_cm;
    private Space space_height;
    private RadioGroup radioGroup;
    private RadioButton radioCategory;
    private Button button_submit, button_logout;
    private TextView label_height, label_weight;
//    private Spinner spinner_bmi_category;

    private SQLiteHelper sqLiteHelper;
    private int USER_ID;

//    ArrayList<String> list_bmi_category = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_class_layout);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        Intent i = getIntent();
        USER_ID = i.getIntExtra("USER_ID", 0);

        field_feet = findViewById(R.id.field_feet);
        field_inches = findViewById(R.id.field_inches);
        field_cm = findViewById(R.id.field_cm);

        field_weight = findViewById(R.id.field_weight);
        space_height = findViewById(R.id.space_height);

        label_height = findViewById(R.id.label_height);
        label_weight = findViewById(R.id.label_weight);


        radioGroup = findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioCategory = group.findViewById(checkedId);
                String radioText = radioCategory.getText().toString();

                if (radioText.equals("Standard")){{
                    field_feet.setVisibility(View.VISIBLE);
                    space_height.setVisibility(View.VISIBLE);
                    field_inches.setVisibility(View.VISIBLE);
                    field_cm.setVisibility(View.GONE);

                    field_feet.setText(null);
                    field_cm.setText(null);
                    field_feet.setText(null);
                    field_inches.setText(null);
                    field_weight.setText(null);

                    label_height.setText("Height(ft & in)");
                    label_weight.setText("Weight(lbs)");


                }}

                else if (radioText.equals("Metric")){
                    field_feet.setVisibility(View.INVISIBLE);
                    space_height.setVisibility(View.INVISIBLE);
                    field_inches.setVisibility(View.INVISIBLE);
                    field_cm.setVisibility(View.VISIBLE);

                    field_feet.setText(null);
                    field_cm.setText(null);
                    field_feet.setText(null);
                    field_inches.setText(null);
                    field_weight.setText(null);


                    label_height.setText("Height(cm)");
                    label_weight.setText("Weight(kg)");

                }

                Toast.makeText(MainBMICalculator.this, radioCategory.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });

        button_submit = findViewById(R.id.button_submit);
        // Test logout button
//        button_logout = findViewById(R.id.button_logout);
//        button_logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                SharedPreferences preferences = getSharedPreferences(PreferencesKeys.MY_PREFS_NAME, MODE_PRIVATE);
//                preferences.edit().remove(PreferencesKeys.USER_LOGGED_IN).apply();
//
//                Intent i = new Intent(getApplicationContext(), Login.class);
//                startActivity(i);
//                finish();
//            }
//        });

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double result = 0;
                    if (radioCategory.getText().equals("Standard")) {
                        double feet = Double.parseDouble(field_feet.getText().toString());
                        double inches = Double.parseDouble(field_inches.getText().toString());
                        double pounds = Double.parseDouble(field_weight.getText().toString());
                        result = calculateStandard(feet, inches, pounds);

                        double totalInches = (feet * 12) + inches;

                        // TODO: If metric, convert the values back to standard before inserting into the databases
                        sqLiteHelper.addBMI(USER_ID, totalInches, pounds, result);

                    } else if (radioCategory.getText().equals("Metric")) {
                        double centimeters = Double.parseDouble(field_feet.getText().toString());
                        double kilograms = Double.parseDouble(field_weight.getText().toString());
                        result = calculateMetric(centimeters, kilograms);
                    }

                    Intent intent = new Intent(getApplicationContext(), WeightClassification.class);
                    intent.putExtra("result_bmi", result);
                    intent.putExtra("USER_ID", USER_ID);
                    startActivity(intent);
                }
                catch(Exception e){
                    // In case of error
                }

            }
        });

    }

    private double calculateStandard(double feet, double inches, double pounds){
        double height = ((feet * 12) + inches) * 0.025;
        double weight  = pounds * 0.45;

        return (double) Math.round((weight / (height * height)) * 100.0) / 100.0;
    }

    private double calculateMetric(double centimeters, double kilograms){
        return (double) Math.round(((kilograms / centimeters / centimeters) * 10000) * 100.0) / 100.0;
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "You can't escape!", Toast.LENGTH_SHORT).show();
        return;
    }
}
