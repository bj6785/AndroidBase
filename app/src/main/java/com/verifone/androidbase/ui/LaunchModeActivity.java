package com.verifone.androidbase.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.orhanobut.logger.Logger;
import com.verifone.androidbase.R;
import com.verifone.androidbase.ui.launchMode.SingleInstanceModeActivity;
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
        Logger.d("onCreate: " + this.toString() + ", taskId=" + this.getTaskId());

        modeStandard = findViewById(R.id.launch_mode_standard);
        modeStandard.setOnClickListener(this);

        modeSingleTop = findViewById(R.id.launch_mode_singleTop);
        modeSingleTop.setOnClickListener(this);

        modeSingleTask = findViewById(R.id.launch_mode_singleTask);
        modeSingleTask.setOnClickListener(this);

        modeSingleInstance = findViewById(R.id.launch_mode_singleInstance);
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
                /*
                 * 总结来说：singleTask的结论与android:taskAffinity相关。以A启动B来说
                 * (01) 当A和B的taskAffinity相同时：第一次创建B的实例时，并不会启动新的task，而是直接将B添加到A所在的task；否则，将B所在task中位于B之上的全部Activity都删除，然后跳转到B中。
                 * (02) 当A和B的taskAffinity不同时：第一次创建B的实例时，会启动新的task，然后将B添加到新建的task中；否则，将B所在task中位于B之上的全部Activity都删除，然后跳转到B中。
                 */
                intent = new Intent(this, SingleTaskModeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;

            case R.id.launch_mode_singleInstance:
                intent = new Intent(this, SingleInstanceModeActivity.class);
                startActivity(intent);
                break;
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        Logger.d("onNewIntent: intent=" + intent + ", activity=" + this + ", taskId=" + this.getTaskId());
    }
}
