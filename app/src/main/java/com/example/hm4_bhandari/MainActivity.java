//Nirmal Bhandari
//L20422171
package com.example.hm4_bhandari;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    int images[] = {R.drawable.babyorangutan, R.drawable.girraffe, R.drawable.greentreepython, R.drawable.hippopotamus, R.drawable.macaw, R.drawable.leopard, R.drawable.rhinoceros};
    int currentImage;
    private ImageView imageView;
    private Button previousButton, nextButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        previousButton = (Button) findViewById(R.id.prevBtn);
        previousButton.setOnClickListener(this);
        nextButton = (Button) findViewById(R.id.nextBtn);
        nextButton.setOnClickListener(this);
        currentImage = 0;

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);


    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("ProjectLogging","Activity Resumed");
        System.out.println("Restoring image "+currentImage);
    }

    @Override
    protected void onPause() {
        if (isFinishing()) {
            Log.i("ProjectLogging", "Activity finishing");
            System.out.println("No image is saved");
        }
        else {
            Log.i("ProjectLogging", "Activity pausing");
            System.out.println("Currently displayed image was " + currentImage);
        }
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId(); // Get the ID of the clicked view
        if (viewId == R.id.nextBtn) {
            currentImage = (currentImage + 1) % images.length;
        } else if (viewId == R.id.prevBtn) {
            currentImage = (currentImage - 1 + images.length) % images.length;
        }
        if (currentImage >= 0 && currentImage < images.length) {
            imageView.setImageResource(images[currentImage]);
        }
    }



}




























