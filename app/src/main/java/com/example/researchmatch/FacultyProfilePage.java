package com.example.researchmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FacultyProfilePage extends AppCompatActivity implements View.OnClickListener {


    //faculty profile page - Vichu push

    ImageView imageViewFProfilePic;

    TextView textViewFacultyProfilePageIntro;

    EditText editTextFFirstName, editTextFLastName, editTextFSchool, editTextFDepartment, editTextFTitle;
    EditText editTextFLocation, editTextFEducation, editTextFBio, editTextFResearchInterest;

    Button buttonFSaveEdits;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_profile_page);

        findViewById(R.id.imageViewFProfilePic);
        findViewById(R.id.textViewFacultyProfilePageIntro);
        findViewById(R.id.editTextFFirstName);
        findViewById(R.id.editTextFLastName);
        findViewById(R.id.editTextFSchool);
        findViewById(R.id.editTextFDepartment);
        findViewById(R.id.editTextFTitle);
        findViewById(R.id.editTextLocation);
        findViewById(R.id.editTextFEducation);
        findViewById(R.id.editTextFBio);
        findViewById(R.id.editTextFResearchInterests);

        //findViewById(R.id.buttonSSaveEdits);

        buttonFSaveEdits.setOnClickListener(this);

        String autoPopulateTest = "Harpreet Kalsi";
        char[] autoPopulateTestChar = autoPopulateTest.toCharArray();

        //I have no idea what the second two things are, but if this work then we can get the stuff
        //from the database and just convert to to a string then char array and set the editText to that
        editTextFFirstName.setText(autoPopulateTestChar,0,0);

        //Right now we need to find a way to auto populate the editTexts with the data from the database so that we can
        //have the editTexts filled in with the user info when they get to tthis page



    }

    @Override
    public void onClick(View view) {

    }
}
