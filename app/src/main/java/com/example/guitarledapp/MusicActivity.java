package com.example.guitarledapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.util.ArrayList;

public class MusicActivity extends AppCompatActivity {

    private String[] scales = {"C major", "G major", "D Major", "A Major", "B major", "F major", "B flat major", "E flat major", "A flat major",
    "D flat major", "A minor", "E minor", "B minor", "F sharp minor", "C sharp minor", "G sharp minor", "D minor", "G minor", "C minor",
    "F minor", "B flat minor", "E flat minor"};
    private String[] octaves = {"1", "2"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        final Spinner SPIN = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1 ,scales);
        SPIN.setAdapter(adapter);

        final Spinner spinTwo = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter adapterTwo = new ArrayAdapter(
                getApplicationContext(),android.R.layout.simple_list_item_1 ,octaves);
        spinTwo.setAdapter(adapterTwo);

       Button play = (Button) findViewById(R.id.play);
       play.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       String octave = spinTwo.getSelectedItem().toString();
                       String scale = SPIN.getSelectedItem().toString();
                       scale.replace(" ", "");
                       scale.toLowerCase();
                       String url = "";
                       if (scale.equalsIgnoreCase("gsharpminor"))
                       {
                           url = "https://www.violinonline.com/sound/scales/" + octave + "octave/vln" + octave + "8v" + scale + "3.mp3";
                       }
                       else
                       {
                           url = "https://www.violinonline.com/sound/scales/" + octave + "octave/vln" + octave + "8v" + scale + ".mp3"; // your URL here
                       }
                       System.out.println(url);
                       MediaPlayer mediaPlayer = new MediaPlayer();
                       mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                       Uri uri = Uri.parse(url);
                       try {

                           mediaPlayer.setDataSource(MusicActivity.this, uri);
                           mediaPlayer.prepare(); // might take long! (for buffering, etc)
                           mediaPlayer.start();
                       } catch (IOException e) {
                           e.printStackTrace();
                       }
                   }
               }
       );




    }
}
