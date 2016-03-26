package org.workersdefense.httpwww.proyectodefensalaboral;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dhernandez on 3/22/16.
 */

public class Results extends CustomWindow {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        this.title.setText("Results");
        //this.icon.setImageResource(R.drawable.menu_news);
    }

}