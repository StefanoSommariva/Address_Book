package com.example.contactsmanagerapp;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Repository {
    // The available data sources:
    // - ROOM Database


    private final ContactDAO contactDAO;

    //usato per background db operations
    ExecutorService executor;

    //usato per fare updating UI
    Handler handler;

    public Repository(Application application) {


        ContactDatabase contactDatabase = ContactDatabase.getInstance(application);
        this.contactDAO = contactDatabase.getContactDAO();

        executor = Executors.newSingleThreadExecutor();
        handler = new Handler(Looper.getMainLooper());
    }

     //Metodi nel DAO che vengono eseguiti dalla repository
    public void addContact(Contacts contact){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.insert(contact);
            }
        });
    }


    public void deleteContact(Contacts contact){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDAO.delete(contact);
            }
        });
       // contactDAO.delete(contact);
    }
    public LiveData<List<Contacts>> getAllContacts(){
        return contactDAO.getAllContacts();
    }



}
