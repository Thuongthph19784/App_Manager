package com.example.nhom_8.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.nhom_8.R;

public class firstScreen extends AppCompatActivity {
    TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/font.ttf");
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        },3000);

    }
}