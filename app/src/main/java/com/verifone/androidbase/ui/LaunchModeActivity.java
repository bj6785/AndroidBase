package com.verifone.androidbase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.verifone.androidbase.R;
import com.verifone.androidbase.ui.launchMode.SingleTaskModeActivity;
import com.verifone.androidbase.ui.launchMode.SingleTopModeActivity;
import com.verifone.androidbase.ui.launchMode.StandardModeActivity;

/**
 * Created by LiKai on 2018-03-12.
 */

public class LaunchModeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button modeStandard;
    private Button modeSingleTop;
    private Button modeSingleTask;
    private Button modeSingleInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_ui);

        modeStandard = findViewById(R.id.launch_mode_standard);
        modeStandard.setOnClickListener(this);

        modeSingleTop= findViewById(R.id.launch_mode_singleTop);
        modeSingleTop.setOnClickListener(this);

        modeSingleTask = findViewById(R.id.launch_mode_singleTask);
        modeSingleTask.setOnClickListener(this);

        modeSingleInstance= findViewById(R.id.launch_mode_singleInstance);
        modeSingleInstance.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.launch_mode_standard: // 每次新创建activity放在栈顶
                intent = new Intent(this, StandardModeActivity.class);
                startActivity(intent);
                break;
            case R.id.launch_mode_singleTop: // 如果要创建的activity已经在栈顶，那么则不去创建Activity，而是调用onNewIntent重用该Activity，否则创建新的Activity
                intent = new Intent(this, SingleTopModeActivity.class);
                startActivity(intent);
                break;
            case R.id.launch_mode_singleTask:
                // 如果taskAffinity相同，那么如果要创建的activity不存在于当前task，那么会简单在当前栈顶创建，如果存在，则删除掉此activity之上的所有activity，并显示此activit
                //如果taskAffinity不相同，要创建的activity会新创建一个taskAffinity对应的task，并把此activity加入到栈，但是如果已经存在对应的task，则删除掉所有此activity之上的所有activity，并显示此activity
                intent = new Intent(this, SingleTaskModeActivity.class);
                startActivity(intent);
                break;

        }

    }
}
