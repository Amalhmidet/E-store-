package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.project.Classes.Article;
import com.example.project.Classes.ListAdapter;
import com.example.project.databinding.ActivityListeItemsBinding;
import com.example.project.databinding.ActivityMainBinding;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class Liste_Items extends AppCompatActivity implements TextWatcher {

    List<Article> list;
    List<Article> listTwo;

    ActivityListeItemsBinding binding;
    AlertDialog.Builder builder;
    String sessionId;
    Dialog ajout,apropo;
    Spinner spn;
    Button selectimage,ajoutelem;
    String imagedata;
    EditText label,desc,prix;
    private static final int PICK_PHOTO_FOR_AVATAR = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_items);

        binding = ActivityListeItemsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

         list=new ArrayList<Article>();
         listTwo=new ArrayList<Article>();

         //add dialog
         ajout = new Dialog(this);
         apropo = new Dialog(this);
         ajout.setContentView(R.layout.ajout_diag);
         apropo.setContentView(R.layout.a_propos);


         spn = ajout.findViewById(R.id.spn);
         selectimage = ajout.findViewById(R.id.imageselctor);
         ajoutelem = ajout.findViewById(R.id.add);
         label =ajout.findViewById(R.id.labelle);
         desc =ajout.findViewById(R.id.dec);
         prix =ajout.findViewById(R.id.prix);
         selectimage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 pickImage();
             }
         });
         ajoutelem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String ref = spn.getSelectedItem().toString();
                 String lib = label.getText().toString();
                 String dc = desc.getText().toString();
                 float prx = Float.parseFloat("0"+prix.getText().toString());
                 if(!ref.isEmpty() && !lib.isEmpty() && !dc.isEmpty() &&
                         imagedata !=null){
                     listTwo.add(new Article(ref,lib,dc,prx,0,imagedata));
                     label.setText("");
                     desc.setText("");
                     prix.setText("");
                     imagedata ="";
                     spn.setSelection(0);
                     ajout.dismiss();
                 }else{
                     Toast.makeText(getApplicationContext(),"Empty Fields !",Toast.LENGTH_SHORT).show();
                 }

                 PrepareLis(listTwo);

             }
         });
        sessionId = getIntent().getStringExtra("ITEM_ID");
         //spinner adapter


        //the dialog init
        builder = new AlertDialog.Builder(this);
        //Laptops
        list.add(new Article("pc", "DELL", "DELL LAPTOP", 2200, R.drawable.laptop,null));
        list.add(new Article("pc", "MSI", "MSI LAPTOP", 4200, R.drawable.laptop3,null));
        list.add(new Article("pc", "ASUS", "ASUS LAPTOP", 2200, R.drawable.laptop1,null));
        list.add(new Article("pc", "HP", "HP LAPTOP", 1200, R.drawable.laptop2,null));
        list.add(new Article("pc", "LENOVO", "LENOVO LAPTOP", 2600, R.drawable.laptop4,null));
         //Mouses
        list.add(new Article("sourie", "Optique JEDEL", "Sourie Optique JEDEL 230 Plus USB", 10, R.drawable.m1,null));
        list.add(new Article("sourie", "Optique USB HAVI", "sourie Optique USB HAVIT MS851", 15, R.drawable.m2,null));
        list.add(new Article("sourie", "Optique OMEGA", "sourie Optique OMEGA OM-06V USB Filaire", 26, R.drawable.m5,null));
        list.add(new Article("sourie", "Sans Fil", "sourie Sans Fil X903", 18, R.drawable.m4,null));
        list.add(new Article("sourie", "Gamer ASUS ROG", "sourie Gamer ASUS ROG Gladius III Sans Fil", 30, R.drawable.m3,null));
        //chargeur
        list.add(new Article("ref", "Refroidisseur ERGOSTAND", "Refroidisseur ERGOSTAND Pour Pc Portable", 22, R.drawable.ref,null));
        list.add(new Article("ref", "Refroidisseur ADVANCE", "Refroidisseur ADVANCE Pour Pc Portable 13-18", 42, R.drawable.ref1,null));
        list.add(new Article("ref", "Refroidisseur SPIRIT", "Refroidisseur SPIRIT OF GAMER Airblade 500 Pour PC Portable 17", 30, R.drawable.ref2,null));
        list.add(new Article("ref", "Refroidisseur NGS GCX-400", "Refroidisseur NGS GCX-400 Avec Écran LCD Pour PC Portable 17", 100, R.drawable.ref3,null));
        list.add(new Article("ref", "REFROIDISSEUR GAMER", "REFROIDISSEUR GAMER WHITE SHARK GCP-33 ICE MASTER 15,6", 200, R.drawable.ref4,null));
        //Cles
        list.add(new Article("cle", "Clé USB TEAM", "Clé USB TEAM GROUP C171 8Go USB 2.0", 7, R.drawable.cle,null));
        list.add(new Article("cle", "Clé USB ADATA", "Clé USB ADATA C008 16Go USB 2.0", 9, R.drawable.cl1,null));
        list.add(new Article("cle", "Clé USB HIKVISION", "Clé USB HIKVISION M200R 16Go USB 2.0", 14, R.drawable.cl2,null));
        list.add(new Article("cle", "Clé USB KODAK", "Clé USB KODAK K102 16Go USB 2.0", 32, R.drawable.cl3,null));
        list.add(new Article("cle", "CLÉ USB HP", "CLÉ USB HP V150W 64GO USB 2.0 ", 40, R.drawable.cl4,null));
        //Printers
        list.add(new Article("impriment", "BROTHER", "IMPRIMANTE MATRICIELLE BROTHER", 500, R.drawable.imprim,null));
        list.add(new Article("impriment", "CANON", "IMPRIMANTE MATRICIELLE CANON ", 470, R.drawable.pr,null));
        list.add(new Article("impriment", "HP", "IMPRIMANTE MATRICIELLE HP ", 1200, R.drawable.pr1,null));
        list.add(new Article("impriment", "SAMSUNG", "IMPRIMANTE MATRICIELLE SAMSUNG ", 700, R.drawable.pr2,null));
        list.add(new Article("impriment", "EPSON", "IMPRIMANTE MATRICIELLE EPSON LQ-350 (C11CC25001)", 620, R.drawable.pr3,null));

        //Intent Received Value


        listTwo = getByReference(sessionId);

        if(sessionId.equals("") || sessionId == null){
            Toast.makeText(getApplicationContext(),"Empty Value",Toast.LENGTH_LONG).show();
        }else{
            String[] refs = {sessionId};
            ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,refs);
            aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            //Setting the ArrayAdapter data on the Spinner
            spn.setAdapter(aa);
            PrepareLis(listTwo);
        }
        binding.monList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Liste_Items.this,"Selected "+getByReference(sessionId).get(i).getLibelle(),Toast.LENGTH_LONG).show();
            }
        });


        ArrayAdapter<String> adapterlst = new ArrayAdapter<>(Liste_Items.this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,getlabels(sessionId));

        binding.autoText.setAdapter(adapterlst);

        binding.autoText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //OrderList(getlabels(sessionId));
            }
        });
        binding.autoText.addTextChangedListener(this);


        binding.monList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDiag(listTwo.get(i));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ajout:
            addrticle();
                return true;
            case R.id.rech:
               // startSettings();
                apropo.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addrticle() {
        ajout.show();

    }


    private void PrepareLis(List<Article> listTwo) {
        ListAdapter adapter = new ListAdapter(Liste_Items.this, (ArrayList<Article>) listTwo);
        binding.monList.setAdapter(adapter);
    }

    private void showDiag(Article article) {

        builder.setMessage(article.getDescription())
                .setCancelable(false)
                .setPositiveButton("Supprimer Article", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        removearticle(article);
                       // finish();
                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Description");
        alert.show();


    }

    private void removearticle(Article article) {

        Iterator<Article> it = listTwo.iterator();
        while (it.hasNext()){
            Article ar = it.next();
            if(ar.getLibelle().equals(article.getLibelle())){
                it.remove();
            }
        }
        PrepareLis(listTwo);
    }

    private ArrayList<String> getlabels(String sessionId) {
        ArrayList<String> Search= new ArrayList<>();
        for (Article art:listTwo) {
            if(art.getReference().equals(sessionId)){
                Search.add(art.getLibelle());
                Search.add(String.valueOf(art.getPrix()));
            }
        }
        return (ArrayList<String>) Search;
    }

    private ArrayList<Article> getByReference(String sessionId) {
        ArrayList<Article> listByref =new ArrayList<Article>() ;
        for (Article art:list) {
            if(art.getReference().equals(sessionId)){
                listByref.add(art);
            }
    }
        return listByref;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        ArrayList<Article> searchlist = new ArrayList<>();
        Iterator<Article> it = listTwo.iterator();
        while (it.hasNext()){
            Article ar = it.next();
            if(ar.getLibelle().toUpperCase(Locale.ROOT).contains(charSequence.toString().toUpperCase(Locale.ROOT))){
                searchlist.add(ar);
            }
        }
        PrepareLis(searchlist);

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    public void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_FOR_AVATAR && resultCode == Activity.RESULT_OK) {
            imagedata =String.valueOf(data.getData());
             //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
        }
    }


}