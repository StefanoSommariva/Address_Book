package com.example.contactsmanagerapp;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanagerapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //data source
    private ContactDatabase contactDatabase;
    private ArrayList<Contacts> contactsArrayList = new ArrayList<>();

    //adapter
    private MyAdapter myAdapter;

    //binding
    private ActivityMainBinding mainBinding;
    private MainActivityClickHandlers handlers;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        handlers = new MainActivityClickHandlers(this );

        mainBinding.setClickHandler(handlers);


        //recyclerView
        RecyclerView recyclerView = mainBinding.recyclerview;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);



        //database
        contactDatabase = ContactDatabase.getInstance(this);

        //view model
        MyViewModel viewModel = new ViewModelProvider(this)
                .get(MyViewModel.class);


        //inserisco un contatto di test
        Contacts c1 = new Contacts( "Stefano", "ste@gmail.com");
        viewModel.addNewContact(c1);

        //carico data dal room db
        viewModel.getAllContacts().observe(this,
                new Observer<List<Contacts>>() {
                    @Override
                    public void onChanged(List<Contacts> contacts) {

                        contactsArrayList.clear();

                        for (Contacts c : contacts){
                            Log.v("TAGY", c.getName());
                            contactsArrayList.add(c);

                        }
                        myAdapter.notifyDataSetChanged();


                    }
                });

        //adapter
        myAdapter = new MyAdapter(contactsArrayList);

        //linking recyclerView con adapter
        recyclerView.setAdapter(myAdapter);

    }
}








