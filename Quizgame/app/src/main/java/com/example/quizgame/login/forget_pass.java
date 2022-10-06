package com.example.quizgame.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizgame.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forget_pass extends AppCompatActivity {
    EditText f_email;
    Button send;

    FirebaseAuth auth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        f_email = findViewById(R.id.femail);
        send = findViewById(R.id.fbutton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                auth.sendPasswordResetEmail(f_email.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    finish();
                                }else{
                                    Toast.makeText(forget_pass.this,"Try Again",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
        });
    }
}