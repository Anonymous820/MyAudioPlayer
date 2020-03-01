package com.example.myaudioplayer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,OnItemCliceKedL {

    private static final String TAG = "ZZZZ";
    FloatingActionButton playpausebtn,stopbtn;
    SeekBar seekBar;
    MediaPlayer mp;
    CountDownTimer countDownTimer;
    ArrayList<SuitcaseSong> suitcaseSongArrayList;
    RecyclerView recyclerView;
    public CardView cardView;
    Animation hideMediaPlayer,showmediaplayer;
    ImageView audiaiv;
    TextView titletv;
    private int flag=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playpausebtn=findViewById(R.id.btnplaypause);
        stopbtn=findViewById(R.id.btnstop);
        seekBar=findViewById(R.id.seekBar);
        recyclerView=findViewById(R.id.rv);
        cardView=findViewById(R.id.cv);
        audiaiv=findViewById(R.id.audioiv);
        titletv=findViewById(R.id.titletv);


        mp=new MediaPlayer();
        suitcaseSongArrayList=new ArrayList<>();
        seekBar.setEnabled(false);



        String audiopath="android.resource://"+getPackageName()+"/raw/memories";
        Uri uri=Uri.parse(audiopath);
        try {
            mp.setDataSource(MainActivity.this,uri);
            mp.prepare();
            seekBar.setMax(mp.getDuration());

        } catch (IOException e) {
            e.printStackTrace();
        }


        addData();
        RecyclerSongsAdapter adapter=new RecyclerSongsAdapter(suitcaseSongArrayList,MainActivity.this,MainActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


     recyclerView.setOnScrollListener(new MyRecyclerScroll() {
         @Override
         public void show() {
             showmediaplayer=AnimationUtils.loadAnimation(MainActivity.this,R.anim.show_media_player);
             cardView.clearAnimation();
             cardView.setAnimation(showmediaplayer);
             cardView.getAnimation().start();
         }

         @Override
         protected void hide() {
             hideMediaPlayer= AnimationUtils.loadAnimation(MainActivity.this,R.anim.hide_media_player);
             cardView.clearAnimation();
             cardView.setAnimation(hideMediaPlayer);
             cardView.getAnimation().start();
         }
     });



        playpausebtn.setOnClickListener(this);
        stopbtn.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (fromUser){
                    countDownTimer.cancel();
                    mp.seekTo(progress);
                    seekBar.setProgress(progress);
                    startCountdownTimer();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                countDownTimer.cancel();
                seekBar.setProgress(0);
                mp.seekTo(0);
                mp.pause();
                playpausebtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
            }
        });
    }

    private void setAudio() {

    }

    private void addData() {
        SuitcaseSong song=new SuitcaseSong();
        song.setTitle("Illegal Weapon");
        song.setApath("android.resource://"+getPackageName()+"/raw/illegalweapon");
        song.setImageid(R.drawable.illegalweapon1);
        suitcaseSongArrayList.add(song);

        SuitcaseSong song1=new SuitcaseSong();
        song1.setTitle("Mere Wala Sardar");
        song1.setApath("android.resource://"+getPackageName()+"/raw/merewalasardar");
        song1.setImageid(R.drawable.merewalasardar1);
        suitcaseSongArrayList.add(song1);

        SuitcaseSong song2=new SuitcaseSong();
        song2.setTitle("On My Way");
        song2.setApath("android.resource://"+getPackageName()+"/raw/onmyway");
        song2.setImageid(R.drawable.onmyway1);
        suitcaseSongArrayList.add(song2);

        SuitcaseSong song3=new SuitcaseSong();
        song3.setTitle("Tere Bin");
        song3.setApath("android.resource://"+getPackageName()+"/raw/terebin");
        song3.setImageid(R.drawable.terebin1);
        suitcaseSongArrayList.add(song3);

        SuitcaseSong song4=new SuitcaseSong();
        song4.setTitle("Satisfya");
        song4.setApath("android.resource://"+getPackageName()+"/raw/satisfya");
        song4.setImageid(R.drawable.satisfya1);
        suitcaseSongArrayList.add(song4);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnplaypause:

                playpause();
                break;

            case R.id.btnstop:

                stopmedia();
                break;
        }



    }

    private void stopmedia() {

        if (mp.isPlaying()) {
            countDownTimer.cancel();
            mp.pause();
            mp.seekTo(0);
            seekBar.setProgress(0);
            playpausebtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        }else {
            if (countDownTimer!=null){
                countDownTimer.cancel();
            }
            mp.seekTo(0);
            seekBar.setProgress(0);
        }
    }

    private void playpause() {
        if (mp.isPlaying()) {
            mp.pause();
            countDownTimer.cancel();
            playpausebtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        }else {
            if (countDownTimer!=null) {
                countDownTimer.cancel();
            }
            mp.start();
            seekBar.setEnabled(true);
            startCountdownTimer();
            playpausebtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_pause_black_24dp));
        }
    }



    private void startCountdownTimer() {
        countDownTimer= new CountDownTimer(mp.getDuration()-seekBar.getProgress(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                seekBar.setProgress((int) (mp.getDuration()- millisUntilFinished));
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }


    @Override
    public void OnClickedImet(SuitcaseSong suitcaseSong) {

        if (mp.isPlaying()){
            mp.pause();
            playpausebtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        }

        audiaiv.setImageDrawable(getResources().getDrawable(suitcaseSong.getImageid()));
        titletv.setText(suitcaseSong.getTitle());
        Uri uri=Uri.parse(suitcaseSong.getApath());
        mp.pause();
        mp.reset();
        playpausebtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_play_arrow_black_24dp));
        try {
            if (countDownTimer!=null) {
                countDownTimer.cancel();
            }
            mp.setDataSource(MainActivity.this,uri);
            mp.prepare();
            seekBar.setMax(mp.getDuration());
            seekBar.setProgress(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        showmediaplayer=AnimationUtils.loadAnimation(MainActivity.this,R.anim.show_media_player);
        cardView.clearAnimation();
        cardView.setAnimation(showmediaplayer);
        cardView.getAnimation().start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mp.isPlaying())
            mp.pause();

        mp.release();
    }
}
