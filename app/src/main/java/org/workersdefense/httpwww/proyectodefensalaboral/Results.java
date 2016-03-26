package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.algolia.search.saas.APIClient;
import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.algolia.search.saas.listeners.SearchListener;

import org.json.JSONObject;

/**
 * Created by dhernandez on 3/22/16.
 */

public class Results extends CustomWindow implements SearchListener {

    private static final APIClient client = new APIClient("R7X1GJKUPR", "fe2deb45679649d8e57466fe088f6d46");
    private static final Index index = client.initIndex("WDPEmployers");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        this.title.setText("Search Results");

        Intent intent = getIntent();
        search(intent.getStringExtra("name"));
        //this.icon.setImageResource(R.drawable.menu_news);
    }

    public void search(String q) {
        index.searchASync(new Query(q), this);
    }

    @Override
    public void searchResult(Index index, Query query, JSONObject results) {
        // Log.d("results", results.toString());
        TextView resultList = (TextView) findViewById(R.id.result_list);
        resultList.setText(results.toString());
    }

    @Override
    public void searchError(Index index, Query query, AlgoliaException e) {
        Log.d("error", e.getMessage());
    }
}