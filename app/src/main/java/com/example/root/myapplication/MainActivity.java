package com.example.root.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import com.example.root.myapplication.LocalRepository.Fruit;
import com.example.root.myapplication.ViewModels.MainActivityViewModel;
import com.example.root.myapplication.controllers.Event;
import com.example.root.myapplication.events.StartActivityEvent;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel fruitViewModel;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        fruitViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        fruitViewModel.setContext(this);
        fruitViewModel.title = "Fruits";
        fruitViewModel.getFruitList().observe(this, fruitlist -> {
            List<String> fList = new ArrayList<>();
            for (Fruit fruit:fruitlist) {
                fList.add(fruit.getName());
            }
            // update UI
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, fList);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        });

        fruitViewModel.loadFruits();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            progressBar.setVisibility(View.VISIBLE);
            fruitViewModel.loadFruits();
        }
    }

    public void addNewFruitActivity(View view) {
        startActivityForResult(new Intent(this, AddNewFruit.class), 1);
    }

    public void OpenDiActivity(View view) {
        startActivityForResult(new Intent(this, AddNewFruit.class), 2);
    }
}
