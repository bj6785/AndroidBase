package com.verifone.kail1.androidbase2;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callOtherAppActivity(View view) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.verifone.androidbase", "com.verifone.androidbase.ui.LaunchModeActivity");
        intent.setComponent(componentName);
        startActivity(intent);
    }
}
