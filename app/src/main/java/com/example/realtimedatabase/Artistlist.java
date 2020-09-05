package com.example.realtimedatabase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Artistlist extends ArrayAdapter<Artist> {
    private Activity context;
    private List<Artist>artistList;

    public  Artistlist(Activity context,List<Artist> artistList){
        super(context, R.layout.list,artistList);
        this.context=context;
        this.artistList=artistList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list,null,true);

        TextView textname = listViewItem.findViewById(R.id.textname);
        TextView textgenre = listViewItem.findViewById(R.id.textgenre);
        TextView textid = listViewItem.findViewById(R.id.textid);
        Artist artist = artistList.get(position);

        textname.setText(artist.getArtistName());
        textgenre.setText(artist.artistGenre);
        textid.setText(artist.artistId);

        return listViewItem;

    }
}
