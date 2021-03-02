package com.makel.ata.yemekhane.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.makel.ata.yemekhane.AppConfig;
import com.makel.ata.yemekhane.R;
import com.makel.ata.yemekhane.entities.FoodPlan;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.ViewHolder> {

    private final List<FoodPlan> listFoodPlan;
    private final List<FoodPlan> listFoodPlan_noOutdated;

    public FoodListAdapter(List<FoodPlan> items) {
        listFoodPlan = items;
        listFoodPlan_noOutdated = TrimOutdatedOptions();
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meal_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        List<FoodPlan> currentListFoodPlan = AppConfig.Settings._showOldPlans ? listFoodPlan : listFoodPlan_noOutdated;

        holder.item_date.setText(AppConfig._simpleDateFormat.format(currentListFoodPlan.get(position).get_date().getTime()));

        if (!currentListFoodPlan.get(position).get_food1().equals("null"))
            holder.item_food1.setText(currentListFoodPlan.get(position).get_food1().trim());
        else
            holder.item_food1.setText("---");

        if (!currentListFoodPlan.get(position).get_food2().equals("null"))
            holder.item_food2.setText(currentListFoodPlan.get(position).get_food2().trim());
        else
            holder.item_food2.setText("---");

        if (!currentListFoodPlan.get(position).get_food3().equals("null"))
            holder.item_food3.setText(currentListFoodPlan.get(position).get_food3().trim());
        else
            holder.item_food3.setText("---");

        if (!currentListFoodPlan.get(position).get_food4().equals("null"))
            holder.item_food4.setText(currentListFoodPlan.get(position).get_food4().trim());
        else
            holder.item_food4.setText("---");

        if (!currentListFoodPlan.get(position).get_food5().equals("null"))
            holder.item_food5.setText(currentListFoodPlan.get(position).get_food5().trim());
        else
            holder.item_food5.setText("---");

    }

    @Override
    public int getItemCount() {

        if (AppConfig.Settings._showOldPlans)
            return listFoodPlan.size();
        else
            return listFoodPlan_noOutdated.size();
    }

    private List<FoodPlan> TrimOutdatedOptions() {
        List<FoodPlan> trimmedListFoodPlan = new ArrayList<>();

        for (FoodPlan foodPlan : listFoodPlan) {
            if (!foodPlan.isOutdated()) trimmedListFoodPlan.add(foodPlan);
        }
       return trimmedListFoodPlan;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public final TextView item_date;
        public final TextView item_food1;
        public final TextView item_food2;
        public final TextView item_food3;
        public final TextView item_food4;
        public final TextView item_food5;

        public final TextView mContentView;


        public ViewHolder(View view) {
            super(view);
            mView = view;

            item_date = view.findViewById(R.id.item_date);
            item_food1 = view.findViewById(R.id.item_food1);
            item_food2 = view.findViewById(R.id.item_food2);
            item_food3 = view.findViewById(R.id.item_food3);
            item_food4 = view.findViewById(R.id.item_food4);
            item_food5 = view.findViewById(R.id.item_food5);

            mContentView = view.findViewById(R.id.content);
        }

        @NotNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}