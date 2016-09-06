package com.example.mobilestyx.sqlitemanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText editTextName;
    private EditText editTextAdd;
    private Button btnSave;
    private Button btnView;

    private Database db;
    //chirag

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAdd = (EditText) findViewById(R.id.editTextAddress);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnView = (Button) findViewById(R.id.btnView);

        db = new Database(this);

        btnSave.setOnClickListener(this);
        btnView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v==btnSave){
            if(editTextName.getText().toString().equals("")||editTextAdd.getText().toString().equals(""))
            {
                Toast.makeText(this,"Enter properly",Toast.LENGTH_SHORT).show();
            }else
            {
                Log.e("gettext",""+editTextName.getText());

                insert();
            }

        }
        if(v==btnView){
            Intent intent = new Intent(this,Main2Activity.class);
            startActivity(intent);
        }


    }

    private void insert(){
        String name = editTextName.getText().toString().trim();
        String add = editTextAdd.getText().toString().trim();
        db.addPerson(name,add);
        Toast.makeText(this,"Inserted Successfully",Toast.LENGTH_LONG).show();
    }
}
