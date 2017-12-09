package ch.hevs.denisdaniel.androidmusicapp.Artists.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.amigold.fundapter.BindDictionary;
import com.amigold.fundapter.FunDapter;
import com.amigold.fundapter.extractors.StringExtractor;

import java.util.ArrayList;

import ch.hevs.denisdaniel.androidmusicapp.Artists.Artist;
import ch.hevs.denisdaniel.androidmusicapp.R;

/**
 * Created by Denis Woeffray on 30.10.2017.
 */

public class ArtistsFragment extends Fragment {
   // private AppDatabase db;
    private ArrayList<Artist> data = null;
    private Artist selectedArtist ;

    void deleteArtist(int id)
    {
        //Replace
    //    db = Room.databaseBuilder(this.getActivity(), AppDatabase.class, AppDatabase.DB_NAME).build();
     //   new ArtistTask(db, "delete",id).execute();
        changeFragment(new ArtistsFragment());
    }

    public void changeFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(R.id.content_main, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.artists_list, container, false);
       // db = Room.databaseBuilder(this.getActivity(), AppDatabase.class, AppDatabase.DB_NAME).build();
        ArrayList<Artist> data = null;


        //TODO replace
        /*
        try {
            data = (ArrayList) new ArtistTask(db, "getAll", 0).execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        */

        BindDictionary<Artist> dictionary = new BindDictionary<>();

        dictionary.addStringField(R.id.textViewName, new StringExtractor<Artist>() {
            @Override
            public String getStringValue(Artist artist, int position) {
                return artist.getName();
            }
        });
        dictionary.addStringField(R.id.textViewId, new StringExtractor<Artist>() {
            @Override
            public String getStringValue(Artist artist, int position) {
                return String.valueOf(artist.getUid());
            }
        });

        FunDapter adapter = new FunDapter(ArtistsFragment.this.getActivity(), (ArrayList<Artist>) data, R.layout.artists_list_item, dictionary);
        ListView artist_listview = (ListView) view.findViewById(R.id.artist_listview);
        artist_listview.setAdapter(adapter);


        artist_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int pos, long id) {

                TextView editTextname = (TextView) v.findViewById(R.id.textViewId);

                final int ArtistId = Integer.parseInt(editTextname.getText().toString());

                //TODO Replace
                /*
                try {
                    selectedArtist = (Artist) new ArtistTask(db, "get", ArtistId).execute().get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                */

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(R.layout.artists_list_popup);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();

                Button deleteButton = alertDialog.findViewById(R.id.deleteButton);
                Button editButton = alertDialog.findViewById(R.id.editButton);

                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteArtist(ArtistId);
                        alertDialog.hide();
                    }
                });

                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                editArtist(selectedArtist);
                                alertDialog.hide();
                            }

                    });

                return true;
            };
        });
            return view;
        }

    public void editArtist(Artist artist)
    {
        ArtistEditionFragment fragment = ArtistEditionFragment.newInstance(artist);

        // save the selectedalbum in main
        //TODO replace

        //((MainActivity) getActivity()).setDataObject(selectedArtist);
        fragment.setArtist(artist);
        changeFragment(fragment);
    }
}
