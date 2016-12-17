package com.justino.macros.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;


import com.justino.macros.Fragment.EntryFoodsFragment;
import com.justino.macros.Fragment.FoodsMenuFragment;
import com.justino.macros.Fragment.MenuListFragment;
import com.justino.macros.Fragment.PicFrag;
import com.justino.macros.R;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;




public class Entry extends AppCompatActivity implements FoodsMenuFragment.MenuListener {

    private SearchView search;
    private SlidingUpPanelLayout slidingLayout;
    private ActionBar actionbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);

        actionbar = getSupportActionBar();
        slidingLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        slidingLayout.setAnchorPoint(1f);      //slide to 30% of the screen
        slidingLayout.setTouchEnabled(false);  //disable menu slide with touch

        search = (SearchView) findViewById(R.id.searchView1);
        search.setQueryHint("SearchView");

        replaceMainFragment(0);  //Display the list of foods when start
        replaceMenuFragment(9);  //Display menu in slide panel

        panelListener();

        //*** setOnQueryTextFocusChangeListener ***
        search.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                Toast.makeText(getBaseContext(), String.valueOf(hasFocus),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //*** setOnQueryTextListener ***
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                Toast.makeText(getBaseContext(), query,
                        Toast.LENGTH_SHORT).show();

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //	Toast.makeText(getBaseContext(), newText,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


    @Override
    public void createClick(int position) {

        replaceMenuFragment(position);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry, menu);

        /*
        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));*/

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.add_food) {

            if (slidingLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

            else {
                actionbar.hide();
                slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
            }


        }
        return super.onOptionsItemSelected(item);
    }


    // Listener for the add entry button
    public void statsClick(View view) {

        startActivity(new Intent(this, Stats.class));
    }

    // Listener for the sliding panel
    public void panelListener(){

        slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {

            // During the transition of expand and collapse onPanelSlide function will be called.
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {

            }
        });
    }


    private void replaceMainFragment(int pos) {

        Fragment fragment;

        switch (pos) {
            case 0:
                fragment = new EntryFoodsFragment();
                break;
            default:
                fragment = new EntryFoodsFragment();
                break;
        }

        if(null!=fragment) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_content, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    private void replaceMenuFragment(int position) {

        Fragment fr;

        switch(position) {

            case 0: fr = new MenuListFragment();
                break;

            case 1: fr = new PicFrag();
                break;

            case 2: fr = new PicFrag();
                break;

            case 3: fr = new PicFrag();
                break;

            case 4: fr = new PicFrag();
                break;

            case 5: fr = new PicFrag();
                break;

            case 9: fr = new FoodsMenuFragment();
                break;

            default: fr = new PicFrag();
                break;
        }

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.panel_content, fr);
        fragmentTransaction.commit();
    }


    // When the add entry button is clicked
    public void exitMenu(View view) {

        slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        actionbar.show();
    }
}



