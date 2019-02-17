package tnt_codefest.diaeta.Account;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tnt_codefest.diaeta.BMI_Calculator.MainBMICalculator;
import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.MainActivity;
import tnt_codefest.diaeta.R;

public class Login extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private EditText username, password;
    private Button login, signup;

    private String Username, Password;

    private int userIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        username = (EditText)findViewById(R.id.field_name);
        password = (EditText)findViewById(R.id.field_password);

        login = (Button)findViewById(R.id.button_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Username = username.getText().toString();
                Password = password.getText().toString();

                String confirmPass = "";
                try{
                    Cursor ver = sqLiteHelper.findUser(Username);
                    while(ver.moveToNext()){
                        userIndex = ver.getInt(0);
                        confirmPass = ver.getString(2);
                    }ver.close();
                }catch(Exception e){
                    toastMessage("Something went wrong.");
                }

                if(Password.equals(confirmPass)){
                    // TODO: Change Activity if conditions were met
                    Intent i = new Intent(getApplicationContext(), MainBMICalculator.class);
                    i.putExtra("USER_ID", userIndex);
                    startActivity(i);
                }
                else{
                    toastMessage("Incorrect Username or Password!-");
                }
            }
        });

        signup = (Button)findViewById(R.id.button_signup);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Signup.class);
                startActivity(i);
            }
        });

    }

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
