package com.makel.ata.yemekhane.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.makel.ata.yemekhane.AppConfig;
import com.makel.ata.yemekhane.MainActivity;
import com.makel.ata.yemekhane.R;
import com.makel.ata.yemekhane.adapters.FoodListAdapter;

/**
 * A fragment representing a list of Items.
 */
public class MealListFragment extends Fragment {

    private FoodListAdapter foodListAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MealListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_list, container, false);
        foodListAdapter = new FoodListAdapter(AppConfig._listFoodPlan);

        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setAdapter(foodListAdapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        if (menu != null) {
            menu.findItem(R.id.button_getMenu).setVisible(false);
        }

        MenuItem checkBox_showOlderPlans = menu.findItem(R.id.checkBox_showOlderPlans);
        checkBox_showOlderPlans.setChecked(AppConfig.Settings._showOldPlans);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.checkBox_showOlderPlans) {
            item.setChecked(!item.isChecked());
            AppConfig.Settings._showOldPlans = item.isChecked();
            AppConfig.Settings.saveSettings();
            foodListAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}