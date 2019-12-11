package com.example.researchmatch;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText loginEmail, loginPass;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private Button loginBtn, facultyRegisterBtn, studentRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginEmail = (EditText)findViewById(R.id.login_email);
        loginPass = (EditText)findViewById(R.id.login_password);

        facultyRegisterBtn = findViewById(R.id.facultyRegisterBtn);
        studentRegisterBtn = findViewById(R.id.studentRegisterBtn);

        facultyRegisterBtn.setOnClickListener(this);
        studentRegisterBtn.setOnClickListener(this);

        loginBtn.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "PROCESSING....", Toast.LENGTH_LONG).show();
                String email = loginEmail.getText().toString().trim();
                String password = loginPass.getText().toString().trim();

                if (!TextUtils.isEmpty(email)&& !TextUtils.isEmpty(password)){

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                checkUserExistence();

                                Toast.makeText(LoginActivity.this, "User Found, Logging In", Toast.LENGTH_SHORT).show();

                                Intent mainPageIntent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(mainPageIntent);

                            }else {
                                Toast.makeText(LoginActivity.this, "Couldn't login, User not found", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(LoginActivity.this, "Complete all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void checkUserExistence(){

        final String user_id = mAuth.getCurrentUser().getUid();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.hasChild(user_id)){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else {
                    Toast.makeText(LoginActivity.this, "User not registered!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.login) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);

        }else if(item.getItemId() == R.id.register) {
            Intent searchIntent = new Intent(this, RegisterActivity.class);
            startActivity(searchIntent);
        }else if(item.getItemId() == R.id.createpost) {
            Intent postIntent = new Intent(this, PostActivity.class);
            startActivity(postIntent);

        } else if(item.getItemId() == R.id.itemProfilePage) {
            Intent searchIntent = new Intent(this, ProfileActivity.class);
            startActivity(searchIntent);

        } else if(item.getItemId() == R.id.questionnaire) {
            Intent searchIntent = new Intent(this, QuestionareActivity.class);
            startActivity(searchIntent);
        }else if(item.getItemId() == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onClick(View view) {
        if(view == facultyRegisterBtn){

            Intent facultyRegisterIntent = new Intent(this, RegisterActivityFaculty.class);
            startActivity(facultyRegisterIntent);

        }else if(view == studentRegisterBtn){

            Intent studentRegisterIntent = new Intent(this, RegisterActivity.class);
            startActivity(studentRegisterIntent);

        }
    }
}
