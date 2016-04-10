***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
import android.widget.Button;
***REMOVED***
***REMOVED***
***REMOVED***
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.widget.ExpandableListView;

***REMOVED***
***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***

public class Details extends CustomWindow {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
***REMOVED***
***REMOVED***
***REMOVED***
    private static final String VIOLATIONCOUNT = "numViolations";
    int oshaViolationCount = 0;
    int minWageViolationCount = 0;
    int wageTheftViolationCount = 0;
    HashMap<String, String> oshaViolations = new HashMap<>();
    HashMap<String, String> wageTheftViolations = new HashMap<>();
    HashMap<String, String> minWageViolations = new HashMap<>();

***REMOVED***
***REMOVED***
***REMOVED***
        setContentView(R.layout.details);
        Button helpButton= (Button) findViewById(R.id.bHelp);
        helpButton.setOnClickListener(new View.OnClickListener() {
        ***REMOVED***
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Details.this, Help.class);
                startActivity(intent);
        ***REMOVED***
    ***REMOVED***);
        String name;
        String postal;
        String address;
        String violationCount;
        JSONArray violationData;

        TextView nameView = (TextView) findViewById(R.id.details_emp_name);
        TextView postalView = (TextView) findViewById(R.id.details_emp_postal);
        TextView addressView = (TextView) findViewById(R.id.details_emp_address);
        TextView violationCountView = (TextView) findViewById(R.id.details_total_violations);

***REMOVED***
        name = intent.getStringExtra(NAME);
        postal = intent.getStringExtra(POSTAL);
        address = intent.getStringExtra(ADDRESS);
        violationCount = intent.getStringExtra(VIOLATIONCOUNT);

***REMOVED***
            violationData = new JSONArray(intent.getStringExtra("violationData"));
            for (int i = 0; i < violationData.length(); i++) {
                if (violationData.getJSONObject(i).get("type").equals("OSHA")) {
                    String vioDate = violationData.getJSONObject(i).getString("date");
                    String vioCount = violationData.getJSONObject(i).getString("count");
                    oshaViolations.put(vioDate, vioCount);
                    oshaViolationCount += Integer.parseInt(vioCount);
            ***REMOVED*** else if (violationData.getJSONObject(i).get("type").equals("TWC")) {
                    String vioDate = violationData.getJSONObject(i).getString("date");
                    String vioCount = violationData.getJSONObject(i).getString("count");
                    wageTheftViolations.put(vioDate, vioCount);
                    wageTheftViolationCount += Integer.parseInt(vioCount);
            ***REMOVED*** else if (violationData.getJSONObject(i).get("type").equals("WHD")) {
                    String vioDate = violationData.getJSONObject(i).getString("date");
                    String vioCount = violationData.getJSONObject(i).getString("count");
                    minWageViolations.put(vioDate, vioCount);
                    minWageViolationCount += Integer.parseInt(vioCount);
            ***REMOVED***
        ***REMOVED***
    ***REMOVED*** catch (JSONException e) {
***REMOVED***
    ***REMOVED***

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
***REMOVED***

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
***REMOVED***

    public void addMapToList(Map map, List list) {
        Iterator it = map.entrySet().iterator();
        if (map.isEmpty()) {
            list.add(getResources().getString(R.string.nothing_to_show));
    ***REMOVED*** else {
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                list.add(String.format("%s on %s", pair.getValue(), pair.getKey()));
                it.remove();
        ***REMOVED***
    ***REMOVED***
***REMOVED***
***REMOVED***
