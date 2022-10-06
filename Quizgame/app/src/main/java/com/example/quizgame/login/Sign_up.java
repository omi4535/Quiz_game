package com.example.quizgame.login;

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

import com.example.quizgame.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up extends AppCompatActivity {

    EditText sign_email,sign_pass,con_pass;
    Button Sign_up_button;
    TextView warning;
    ProgressBar progressBar;

    String email,pass,con;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_email = findViewById(R.id.sign_email);
        sign_pass = findViewById(R.id.sign_pass);
        con_pass = findViewById(R.id.confirm_pass);
        Sign_up_button = findViewById(R.id.sign_up_button);
        warning = findViewById(R.id.warning);
        progressBar = findViewById(R.id.progresbar);


        //create_high_score();
        Sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up();
            }
        });
    }

    private void create_high_score() {
    FirebaseUser user = auth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference().child("score").child(user.getUid());

        reference.child("h1").setValue(0);
        reference.child("h2").setValue(0);
        reference.child("h3").setValue(0);
        reference.child("h4").setValue(0);
        reference.child("h5").setValue(0);



    }

    public void sign_up(){
        Sign_up_button.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        email = sign_email.getText().toString();
        pass = sign_pass.getText().toString();
        con = con_pass.getText().toString();

        if(check()){
            auth.createUserWithEmailAndPassword(email,pass).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            create_high_score();
                            Intent i = new Intent(Sign_up.this, create_profile.class);
                            startActivity(i);

                            progressBar.setVisibility(View.INVISIBLE);
                        }else
                        {
                            Toast.makeText(Sign_up.this,"try again",Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
        }else
        {
         warning.setText("check email or Password again");
        }

    }

    private Boolean check(){

        if ((con.length() != pass.length() || pass.length() <= 6) || con.compareTo(pass)==0) {
            return true;
        }
        return false;
    }
}