package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.algolia.search.saas.APIClient;
import com.algolia.search.saas.AlgoliaException;
import com.algolia.search.saas.Index;
import com.algolia.search.saas.Query;
import com.algolia.search.saas.listeners.SearchListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by dhernandez on 3/22/16.
 */

public class Results extends CustomWindow implements SearchListener {

    private static final APIClient client = new APIClient("R7X1GJKUPR", "fe2deb45679649d8e57466fe088f6d46");
    private static final Index index = client.initIndex("WDPEmployers");
    ListView list;
    TextView name;
    TextView address;
    TextView violations;
    ArrayList<HashMap<String, String>> empList = new ArrayList<HashMap<String, String>>();

    //Json keys
    private static final String HITS = "hits";
    private static final String NAME = "Name";
    private static final String POSTAL = "PostalCode";
    private static final String ADDRESS = "Address";

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
        try {
            JSONArray res = results.getJSONArray(HITS);
            for (int i = 0; i < res.length(); i++) {
                JSONObject row = res.getJSONObject(i);

                //Store JSON items
                String name = row.getString(NAME);
                String postal = row.getString(POSTAL);
                String address = row.getString(ADDRESS);

                // Construct HashMap
                HashMap<String, String> map = new HashMap<String,String>();
                map.put(NAME, name);
                map.put(POSTAL, postal);
                map.put(ADDRESS, address);

                empList.add(map);
                list = (ListView) findViewById(R.id.results_list);

                ListAdapter adapter = new SimpleAdapter(Results.this, empList,
                        R.layout.results_list,
                        new String[] {NAME, POSTAL, ADDRESS }, new int[] {
                        R.id.results_emp_name, R.id.results_emp_postal, R.id.results_emp_address});
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Toast.makeText(Results.this, "You Clicked at " + empList.get(+position).get("name"), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        empList = new ArrayList<HashMap<String, String>>();
    }

    @Override
    public void searchError(Index index, Query query, AlgoliaException e) {
        Log.d("error", e.getMessage());
    }
}