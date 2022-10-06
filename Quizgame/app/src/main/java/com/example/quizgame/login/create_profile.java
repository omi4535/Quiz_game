package com.example.quizgame.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.quizgame.R;
import com.example.quizgame.quiz.Main_page;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_profile extends AppCompatActivity {

    ImageView pro_pic;
    EditText name,phone,add,clg;
    Button submit;
    ProgressBar progressBar;
    String n,p,a,c;


    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference reference = database.getReference();
   // StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);


//        storageReference = FirebaseStorage.getInstance().getReference(user.getEmail());
        pro_pic = findViewById(R.id.profile_pic);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        add = findViewById(R.id.address);
        clg = findViewById(R.id.clg);
        submit = findViewById(R.id.submit);
        progressBar = findViewById(R.id.progresbar);



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                n= name.getText().toString();
                p = phone.getText().toString();
                a = add.getText().toString();
                c = clg.getText().toString();
                String uid = user.getUid().toString();

                reference.child("profile").child(uid).child("name").setValue(""+n);
                reference.child("profile").child(uid).child("phone").setValue(""+p);
                reference.child("profile").child(uid).child("address").setValue(""+a);
                reference.child("profile").child(uid).child("clg").setValue(""+c).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(create_profile.this, Main_page.class));
                            finish();
                        }else
                        {
                            Toast.makeText(create_profile.this,"try Again",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }
        });


    }

}