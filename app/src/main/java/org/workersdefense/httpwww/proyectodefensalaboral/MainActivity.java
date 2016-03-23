***REMOVED***

***REMOVED***
import android.os.AsyncTask;
***REMOVED***
***REMOVED***
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends CustomWindow {
    public static final String apiURL = "https://0h0zitnt47.execute-api.us-west-2.amazonaws.com/prod/";

***REMOVED***
***REMOVED***
***REMOVED***
        this.title.setText("Know Your Employer");
        Button b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
        ***REMOVED***
            public void onClick(View v) {
                lookupEmployer(v);
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, Results.class);
//
//                startActivity(intent);
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

    public void lookupEmployer(View view) {
        EditText employerEditText = (EditText) findViewById(R.id.emp_name);
        String employer_name = employerEditText.getText().toString();

        if ( employer_name != null && !employer_name.isEmpty()) {
            String urlString = apiURL + "employer/name?q=" + employer_name;
            new CallAPI().execute(urlString);
    ***REMOVED***
***REMOVED***

    private class CallAPI extends AsyncTask<String, String, String> {

    ***REMOVED***
        protected String doInBackground(String... params) {

            String urlString = params[0]; // URL to call
            String resultToDisplay = "";
            searchTextResult result = null;
            InputStream in = null;

            // HTTP Get
    ***REMOVED***

                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());

        ***REMOVED*** catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
        ***REMOVED***
            System.out.println(convertStreamToString(in));
            return resultToDisplay;
    ***REMOVED***

        protected void onPostExecute(String result) {

    ***REMOVED***
***REMOVED***

    private class searchTextResult {
        public String statusNbr;
        public String hygieneResult;
***REMOVED***

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
***REMOVED***
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
        ***REMOVED***
    ***REMOVED*** catch (IOException e) {
***REMOVED***
    ***REMOVED*** finally {
    ***REMOVED***
                is.close();
        ***REMOVED*** catch (IOException e) {
    ***REMOVED***
        ***REMOVED***
    ***REMOVED***
        return sb.toString();
***REMOVED***

***REMOVED***