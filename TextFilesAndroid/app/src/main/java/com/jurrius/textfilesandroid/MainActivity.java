package com.jurrius.textfilesandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    EditText etName;
    EditText etSurname;
    TextView tvResults;
    ArrayList<Person> persons;
    OutputStreamWriter outputFile=null;
    BufferedReader reader=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        tvResults = (TextView) findViewById(R.id.tvResults);
        persons = new ArrayList<Person>();
        loadData();
    }

    public void btnAddData(View v){
        String name = etName.getText().toString();
        String surname= etSurname.getText().toString();
        Person person = new Person(name, surname);
        persons.add(person);
        setTextToTextView();
    }

    public void setTextToTextView(){
        String text = "";
        for(int i = 0; i<persons.size(); i++)
        {
            text = text + persons.get(i).getName()+ ","+ persons.get(i).getSurname() + "\n";
        }
        tvResults.setText(text);
    }

    public void loadData(){
        persons.clear();
        File file = getApplicationContext().getFileStreamPath("Data.txt");
        String lineFromFile;
        if(file.exists())
        {
            try {

                reader = new BufferedReader(new InputStreamReader(openFileInput("Data.txt")));
                lineFromFile = reader.readLine();
                while(lineFromFile != null)
                {
                    StringTokenizer tokenizer = new StringTokenizer(lineFromFile,",");
                    String token1 = tokenizer.nextToken().trim();
                    String token2 = tokenizer.nextToken().trim();
                    Person person = new Person(token1, token2);
                    persons.add(person);
                    if(persons.size()>5){
                        break;
                    }
                }

                setTextToTextView();

            }catch (FileNotFoundException fnfe){
                Toast.makeText(this,fnfe.getMessage(),Toast.LENGTH_SHORT).show();
            }catch (IOException io){
                Toast.makeText(this, io.getMessage(), Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }finally{
                try {
                    reader.close();
                }catch (IOException io){
                    Toast.makeText(this,io.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void btnSaveData(View v){
        try{
            FileOutputStream file = openFileOutput("Data.txt", MODE_PRIVATE);
            outputFile = new OutputStreamWriter(file);
            for(int i = 0; i<persons.size(); i++)
            {
                outputFile.write(persons.get(i).getName()+ ","+ persons.get(i).getSurname() + "\n");
            }
            Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show();
        }catch (FileNotFoundException fnfe){
            Toast.makeText(this, fnfe.getMessage(), Toast.LENGTH_SHORT).show();
            fnfe.printStackTrace();
        }catch(IOException io){
            Toast.makeText(this, io.getMessage(), Toast.LENGTH_SHORT).show();
            io.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }finally {
            try {
                outputFile.flush();
                outputFile.close();
            }catch(IOException io){
                Toast.makeText(this, io.getMessage(), Toast.LENGTH_SHORT).show();
                io.printStackTrace();
            }
        }
    }
}