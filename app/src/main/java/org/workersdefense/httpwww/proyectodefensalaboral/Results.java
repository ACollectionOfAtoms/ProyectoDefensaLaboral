package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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
    ListView list;
    Index index;
    APIClient algoliaClient;
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
        String title = getResources().getString(R.string.search_results);
        this.title.setText(title);
        String apikey = getResources().getString(R.string.api_key);
        algoliaClient = new APIClient("R7X1GJKUPR", apikey);
        index = algoliaClient.initIndex("WDPEmployers");

        Intent intent = getIntent();
        String empName = intent.getStringExtra("name");
        String empZip = intent.getStringExtra("zip");
        search(empName, empZip);
    }

    public void search(String empName, String empZip) {
        Query query = new Query(empName);
        if (empZip.matches("")) {
            index.searchASync(query, this);
        } else {
            query.setNumericFilters("PostalCode=" + empZip);
            index.searchASync(query, this);
        }
    }

    @Override
    public void searchResult(Index index, Query query, JSONObject results) {
        list = (ListView) findViewById(R.id.results_list);
        TextView resultCount = new TextView(getApplicationContext());
        TextView searchingLabel = (TextView) findViewById(R.id.searching_label);
        TextView noResultsLabel = (TextView) findViewById(R.id.no_results_label);
        try {
            // Modeled after https://www.learn2crack.com/2013/11/listview-from-json-example.html
            JSONArray res = results.getJSONArray(HITS);
            int resCount = results.getJSONArray(HITS).length();
            String resultHeader = Integer.toString(resCount) + " " + getResources().getString(R.string.results_found);
            resultCount.setText(resultHeader);
            resultCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            resultCount.setTextColor(Color.BLACK);
            list.addHeaderView(resultCount, "", false);
            list.setHeaderDividersEnabled(false);

            for (int i = 0; i < res.length(); i++) {
                JSONObject row = res.getJSONObject(i);

                // Store JSON items
                String name = row.getString(NAME);
                String postal = getResources().getString(R.string.zip_code) + ": " + row.getString(POSTAL);
                String address = row.getString(ADDRESS);
                int violationsCount = 0;
                JSONArray totalViolationsArray = row.getJSONArray(VIOLATIONS);
                for (int j = 0; j < totalViolationsArray.length(); j ++) {
                    if (!totalViolationsArray.getJSONObject(j).getString("type").equals("NA"))
                        violationsCount += totalViolationsArray.getJSONObject(j).getInt("count");
                }
                String totalViolations = getResources().getString(R.string.total_violations) + " " + Integer.toString(violationsCount) + " ";
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
                        new String[] {NAME, POSTAL, VIOLATIONS}, new int[] {
                        R.id.results_emp_name, R.id.results_emp_postal, R.id.results_total_violations});
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        position -= 1;  // Compensate for header view of list
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
            searchingLabel.setVisibility(View.GONE);
            if (empList.isEmpty()) {
                noResultsLabel.setVisibility(View.VISIBLE);
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