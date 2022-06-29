package com.jurrius.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;
import com.jurrius.sqliteexample.ContactsDB;
import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Data extends AppCompatActivity {
    TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        tvData= (TextView) findViewById(R.id.tvData);

        try {
            ContactsDB db = new ContactsDB(this);
            db.open();
            System.out.println("db.getData()"+db.getData());
            tvData.setText(db.getData());
            db.close();

        }catch (SQLException e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}