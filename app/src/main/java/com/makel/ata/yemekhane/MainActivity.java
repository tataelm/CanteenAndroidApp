package com.makel.ata.yemekhane;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.makel.ata.yemekhane.entities.FoodPlan;
import com.makel.ata.yemekhane.fragments.DailyMealFragment;

import androidx.annotation.NonNull;
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
import java.util.Collections;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private static MainActivity instance;
    public static MainActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dailymeal, R.id.navigation_dashboard)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int k = 3;
            }
        });


        new ReceiveFoodPlan().execute();
        AppConfig.Settings.loadSettings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.button_getMenu) {
            new ReceiveFoodPlan().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("StaticFieldLeak")
    public class ReceiveFoodPlan extends AsyncTask {
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
                URL Url = new URL(AppConfig.BASE_URL);
                URLConnection urlConnection = Url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String str = "";
                str = bufferedReader.readLine();
                return str;
            } catch (Exception e) {
                e.printStackTrace();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            try {

                if (o == null) return;

                JSONArray jsonArr = new JSONArray(o.toString());

                for (int i = 0; i < jsonArr.length(); i++) {
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

                Collections.sort(AppConfig._listFoodPlan);

                DailyMealFragment.getInstance().setTodaysMealPlan();

                progressDialog.setMessage("Başarılı");
                Thread.sleep(1000);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            } catch (Exception e) {
                Log.e("ReceiveFoodPlan", e.getMessage());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

        }
    }


}