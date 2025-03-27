// DashboardActivity.java
package com.example.fire_alarm_system;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> householdList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        listView = findViewById(R.id.listView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Households");
        householdList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, householdList);
        listView.setAdapter(adapter);

        loadHouseholds();
    }

    private void loadHouseholds() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                householdList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Household household = snapshot.getValue(Household.class);
                    householdList.add("Name: " + household.name + "\nContact: " + household.contact);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}