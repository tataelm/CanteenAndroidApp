package com.makel.ata.yemekhane.entities;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodPlan {



    private int _id;
    private Date _date;
    private String _food1;
    private String _food2;
    private String _food3;
    private String _food4;
    private String _food5;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public Date get_date() {
        return _date;
    }

    public void set_date(String strDate) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        try {
            _date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String get_food1() {
        return _food1;
    }

    public void set_food1(String _food1) {
        this._food1 = _food1;
    }

    public String get_food2() {
        return _food2;
    }

    public void set_food2(String _food2) {
        this._food2 = _food2;
    }

    public String get_food3() {
        return _food3;
    }

    public void set_food3(String _food3) {
        this._food3 = _food3;
    }

    public String get_food4() {
        return _food4;
    }

    public void set_food4(String _food4) {
        this._food4 = _food4;
    }

    public String get_food5() {
        return _food5;
    }

    public void set_food5(String _food5) {
        this._food5 = _food5;
    }
}
