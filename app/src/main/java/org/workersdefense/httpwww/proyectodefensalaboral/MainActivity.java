***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
***REMOVED***
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends CustomWindow {
    Button mButton;
    EditText mEmpZipEditText;
    EditText mEmpNameEditText;

***REMOVED***
***REMOVED***
***REMOVED***
        this.title.setText("Know Your Employer");

        mButton = (Button) findViewById(R.id.b1);
        mEmpNameEditText = (EditText)findViewById(R.id.emp_name);
        mEmpZipEditText = (EditText)findViewById(R.id.zipcode);

        mButton.setOnClickListener(new View.OnClickListener() {
        ***REMOVED***
            public void onClick(View v) {
                String searchName = mEmpNameEditText.getText().toString();
                String searchZip = mEmpZipEditText.getText().toString();

                Intent intent = new Intent();
                intent.putExtra("name", searchName);
                intent.putExtra("zip", searchZip);
                intent.setClass(MainActivity.this, Results.class);
                startActivity(intent);
        ***REMOVED***
    ***REMOVED***);
***REMOVED***
***REMOVED***