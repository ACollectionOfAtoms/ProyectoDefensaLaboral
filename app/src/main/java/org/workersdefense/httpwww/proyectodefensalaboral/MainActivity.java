package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


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
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Results.class);
                startActivity(intent);
            }
        });

        Button b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Results.class);
                startActivity(intent);
                waitForResults();
            }
        });
    }

    public void waitForResults() {
        TextView resultTextView = (TextView) findViewById(R.id.result_list);
        while (resultTextView == null) {
            System.out.println("Waiting");
        }
    }
    public void lookupEmployerName(View view) {
        EditText employerEditText = (EditText) findViewById(R.id.emp_name);
        String employer_name = employerEditText.getText().toString();

        if ( employer_name != null && !employer_name.isEmpty()) {
            String urlString = apiURL + "employer/name?q=" + employer_name;
            new CallAPI().execute(urlString);
        }
    }

    public void lookupEmployerPostal(View view) {
        EditText employerEditText = (EditText) findViewById(R.id.zipcode);
        String employer_postal = employerEditText.getText().toString();

        if (employer_postal != null && !employer_postal.isEmpty()) {
            String urlString = apiURL + "employer/postal?q=" + employer_postal;
            new CallAPI().execute(urlString);
        }
    }

    public class Wrapper {
        public TextView resultsTextView;
        public String resultsToDisplay;
    }

    private class CallAPI extends AsyncTask<String, String, Wrapper> {

        @Override
        protected Wrapper doInBackground(String... params) {

        String urlString = params[0]; // URL to call
        InputStream in;
        Wrapper w = new Wrapper();
        w.resultsTextView = (TextView) findViewById(R.id.result_list);
        // HTTP Get
        try {

            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return w;
            }
            w.resultsToDisplay = convertStreamToString(in);
            return w;
        }

        protected void onPostExecute(Wrapper w) {
            setData(w.resultsToDisplay, w.resultsTextView);
        }
    }

    private void setData(String data, TextView mTextView) {
        mTextView.setText(data);
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
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