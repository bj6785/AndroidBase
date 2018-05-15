package com.verifone.androidbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.verifone.androidbase.ui.ExpandListViewActivity;
import com.verifone.androidbase.ui.LaunchModeActivity;
import com.verifone.androidbase.ui.ListViewActivity;
import com.verifone.androidbase.ui.SlideListActivity;

public class UiActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ui_list_bt;
    private Button ui_expand_list_bt;
    private Button ui_slide_list_bt;
    private Button ui_launch_mode_bt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        ui_list_bt = (Button) findViewById(R.id.ui_list_bt);
        ui_expand_list_bt = (Button) findViewById(R.id.ui_expand_list_bt);
        ui_slide_list_bt = (Button) findViewById(R.id.ui_slide_list_bt);
        ui_launch_mode_bt = (Button) findViewById(R.id.ui_launch_mode_bt);

        ui_list_bt.setOnClickListener(this);
        ui_expand_list_bt.setOnClickListener(this);
        ui_slide_list_bt.setOnClickListener(this);
        ui_launch_mode_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ui_launch_mode_bt:
                intent.setClass(UiActivity.this, LaunchModeActivity.class);
                startActivity(intent);
                break;
            case R.id.ui_list_bt:
                intent.setClass(UiActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.ui_expand_list_bt:
                intent.setClass(UiActivity.this, ExpandListViewActivity.class);
                startActivity(intent);
                break;
            case R.id.ui_slide_list_bt:
                intent.setClass(UiActivity.this, SlideListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
