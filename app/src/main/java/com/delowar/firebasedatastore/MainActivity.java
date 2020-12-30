package com.delowar.firebasedatastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import model.Student;

public class MainActivity extends AppCompatActivity {
    private EditText nameET, emailET, phoneET;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = findViewById(R.id.nameEditText);
        emailET = findViewById(R.id.emailEditText);
        phoneET = findViewById(R.id.phoneEditText);
        databaseReference= FirebaseDatabase.getInstance().getReference("Student");
    }

    public void saveAllInformation(View view) {
        saveDataIntoFirebase();
    }

    private void saveDataIntoFirebase() {
        String name=nameET.getText().toString().trim();
        String email=emailET.getText().toString().trim();
        String phone=phoneET.getText().toString().trim();
        String key=databaseReference.push().getKey();

        databaseReference.child(key).setValue(new Student(name,email,phone));
        Toast.makeText(getApplicationContext(),"Store successful",Toast.LENGTH_SHORT).show();
        cleanEditTextField();
    }

    private void cleanEditTextField() {
        nameET.setText("");
        emailET.setText("");
        phoneET.setText("");
    }

    public void loadAllInformationFromFirebase(View view) {
        startActivity(new Intent(MainActivity.this,DisplayActivity.class));
    }
}