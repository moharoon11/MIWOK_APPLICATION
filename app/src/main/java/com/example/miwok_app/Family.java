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


public class Family extends Fragment {
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




    MediaPlayer.OnCompletionListener mediaCompletionListenerFamily = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    View familyView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        audioManager1 = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);



        familyView= inflater.inflate(R.layout.fragment_family, container, false);
        final ArrayList<Words> familyMembers = new ArrayList<>();
        familyMembers.add(new Words("Father","Apa",R.drawable.family_father,R.raw.audio_family_father));
        familyMembers.add(new Words("Mother","Ata",R.drawable.family_mother,R.raw.audio_family_mother));
        familyMembers.add(new Words("Son","Angsi",R.drawable.family_son,R.raw.audio_family_son));
        familyMembers.add(new Words("Daughter","Tune",R.drawable.family_daughter,R.raw.audio_family_daughter));
        familyMembers.add(new Words("Old Brother","Taachi",R.drawable.family_older_brother,R.raw.audio_family_older_brother));
        familyMembers.add(new Words("Younger Brother","Chaliti",R.drawable.family_younger_brother,R.raw.audio_family_younger_brother));
        familyMembers.add(new Words("Older Sister","Tete",R.drawable.family_older_sister,R.raw.audio_family_older_sister));
        familyMembers.add(new Words("Younger Sister","Kolliti",R.drawable.family_younger_sister,R.raw.audio_family_younger_sister));
        familyMembers.add(new Words("Grand Mother","Ama",R.drawable.family_grandmother,R.raw.audio_family_grandmother));
        familyMembers.add(new Words("Grand Father","Paapa",R.drawable.family_grandfather,R.raw.audio_family_grandfather));


        MyAdapter1 familyAdapter = new MyAdapter1(getActivity(),R.layout.my_list_item1,familyMembers,R.color.category_family);

        ListView familyList = (ListView) familyView.findViewById(R.id.family_list_view);

        familyList.setAdapter(familyAdapter);


        familyList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){
                releaseMediaPlayer();
                Words currentAudio = familyMembers.get(position);
                int result = audioManager1.requestAudioFocus(afchangeListener1,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    media = MediaPlayer.create(getActivity(), currentAudio.getAudio());
                    media.start();
                }
                media.setOnCompletionListener(mediaCompletionListenerFamily);

            }
        });





        return familyView;

    }


    public void releaseMediaPlayer(){
        if(media != null){
            media.release();
            media = null;
            audioManager1.abandonAudioFocus(afchangeListener1);
        }
    }
}
