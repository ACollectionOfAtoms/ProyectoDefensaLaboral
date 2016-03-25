package org.workersdefense.httpwww.proyectodefensalaboral;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dhernandez on 3/22/16.
 */
public class Results extends CustomWindow {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        this.title.setText("Results");
        //this.icon.setImageResource(R.drawable.menu_news);
    }

    public class CallAPI extends AsyncTask<String, String, String> {

        public class Wrapper {
            TextView resultsTextView;
            String resultsToDisplay;
        }

        @Override
        protected String doInBackground(String... params) {

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
        private void setData(String data, TextView mTextView) {
            mTextView.setText(data);
        }

    }
}