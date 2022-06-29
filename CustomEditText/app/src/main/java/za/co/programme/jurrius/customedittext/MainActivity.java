package za.co.programme.jurrius.customedittext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import za.co.programme.jurrius.customedittext.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView etFirstName;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //etFirstName = (AutoCompleteTextView)findViewById(R.id.etFirstName);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        etFirstName = (AutoCompleteTextView)binding.etFirstName;
        String [] names = {"James", "John", "Jenny", "Jenine", "Jennifer", "Jack", "Jonny"};
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(this, R.layout.custom_design_autocomplete, names);
        binding.etFirstName.setThreshold(1);
        etFirstName.setAdapter(adapter);
    }
}