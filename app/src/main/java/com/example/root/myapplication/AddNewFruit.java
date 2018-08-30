package com.example.root.myapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import com.example.root.myapplication.LocalRepository.Fruit;
import com.example.root.myapplication.ViewModels.AddNewFruitViewModel;

public class AddNewFruit extends AppCompatActivity {
    AddNewFruitViewModel addFruitViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfruit);
        addFruitViewModel = ViewModelProviders.of(this).get(AddNewFruitViewModel.class);
        addFruitViewModel.setContext(this);
        //addFruitViewModel.liveBus.observe(this, CloseActivityEvent.class, this::finishAddFruitActivity);
        addFruitViewModel.getFruit().observe(this, fruit -> {
            addFruitViewModel.addNewFruit();
            finish();
        });
    }

    public void AddFruit(View view) {
        String Id = ((EditText)findViewById(R.id.fId)).getText().toString();
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String cost = ((EditText)findViewById(R.id.cost)).getText().toString();
        Fruit f = new Fruit(Id, name, Integer.parseInt(cost));
        addFruitViewModel.setFruit(f);
        finish();
    }
}
