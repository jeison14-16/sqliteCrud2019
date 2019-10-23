package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorU extends BaseAdapter {

    private Context context;
    private ArrayList <UsuarioList> list;

    public AdaptadorU(Context context, ArrayList<UsuarioList> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UsuarioList Item = (UsuarioList) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.list_usu, null);
        TextView firsname   = convertView.findViewById(R.id.nombrelist);
        TextView lastname   =  convertView.findViewById(R.id.apellist);
        TextView email      =  convertView.findViewById(R.id.correolist);

        firsname.setText(Item.getFirsname());
        lastname.setText(Item.getLastname());
        email.setText(Item.getEmail());


        return convertView;
    }

}



