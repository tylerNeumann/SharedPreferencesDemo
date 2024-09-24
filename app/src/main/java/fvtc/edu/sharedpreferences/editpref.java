package fvtc.edu.sharedpreferences;

import static java.lang.Boolean.valueOf;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class editpref extends AppCompatActivity {

    public static final String TAG = "EditPref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editpref);
        Log.d(TAG, "onCreate: here");
        initButton();
        initCheckBox();
        getSettings();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initButton() {
        TextView tvInfo = findViewById(R.id.tvInfo);
        Button btnNavigate = findViewById(R.id.btnNavigate);
        Log.d(TAG, "initButton: hit");

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the other screen
                startActivity(new Intent(view.getContext(), MainActivity.class));
            }
        });
    }
    private void initCheckBox() {
        Log.d(TAG, "initCheckBox: entered");
        CheckBox chkDarkMode = findViewById(R.id.chkDarkMode);
        chkDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: started");
                //save the value of the checkbox to shared preferences
                SharedPreferences preferences = getApplication().getSharedPreferences("myprefs", MODE_PRIVATE);

                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("darkmode", isChecked);
                editor.commit();

                editor.putString("name", "Simple Pref Demo");//String.valueOf(R.string.app_name)
                editor.putInt("age", 45);
                editor.commit();
                Log.d(TAG, "onCheckedChanged: " + isChecked);

                chkDarkMode.setText(valueOf(isChecked).toString());
                Log.d(TAG, "onCheckedChanged: " + isChecked);
            }
        });
    }
    private void getSettings() {
        //read the shared preferences
        SharedPreferences preferences = getApplication().getSharedPreferences("myprefs", MODE_PRIVATE);
        Boolean isDarkMode = preferences.getBoolean("darkmode", false);
        Log.d(TAG, "onResume: " + isDarkMode);

        CheckBox chkDarkMode = findViewById(R.id.chkDarkMode);
        chkDarkMode.setChecked(isDarkMode);
    }
}