***REMOVED***

import android.app.Activity;
***REMOVED***
import android.view.Window;
import android.widget.ImageView;
***REMOVED***

***REMOVED***
 * Created by dhernandez on 3/22/16.
***REMOVED***

public class CustomWindow extends Activity {
    protected TextView title;
    protected ImageView icon;

***REMOVED***
***REMOVED***
***REMOVED***

        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);

        setContentView(R.layout.content_main);

        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.window_title);

        title = (TextView) findViewById(R.id.title);
        icon  = (ImageView) findViewById(R.id.icon);
***REMOVED***
***REMOVED***