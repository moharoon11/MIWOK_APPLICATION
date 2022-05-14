package com.example.miwok_app;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Colors extends Fragment {

    MediaPlayer media;


    AudioManager audioManager1;

    AudioManager.OnAudioFocusChangeListener afchangeListener1 = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if(i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                media.stop();
            }else if(i == AudioManager.AUDIOFOCUS_GAIN){
                media.start();
            }else if(i == AudioManager.AUDIOFOCUS_LOSS){
                media.stop();
                releaseMediaPlayer();
            }
        }
    };


    MediaPlayer.OnCompletionListener mediaCompletionListenerColors = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    View colorsView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        audioManager1 = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        colorsView= inflater.inflate(R.layout.fragment_colors, container, false);

        final ArrayList<Words> myColors = new ArrayList<>();
        myColors.add(new Words("Red","Wetteti",
                R.drawable.color_red,R.raw.audio_color_red));
        myColors.add(new Words("Green","Chokoki",
                R.drawable.color_green,R.raw.audio_color_green));
        myColors.add(new Words("Brown","Takaakki",
                R.drawable.color_brown,R.raw.audio_color_brown));
        myColors.add(new Words("Grey","Topoppi",
                R.drawable.color_gray,R.raw.audio_color_gray));
        myColors.add(new Words("Black","Kululli",
                R.drawable.color_black,R.raw.audio_color_black));
        myColors.add(new Words("White","Kelelli",
                R.drawable.color_white,R.raw.audio_color_white));
        myColors.add(new Words("Dusty Yellow","Topisa",
                R.drawable.color_dusty_yellow,R.raw.audio_color_dusty_yellow));
        myColors.add(new Words("Mustard Yellow","Chewita",
                R.drawable.color_mustard_yellow,R.raw.audio_color_mustard_yellow));


        MyAdapter1 colorsAdapter = new MyAdapter1(getActivity(),R.layout.my_list_item1,
                myColors,R.color.category_colors);

        ListView colorsList = (ListView) colorsView.findViewById(R.id.colors_list_view);

        colorsList.setAdapter(colorsAdapter);

        colorsList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                releaseMediaPlayer();
                Words currentAudio = myColors.get(position);
                int result = audioManager1.requestAudioFocus(afchangeListener1,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(getActivity(), currentAudio.getAudio());
                    media.start();
                }


                media.setOnCompletionListener(mediaCompletionListenerColors);

            }
        });


        return colorsView;

    }


    public void releaseMediaPlayer(){
        if(media != null){
            media.release();
            media = null;
            audioManager1.abandonAudioFocus(afchangeListener1);

        }
    }
}
