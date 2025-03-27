// MainActivity.java
package com.example.fire_alarm_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize Firebase
        FirebaseApp.initializeApp(this);

        setContentView(R.layout.activity_main);
    }

    public void openRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void openDashboard(View view) {
        startActivity(new Intent(this, DashboardActivity.class));
    }
}
