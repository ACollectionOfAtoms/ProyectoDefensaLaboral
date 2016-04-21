package org.workersdefense.httpwww.proyectodefensalaboral;

import android.os.Bundle;

/**
 * Created by dhernandez on 4/3/16.
 */
public class Help extends CustomWindow {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        String title = getResources().getString(R.string.help);
        this.title.setText(title);
    }
}
