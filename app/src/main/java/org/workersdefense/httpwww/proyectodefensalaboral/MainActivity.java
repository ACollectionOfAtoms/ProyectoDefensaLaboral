***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
import android.widget.Button;

public class MainActivity extends CustomWindow {
***REMOVED***
***REMOVED***
***REMOVED***
        this.title.setText("Know Your Employer");
        Button b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
        ***REMOVED***
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Results.class);

                startActivity(intent);
        ***REMOVED***
    ***REMOVED***);

        Button b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
        ***REMOVED***
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Details.class);

                startActivity(intent);
        ***REMOVED***
    ***REMOVED***);
***REMOVED***
***REMOVED***