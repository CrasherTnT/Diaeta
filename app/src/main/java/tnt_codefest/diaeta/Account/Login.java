package tnt_codefest.diaeta.Account;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.R;

public class Login extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private EditText username, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        username = (EditText)findViewById(R.id.field_name);
        password = (EditText)findViewById(R.id.field_password);

        if(validateAccount(username.getText().toString(), password.getText().toString())){
            // Successfully Logged In
        }

    }

    private boolean validateAccount(String username, String password){
        Cursor userData = sqLiteHelper.findUser(username);
        while(userData.moveToNext()){

            String securePassword = userData.getString(2);
            String salt = userData.getString(3);

            boolean passwordMatch = PasswordUtil.verifyUserPassword(password, securePassword, salt);

            if(passwordMatch)
                return true;
        }
        return false;
    }
}
