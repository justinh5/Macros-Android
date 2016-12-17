package com.justino.macros.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.justino.macros.R;

public class GridAdapter extends BaseAdapter {


    private Context mContext;
    private static LayoutInflater inflater = null;
    private final String [] gridValues;

    // references to our images
    private Integer [] mThumbIds = {
            R.drawable.star,
            R.drawable.recent,
            R.drawable.add_custom,
            R.drawable.type,
            R.drawable.cuisine,
            R.drawable.brand
    };


    public GridAdapter(Context c, String [] gridValues) {
        this.mContext = c;
        this.gridValues = gridValues;
        inflater = ( LayoutInflater ) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }



    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {


        View gridView = null;
        ImageView img;
        TextView tv;


        if (convertView == null) {

            gridView = new View(mContext);
            // if it's not recycled, initialize some attributes
            gridView = inflater.inflate(R.layout.grid_item, null);

            img =(ImageView) gridView.findViewById(R.id.imageView1);
            tv = (TextView) gridView.findViewById(R.id.textView1);

            img.setImageResource(mThumbIds[position]);
            tv.setText(gridValues[position]);

            switch (position) {
                case 0:
                    img.setBackgroundColor(Color.rgb(244, 241, 66));
                    break;
                case 1:
                    img.setBackgroundColor(Color.rgb(244, 188, 66));
                    break;
                case 2:
                    img.setBackgroundColor(Color.rgb(244, 125, 66));
                    break;
                case 3:
                    img.setBackgroundColor(Color.rgb(214, 72, 169));
                    break;
                case 4:
                    img.setBackgroundColor(Color.rgb(145, 72, 214));
                    break;
                case 5:
                    img.setBackgroundColor(Color.rgb(72, 157, 214));
                    break;
            }

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}