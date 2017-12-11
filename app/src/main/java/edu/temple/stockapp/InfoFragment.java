package edu.temple.stockapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {
    View v;

    public InfoFragment() {
        // Required empty public constructor
    }

    public static InfoFragment getInstance(String url,String name){
        InfoFragment canvasFragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url",url);
        bundle.putString("name",name);
        canvasFragment.setArguments(bundle);
        return canvasFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_info, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null){
            ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
            TextView textView = (TextView) v.findViewById(R.id.name);
            String imageUrl = bundle.getString("url");
            textView.setText(bundle.getString("name"));
            Bitmap bm = addImage(imageUrl);
            imageView.setImageBitmap(bm);
        }
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

