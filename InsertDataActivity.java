package com.codepolitan.myapplicationdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.codepolitan.myapplicationdatabase.data.MyDatabase;
import com.codepolitan.myapplicationdatabase.entity.Item;

public class InsertDataActivity extends AppCompatActivity {
    Item item;
    EditText etName, etPriority;
    MyDatabase myDatabase;

    Boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        etName = findViewById(R.id.et_name);
        etPriority = findViewById(R.id.et_priority);
        myDatabase = new MyDatabase(this);

        //cek apa ada activity yang sebelumny dikirim atau tdk
        if (getIntent().getParcelableExtra("ITEM")!=null) {
            //jika dia mengirim data atau getIntent!=null atau ada yang dikirim
            edit = true;
            item = getIntent().getParcelableExtra("ITEM");
            etName.setText(item.getName());
            etPriority.setText(String.valueOf(item.getPriority()));
        }
    }

    public void insertData(View view) {
        //create content from edit text
        String nama = etName.getText().toString();
        String priority  = etPriority.getText().toString();
       //kalau edit true
        if (edit) {
            item.setName(nama);
            item.setPriority(Integer.parseInt(priority));
            myDatabase.updateItem(item);
        } else  {
            //create item object than send name and priority paramas
            Item item = new Item(nama,Integer.valueOf(priority));
            //call additem methon on myDatabase object
            myDatabase.addItem(item);

        }
        //create intent
        Intent intent = new Intent(InsertDataActivity.this, MainActivity.class);
        //memulai activity insert data
        startActivity(intent);
        //selesai insert
        finish();
    }
}
