***REMOVED***

***REMOVED***
***REMOVED***
***REMOVED***
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
        mEmpNameEditText.setHint("Type Employer's/Company's Name");
        mEmpZipEditText = (EditText)findViewById(R.id.zipcode);
        mEmpZipEditText.setHint("Type Employer's Zipcode");

        mButton.setOnClickListener(new View.OnClickListener() {
        ***REMOVED***
            public void onClick(View v) {
                String searchName = mEmpNameEditText.getText().toString();
                String searchZip = mEmpZipEditText.getText().toString();
                if (searchName.matches("") && searchZip.matches("")) {
                    makeToast("Please enter a zipcode or employer name.");
                    return;
            ***REMOVED***

                Intent intent = new Intent();
                intent.putExtra("name", searchName);
                intent.putExtra("zip", searchZip);
                intent.setClass(MainActivity.this, Results.class);
                startActivity(intent);
        ***REMOVED***
    ***REMOVED***);
***REMOVED***
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
***REMOVED***
***REMOVED***
