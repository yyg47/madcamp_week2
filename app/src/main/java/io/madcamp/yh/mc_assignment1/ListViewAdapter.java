package io.madcamp.yh.mc_assignment1;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<Pair<String,String>> data;
    private LayoutInflater inflater;
    private int layout;

    public ListViewAdapter(Context context, int layout, ArrayList<Pair<String, String>> contacts) {
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.data=contacts;
        this.layout=layout;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView=inflater.inflate(layout,parent,false);
        }

        Pair<String,String> contact = data.get(position);

        TextView contact_name = (TextView) convertView.findViewById(R.id.name_textview);
        contact_name.setText(contact.first);

        TextView contact_num = (TextView) convertView.findViewById(R.id.number_textview);
        contact_num.setText(contact.second);

        return convertView;
    }
}