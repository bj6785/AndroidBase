package com.verifone.androidbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ui_bt;
    private Button camera_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ui_bt = (Button) findViewById(R.id.ui_bt);
        ui_bt.setOnClickListener(this);
        camera_bt = (Button) findViewById(R.id.camera_bt);
        camera_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ui_bt:
                intent.setClass(MainActivity.this, UiActivity.class);
                startActivity(intent);
                break;
            case R.id.camera_bt:
                intent.setClass(MainActivity.this, CameraActivity.class);
                startActivity(intent);
                break;
        }
    }
}
