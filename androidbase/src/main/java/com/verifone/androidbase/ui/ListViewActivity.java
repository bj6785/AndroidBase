package com.verifone.androidbase.ui;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.verifone.androidbase.R;

public class ListViewActivity extends FragmentActivity implements View.OnClickListener {

    private Button list_arrayadapter_bt;
    private Button list_simpleadapter_bt;
    private Button list_baseadapter_bt;

    private ArrayAdapterListViewFragment mArrayAdapterListViewFragment;
    private SimpleAdapterListViewFragment mSimpleAdapterListViewFragment;
    private BaseAdapterListviewFragment mBaseAdapterListviewFragment;

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
        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.listview_arrayadapter_bt:
                mArrayAdapterListViewFragment = new ArrayAdapterListViewFragment();
                mFragmentTransaction.replace(R.id.right_scope, mArrayAdapterListViewFragment);
                mFragmentTransaction.commit();
                break;
            case R.id.listview_simpleadapter_bt:
                mSimpleAdapterListViewFragment = new SimpleAdapterListViewFragment();
                mFragmentTransaction.replace(R.id.right_scope, mSimpleAdapterListViewFragment);
                mFragmentTransaction.commit();
                break;
            case R.id.listview_baseadapter_bt:
                mBaseAdapterListviewFragment = new BaseAdapterListviewFragment();
                mFragmentTransaction.replace(R.id.right_scope, mBaseAdapterListviewFragment);
                mFragmentTransaction.commit();
                break;
        }

    }
}
