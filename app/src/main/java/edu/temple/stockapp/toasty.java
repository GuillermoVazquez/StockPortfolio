package edu.temple.stockapp;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by guillermo on 12/6/17.
 */

public class toasty extends Dialog {
    Activity activity;
    Dialog dialog;
    Button add;
    Button cancel;
    StockListFragement stockListFragement;
    public toasty(Activity activity){
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Stock TCKR");
        setContentView(R.layout.toast);
    }

}
