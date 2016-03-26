package org.workersdefense.httpwww.proyectodefensalaboral;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dhernandez on 3/22/16.
 */

public class CustomWindow extends Activity {
    protected TextView title;
    protected ImageView icon;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.content_main);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);

        title = (TextView) findViewById(R.id.title);
        icon  = (ImageView) findViewById(R.id.icon);
    }
}