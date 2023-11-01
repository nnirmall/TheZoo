//Nirmal Bhandari
//L20422171
package com.example.hm8_bhandari;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity  implements View.OnClickListener {

    int images[] = {R.drawable.babyorangutan, R.drawable.hippopotamus, R.drawable.macaw, R.drawable.leopard, R.drawable.rhinoceros, R.drawable.girraffe, R.drawable.greentreepython};
    int currentImage;
    MediaPlayer mediaPlayer;
    int sound_click, sound_babyorangutan, sound_girraffe, sound_greentreepython, sound_hippopotamus, sound_macaw, sound_leopard, sound_rhinoceros;
    SoundPool sp;
    int num_sounds_loaded;
    boolean sounds_loaded;
    private ImageView imageView;
    private Button previousButton, nextButton, infoButton;


    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView = (ImageView) findViewById(R.id.imageView);
        previousButton = (Button) findViewById(R.id.prevBtn);
        previousButton.setOnClickListener(this);
        nextButton = (Button) findViewById(R.id.nextBtn);
        infoButton = (Button) findViewById(R.id.infoBtn);
        nextButton.setOnClickListener(this);
        imageView.setOnClickListener(this);
        infoButton.setOnClickListener(this);

        Assets.mediaPlayer = null;

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean isAnimalSoundEnabled = prefs.getBoolean("key_sound_enabled", true);
        Assets.sp_status = isAnimalSoundEnabled;

        currentImage = 0;

        num_sounds_loaded = 0;
        sounds_loaded = false;

        num_sounds_loaded = 0;
        sounds_loaded = false;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Assets.sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            Assets.sp = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();
        }

        Assets.sp.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
            num_sounds_loaded++;
            if (num_sounds_loaded == 6) {
                sounds_loaded = true;
            }
        });

        sound_click = Assets.sp.load(this, R.raw.snd_click, 1);
        sound_leopard = Assets.sp.load(this, R.raw.snd_leopard, 1);
        sound_macaw = Assets.sp.load(this, R.raw.snd_macaw, 1);
        sound_babyorangutan = Assets.sp.load(this, R.raw.snd_babyorangutan, 1);
        sound_girraffe = Assets.sp.load(this, R.raw.snd_giraffe, 1);
        sound_hippopotamus = Assets.sp.load(this, R.raw.snd_hippo, 1);
        sound_rhinoceros = Assets.sp.load(this, R.raw.snd_rhinos, 1);
        sound_greentreepython = Assets.sp.load(this, R.raw.snd_python, 1);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("ProjectLogging","Activity Resumed");
        System.out.println("Restoring image "+currentImage);

        imageView.setImageResource(images[currentImage]);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        boolean b = prefs.getBoolean("key_music_enabled",true);

        if(b == true){
            if(Assets.mediaPlayer != null){
                Assets.mediaPlayer.release();
                Assets.mediaPlayer = null;
            }
            Assets.mediaPlayer = MediaPlayer.create(this, R.raw.snd_main);
            Assets.mediaPlayer.setLooping(true);
            Assets.mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        if (Assets.mediaPlayer != null){
            Assets.mediaPlayer.pause();
            Assets.mediaPlayer.release();
            Assets.mediaPlayer = null;
        }
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
            if (sounds_loaded) {
                Assets.sp.play(sound_click, 1, 1, 0, 0, 1);
            }
            currentImage = (currentImage + 1) % images.length;
        } else if (viewId == R.id.infoBtn) {
            if ((sounds_loaded)) {
                Assets.sp.play(sound_click,1,1,0,0,1);
            }
            startActivity(new Intent(this, PrefsActivity.class));
//            if(currentImage == 0){
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Orangutan"));
//                startActivity(myIntent);
//            }
//            else if (currentImage == 1) {
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Hippopotamus"));
//                startActivity(myIntent);
//            }
//            else if (currentImage == 2) {
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Macaw"));
//                startActivity(myIntent);
//            }
//            else if (currentImage == 3) {
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Leopard"));
//                startActivity(myIntent);
//            }
//            else if (currentImage == 4) {
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Rhinoceros"));
//                startActivity(myIntent);
//            }
//            else if (currentImage == 5) {
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Giraffe"));
//                startActivity(myIntent);
//            }
//            else if (currentImage == 6) {
//                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://en.wikipedia.org/wiki/Green_tree_python"));
//                startActivity(myIntent);
//            }
        }else if (viewId == R.id.prevBtn) {
            if (sounds_loaded) {
                Assets.sp.play(sound_click, 1, 1, 0, 0, 1);
            }
            if (currentImage == 0) {
                currentImage = images.length - 1; // Set index to the maximum value
            } else {
                currentImage = (currentImage - 1) % images.length;
            }
        } else if (viewId == R.id.imageView) {
            if (sounds_loaded && Assets.sp_status == true) {
                if(currentImage == 0){
                    Assets.sp.play(sound_babyorangutan, 1, 1, 0, 0, 1);
                    Toast.makeText(this,"Orangutan Squeaks", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 1) {
                    Assets.sp.play(sound_hippopotamus, 1, 1, 0, 0, 1);
                    Toast.makeText(this,"Hippopotamus Honks", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 2) {
                    Assets.sp.play(sound_macaw, 1, 1, 0, 0, 1);
                    Toast.makeText(this,"Mascaw Squawks", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 3) {
                    Assets.sp.play(sound_leopard, 1, 1, 0, 0, 1);
                    Toast.makeText(this,"Leopard Roars", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 4) {
                    Assets.sp.play(sound_rhinoceros, 1, 1, 0, 0, 1);
                    Toast.makeText(this,"Rhinoceros Trumpets", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 5) {
                    Assets.sp.play(sound_girraffe, 1, 1, 0, 0, 1);
                    Toast.makeText(this,"Giraffe Bleats", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 6) {
                    Assets.sp.play(sound_greentreepython, 1, 1, 0, 0, 1);
                    Toast.makeText(this,"GreenPython Hisses", Toast.LENGTH_SHORT).show();
                }
            } else if (sounds_loaded && Assets.sp_status == false) {
                if(currentImage == 0){
                    Toast.makeText(this,"Orangutan Squeaks", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 1) {
                    Toast.makeText(this,"Hippopotamus Honks", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 2) {
                    Toast.makeText(this,"Mascaw Squawks", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 3) {
                    Toast.makeText(this,"Leopard Roars", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 4) {
                    Toast.makeText(this,"Rhinoceros Trumpets", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 5) {
                    Toast.makeText(this,"Giraffe Bleats", Toast.LENGTH_SHORT).show();
                }
                else if (currentImage == 6) {
                    Toast.makeText(this,"GreenPython Hisses", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (currentImage >= 0 && currentImage < images.length) {
            imageView.setImageResource(images[currentImage]);
        }
    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.alert_img)
                .setTitle("Closing Application")
                .setMessage("Are you sure you want to exit??")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}