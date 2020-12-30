package com.delowar.firebasedatastore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.CustomAdapter;
import model.Student;

public class DisplayActivity extends AppCompatActivity {
     private ListView listView;
     private CustomAdapter adapter;
     private ArrayList<Student> studentArrayList;
     private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
        listView=findViewById(R.id.list_view);
        studentArrayList=new ArrayList<>();
    }

    @Override
    protected void onStart() {
         databaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 studentArrayList.clear();
                 for (DataSnapshot snapshot : dataSnapshot.getChildren())
                 {
                    Student student=snapshot.getValue(Student.class);
                     studentArrayList.add(student);
                 }
                 adapter=new CustomAdapter(DisplayActivity.this,studentArrayList);
                 listView.setAdapter(adapter);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });
        super.onStart();
    }
}