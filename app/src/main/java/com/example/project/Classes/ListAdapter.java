package com.example.project.Classes;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<Article> articles;


    LayoutInflater inflater;

    public ListAdapter(Context context, ArrayList<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.custom_list,null);
        }

        ImageView imageView = view.findViewById(R.id.image_list);
        TextView textView = view.findViewById(R.id.text_list);
        TextView textPrix = view.findViewById(R.id.text_prix);
        TextView textref = view.findViewById(R.id.text_ref);

        if(articles.get(i).getImage()!=0){
            imageView.setImageResource(articles.get(i).getImage());
        }else{
        // Bitmap img = articles.get(i).getImagedata();
            imageView.setImageURI(Uri.parse(articles.get(i).getImagedata()));
        }




        textView.setText(articles.get(i).getLibelle());
        textPrix.setText(String.valueOf(articles.get(i).getPrix())+" TND");

        textref.setText(articles.get(i).getReference()+"-"+getRandomString(10));
        return view;
    }
    private static final String ALLOWED_CHARACTERS ="0123456789qwertyuiopasdfghjklzxcvbnm";

    private static String getRandomString(final int sizeOfRandomString)
    {
        final Random random=new Random();
        final StringBuilder sb=new StringBuilder(sizeOfRandomString);
        for(int i=0;i<sizeOfRandomString;++i)
            sb.append(ALLOWED_CHARACTERS.charAt(random.nextInt(ALLOWED_CHARACTERS.length())));
        return sb.toString();
    }
}
