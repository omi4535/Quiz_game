package com.example.quizgame.quiz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quizgame.R;
import com.example.quizgame.login.create_profile;
import com.example.quizgame.login.log_in;
import com.google.firebase.auth.FirebaseAuth;

public class Main_page extends AppCompatActivity {

    Button sign_out,pro,high_score,start;


    FirebaseAuth auth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        sign_out = findViewById(R.id.sign_out);
        pro = findViewById(R.id.profile);
        high_score = findViewById(R.id.high_score);
        start = findViewById(R.id.start_quiz);

        sign_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent i = new Intent(Main_page.this, log_in.class);
                startActivity(i);
                finish();


            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main_page.this, quiz_page.class));
            }
        });

        pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_page.this, create_profile.class));
            }
        });
    }
}