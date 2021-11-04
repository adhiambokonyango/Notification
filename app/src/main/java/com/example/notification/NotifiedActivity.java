package com.example.notification;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class NotifiedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notified);
        TextView textView = findViewById(R.id.notified);
        String message = getIntent().getStringExtra("msg");
        textView.setText(message);
    }
}