package tnt_codefest.diaeta.Account;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

import tnt_codefest.diaeta.BMI_Calculator.MainBMICalculator;
import tnt_codefest.diaeta.Database.SQLiteHelper;
import tnt_codefest.diaeta.R;

public class Signup extends AppCompatActivity {

    private SQLiteHelper sqLiteHelper;

    private EditText fname, lname, username, password, confirmation, date;
    private String FirstName, LastName, UserName, Password, Confirmation, BirthDate;
    private Button signup;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

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
        date = (EditText)findViewById(R.id.field_date);

        // Date Function

        showDialogOnButtonClick();

        // Date Function ^


        signup = (Button)findViewById(R.id.button_sign_up);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstName = fname.getText().toString();
                LastName = lname.getText().toString();
                UserName = username.getText().toString();
                Password = password.getText().toString();
                Confirmation = confirmation.getText().toString();
                BirthDate = date.getText().toString();

                if(!(   FirstName.isEmpty() ||
                        LastName.isEmpty() ||
                        UserName.isEmpty() ||
                        Password.isEmpty() ||
                        Confirmation.isEmpty() ||
                        BirthDate.isEmpty())) {

                    if (UserName.length() > 4) {
                        if (!usernameExists(UserName)) {
                            if(Password.equals(Confirmation)){
                                if(Password.length() >= 8){

                                    // Calculate Age
                                    String birth_year = BirthDate.substring(BirthDate.length() - 4);
                                    int age = 2019 - Integer.parseInt(birth_year);

                                    sqLiteHelper.addUser(UserName, Password, FirstName, LastName, 0, 0, 0, 0, age);
                                    int user_id = 0;
                                    // TODO: DO NOTHING
                                    // Getting the user's id in the database
//                                    Cursor user = sqLiteHelper.findUser(UserName);
//                                    while(user.moveToNext()){
//                                        // Get the last inserted index
//                                        user_id = user.getInt(0);
//                                    }
//                                    user.close();

                                    Intent i = new Intent(getApplicationContext(), Login.class);
//                                    i.putExtra("USER_ID", user_id);
                                    startActivity(i);
                                }
                                else{
                                    toastMessage("Password should be 8 characters or more");
                                }
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

    public void showDialogOnButtonClick(){
        date = (EditText)findViewById(R.id.field_date);

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    // TODO: Change if bugs occurs
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(
                            Signup.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            mDateSetListener,
                            year, month, day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date.setText(month + "-" + dayOfMonth + "-" + year);
            }
        };
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
