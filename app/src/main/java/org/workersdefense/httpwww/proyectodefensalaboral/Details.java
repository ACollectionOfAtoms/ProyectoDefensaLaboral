***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
import java.util.List;
import android.app.Activity;
***REMOVED***
***REMOVED***
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

***REMOVED***
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

***REMOVED***
***REMOVED***
***REMOVED***
        setContentView(R.layout.details);

        String name;
        String postal;
        String address;
        String violationCount;
        JSONArray violationData;

        TextView nameView = (TextView) findViewById(R.id.details_emp_name);
        TextView postalView = (TextView) findViewById(R.id.details_emp_postal);
        TextView addressView = (TextView) findViewById(R.id.details_emp_address);
        TextView violationCountView = (TextView) findViewById(R.id.details_total_violations);
        TextView violationDataView = (TextView) findViewById(R.id.details_violation_data);

***REMOVED***
        name = intent.getStringExtra(NAME);
        postal = intent.getStringExtra(POSTAL);
        address = intent.getStringExtra(ADDRESS);
        violationCount = intent.getStringExtra(VIOLATIONCOUNT);

***REMOVED***
            violationData = new JSONArray(intent.getStringExtra("violationData"));
            System.out.println(violationData);
            for (int i = 0; i < violationData.length(); i++) {
                System.out.println(violationData.getJSONObject(i).get("type"));
                if (violationData.getJSONObject(i).get("type").equals("OSHA")) {
                    oshaViolationCount += violationData.getJSONObject(i).getInt("count");
            ***REMOVED*** else if (violationData.getJSONObject(i).get("type").equals("TWC")) {
                    wageTheftViolationCount += violationData.getJSONObject(i).getInt("count");
            ***REMOVED*** else if (violationData.getJSONObject(i).get("type").equals("WHD")) {
                    minWageViolationCount += violationData.getJSONObject(i).getInt("count");
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

        this.title.setText("Employer Details");
        // this.icon.setImageResource(R.drawable.menu_info);
***REMOVED***

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("OSHA: " + oshaViolationCount + " Violations");
        listDataHeader.add("Minimum Wage and Overtime: " +  minWageViolationCount + " Violations");
        listDataHeader.add("Wage theft: " + wageTheftViolationCount + " wage theft claims");

        // Adding child data
        List<String> osha = new ArrayList<String>();
        osha.add(String.format("%d violations in the past 6 years\n ", oshaViolationCount));

        List<String> minWage = new ArrayList<String>();
        minWage.add(String.format("%d violations in the past 6 years\n ", minWageViolationCount));

        List<String> wageTheft = new ArrayList<String>();
        wageTheft.add(String.format("%d violations in the past 6 years\n ", wageTheftViolationCount));

        listDataChild.put(listDataHeader.get(0), osha); // Header, Child data
        listDataChild.put(listDataHeader.get(1), minWage);
        listDataChild.put(listDataHeader.get(2), wageTheft);
***REMOVED***
***REMOVED***
