package com.verifone.androidbase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class UiActivity extends AppCompatActivity implements View.OnClickListener {

    private Button ui_list_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui);
        ui_list_bt = (Button) findViewById(R.id.ui_list_bt);
        ui_list_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ui_list_bt:
                intent.setClass(UiActivity.this, ListViewActivity.class);
                startActivity(intent);
                break;
        }
    }
}
