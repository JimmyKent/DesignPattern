package com.jimmy.designpattern;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jimmy.designpattern.singleton.Singleton2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Singleton2.getInstance().getName();

        //http://www.importnew.com/6461.html
    }
}
