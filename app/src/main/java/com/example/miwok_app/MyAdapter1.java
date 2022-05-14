package com.example.miwok_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.miwok_app.R;

import java.util.ArrayList;

public class MyAdapter1 extends ArrayAdapter<Words> {
    int mColor;

    public MyAdapter1(@NonNull Context context, int resource, ArrayList<Words> arr,int color) {
        super(context, resource,arr);
        mColor=color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.my_list_item1,parent,false);
        }

        Words currentWord = getItem(position);


        TextView miwokText = (TextView) listItemView.findViewById(R.id.Miwok_text);
        miwokText.setText(currentWord.getMiwokWord());

        TextView englishText = (TextView) listItemView.findViewById(R.id.English_text);
        englishText.setText(currentWord.getEnglishWord());


        ImageView imageCount = (ImageView) listItemView.findViewById(R.id.image1_count);

        if(currentWord.hasImage()){
            imageCount.setImageResource(currentWord.getCounterImage());
        }else{
            imageCount.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_Container);
        int color = ContextCompat.getColor(getContext(),mColor);

        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
