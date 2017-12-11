package edu.temple.stockapp;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.IDNA;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * A simple {@link Fragment} subclass.
 */
public class StockListFragement extends Fragment {
    //lets define and int that will define what configuration the android phone is in
    //0 = normal
    //1 = landscape
    //2 = large
    int configuration;
    View v;
    Array strings;
    ArrayList<String> arrayList = new ArrayList<String>();
    final String urlImgParsh = "https://finance.google.com/finance/getchart?p=5d&q=";

    public StockListFragement() {
        // Required empty public constructor
    }

    public void setConfiguration(int configuration) {
        this.configuration = configuration;
    }

    public static StockListFragement getInstance() {
        StockListFragement stockListFragement = new StockListFragement();
        Bundle bundle = new Bundle();
        stockListFragement.setArguments(bundle);
        return stockListFragement;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final toasty toasty = new toasty(getActivity());

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_stock_list_fragement, container, false);
        //lets first get the mainActivity context
        final Context context = ((MainActivity) getActivity());
        //Resources resources =context.getResources();
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>((MainActivity) getActivity(), android.R.layout.simple_list_item_1, arrayList);
        final GridView listView = (GridView) v.findViewById(R.id.stockListFragment);
        listView.setAdapter(arrayAdapter);
        FloatingActionButton button = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toasty.show();

                Button add = (Button) toasty.findViewById(R.id.addButton);
                Button cancel = (Button) toasty.findViewById(R.id.cancel);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //add to the list
                        EditText input = (EditText) toasty.findViewById(R.id.stockToast);
                        String tckr;
                        tckr = input.getText().toString();
                        arrayAdapter.add(tckr);
                        input.setText(null);
                        toasty.dismiss();

                    }

                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        toasty.dismiss();
                    }

                });
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (configuration == 0) {
                    //regular
                    //action for when item is clicked
                    //get the ticker
                    String ticker = (String) listView.getItemAtPosition(i);
                    InfoFragment infoFragment = new InfoFragment();
                    String full = urlImgParsh + ticker;
                    infoFragment = InfoFragment.getInstance(full,ticker);
                    getFragmentManager().beginTransaction().replace(R.id.stockList,infoFragment).addToBackStack(null).commit();

                } else if (configuration == 1) {
                    //landscape
                    //action for when item is clicked
                    String ticker = (String) listView.getItemAtPosition(i);
                    InfoFragment infoFragment = new InfoFragment();
                    String full = urlImgParsh + ticker;
                    ImageView imageView = (ImageView) getActivity().findViewById(R.id.imageView);
                    TextView textView = (TextView) getActivity().findViewById(R.id.name);
                    textView.setText(ticker);
                    imageView.setImageBitmap(addImage(full));


                } else if (configuration == 2) {
                    //large
                    //action for when item is clicked
                    String ticker = (String) listView.getItemAtPosition(i);
                    InfoFragment infoFragment = new InfoFragment();
                    String full = urlImgParsh + ticker;
                    ImageView imageView = getActivity().findViewById(R.id.imageView);
                    TextView textView = (TextView) getActivity().findViewById(R.id.name);
                    textView.setText(ticker);
                    imageView.setImageBitmap(addImage(full));
                }

            }
        });
        return v;

    }
    public Bitmap addImage(String url){
        try {
            String u = url;
            URL imageUrl = new URL(url);
            try{
                Bitmap bmp = BitmapFactory.decodeStream(imageUrl.openConnection().getInputStream());
                return bmp;
            } catch(IOException ioEx) {
                ioEx.printStackTrace(); // or what ever you want to do with it
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

