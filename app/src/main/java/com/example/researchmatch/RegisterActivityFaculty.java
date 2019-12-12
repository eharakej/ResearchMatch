package com.example.researchmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivityFaculty extends AppCompatActivity implements View.OnClickListener {

    TextView textViewRegisterText;

    EditText usernameFacField, emailFacField, passwordFacField, editTextFacFName, editTextFacLName;
    EditText editTextFacSchool, editTextFacDepartment, editTextResearchTitle, editTextDegrees, editTextResearchFocus;

    TextView loginTxtviewFac;

    Button registerFacBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_faculty);

        textViewRegisterText = findViewById(R.id.textViewRegisterText);
        loginTxtviewFac = findViewById(R.id.loginTxtViewFac);

        usernameFacField = findViewById(R.id.usernameFacField);
        emailFacField = findViewById(R.id.passwordFacField);
        passwordFacField = findViewById(R.id.passwordFacField);
        editTextFacFName = findViewById(R.id.editTextFacFName);
        editTextFacLName = findViewById(R.id.editTextFacLName);

        editTextFacSchool = findViewById(R.id.editTextFacSchool);
        editTextFacDepartment = findViewById(R.id.editTextFacDepartment);
        editTextResearchTitle = findViewById(R.id.editTextResearchTitle);
        editTextDegrees = findViewById(R.id.editTextDegrees);
        editTextResearchFocus = findViewById(R.id.editTextResearchFocus);

        registerFacBtn = findViewById(R.id.registerFacBtn);

        registerFacBtn.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {
         if (view == registerFacBtn){

             String facUsername = usernameFacField.getText().toString().trim();
             String facemail = emailFacField.getText().toString().trim();
             String facPassword = passwordFacField.getText().toString().trim();

             if (!TextUtils.isEmpty(facUsername)&& !TextUtils.isEmpty(facemail)&& !TextUtils.isEmpty(facPassword)){



                 //We will need to extract all the info and upload it before the intent


                 Intent facRegisterToProfile = new Intent(this, ProfileActivity.class);
                 startActivity(facRegisterToProfile);
             }

             else if(TextUtils.isEmpty(facUsername) || TextUtils.isEmpty(facemail)|| TextUtils.isEmpty(facPassword)){

                 Toast.makeText(this, "Please Complete all fields", Toast.LENGTH_SHORT).show();
             }







             //Need to add the code to get all of the info and add it up to firebase

             Intent facultyRegistrationToLoginPage = new Intent(this, LoginActivity.class);
             startActivity(facultyRegistrationToLoginPage);

         }

    }
}
