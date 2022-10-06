package com.example.quizgame.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizgame.R;
import com.example.quizgame.data.Question;
import com.example.quizgame.data.Result;
import com.example.quizgame.data.data;

import com.example.quizgame.data.set_data;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Collections;

public class quiz_page extends AppCompatActivity {

    TextView wrong;
    TextView right;
    TextView timer;
    TextView text_question,a,b,c,d;
    Button next,prv,finish;
    public ArrayList<ArrayList<Integer>> corr_ans = new ArrayList<ArrayList<Integer>>();
    public ArrayList<Integer> corr1 = new ArrayList<Integer>();
    public ArrayList<Integer> corr2 = new ArrayList<Integer>();
    public ArrayList<Integer> corr3 = new ArrayList<Integer>();
    public ArrayList<Integer> corr4 = new ArrayList<Integer>();
    public ArrayList<Integer> corr5 = new ArrayList<Integer>();
    public ArrayList<Integer> corr6 = new ArrayList<Integer>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    DatabaseReference scoreref = database.getReference().child("score");
    int que_no =1;
    int r=0,w=0;
    String highest;
    boolean exist= false;
    int all_high;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        wrong = findViewById(R.id.wrong_score);
        right = findViewById(R.id.right_score);
        timer = findViewById(R.id.timer);
        text_question = findViewById(R.id.question);
        a = findViewById(R.id.option1);
        b = findViewById(R.id.option2);
        c = findViewById(R.id.option3);
        d = findViewById(R.id.option4);
        next = findViewById(R.id.next);
        prv = findViewById(R.id.next2);
        show_que(que_no);
        finish =findViewById(R.id.next3);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                que_no++;
                if(que_no>7) que_no=0;

                   show_que(que_no);
                   a.setBackgroundResource(R.color.white);
                   b.setBackgroundResource(R.color.white);
                   c.setBackgroundResource(R.color.white);
                   d.setBackgroundResource(R.color.white);
                   right.setText(""+r);
                   wrong.setText(""+w);

            }
        });

        scoreref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(user.getUid().toString()).child("highest").exists()){
                    highest = snapshot.child(user.getUid().toString()).child("highest").getValue().toString();
                }else
                {
                   highest = "0";
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int h = Integer.parseInt(highest);
                if(h < r*4-w){
                    scoreref.child(user.getUid().toString()).child("highest").setValue(highest);
                    scoreref.child(user.getUid().toString()).child("right").setValue(r);
                    scoreref.child(user.getUid().toString()).child("wrong").setValue(w);

                }else
                {
                    scoreref.child(user.getUid().toString()).child("right").setValue(r);
                    scoreref.child(user.getUid().toString()).child("wrong").setValue(w);
                }


                Toast.makeText(quiz_page.this,"sdfas",Toast.LENGTH_SHORT).show();

                Intent i = new Intent(quiz_page.this,result.class);
                startActivity(i);
                finish();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              check_ans(1);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_ans(2);
            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_ans(3);
            }
        });

        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check_ans(4);
            }
        });
        prv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                que_no--;
                if(que_no<0) que_no =6;
                show_que(que_no);

            }
        });


    }

    private void check_ans(int i) {


                if(corr_ans.get(que_no-1).contains(i)){
                   switch (i){
                       case 1: r++;a.setBackgroundResource(R.color.green);break;
                       case 2: r++;b.setBackgroundResource(R.color.green);break;
                       case 3: r++;c.setBackgroundResource(R.color.green);break;
                       case 4: r++;d.setBackgroundResource(R.color.green);break;


                   }
                }else
                {
                    switch (i){
                        case 1: w++;a.setBackgroundResource(R.color.red);break;
                        case 2: w++;b.setBackgroundResource(R.color.red);break;
                        case 3: w++;c.setBackgroundResource(R.color.red);break;
                        case 4: w++;d.setBackgroundResource(R.color.red);break;

                    }
                }

    }

    void show_que(int n){

        set_data sd = new set_data();
       // Result r = new Result();


        switch (n){
            case 1:
                text_question.setText(sd.q1.lable);
                a.setText(sd.q1.options.get(0).lable);
                b.setText(sd.q1.options.get(1).lable);
                c.setText(sd.q1.options.get(2).lable);
                d.setText(sd.q1.options.get(3).lable);
               corr1.add(1);
          //      r.questions.add(sd.q1);
                break;
            case 2:
                text_question.setText(sd.q2.lable);
                a.setText(sd.q2.options.get(0).lable);
                b.setText(sd.q2.options.get(1).lable);
                c.setText(sd.q2.options.get(2).lable);
                d.setText(sd.q2.options.get(3).lable);
                corr2.add(2);
                corr2.add(3);
                corr2.add(4);
          //      r.questions.add(sd.q2);
                break;
            case 3:
               text_question.setText(sd.q3.lable);
                a.setText(sd.q3.options.get(0).lable);
                b.setText(sd.q3.options.get(1).lable);
                c.setText(sd.q3.options.get(2).lable);
                d.setText(sd.q3.options.get(3).lable);
              corr3.add(4);
              //  r.questions.add(sd.q3);
                break;
            case 4:
                text_question.setText(sd.q4.lable);
                a.setText(sd.q4.options.get(0).lable);
                b.setText(sd.q4.options.get(1).lable);
                c.setText(sd.q4.options.get(2).lable);
                d.setText(sd.q4.options.get(3).lable);
                corr4.add(2);
                break;

            case 5:
                text_question.setText(sd.q5.lable);
                a.setText(sd.q5.options.get(0).lable);
                b.setText(sd.q5.options.get(1).lable);
                c.setText(sd.q5.options.get(2).lable);
                d.setText(sd.q5.options.get(3).lable);
                corr5.add(1);
                corr5.add(2);
                break;
           case 6:
            {
                text_question.setText(sd.q6.lable);
                a.setText(sd.q6.options.get(0).lable);
                b.setText(sd.q6.options.get(1).lable);
                c.setText(sd.q6.options.get(2).lable);
                d.setText(sd.q6.options.get(3).lable);
                corr6.add(2);
                corr6.add(1);
                corr6.add(4);
                break;
            }
        }
        corr_ans.add(corr1);
        corr_ans.add(corr2);
        corr_ans.add(corr3);
        corr_ans.add(corr4);
        corr_ans.add(corr5);
        corr_ans.add(corr6);



    }
}