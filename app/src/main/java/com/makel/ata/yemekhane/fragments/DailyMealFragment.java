package com.makel.ata.yemekhane.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.makel.ata.yemekhane.AppConfig;
import com.makel.ata.yemekhane.R;
import com.makel.ata.yemekhane.entities.FoodPlan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DailyMealFragment extends Fragment {

    LinearLayout linearLayout_food1, linearLayout_food2, linearLayout_food3, linearLayout_food4, linearLayout_food5;
    TextView textView_date, textView_food1, textView_food2, textView_food3, textView_food4, textView_food5;

    private FoodPlan _foodPlan = null;

    @SuppressLint("StaticFieldLeak")
    private static DailyMealFragment instance;

    public static DailyMealFragment getInstance() {
        return instance;
    }

    @SuppressLint("CutPasteId")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View root = inflater.inflate(R.layout.fragment_dailymeal, container, false);
        instance = this;

        _foodPlan = AppConfig._foodPlan;

        linearLayout_food1 = root.findViewById(R.id.linearLayout_food1);
        linearLayout_food2 = root.findViewById(R.id.linearLayout_food2);
        linearLayout_food3 = root.findViewById(R.id.linearLayout_food3);
        linearLayout_food4 = root.findViewById(R.id.linearLayout_food4);
        linearLayout_food5 = root.findViewById(R.id.linearLayout_food5);

        textView_date = root.findViewById(R.id.textView_date);
        textView_food1 = root.findViewById(R.id.textView_food1);
        textView_food2 = root.findViewById(R.id.textView_food2);
        textView_food3 = root.findViewById(R.id.textView_food3);
        textView_food4 = root.findViewById(R.id.textView_food4);
        textView_food5 = root.findViewById(R.id.textView_food5);

        setNoMealFindLayout();

        if (_foodPlan == null) setTodaysMealPlan();
        else displayFoodPlan();


        return root;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.findItem(R.id.checkBox_showOlderPlans).setVisible(false);
    }

    private void setNoMealFindLayout() {
        textView_date.setText(R.string.yemek_plani_cekilemedi);
        linearLayout_food1.setVisibility(View.GONE);
        linearLayout_food2.setVisibility(View.GONE);
        linearLayout_food3.setVisibility(View.GONE);
        linearLayout_food4.setVisibility(View.GONE);
        linearLayout_food5.setVisibility(View.GONE);
    }

    public void setTodaysMealPlan() {


        Date currentTime = Calendar.getInstance().getTime();
        Calendar calendarCurrentTime = Calendar.getInstance();
        calendarCurrentTime.setTime(currentTime);

        //FoodPlan todaysFoodPlan = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            _foodPlan = AppConfig._listFoodPlan.stream()
                    .filter(foodPlan ->
                            foodPlan.get_date().get(Calendar.YEAR) == calendarCurrentTime.get(Calendar.YEAR) &&
                                    foodPlan.get_date().get(Calendar.DAY_OF_YEAR) == calendarCurrentTime.get(Calendar.DAY_OF_YEAR))
                    .findAny()
                    .orElse(null);
        } else {
            for (FoodPlan foodPlan : AppConfig._listFoodPlan) {
                if (foodPlan.get_date().get(Calendar.YEAR) == calendarCurrentTime.get(Calendar.YEAR) &&
                        foodPlan.get_date().get(Calendar.DAY_OF_YEAR) == calendarCurrentTime.get(Calendar.DAY_OF_YEAR))
                    _foodPlan = foodPlan;
            }
        }

        displayFoodPlan();
    }

    private void displayFoodPlan() {

        if (_foodPlan == null) {
            setNoMealFindLayout();
            return;
        }

        textView_date.setText(AppConfig._simpleDateFormat.format(_foodPlan.get_date().getTime()));
        AppConfig._foodPlan = _foodPlan;

        if (!_foodPlan.get_food1().equals("null")) {
            linearLayout_food1.setVisibility(View.VISIBLE);
            textView_food1.setText(_foodPlan.get_food1().trim());
        }
        if (!_foodPlan.get_food2().equals("null")) {
            linearLayout_food2.setVisibility(View.VISIBLE);
            textView_food2.setText(_foodPlan.get_food2().trim());
        }
        if (!_foodPlan.get_food3().equals("null")) {
            linearLayout_food3.setVisibility(View.VISIBLE);
            textView_food3.setText(_foodPlan.get_food3().trim());
        }
        if (!_foodPlan.get_food4().equals("null")) {
            linearLayout_food4.setVisibility(View.VISIBLE);
            textView_food4.setText(_foodPlan.get_food4().trim());
        }
        if (!_foodPlan.get_food5().equals("null")) {
            linearLayout_food5.setVisibility(View.VISIBLE);
            textView_food5.setText(_foodPlan.get_food5().trim());
        }

    }


}