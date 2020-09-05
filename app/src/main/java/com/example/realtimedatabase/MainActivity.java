package com.example.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editname;
    Spinner spinner;
    Button button;
    DatabaseReference databaseArtists;
    ListView listViewartists;
    List<Artist> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseArtists= FirebaseDatabase.getInstance().getReference("artists");

         editname=findViewById(R.id.editname);
         spinner=findViewById(R.id.spinner);
         button=findViewById(R.id.button);

         listViewartists=findViewById(R.id.listViewartists);
         artistList = new ArrayList<>();

         button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 addArtist();
             }
         });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseArtists.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                artistList.clear();

                for (DataSnapshot artistSnaoshot : dataSnapshot.getChildren()){
                    Artist artist=artistSnaoshot.getValue(Artist.class);
                    artistList.add(artist);
                }
                Artistlist adapter =new Artistlist(MainActivity.this,artistList);
                listViewartists.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private  void  addArtist(){

        String name = editname.getText().toString().trim();
        String genre = spinner.getSelectedItem().toString();

        if (!TextUtils.isEmpty(name)){
           String id= databaseArtists.push().getKey();

           Artist artist =new Artist(id, name, genre);
           databaseArtists.child(id).setValue(artist);
           Toast.makeText(this,"Artists added",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,"enter name",Toast.LENGTH_LONG).show();
        }
    }
}