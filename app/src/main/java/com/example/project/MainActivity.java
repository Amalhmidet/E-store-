package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.project.Classes.GridAdapter;
import com.example.project.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String[] names = {"pc portable","souries","referoidisseurs","cles","impriments"};
        String[] refs = {"pc","sourie","ref","cle","impriment"};
        int[] images = {R.drawable.laptop,R.drawable.mouse,R.drawable.ref2,
                        R.drawable.disck,R.drawable.imprim};

        GridAdapter adapter = new GridAdapter(MainActivity.this,names,images);
        binding.monGrid.setAdapter(adapter);

        binding.monGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), Liste_Items.class);
                intent.putExtra("ITEM_ID", refs[i]);
                startActivity(intent);
            }
        });

    }
}