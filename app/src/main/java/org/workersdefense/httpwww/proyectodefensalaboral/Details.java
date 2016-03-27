***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***

***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***

public class Details extends CustomWindow {
***REMOVED***
***REMOVED***
***REMOVED***
    private static final String VIOLATIONCOUNT = "numViolations";

***REMOVED***
***REMOVED***
***REMOVED***
        setContentView(R.layout.details);

        String name;
        String postal;
        String address;
        String violationCount;
        String violationData;

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
        violationData = intent.getStringExtra("violationData");

        nameView.setText(name);
        postalView.setText(postal);
        addressView.setText(address);
        violationCountView.setText(violationCount);
        violationDataView.setText(violationData);


        this.title.setText(name);
        // this.icon.setImageResource(R.drawable.menu_info);
***REMOVED***
***REMOVED***
