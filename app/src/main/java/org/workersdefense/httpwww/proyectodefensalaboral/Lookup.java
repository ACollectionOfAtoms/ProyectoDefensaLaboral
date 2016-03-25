***REMOVED***

import android.app.Activity;
***REMOVED***
import android.widget.EditText;

***REMOVED***
 * Created by dhernandez on 3/24/16.
***REMOVED***
public class Lookup extends Activity {
    public static final String apiURL = "https://0h0zitnt47.execute-api.us-west-2.amazonaws.com/prod/";

    public boolean employerName(View view) {
        EditText employerEditText = (EditText) findViewById(R.id.emp_name);
        String employer_name = employerEditText.getText().toString();

        if ( employer_name != null && !employer_name.isEmpty()) {
            String urlString = apiURL + "employer/name?q=" + employer_name;
            new Results.CallAPI().execute(urlString);
    ***REMOVED***
        return true;
***REMOVED***
    public void employerPostal(View view) {
        EditText employerEditText = (EditText) findViewById(R.id.zipcode);
        String employer_postal = employerEditText.getText().toString();

        if (employer_postal != null && !employer_postal.isEmpty()) {
            String urlString = apiURL + "employer/postal?q=" + employer_postal;
            new Results.CallAPI().execute(urlString);
    ***REMOVED***
***REMOVED***
***REMOVED***