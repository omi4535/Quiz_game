package com.example.quizgame.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.quizgame.R;

public class MainActivity extends AppCompatActivity {

    TextView splash_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash_text = findViewById(R.id.textView);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.splash_anim);
        splash_text.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent to_login = new Intent(MainActivity.this, log_in.class);
                startActivity(to_login);
                finish();
            }
        },3000);
    }


}