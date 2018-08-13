package com.verifone.androidbase.ui.customView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.verifone.androidbase.R;

public class CustomViewActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.custom_view);
        View v = new MyCustomView(this, null);
        setContentView(v);
    }
}
