package org.workersdefense.httpwww.proyectodefensalaboral;

import android.os.Bundle;

/**
 * Created by dhernandez on 3/22/16.
 */
public class Results extends CustomWindow {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.results);

        this.title.setText("News");
        //this.icon.setImageResource(R.drawable.menu_news);
    }
}