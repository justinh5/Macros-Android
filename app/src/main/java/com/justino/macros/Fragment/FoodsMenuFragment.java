package com.justino.macros.Fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;


import com.justino.macros.Adapter.GridAdapter;
import com.justino.macros.R;




public class FoodsMenuFragment extends Fragment {


    private GridView gridview;
    MenuListener activityCommander;

    static final String [] GRID_DATA = new String[] {
            "Custom",
            "Recent",
            "Add" ,
            "Type",
            "Cuisine" ,
            "Brand"
    };

    public FoodsMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = null;

        try {
            activity = (Activity) context;
            activityCommander = (MenuListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foods_menu, container, false);


        gridview = (GridView) view.findViewById(R.id.panel_grid);
        gridview.setAdapter(new GridAdapter(view.getContext(), GRID_DATA));


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                buttonClicked(position);

            }
        });

        return view;
    }

    public interface MenuListener{
        public void createClick(int position);
    }

    // Calls this when button clicked
    public void buttonClicked(int position){
        activityCommander.createClick(position);
    }


}
