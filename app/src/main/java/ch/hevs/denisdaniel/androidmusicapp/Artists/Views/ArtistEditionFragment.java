package ch.hevs.denisdaniel.androidmusicapp.Artists.Views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ch.hevs.denisdaniel.androidmusicapp.Albums.Albums.Album;
import ch.hevs.denisdaniel.androidmusicapp.Albums.Albums.Views.AlbumEditionFragment;
import ch.hevs.denisdaniel.androidmusicapp.AppDatabase;
import ch.hevs.denisdaniel.androidmusicapp.Artists.Artist;
import ch.hevs.denisdaniel.androidmusicapp.R;

/**
 * Created by Denis Woeffray on 19.11.2017.
 */

public class ArtistEditionFragment extends Fragment {
    private int albumId;
    private Artist artist;
    private AppDatabase db;
    private String newAlbumTitle;
    public void setArtist(Artist artist){
        this.artist = artist;
    }

    public static ArtistEditionFragment newInstance(Artist artist) {
        ArtistEditionFragment fragment = new ArtistEditionFragment();
        Bundle args = new Bundle();
        if(artist!=null)
            args.putLong("ARITST_ID", artist.getUid());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artist_edit, container, false);
        EditText editArtistName = view.findViewById(R.id.editArtistName);
        editArtistName.setText(artist.getName());
        EditText editArtistDescription = view.findViewById(R.id.editArtistDescription);
        editArtistDescription.setText(artist.getDescription());
        EditText editArtistId = view.findViewById(R.id.editArtistId);
        editArtistId.setText(artist.getUid().toString());
        return view;
    }
}