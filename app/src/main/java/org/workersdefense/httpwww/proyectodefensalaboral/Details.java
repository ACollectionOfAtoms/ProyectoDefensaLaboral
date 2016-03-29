package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.algolia.search.saas.listeners.SearchListener;

/**
 * Created by ahernandez on 3/22/16.
 */

public class Details extends CustomWindow {
    private static final String NAME = "Name";
    private static final String POSTAL = "PostalCode";
    private static final String ADDRESS = "Address";
    private static final String VIOLATIONCOUNT = "numViolations";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        Intent intent = getIntent();
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

        // TODO Add list of violation types with details for each row
        this.title.setText(name);
        // this.icon.setImageResource(R.drawable.menu_info);
    }
}
