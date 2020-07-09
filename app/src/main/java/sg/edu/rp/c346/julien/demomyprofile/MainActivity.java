package sg.edu.rp.c346.julien.demomyprofile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGPA = findViewById(R.id.editgpa);
        etName = findViewById(R.id.editname);
        rgGender = findViewById(R.id.radioGroupGender);
        button = findViewById(R.id.buttonSave);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor prefedit = prefs.edit();
                String strName = etName.getText().toString();
                float GPA = Float.parseFloat(etGPA.getText().toString());
                int gender = rgGender.getCheckedRadioButtonId();
                prefedit.putString("name", strName);
                prefedit.putFloat("GPA", GPA);
                prefedit.putInt("Gender",gender);
                prefedit.commit();
                Toast.makeText(MainActivity.this, "Saved!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String msg = prefs.getString("name", "New User");
        etName.setText(msg);
        float gpa = prefs.getFloat("GPA",0);
        etGPA.setText(gpa + "");
        int button = prefs.getInt("Gender",0);
        rgGender.check(button);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefedit = prefs.edit();
        String strName = etName.getText().toString();
        float GPA = Float.parseFloat(etGPA.getText().toString());
        int gender = rgGender.getCheckedRadioButtonId();
        prefedit.putString("name", strName);
        prefedit.putFloat("GPA", GPA);
        prefedit.putInt("Gender",gender);
        prefedit.commit();
    }

}