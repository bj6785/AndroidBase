package com.verifone.androidbase.ui.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.verifone.androidbase.R;

public class StandardModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_standard_mode);
        Logger.d("oncreate...");
    }

    public void onJump(View view) {
        Intent intent = new Intent(this, StandardModeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Logger.d("onNewIntent: intent=" + intent);
    }
}
