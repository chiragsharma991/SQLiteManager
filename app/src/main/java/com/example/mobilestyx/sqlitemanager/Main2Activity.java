package com.example.mobilestyx.sqlitemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.DialogPreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText edittext_id;
    private EditText edittext_add;
    private EditText edittext_name;
    private Button button_show;
    private Database db;
    private Cursor c;
    private Button button_next;
    private Button button_pre;
    private Button button_update;
    private Button button_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        find();
        result();
        c= db.rawquery();




    }

    private void result()
    {
        button_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                String value=String.valueOf(edittext_id.getText().toString().trim());
                Log.e("edit  text",""+value.toString());
                if(!(value.equals("")))
                {
                    showperson(Integer.parseInt(String.valueOf(edittext_id.getText().toString().trim())));




                }else
                {
                    Toast.makeText(getApplicationContext(),"enter id number",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void find()
    {
        edittext_id=(EditText)findViewById(R.id.editTextId);
        edittext_add=(EditText)findViewById(R.id.editTextAddress);
        edittext_name=(EditText)findViewById(R.id.editTextName);
        button_show=(Button)findViewById(R.id.btnShow);
        button_next=(Button)findViewById(R.id.nxt);
        button_pre=(Button)findViewById(R.id.pre);
        button_update=(Button)findViewById(R.id.btnSave);
        button_delete=(Button)findViewById(R.id.delete);
        button_delete.setOnClickListener(this);
        button_update.setOnClickListener(this);

        button_next.setOnClickListener(this);
        button_pre.setOnClickListener(this);

        db=new Database(this);
    }

    private void showperson(int id)
    {
        Cursor c=db.getPerson(id);
        c.moveToFirst();
        if(c!=null)
        {
            edittext_id.setText(c.getString(c.getColumnIndex(Database.COLUMN_ID)));
            edittext_name.setText(c.getString(c.getColumnIndex(Database.COLUMN_NAME)));
            edittext_add.setText(c.getString(c.getColumnIndex(Database.COLUMN_ADD)));

        }

    }

  /*  private void getVal(){
        List<HashMap<String, String>> listMap = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("name", "rahul");
            map.put("address", "adrr");
            map.put("age", "12");


            listMap.add(map);
        }

        for (int i = 0; i < listMap.size(); i++) {
            Log.e("", ""+listMap.get(i).get("name"));
        }
    }*/


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.nxt:
                if(!c.isLast())
                {
                    c.moveToNext();
                    showresult();

                }


                break;

            case R.id.pre:
                if(!c.isFirst())
                {
                    c.moveToPrevious();
                    showresult();

                }


                break;

            case  R.id.btnSave:
                String name=edittext_name.getText().toString();
                String add=edittext_add.getText().toString();
                String id=edittext_id.getText().toString();
                if(name.equals("")||add.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"you can give blank value",Toast.LENGTH_SHORT).show();
                }else
                {
                    db.update(name,add,id);
                    Toast.makeText(getApplicationContext(),"update sucess",Toast.LENGTH_SHORT).show();

                }
            case R.id.delete:
                delete_from_sql();




        }
    }

    private void delete_from_sql()
    {
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(this);
        alertdialog.setMessage("Are you sure want to delete");
        alertdialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {


            }
        });
        alertdialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String id=edittext_id.getText().toString();
                db.delete(id);
                Toast.makeText(getApplicationContext()," sucess",Toast.LENGTH_SHORT).show();


            }
        });
        AlertDialog alert=alertdialog.create();
        alert.show();


    }

    private void showresult()
    {
        edittext_id.setText(c.getString(c.getColumnIndex(Database.COLUMN_ID)));
        edittext_name.setText(c.getString(c.getColumnIndex(Database.COLUMN_NAME)));
        edittext_add.setText(c.getString(c.getColumnIndex(Database.COLUMN_ADD)));


    }
}

