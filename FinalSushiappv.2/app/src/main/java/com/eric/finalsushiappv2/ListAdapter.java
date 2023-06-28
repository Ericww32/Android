package com.eric.finalsushiappv2;

/**
 * Created by Eric on 8/2/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<Sushi> sushiList;

    public ListAdapter(Context context, ArrayList<Sushi> list) {

        this.context = context;
        sushiList = list;
    }

    @Override
    public int getCount() {

        return sushiList.size();
    }

    @Override
    public Object getItem(int position) {

        return sushiList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup arg2) {
        Sushi ListItems = sushiList.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sushi_list_row, null);
        }
        TextView tvsNum = (TextView) convertView.findViewById(R.id.tv_sNum);
        tvsNum.setText(ListItems.getsNum());
        TextView tvName = (TextView) convertView.findViewById(R.id.tv_name);
        tvName.setText(ListItems.getName());
        TextView tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
        tvPhone.setText(ListItems.getDescription());

        return convertView;
    }
}