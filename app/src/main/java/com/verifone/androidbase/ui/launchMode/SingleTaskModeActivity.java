package com.verifone.androidbase.ui.launchMode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.orhanobut.logger.Logger;
import com.verifone.androidbase.R;
import com.verifone.androidbase.ui.LaunchModeActivity;

public class SingleTaskModeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_single_task);
        Logger.d("oncreate...");
    }

    public void onJumpMySelf(View view) {
        Intent intent = new Intent(this, SingleTaskModeActivity.class);
        startActivity(intent);
    }

    public void onJumpFatherActivity(View view) {
        Intent intent = new Intent(this, LaunchModeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Logger.d("onNewIntent: intent=" + intent);
    }
}
