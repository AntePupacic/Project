package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PetListActivity extends AppCompatActivity {

    private RecyclerView dogRecycleView;
    private PetAdapter.RecycleViewClickListener listener;
    private FloatingActionButton addBtn, logoutBtn;
    PetAdapter petAdapter;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);


        DataStorage.fillData(getApplicationContext());
        findViews();
        addPet();


        recycleViewClickListener();
        dogRecycleView.addItemDecoration(new DividerItemDecoration(dogRecycleView.getContext(), DividerItemDecoration.VERTICAL));
        dogRecycleView.setItemAnimator(new DefaultItemAnimator());
        petAdapter = new PetAdapter(getApplicationContext(), listener);
        dogRecycleView.setAdapter(petAdapter);

        searchRecycleView();
        logout();

    }


    private void logout(){
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetListActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void searchRecycleView(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                petAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                petAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void findViews(){
        addBtn = (FloatingActionButton) findViewById(R.id.addBtn);
        logoutBtn = (FloatingActionButton) findViewById(R.id.logoutBtn);
        searchView = (SearchView) findViewById(R.id.conatinerSearchView);
        dogRecycleView = (RecyclerView) findViewById(R.id.dogRecycleView);
    }

    private void recycleViewClickListener(){
        listener = new PetAdapter.RecycleViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(PetListActivity.this, PetInfoActivity.class);
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        };
    }

    //New activity to add pet data into DB
    private void addPet(){
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetListActivity.this, AddPetActivity.class);
                startActivity(intent);
            }
        });
    }

}