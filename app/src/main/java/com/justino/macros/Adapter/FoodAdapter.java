package com.justino.macros.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.justino.macros.Database.DBHandler;
import com.justino.macros.R;

/**
 * Created by Justin on 10/3/2016.
 */

public class FoodAdapter extends CursorAdapter {

    private DBHandler myDBHandler;
    private LayoutInflater myInflater;
    private Context context;

    public FoodAdapter(Context cntx, Cursor c, int flags) {
        super(cntx, c, flags);
        this.context = cntx;
        myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        myDBHandler = DBHandler.getInstance(context);
        myDBHandler.open();
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return myInflater.inflate(R.layout.category_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView name = (TextView) view.findViewById(R.id.entry_title);
        name.setText(cursor.getString(cursor.getColumnIndex(DBHandler.COLUMN_NAME)));

        final String itemId = cursor.getString(cursor.getColumnIndex("_id"));

        ImageView deleteButton = (ImageView) view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Cursor update = myDBHandler.remove(itemId);
                swapCursor(update);
                notifyDataSetChanged();
            }
        });
    }

}

