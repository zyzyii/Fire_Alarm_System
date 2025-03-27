// RegisterActivity.java
package com.example.fire_alarm_system;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextName, editTextContact;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = findViewById(R.id.editTextName);
        editTextContact = findViewById(R.id.editTextContact);
        databaseReference = FirebaseDatabase.getInstance().getReference("Households");
    }

    public void registerHousehold(View view) {
        String name = editTextName.getText().toString();
        String contact = editTextContact.getText().toString();

        if (!name.isEmpty() && !contact.isEmpty()) {
            String id = databaseReference.push().getKey();
            Household household = new Household(name, contact);
            databaseReference.child(id).setValue(household);
            Toast.makeText(this, "Household Registered!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }
}