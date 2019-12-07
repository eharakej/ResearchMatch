package com.example.researchmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FacultyProfilePage extends AppCompatActivity {

    ImageView imageViewFProfilePic;

    TextView textViewFacultyProfilePageIntro;

    EditText editTextFFirstName, editTextFLastName, editTextFSchool, editTextFDepartment, editTextFTitle;
    EditText editTextFLocation, editTextFEducation, editTextFBio, editTextFResearchInterest;

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
        findViewById(R.id.editTextFLocation);
        findViewById(R.id.editTextFEducation);
        findViewById(R.id.editTextFBio);
        findViewById(R.id.editTextFResearchInterest);



    }
}
