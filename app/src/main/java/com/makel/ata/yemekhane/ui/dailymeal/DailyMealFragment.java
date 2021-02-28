package com.makel.ata.yemekhane.ui.dailymeal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.makel.ata.yemekhane.AppConfig;
import com.makel.ata.yemekhane.MainActivity;
import com.makel.ata.yemekhane.R;
import com.makel.ata.yemekhane.entities.FoodPlan;

public class DailyMealFragment extends Fragment {

    TextView textView;
    LinearLayout elementFood_container;

    @SuppressLint("StaticFieldLeak")
    private static DailyMealFragment instance;

    public static DailyMealFragment getInstance() {
        return instance;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dailymeal, container, false);
        instance = this;

        textView = root.findViewById(R.id.textView);
        elementFood_container = root.findViewById(R.id.elementFood_container);

        return root;
    }



    public void setTodaysMealPlan()
    {
        FoodPlan foodPlan = AppConfig._listFoodPlan.get(0);
        // textView.setText(foodPlan.get_food1());

       // setContentView(R.layout.activity_main);




        LinearLayout date = (LinearLayout) getView().findViewById(R.id.linearLayout_elementFood);



        elementFood_container.addView(date);
       // View date = inflater.inflate(R.layout.fragment_dailymeal, container, false);


    }


}