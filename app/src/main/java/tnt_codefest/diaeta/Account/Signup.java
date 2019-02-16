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

import tnt_codefest.diaeta.BMI_Calculator.MainBMICalculator;
import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.R;

public class Signup extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private EditText fname, lname, username, password, confirmation;
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
                if( fname.getText().toString().isEmpty() ||
                        lname.getText().toString().isEmpty() ||
                        username.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty() ||
                        confirmation.getText().toString().isEmpty()){
                    toastMessage("Please fill out all the fields!");
                }
                else{
                    if(usernameExists(username.getText().toString())){
                        // Do something if username exists
                        toastMessage("User already exists!");
                    }
                    else{
                        if(password.getText().toString().equals(confirmation.getText().toString())){
                            // If successfully inserted data
                            // Hash password
                            String salt = PasswordUtil.getSalt(30);
                            String mySecurePassword = PasswordUtil.generateSecurePassword(password.getText().toString(), salt);

                            sqLiteHelper.addUser(username.getText().toString(), mySecurePassword, salt, fname.getText().toString(), lname.getText().toString(),
                                    0, 0, 0, 0);

                            // Go to next activity
                            int index = 0;
                            Cursor c = sqLiteHelper.getUser();
                            if(c.moveToLast()){
                                index = c.getInt(0);
                            }
                            c.close();
                            Intent i = new Intent(getApplicationContext(), MainBMICalculator.class);
                            i.putExtra("USER_ID", index);
                            startActivity(i);
                        }
                        else{
                            toastMessage("Passwords do not match!");
                        }
                    }
                }
            }
        });
    }

    private boolean usernameExists(String username){
        Cursor user = sqLiteHelper.findUser(username);
        int count = 0;
        while(user.moveToNext()){
            count++;
        }
        user.close();
        if(count > 0){
            return true;
        }
        else{
            return false;
        }
    }

    // Toast Message
    private void toastMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
    }
}
