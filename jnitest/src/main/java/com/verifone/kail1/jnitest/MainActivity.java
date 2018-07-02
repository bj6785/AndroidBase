package com.verifone.kail1.jnitest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonStringFromJni(View view) {
        Log.d("TAG", stringFromJNI());
    }

    public void buttonStringToJni(View view) {
        stringToJNI("Hello World From JAVA");
    }

    public native String stringFromJNI();
    public native void stringToJNI(String message);

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

}
