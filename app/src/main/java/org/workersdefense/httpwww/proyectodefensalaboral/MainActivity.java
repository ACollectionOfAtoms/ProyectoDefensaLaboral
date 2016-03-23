package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends CustomWindow {
    public static final String apiURL = "https://0h0zitnt47.execute-api.us-west-2.amazonaws.com/prod/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.title.setText("Know Your Employer");
        Button b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lookupEmployer(v);
//                Intent intent = new Intent();
//                intent.setClass(MainActivity.this, Results.class);
//
//                startActivity(intent);
            }
        });

        Button b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Details.class);

                startActivity(intent);
            }
        });
    }

    public void lookupEmployer(View view) {
        EditText employerEditText = (EditText) findViewById(R.id.emp_name);
        String employer_name = employerEditText.getText().toString();

        if ( employer_name != null && !employer_name.isEmpty()) {
            String urlString = apiURL + "employer/name?q=" + employer_name;
            new CallAPI().execute(urlString);
        }
    }

    private class CallAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String urlString = params[0]; // URL to call
            String resultToDisplay = "";
            searchTextResult result = null;
            InputStream in = null;

            // HTTP Get
            try {

                URL url = new URL(urlString);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                in = new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
            System.out.println(convertStreamToString(in));
            return resultToDisplay;
        }

        protected void onPostExecute(String result) {

        }
    }

    private class searchTextResult {
        public String statusNbr;
        public String hygieneResult;
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}