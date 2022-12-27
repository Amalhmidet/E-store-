package com.example.project.Classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.project.R;

public class GridAdapter extends BaseAdapter {

    Context context;
    String[] Names;
    int[] Images;


    LayoutInflater inflater;

    public GridAdapter(Context context, String[] names, int[] images) {
        this.context = context;
        Names = names;
        Images = images;
    }

    @Override
    public int getCount() {
        return Names.length;
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
            view = inflater.inflate(R.layout.grid_item,null);
        }

        ImageView imageView = view.findViewById(R.id.image);
        TextView textView = view.findViewById(R.id.text);

        imageView.setImageResource(Images[i]);
        textView.setText(Names[i]);
        return view;
    }
}
