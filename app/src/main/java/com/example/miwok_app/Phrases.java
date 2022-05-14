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

public class Phrases extends Fragment {
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



    MediaPlayer.OnCompletionListener mediaCompletionListenerPhrases = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    View phrasesView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        audioManager1 = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        phrasesView = inflater.inflate(R.layout.fragment_phrases, container, false);


        final ArrayList<Words> myPhrases = new ArrayList<>();
        myPhrases.add(new Words("Where are you going?","Minto Wuksus",
                R.raw.audio_phrase_where_are_you_going));
        myPhrases.add(new Words("What is your name","tiya Oyaase'na",
                R.raw.audio_phrase_what_is_your_name));
        myPhrases.add(new Words("My name is...","Oyaaset",
                R.raw.audio_phrase_my_name_is));
        myPhrases.add(new Words("How are you feeling?","Michaksea",
                R.raw.audio_phrase_how_are_you_feeling));
        myPhrases.add(new Words("I,m feeling good","Kuchi achit"
                ,R.raw.audio_phrase_im_feeling_good));
        myPhrases.add(new Words("Are you coming?","Aanas'aa",
                R.raw.audio_phrase_are_you_coming));
        myPhrases.add(new Words("Yes,I'm coming","Haa,aanam",
                R.raw.audio_phrase_yes_im_coming));
        myPhrases.add(new Words("Lets'go","Yoowutis",
                R.raw.audio_phrase_lets_go));
        myPhrases.add(new Words("Come here","anni,nem",
                R.raw.audio_phrase_come_here));



        MyAdapter1 phrasesAdapter = new MyAdapter1(getActivity(),R.layout.my_list_item1,
                myPhrases,R.color.category_phrases);

        ListView phrasesList = (ListView) phrasesView.findViewById(R.id.phrases_list_view);

        phrasesList.setAdapter(phrasesAdapter);


        phrasesList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                releaseMediaPlayer();
                Words currentAudio = myPhrases.get(position);

                int result = audioManager1.requestAudioFocus(afchangeListener1,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(getActivity(), currentAudio.getAudio());
                    media.start();
                }


                media.setOnCompletionListener(mediaCompletionListenerPhrases);
            }
        });





        return phrasesView;
    }

    public void releaseMediaPlayer(){
        if(media != null){
            media.release();
            media = null;
            audioManager1.abandonAudioFocus(afchangeListener1);
        }
    }
}