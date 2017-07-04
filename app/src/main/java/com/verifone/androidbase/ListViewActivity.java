package com.verifone.androidbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ListViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button list_arrayadapter_bt;
    private Button list_simpleadapter_bt;
    private Button list_baseadapter_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        list_arrayadapter_bt = (Button) findViewById(R.id.listview_arrayadapter_bt);
        list_arrayadapter_bt.setOnClickListener(this);

        list_simpleadapter_bt = (Button) findViewById(R.id.listview_simpleadapter_bt);
        list_simpleadapter_bt.setOnClickListener(this);

        list_baseadapter_bt = (Button) findViewById(R.id.listview_baseadapter_bt);
        list_baseadapter_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.listview_arrayadapter_bt:
                break;
            case R.id.listview_simpleadapter_bt:
                break;
            case R.id.listview_baseadapter_bt:
                break;
        }
    }
}
