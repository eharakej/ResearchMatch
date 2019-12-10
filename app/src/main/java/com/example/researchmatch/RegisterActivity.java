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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private Button registerBtn;
    private EditText emailField, usernameField, passwordField;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private TextView loginTxtView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loginTxtView = (TextView)findViewById(R.id.loginTxtViewFac);
        registerBtn = (Button)findViewById(R.id.registerFacBtn);
        emailField = (EditText)findViewById(R.id.emailFacField);
        usernameField = (EditText)findViewById(R.id.usernameFacField);
        passwordField = (EditText)findViewById(R.id.passwordFacField);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        loginTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RegisterActivity.this, "LOADING...", Toast.LENGTH_LONG).show();
                final String username = usernameField.getText().toString().trim();
                final String email = emailField.getText().toString().trim();
                final String password = passwordField.getText().toString().trim();
                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(username)&&!TextUtils.isEmpty(password)){
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            String user_id = mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = mDatabase.child(user_id);
                            current_user_db.child("Username").setValue(username);
                            current_user_db.child("Image").setValue("Default");
                            Toast.makeText(RegisterActivity.this, "Registeration Succesful", Toast.LENGTH_SHORT).show();
                            Intent regIntent = new Intent(RegisterActivity.this, ProfileActivity.class);
                            regIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(regIntent);
                        }
                    });
                }else {

                    Toast.makeText(RegisterActivity.this, "Complete all fields", Toast.LENGTH_SHORT).show();
                }
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


}



