package com.example.grabitandgo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private CheckBox checkBox;
    private TextView termsCond;

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
        checkBox=findViewById(R.id.checkBox);
        termsCond=findViewById(R.id.termsCond);



        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                registerBtn.setEnabled(isChecked);
            }
        });



        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        //check if user is already logged in
        /*if(mAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/


        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                System.out.println(passwordText.getText());

                System.out.println(password2Text.getText());

                if(usernameText.getText().toString().equals("")){
                    usernameText.setError("Username is required");
                    return;
                }
                if(emailText.getText().toString().equals("")){
                    emailText.setError("Email is required");
                    return;
                }
                if(passwordText.getText().toString().equals("")){
                    passwordText.setError("Password is required");
                    return;
                }
                if(passwordText.getText().toString().length()<8){
                    passwordText.setError("Password must be longer than 8 characters");
                    return;
                }
                if(!password2Text.getText().toString().equals(passwordText.getText().toString())){
                    password2Text.setError("Passwords don't match");
                    return;
                }


                mAuth.createUserWithEmailAndPassword(emailText.getText().toString().trim(), passwordText.getText().toString().trim())
                        .addOnCompleteListener(thisActivity, (OnCompleteListener<AuthResult>) task -> {
                            if (task.isSuccessful()) {

                                final FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification()
                                        .addOnCompleteListener( new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(RegisterActivity.this,
                                                            "Verification email sent to " + user.getEmail(),
                                                            Toast.LENGTH_SHORT).show();
                                                } else {

                                                    Toast.makeText(RegisterActivity.this,
                                                            "Failed to send verification email.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                App.user = mAuth.getCurrentUser();

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("users/"+App.user.getUid());
                                myRef.child("username").setValue(usernameText.getText().toString());
                                myRef.child("rewardPoints").setValue(0);


                                openLoginAfterRegistration(App.user.getUid());
                                System.out.println(App.user.getEmail());
                            } else {
                                Toast.makeText(RegisterActivity.this, "Error!" +task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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

        termsCond.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openTermsConditionsActivity();
            }

        });

    }
    private void openLoginAfterRegistration(String userid){
        Intent intent = new Intent(this, LoginActivity.class);
        Bundle b = new Bundle();
        b.putString("user", userid); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    private void openLoginActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


    private void openTermsConditionsActivity(){
            Intent intent = new Intent(this, TermsConditionsActivity.class);
            startActivity(intent);
        }
}
