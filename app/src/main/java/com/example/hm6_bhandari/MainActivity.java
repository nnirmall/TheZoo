//Nirmal Bhandari
//L20422171
package com.example.hm6_bhandari;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private Handler mHandler = new Handler ();

    boolean quit;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quit = false;
        mHandler.postDelayed ( () -> {
            if (! quit)
                doStuff();
        }, 2000) ;
    }
    private void doStuff () {
        Intent intent = new Intent (MainActivity.this, SecondActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed () {
        quit = true;
        super.onBackPressed();
    }


}