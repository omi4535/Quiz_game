package com.example.quizgame.quiz;

import static java.lang.System.exit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizgame.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class result extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    DatabaseReference result = database.getReference().child("score").child(user.getUid());

    TextView show_r,show_w,max,total,newh;
    Button exit,play_again;


    String ri,wr,high;
    int t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        show_r = findViewById(R.id.t_right);
        show_w = findViewById(R.id.t_wrong);
        exit =  findViewById(R.id.exit);
        play_again = findViewById(R.id.play_again);
        max = findViewById(R.id.max);
        total = findViewById(R.id.total);
        newh = findViewById(R.id.highest);

        result.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ri =snapshot.child("right").getValue().toString();
                wr =  snapshot.child("wrong").getValue().toString();
                high =  snapshot.child("highest").getValue().toString();
                show_w.setText(""+wr);
                show_r.setText(""+ri);
                max.setText("Highest Score : "+high);
                t = (Integer.parseInt(ri)*4-Integer.parseInt(wr));
                total.setText("Total Score : "+t);
                if(t>Integer.parseInt(high)) {
                    newh.setVisibility(View.VISIBLE);
                }else
                {
                    newh.setVisibility(View.INVISIBLE);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//
//        show_w.setText(""+wr);
//        show_r.setText(""+ri);
//        total.setText("Total Score : "+(ri*4-wr));
//        max.setText("Highest Score : "+high);
//        int t = ri*4-wr;

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(com.example.quizgame.quiz.result.this);
                alertDialogBuilder.setTitle("Exit Application?");
                alertDialogBuilder
                        .setMessage("Click yes to exit!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })

                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        play_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.quizgame.quiz.result.this,Main_page.class));
                finish();
            }
        });
        }

}