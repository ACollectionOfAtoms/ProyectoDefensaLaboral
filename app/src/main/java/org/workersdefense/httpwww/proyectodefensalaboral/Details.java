package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by ahernandez on 3/22/16.
 */

public class Details extends CustomWindow {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private static final String NAME = "Name";
    private static final String POSTAL = "PostalCode";
    private static final String ADDRESS = "Address";
    private static final String VIOLATIONCOUNT = "numViolations";
    int oshaViolationCount = 0;
    int minWageViolationCount = 0;
    int wageTheftViolationCount = 0;
    HashMap<String, String> oshaViolations = new HashMap<>();
    HashMap<String, String> wageTheftViolations = new HashMap<>();
    HashMap<String, String> minWageViolations = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        Button helpButton= (Button) findViewById(R.id.bHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Details.this, Help.class);
                startActivity(intent);
            }
        });
        String name;
        String postal;
        String address;
        String violationCount;
        JSONArray violationData;

        TextView nameView = (TextView) findViewById(R.id.details_emp_name);
        TextView postalView = (TextView) findViewById(R.id.details_emp_postal);
        TextView addressView = (TextView) findViewById(R.id.details_emp_address);
        TextView violationCountView = (TextView) findViewById(R.id.details_total_violations);

        Intent intent = getIntent();
        name = intent.getStringExtra(NAME);
        postal = intent.getStringExtra(POSTAL);
        address = intent.getStringExtra(ADDRESS);
        if (address.equals("No address - no direccion")) {
            if (Locale.getDefault().getLanguage().equals("en")){
                address = "No address";
            } else {
                address = "No dirección";
            }
        }
        violationCount = intent.getStringExtra(VIOLATIONCOUNT);


        try {
            violationData = new JSONArray(intent.getStringExtra("violationData"));
            for (int i = 0; i < violationData.length(); i++) {
                String type = (String) violationData.getJSONObject(i).get("type");
                if (type.replaceAll("\\s+","").equals("OSHA")) {
                    String vioDate = violationData.getJSONObject(i).getString("date");
                    String vioCount = violationData.getJSONObject(i).getString("count");
                    if (oshaViolations.containsKey(vioDate)) {
                        int total = Integer.parseInt(oshaViolations.get(vioDate)) + Integer.parseInt(vioCount);
                        oshaViolations.put(vioDate, Integer.toString(total));
                    } else {
                        oshaViolations.put(vioDate, vioCount);
                    }
                    oshaViolationCount += Integer.parseInt(vioCount);
                } else if (type.replaceAll("\\s+","").equals("TWC")) {
                    String vioDate = violationData.getJSONObject(i).getString("date");
                    String vioCount = violationData.getJSONObject(i).getString("count");
                    if (wageTheftViolations.containsKey(vioDate)) {
                        int total = Integer.parseInt(wageTheftViolations.get(vioDate)) + Integer.parseInt(vioCount);
                        wageTheftViolations.put(vioDate, Integer.toString(total));
                    } else {
                        wageTheftViolations.put(vioDate, vioCount);
                    }
                    wageTheftViolationCount += Integer.parseInt(vioCount);
                } else if (type.replaceAll("\\s+","").equals("WHD")) {
                    String vioDate = violationData.getJSONObject(i).getString("date");
                    String vioCount = violationData.getJSONObject(i).getString("count");
                    if (minWageViolations.containsKey(vioDate)) {
                        int total = Integer.parseInt(minWageViolations.get(vioDate)) + Integer.parseInt(vioCount);
                        minWageViolations.put(vioDate, Integer.toString(total));
                    } else {
                        minWageViolations.put(vioDate, vioCount);
                    }
                    minWageViolationCount += Integer.parseInt(vioCount);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        nameView.setText(name);
        postalView.setText(postal);
        addressView.setText(address);
        violationCountView.setText(violationCount);

        // get the list view
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        //preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        //setting list adapter
        expListView.setAdapter(listAdapter);

        this.title.setText(getResources().getString(R.string.details_title));
        // this.icon.setImageResource(R.drawable.menu_info);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("OSHA: " + oshaViolationCount + " " + getResources().getString(R.string.violations));
        listDataHeader.add(getResources().getString(R.string.Minimum_wage_and_OverTime)+ ": " +  minWageViolationCount + " " + getResources().getString(R.string.violations));
        listDataHeader.add(getResources().getString(R.string.wage_theft) + ": " + wageTheftViolationCount + " " + getResources().getString(R.string.WageTheftClaims));

        // Adding child data
        List<String> osha = new ArrayList<String>();
        addMapToList(oshaViolations, osha);

        List<String> minWage = new ArrayList<String>();
        addMapToList(minWageViolations, minWage);

        List<String> wageTheft = new ArrayList<String>();
        addMapToList(wageTheftViolations, wageTheft);

        listDataChild.put(listDataHeader.get(0), osha); // Header, Child data
        listDataChild.put(listDataHeader.get(1), minWage);
        listDataChild.put(listDataHeader.get(2), wageTheft);
    }
    //TODO Mustang building materials. Discard NA and catch OSHA
    public void addMapToList(Map map, List list) {
        Iterator it = map.entrySet().iterator();
        if (map.isEmpty()) {
            list.add(getResources().getString(R.string.nothing_to_show));
        } else {
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                Resources res = getResources();
                list.add(String.format(res.getString(R.string.on), pair.getValue(), pair.getKey()));
                it.remove();
            }
        }
    }
}
