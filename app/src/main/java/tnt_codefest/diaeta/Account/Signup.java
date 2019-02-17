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
import tnt_codefest.diaeta.R;

public class Signup extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private EditText fname, lname, username, password, confirmation;
    private String FirstName, LastName, UserName, Password, Confirmation;
    private Button signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        sqLiteHelper = new SQLiteHelper(getApplicationContext());

        fname = (EditText)findViewById(R.id.field_first_name);
        lname = (EditText)findViewById(R.id.field_last_name);
        username = (EditText)findViewById(R.id.field_user_name);
        password = (EditText)findViewById(R.id.field_pass_word);
        confirmation = (EditText)findViewById(R.id.field_confirmation);

        signup = (Button)findViewById(R.id.button_sign_up);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName = fname.getText().toString();
                LastName = lname.getText().toString();
                UserName = username.getText().toString();
                Password = password.getText().toString();
                Confirmation = confirmation.getText().toString();

                if(!(   FirstName.isEmpty() ||
                        LastName.isEmpty() ||
                        UserName.isEmpty() ||
                        Password.isEmpty() ||
                        Confirmation.isEmpty())) {

                    if (UserName.length() > 4) {
                        if (!usernameExists(UserName)) {
                            if(Password.equals(Confirmation)){
                                sqLiteHelper.addUser(UserName, Password, FirstName, LastName, 0, 0, 0, 0);
                                toastMessage("User successfully Inserted into the database");
                            }
                            else{
                                toastMessage("Passwords do not match!");
                            }
                        } else {
                            toastMessage("Username already taken");
                        }
                    } else{
                        toastMessage("Username too short");
                    }
                }
                else{
                    toastMessage("Please fill out everything!");
                }
            }
        });
    }

    private boolean usernameExists(String username){
        try {
            Cursor user = sqLiteHelper.findUser(username);
            int count = 0;
            while (user.moveToNext()) {
                count++;
            }
            user.close();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        }
        catch(Exception e){
            return false;
        }
    }

    // Toast Message
    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
