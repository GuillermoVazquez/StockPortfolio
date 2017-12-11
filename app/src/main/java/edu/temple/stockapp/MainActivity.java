package edu.temple.stockapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        StockListFragement stockListFragement = new StockListFragement();
        InfoFragment infoFragment = new InfoFragment();


        //lets set up our floating action button
        //FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        //toast instantiation



        final FragmentManager fragmentManager = getFragmentManager();

        //lets create our support bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        toolbar.setTitle("Stocks");
        setSupportActionBar(toolbar);

        //now lets figure out what device configs are being run
        //device in landscape mode
        ConstraintLayout land = (ConstraintLayout) findViewById(R.id.land);
        //device is large
        ConstraintLayout large = (ConstraintLayout) findViewById(R.id.large);

        if(land != null){
            //the device is in landscape mode
            //we will use the land main activity to attach our fragments
            //lets set up our fragment manager
            stockListFragement.setConfiguration(1);
            fragmentManager.beginTransaction().add(R.id.stockListLand,stockListFragement).commit();
            getFragmentManager().beginTransaction().add(R.id.stockPickLand,infoFragment).commit();

        }
        else if(large != null){
            //we have a large device
            //we will use the large main activity to attach our fragments
            stockListFragement.setConfiguration(2);
            fragmentManager.beginTransaction().add(R.id.stockListLarge,stockListFragement).commit();
            getFragmentManager().beginTransaction().add(R.id.stockPickLarge,infoFragment).commit();


        }
        else {
            //we have regular configurations
            //we will use main activity to attach our fragments
            stockListFragement.setConfiguration(0);
            getFragmentManager().beginTransaction().add(R.id.stockList,stockListFragement).commit();
        }

    }

}
