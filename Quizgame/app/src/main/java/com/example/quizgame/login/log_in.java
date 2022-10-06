package com.example.quizgame.login;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.quiz.Main_page;
import com.example.quizgame.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class log_in extends AppCompatActivity {
        EditText sign_email,sign_pass;
        Button sign_in;
        TextView to_sign_up,forgot_pass;
        SignInButton  sign_in_with_google;
        ProgressBar progressBar;

        String email,pass;

        GoogleSignInClient signInClient;

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        ActivityResultLauncher<Intent> activityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sign_email = findViewById(R.id.user_email);
        sign_pass = findViewById(R.id.user_pass);
        sign_in = findViewById(R.id.login_button);
        sign_in_with_google = findViewById(R.id.Sign_in_with_google);
        to_sign_up = findViewById(R.id.to_sign_up);
        forgot_pass = findViewById(R.id.forgot_pass);
        progressBar = findViewById(R.id.pro);

        registeractivity();

        Intent to_sign_up_intent = new Intent(log_in.this, Sign_up.class);



        to_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(to_sign_up_intent);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sign_in();

            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(log_in.this, forget_pass.class);
               startActivity(i);
            }
        });

        sign_in_with_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signinwithgoogle();
            }
        });

    }

    void sign_in(){
        sign_in.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        email = sign_email.getText().toString();
        pass = sign_pass.getText().toString();
        if(pass.length()>=6){
            auth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent i = new Intent(log_in.this, Main_page.class);
                                startActivity(i);
                                finish();
                            }else{
                                sign_in.setVisibility(View.VISIBLE);
                                Toast.makeText(log_in.this,"Try Again",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }else
        {
            sign_in.setVisibility(View.VISIBLE);
            Toast.makeText(log_in.this,"Password is too short",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(user!=null){
            Intent i = new Intent(log_in.this,Main_page.class);
            startActivity(i);
            finish();
        }
    }

    void signinwithgoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestIdToken("523434800293-1bms4u5kqi5n404ki3bldt3i52aj4v0d.apps.googleusercontent.com").requestEmail()
                .build();

        signInClient = GoogleSignIn.getClient(this,gso);
        signin();
    }

    void signin(){
            Intent signinintent  = signInClient.getSignInIntent();
            activityResultLauncher.launch(signinintent);
    }

    void registeractivity(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {

                            int resultcode = result.getResultCode();
                            Intent data = result.getData();

                            if(resultcode==RESULT_OK && data!=null){
                                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                                firebasegooglesignin(task);

                            }
                    }
                });
    }

    void firebasegooglesignin(Task<GoogleSignInAccount> task){
        try {
            GoogleSignInAccount account = task.getResult(ApiException.class);
            Intent i = new Intent(log_in.this,Main_page.class);
            startActivity(i);
            finish();
            signinfirebase(account);

        } catch (ApiException e) {
            e.printStackTrace();
            Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show();
        }
    }

    void signinfirebase(GoogleSignInAccount account){
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                        }
                    }
                });
    }
}