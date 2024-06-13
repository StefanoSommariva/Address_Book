package com.example.contactsmanagerapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactsmanagerapp.databinding.ContactListItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ContactViewHolder> {

    private ArrayList<Contacts> contacts;

    public MyAdapter(ArrayList<Contacts> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        ContactListItemBinding contactListItemBinding =
                DataBindingUtil.inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.contact_list_item,
                        parent,
                        false

                );
        return new ContactViewHolder(contactListItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        //chiamato da reciclerView quando ha bisogno di mostrare  o aggiornare un item
        //ad una posizione specifica della list or grid

        Contacts currentContact = contacts.get(position);

        holder.contactListItemBinding.setContact(currentContact);
    }

    @Override
    public int getItemCount() {
        //determina il numero totale di items nel dataset che verranno mostrati

        if(contacts != null){
            return contacts.size();
        }else {
            return 0;
        }
    }


    public void setContacts(ArrayList<Contacts> contacts) {
        this.contacts = contacts;

        //informa il recyclerView associato che il dataset è cambiato
        // e il reciclerView dovrebbe refresharsi per riflettere il cambiamento

        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder{

        private ContactListItemBinding contactListItemBinding;

        public ContactViewHolder(@NonNull  ContactListItemBinding contactListItemBinding) {
            super(contactListItemBinding.getRoot());
            this.contactListItemBinding = contactListItemBinding;
        }
    }
}
