package za.co.programme.jurrius.fragmentswithrecyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import za.co.programme.jurrius.fragmentswithrecyclerview.databinding.ActivityMainBinding;
import za.co.programme.jurrius.fragmentswithrecyclerview.databinding.FragmentAddPersonBinding;
import za.co.programme.jurrius.fragmentswithrecyclerview.databinding.FragmentDetailBinding;
import za.co.programme.jurrius.fragmentswithrecyclerview.databinding.FragmentListBinding;

public class MainActivity extends AppCompatActivity implements PersonAdapter.ItemClicked {
    TextView tvName, tvTel;
    EditText etName, etTel;
    ListFrag listFrag;
    private FragmentAddPersonBinding binding;
    private FragmentDetailBinding binding1;
    private FragmentListBinding binding2;
    private ActivityMainBinding binding3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        binding = FragmentAddPersonBinding.inflate(getLayoutInflater());
        binding1 = FragmentDetailBinding.inflate(getLayoutInflater());

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        listFrag = (ListFrag)fragmentManager.findFragmentById(R.id.fragmentContainerListFrag);


        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etName.getText().toString().isEmpty()||etTel.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ApplicationClass.people.add(new Person(etName.getText().toString().trim(), etTel.getText().toString().trim()));
                    Toast.makeText(MainActivity.this, "Person Successfully added", Toast.LENGTH_SHORT).show();
                    binding.etName.setText(null);
                    binding.etTel.setText(null);
                    binding2.list.notify();
                }

            }
        });
        onItemClicked(0);
    }

    @Override
    public void onItemClicked(int index) {
        binding1.tvName.setText(ApplicationClass.people.get(index).getName());
        binding1.tvTel.setText(ApplicationClass.people.get(index).getTelNr());
    }
}