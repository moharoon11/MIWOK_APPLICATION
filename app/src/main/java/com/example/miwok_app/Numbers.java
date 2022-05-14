package com.example.miwok_app;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class Numbers extends Fragment {

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



    MediaPlayer.OnCompletionListener mediaCompletionListenerNumbers = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };



    View numbersView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        audioManager1 = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        numbersView = inflater.inflate(R.layout.fragment_numbers, container, false);
        final ArrayList<Words> myNumbers = new ArrayList<>();
        myNumbers.add(new Words("One","Lutti",
                R.drawable.number_one,R.raw.audio_number_one));
        myNumbers.add(new Words("Two","Otiiko",
                R.drawable.number_two,R.raw.audio_number_two));
        myNumbers.add(new Words("Three","Tolookosu"
                ,R.drawable.number_three,R.raw.audio_number_three));
        myNumbers.add(new Words("Four","Oyissa",
                R.drawable.number_four,R.raw.audio_number_four));
        myNumbers.add(new Words("Five","Massaka",
                R.drawable.number_five,R.raw.audio_number_five));
        myNumbers.add(new Words("Six","Tammaokka",
                R.drawable.number_six,R.raw.audio_number_six));
        myNumbers.add(new Words("Seven","KenneKakku",
                R.drawable.number_seven,R.raw.audio_number_seven));
        myNumbers.add(new Words("Eight","Kawinta",
                R.drawable.number_eight,R.raw.audio_number_eight));
        myNumbers.add(new Words("Nine","Wo'e",
                R.drawable.number_nine,R.raw.audio_number_nine));
        myNumbers.add(new Words("Ten","Na'aacha",
                R.drawable.number_ten,R.raw.audio_number_ten));



        MyAdapter1 numbersAdapter = new MyAdapter1(getActivity(),R.layout.my_list_item1,myNumbers,
                R.color.category_number);

        ListView numbersList =(ListView) numbersView.findViewById(R.id.numbers_list_view);

        numbersList.setAdapter(numbersAdapter);

        numbersList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                releaseMediaPlayer();
                Words currentAudio = myNumbers.get(position);
                int result = audioManager1.requestAudioFocus(afchangeListener1,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(getActivity(), currentAudio.getAudio());
                    media.start();
                }


                media.setOnCompletionListener(mediaCompletionListenerNumbers);


            }
        });




        return numbersView;

    }


    public void releaseMediaPlayer(){
        if(media != null){
            media.release();
            media = null;
            audioManager1.abandonAudioFocus(afchangeListener1);
        }
    }


}