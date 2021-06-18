package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //check if user is already logged in
        /*if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(emailText.getText().toString().trim(), passwordText.getText().toString().trim())
                        .addOnCompleteListener(thisActivity, (OnCompleteListener<AuthResult>) task -> {
                            if (task.isSuccessful()) {
                               //Toast.makeText(RegisterActivity.this, "User created", Toast.LENGTH_SHORT).show();

                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("users/"+user.getUid());
                                myRef.child("username").setValue(usernameText.getText().toString());


                                openTermsConditionsActivity(user.getUid());
                                System.out.println(user.getEmail());
                            } else {
                                //Toast.makeText(RegisterActivity.this, "Error!" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user.

                               /* if(TextUtils.isEmpty(username_text)){
                                    usernameText.setError("Email is required");
                                    return;
                                }
                                if(TextUtils.isEmpty(email_text)){
                                   emailText.setError("Email is required");
                                   return;
                                }
                                if(TextUtils.isEmpty(password_text)){
                                    passwordText.setError("Email is required");
                                    return;
                                }
                                if(password_text.length()<8){
                                    passwordText.setError("Password must be longer than 8 characters");
                                    return;
                                }*/
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
    private void openTermsConditionsActivity(String userid){
        Intent intent = new Intent(this, TermsConditionsActivity.class);
        Bundle b = new Bundle();
        b.putString("user", userid); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}