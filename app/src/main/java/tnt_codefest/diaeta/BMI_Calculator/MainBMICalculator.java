package tnt_codefest.diaeta.BMI_Calculator;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tnt_codefest.diaeta.Account.Login;
import tnt_codefest.diaeta.Database.PreferencesKeys;
import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.R;


public class MainBMICalculator extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText field_feet, field_inches, field_pounds;
    private Button button_calculate, button_logout;
    private TextView label_result;
    private Spinner spinner_bmi_category;

    private SQLiteHelper sqLiteHelper;
    private int USER_ID;

    ArrayList<String> list_bmi_category = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        Intent i = getIntent();
        USER_ID = i.getIntExtra("USER_ID", 0);

        field_feet = findViewById(R.id.field_feet);
        field_inches = findViewById(R.id.field_inches);
        field_pounds = findViewById(R.id.field_pounds);
        button_calculate = findViewById(R.id.button_calculate);
        // Test logout button
        button_logout = findViewById(R.id.button_logout);

        label_result = findViewById(R.id.label_bmi_category);
        spinner_bmi_category = findViewById(R.id.spinner_bmi_category);

        spinner_bmi_category.setOnItemSelectedListener(this);

        list_bmi_category.add("Standard");
        list_bmi_category.add("Metric");
        ArrayAdapter<String> adapter_bmi_category = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, list_bmi_category);
        spinner_bmi_category.setAdapter(adapter_bmi_category);


        button_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double result = 0;
                    if (spinner_bmi_category.getSelectedItem().toString().equals("Standard")) {
                        double feet = Double.parseDouble(field_feet.getText().toString());
                        double inches = Double.parseDouble(field_inches.getText().toString());
                        double pounds = Double.parseDouble(field_pounds.getText().toString());
                        result = calculateStandard(feet, inches, pounds);

                        double totalInches = (feet * 12) + inches;

                        // TODO: If metric, convert the values back to standard before inserting into the databases
                        sqLiteHelper.addBMI(USER_ID, totalInches, pounds, result);

                    } else if (spinner_bmi_category.getSelectedItem().toString().equals("Metric")) {
                        double centimeters = Double.parseDouble(field_feet.getText().toString());
                        double kilograms = Double.parseDouble(field_pounds.getText().toString());

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

        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences preferences = getSharedPreferences(PreferencesKeys.MY_PREFS_NAME, MODE_PRIVATE);
                preferences.edit().remove(PreferencesKeys.USER_LOGGED_IN).apply();

                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        switch (item){
            case "Standard":
                field_inches.setText(null);
                field_inches.setVisibility(View.VISIBLE);

                field_feet.setText(null);
                field_feet.setHint("Feet");

                field_pounds.setHint("Pounds");
                field_pounds.setText(null);

                Toast.makeText(this, "STANDARD", Toast.LENGTH_SHORT).show();
                break;
            case "Metric":
                field_inches.setText(null);
                field_inches.setVisibility(View.INVISIBLE);

                field_feet.setText(null);
                field_feet.setHint("Centimeters");

                field_pounds.setText(null);
                field_pounds.setHint("Kilograms");
                Toast.makeText(this, "METRIC", Toast.LENGTH_SHORT).show();
            default:
                Toast.makeText(this, "NOTHING", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
