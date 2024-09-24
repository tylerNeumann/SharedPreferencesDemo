package fvtc.edu.sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initButton();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void initButton() {
        TextView tvInfo = findViewById(R.id.tvInfo);
        Button btnNavigate = findViewById(R.id.btnNavigate);


        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to the other screen
                startActivity(new Intent(view.getContext(), editpref.class));
            }
        });
    }
    @Override
    public  void onResume(){
        //read the shared preferences
        SharedPreferences preferences = getApplication().getSharedPreferences("myprefs", MODE_PRIVATE);
        Boolean isDarkMode = preferences.getBoolean("darkmode", false);

        CheckBox chkDarkMode = findViewById(R.id.chkDarkMode);
        chkDarkMode.setText("DarkMode : " + isDarkMode.toString());
        chkDarkMode.setChecked(isDarkMode);
        Log.d(TAG, "onResume: " + isDarkMode);
        super.onResume();
    }
}