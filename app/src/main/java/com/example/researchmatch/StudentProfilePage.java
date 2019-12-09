package com.example.researchmatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class StudentProfilePage extends AppCompatActivity implements View.OnClickListener {

    ImageView imageViewSProfilePic;

    TextView textViewStudentProfilePageIntro;

    EditText editTextSFirstName, editTextSLastName, editTextSSchool, editTextSProgram, editTextSDegree;
    EditText editTextSYear,editTextSBio, editTextSLinkToResume;

    Button buttonSSaveEdits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile_page);

        findViewById(R.id.imageViewSProfilePic);
        findViewById(R.id.textViewStudentProfilePageIntro);
        findViewById(R.id.editTextSFirstName);
        findViewById(R.id.editTextSLastName);
        findViewById(R.id.editTextSSchool);
        findViewById(R.id.editTextSProgram);
        findViewById(R.id.editTextSYear);
        findViewById(R.id.editTextSDegree);
        findViewById(R.id.editTextSBio);
        findViewById(R.id.editTextSLinkToResume);
        findViewById(R.id.buttonSSaveEdits);

        buttonSSaveEdits.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

    }
}
