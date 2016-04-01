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
 * Created by ahernandez on 3/22/16.
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
    private static final String VIOLATIONS = "violations";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);
        this.title.setText("Search Results");

        Intent intent = getIntent();
        search(intent.getStringExtra("name"));
        // this.icon.setImageResource(R.drawable.menu_news);
    }

    public void search(String q) {
        index.searchASync(new Query(q), this);
    }

    @Override
    public void searchResult(Index index, Query query, JSONObject results) {
        list = (ListView) findViewById(R.id.results_list);
        TextView resultCount = new TextView(getApplicationContext());
        try {
            // Modeled after https://www.learn2crack.com/2013/11/listview-from-json-example.html
            JSONArray res = results.getJSONArray(HITS);
            int resCount = results.getJSONArray(HITS).length();
            resultCount.setText(Integer.toString(resCount) + " results found");
            list.addHeaderView(resultCount, "", false);

            for (int i = 0; i < res.length(); i++) {
                JSONObject row = res.getJSONObject(i);

                // Store JSON items
                String name = row.getString(NAME);
                String postal = row.getString(POSTAL);
                String address = row.getString(ADDRESS);
                int violationsCount = 0;
                JSONArray totalViolationsArray = row.getJSONArray(VIOLATIONS);
                for (int j = 0; j < totalViolationsArray.length(); j ++) {
                    violationsCount += totalViolationsArray.getJSONObject(j).getInt("count");
                }
                String totalViolations = Integer.toString(violationsCount) + " total violations";
                String violationData = row.getString(VIOLATIONS);

                // Construct HashMap
                HashMap<String, String> map = new HashMap<String,String>();
                map.put(NAME, name);
                map.put(ADDRESS, address);
                map.put(POSTAL, postal);
                map.put(VIOLATIONS, totalViolations);
                map.put("violationData", violationData);


                empList.add(map);

                ListAdapter adapter = new SimpleAdapter(Results.this, empList,
                        R.layout.results_list,
                        new String[] {NAME, ADDRESS, POSTAL, VIOLATIONS}, new int[] {
                        R.id.results_emp_name, R.id.results_emp_address, R.id.results_emp_postal, R.id.results_total_violations});
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        position -= 1;  //Compensate for header view of list
                        Toast.makeText(Results.this, "You Clicked at " + empList.get(+position).get(NAME), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra(NAME, empList.get(+position).get(NAME));
                        intent.putExtra(ADDRESS, empList.get(+position).get(ADDRESS));
                        intent.putExtra(POSTAL, empList.get(+position).get(POSTAL));
                        intent.putExtra("numViolations", empList.get(+position).get(VIOLATIONS));
                        intent.putExtra("violationData", empList.get(+position).get("violationData"));
                        intent.setClass(Results.this, Details.class);
                        startActivity(intent);
                    }
                });
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void searchError(Index index, Query query, AlgoliaException e) {
        Log.d("error", e.getMessage());
    }
}