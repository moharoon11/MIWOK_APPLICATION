package com.example.miwok_app;

public class Words {

    String mEnglishWord;
    String mMiwokWord;
    int mImage1;
    int mAudio;

    static final int NO_IMAGE_PROVIDED = -1;



    public Words(String englsihWord,String miwokWord,int audio) {
        mEnglishWord = englsihWord;
        mMiwokWord =  miwokWord;
        mAudio = audio;
        mImage1 = NO_IMAGE_PROVIDED;
    }










    public Words(String englishWord, String miwokWord,int image1,int audio){
        mEnglishWord = englishWord;
        mMiwokWord = miwokWord;
        mImage1 = image1;
        mAudio = audio;
    }



    public String getEnglishWord(){
        return mEnglishWord;
    }

    public String getMiwokWord(){
        return mMiwokWord;
    }

    public int getCounterImage() {
        return mImage1;
    }

    public int getAudio(){
        return mAudio;
    }

    public boolean hasImage(){

        return mImage1 != NO_IMAGE_PROVIDED;
    }




}
