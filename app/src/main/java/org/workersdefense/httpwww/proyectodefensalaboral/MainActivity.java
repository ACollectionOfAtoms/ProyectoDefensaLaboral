***REMOVED***

***REMOVED***
import android.net.Uri;
***REMOVED***
***REMOVED***
***REMOVED***
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends CustomWindow {
    Button mButton;
    EditText mEmpZipEditText;
    EditText mEmpNameEditText;
    ***REMOVED***
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
    ***REMOVED***
    private GoogleApiClient client;

***REMOVED***
***REMOVED***
***REMOVED***
        this.title.setText("Know Your Employer");
        this.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 26);

        mButton = (Button) findViewById(R.id.b1);
        mEmpNameEditText = (EditText) findViewById(R.id.emp_name);
        mEmpNameEditText.setHint("Type Employer's/Company's Name");
        mEmpZipEditText = (EditText) findViewById(R.id.zipcode);
        mEmpZipEditText.setHint("Type Employer's Zipcode");

        mButton.setOnClickListener(new View.OnClickListener() {
        ***REMOVED***
            public void onClick(View v) {
                String searchName = mEmpNameEditText.getText().toString();
                String searchZip = mEmpZipEditText.getText().toString();
                if (searchName.matches("") && searchZip.matches("")) {
                    makeToast("Please enter a zipcode or employer name.");
                    return;
            ***REMOVED***

                Intent intent = new Intent();
                intent.putExtra("name", searchName);
                intent.putExtra("zip", searchZip);
                intent.setClass(MainActivity.this, Results.class);
                startActivity(intent);
        ***REMOVED***
    ***REMOVED***);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
***REMOVED***

    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
***REMOVED***

***REMOVED***
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://org.workersdefense.httpwww.proyectodefensalaboral/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
***REMOVED***

***REMOVED***
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://org.workersdefense.httpwww.proyectodefensalaboral/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
***REMOVED***
***REMOVED***
