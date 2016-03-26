package org.workersdefense.httpwww.proyectodefensalaboral;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends CustomWindow {
    Button mButton;
    EditText mEmpZipEditText;
    EditText mEmpNameEditText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.title.setText("Know Your Employer");

        mButton = (Button) findViewById(R.id.b1);
        mEmpNameEditText = (EditText)findViewById(R.id.emp_name);
        mEmpZipEditText = (EditText)findViewById(R.id.zipcode);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchName = mEmpNameEditText.getText().toString();
                String searchZip = mEmpZipEditText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("name", searchName);
                intent.putExtra("zip", searchZip);
                intent.setClass(MainActivity.this, Results.class);
                startActivity(intent);
            }
        });
    }
}