package com.makel.ata.yemekhane;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.makel.ata.yemekhane.entities.FoodPlan;
import com.makel.ata.yemekhane.ui.dailymeal.DailyMealFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dailymeal, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        new ReceiveFoodPlan().execute();
    }

    public class ReceiveFoodPlan extends AsyncTask {
        private final String get_FoodPlans = "http://192.168.1.13:5002/foodplan";


        private ProgressDialog progressDialog = null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Yemekhane bilgisi çekiliyor");
            progressDialog.setIndeterminate(true);
            progressDialog.show();

        }

        @Override
        protected Object doInBackground(Object[] objects) {

            try {
                URL Url = new URL(get_FoodPlans);
                URLConnection urlConnection = Url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                str = bufferedReader.readLine();
                return str;
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            try {

                JSONArray jsonArr = new JSONArray(o.toString());

                for (int i = 0; i < jsonArr.length(); i++)
                {
                    FoodPlan foodPlan = new FoodPlan();
                    foodPlan.set_id(Integer.parseInt(jsonArr.getJSONObject(i).getString("id")));
                    foodPlan.set_date(jsonArr.getJSONObject(i).getString("date"));
                    foodPlan.set_food1(jsonArr.getJSONObject(i).getString("food1"));
                    foodPlan.set_food2(jsonArr.getJSONObject(i).getString("food2"));
                    foodPlan.set_food3(jsonArr.getJSONObject(i).getString("food3"));
                    foodPlan.set_food4(jsonArr.getJSONObject(i).getString("food4"));
                    foodPlan.set_food5(jsonArr.getJSONObject(i).getString("food5"));

                    AppConfig._listFoodPlan.add(foodPlan);
                }

                DailyMealFragment.getInstance().setTodaysMealPlan();

                progressDialog.setMessage("Başarılı");
                Thread.sleep(1000);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
            catch (Exception e)
            {
                String ee = e.getMessage();
            }

        }
    }



}