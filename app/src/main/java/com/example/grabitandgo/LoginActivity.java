package com.example.grabitandgo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private TextView registerBtn;
    private Button loginBtn;
    private EditText emailText;
    private EditText passwordText;

    private FirebaseAuth mAuth;

    private LoginActivity thisActivity=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        registerBtn =findViewById(R.id.register_btn);
        loginBtn =findViewById(R.id.login_btn);
        emailText =findViewById(R.id.email_text);
        passwordText =findViewById(R.id.password_text);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(emailText.getText().toString(), passwordText.getText().toString())
                        .addOnCompleteListener(thisActivity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    App.user = mAuth.getCurrentUser();

                                    if(App.user.isEmailVerified())
                                    {
                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                                        DatabaseReference myRef = database.getReference("users/"+App.user.getUid());

                                        myRef.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                App.userName=dataSnapshot.child("username").getValue(String.class);
                                                App.rewardPoints=dataSnapshot.child("rewardPoints").getValue(Integer.class);
                                            }


                                            @Override
                                            public void onCancelled(DatabaseError error) {
                                                // Failed to read value
                                                Log.w("Firebase", "Failed to read value.", error.toException());
                                            }
                                        });

                                        openHomePage(App.user.getUid());
                                    }else {
                                        App.user = null;
                                        Toast.makeText(getBaseContext(),
                                                "Email not verified.",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    // If sign in fails, display a message to the user.

                                    System.out.println("Failed to login");
                                    Toast.makeText(getBaseContext(),
                                            "Failed to login.",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) { openRegisterActivity();}
        });
    }
    private void openHomePage(String userid){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putString("user", userid); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    private void openRegisterActivity(){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}