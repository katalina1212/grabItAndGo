package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText usernameText;
    private Button registerBtn;
    private TextView loginBtn;
    private EditText emailText;
    private EditText passwordText;
    private EditText password2Text;
    private RegisterActivity thisActivity = this;

    private FirebaseAuth mAuth;

    public RegisterActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        usernameText = findViewById(R.id.username_text);
        registerBtn=findViewById(R.id.register_btn);
        loginBtn=findViewById(R.id.login_btn);
        emailText=findViewById(R.id.email_text);
        passwordText=findViewById(R.id.password_text);
        password2Text=findViewById(R.id.password2_text);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                        .addOnCompleteListener(thisActivity, (OnCompleteListener<AuthResult>) task -> {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("users/"+user.getUid());
                                myRef.child("username").setValue(usernameText.getText().toString());


                                openTermsConditionsActivity();
                                System.out.println(user.getEmail());
                            } else {
                                // If sign in fails, display a message to the user.


                            }

                        });
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }
    private void openTermsConditionsActivity(){
        Intent intent = new Intent(this, TermsConditionsActivity.class);
        startActivity(intent);
    }

    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}