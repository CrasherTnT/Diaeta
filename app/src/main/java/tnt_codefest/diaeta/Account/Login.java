package tnt_codefest.diaeta.Account;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.MainActivity;
import tnt_codefest.diaeta.R;

public class Login extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private EditText username, password;
    private Button login, signup;

    private int userIndex = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        username = (EditText)findViewById(R.id.field_name);
        password = (EditText)findViewById(R.id.field_password);

        login = (Button)findViewById(R.id.button_login);
        signup = (Button)findViewById(R.id.button_signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateAccount(username.getText().toString(), password.getText().toString())){
                    // Successfully Logged In
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    // TODO: Change the MainActivity.class
                    i.putExtra("USER_INDEX", userIndex);
                }
                else{
                    toastMessage("Username or Password is either incorrect");
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Signup.class);
                startActivity(i);
            }
        });


    }

    private boolean validateAccount(String username, String password){
        Cursor userData = sqLiteHelper.findUser(username);
        while(userData.moveToNext()){

            String securePassword = userData.getString(2);
            String salt = userData.getString(3);

            boolean passwordMatch = PasswordUtil.verifyUserPassword(password, securePassword, salt);

            if(passwordMatch) {
                userIndex = userData.getInt(0);
                return true;
            }
        }
        userData.close();
        return false;
    }

    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }
}
