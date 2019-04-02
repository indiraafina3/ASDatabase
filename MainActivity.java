package com.codepolitan.myapplicationdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.codepolitan.myapplicationdatabase.adapter.TodoListAdapter;
import com.codepolitan.myapplicationdatabase.data.MyDatabase;
import com.codepolitan.myapplicationdatabase.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TodoListAdapter adapter;
    List<Item> itemList;
    MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        itemList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new TodoListAdapter(itemList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //setDummyData();
        myDatabase = new MyDatabase(this);
        itemList.addAll(myDatabase.getAll());
        adapter.notifyDataSetChanged();
    }
//
//    private void setDummyData() {
//        itemList.add(new Item("belajar pemrograman website dengan CI", 1));
//        itemList.add(new Item("belajar pemrograman website dengan Laravel", 2));
//        itemList.add(new Item("belajar pemrograman website dengan Nodejs", 3));
//        itemList.add(new Item("belajar pemrograman website dengan Ruby", 4));
//        itemList.add(new Item("belajar pemrograman website dengan Django", 5));
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Intent intent = new Intent(this, InsertDataActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
