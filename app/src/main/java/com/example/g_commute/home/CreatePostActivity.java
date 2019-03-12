package com.example.g_commute.home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.g_commute.R;
import com.example.g_commute.data.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreatePostActivity extends AppCompatActivity {

    EditText editTxtPostTitle, editTxtPostDesc;
    Button btnCreate;
    String title, description;
    FirebaseFirestore createpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        editTxtPostTitle = (EditText) findViewById(R.id.editTxtPostTitle);
        editTxtPostDesc = (EditText) findViewById(R.id.editTxtPostDesc);
        btnCreate = (Button) findViewById(R.id.btnCreate);

        createpost = FirebaseFirestore.getInstance();

       /* SharedPreferences sharedPref = getSharedPreferences("MyPref",MODE_PRIVATE);
        final String userName = sharedPref.getString("username","");
        final int userId = sharedPref.getInt("userid",0);*/

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = editTxtPostTitle.getText().toString();
                description = editTxtPostDesc.getText().toString();

                int creator =2;
                int group =101;
                int id =2101;
                CollectionReference reference = createpost.collection("post");
                Post create = new Post(creator,description,group,id,title, Timestamp.now());
                reference.add(create).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CreatePostActivity.this, title + " " + description+" added", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(CreatePostActivity.this, PostActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreatePostActivity.this, "not added "+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}
