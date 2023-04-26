package com.example.crud_operations;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Read extends AppCompatActivity {

    ListView list;
    FirebaseFirestore db;
    List<String>values= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        list = findViewById(R.id.list);
        db=FirebaseFirestore.getInstance();
        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,values);
        list.setAdapter(arrayAdapter);
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("This is my data",document.getId()+"==="+document.getData());
                                values.add(document.getString("Course name")+document.getData());
                            }
                            arrayAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(Read.this, "Read data failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}