package com.project.arzun.serverdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by arzun on 3/26/17.
 */
public class MyAdapter extends BaseAdapter {

    ArrayList<Module> myData=new ArrayList<>();
    Context c;
    LayoutInflater inflater;

    public MyAdapter(MainActivity mainActivity, ArrayList<Module> savedData) {
        c=mainActivity;
        myData=savedData;
        inflater=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder hold;

        if (convertView==null){
            convertView=inflater.inflate(R.layout.listdata,null);
            hold=new ViewHolder();
            hold.name=(TextView)convertView.findViewById(R.id.listname);
            hold.address=(TextView)convertView.findViewById(R.id.listaddress);
            hold.phone=(TextView)convertView.findViewById(R.id.listphone);
            hold.cost=(TextView)convertView.findViewById(R.id.listcost);
            hold.description=(TextView)convertView.findViewById(R.id.listdescription);

            convertView.setTag(hold);
        }else{
            hold=(ViewHolder)convertView.getTag();
        }
        hold.name.setText(myData.get(position).getName());
        hold.address.setText(myData.get(position).getAddress());
        hold.phone.setText(myData.get(position).getPhone());
        hold.cost.setText(myData.get(position).getCost());
        hold.description.setText(myData.get(position).getDescription());

        return convertView;
    }
}
