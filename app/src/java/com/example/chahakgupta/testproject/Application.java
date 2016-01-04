package com.example.chahakgupta.testproject;

import com.parse.Parse;

/**
 * Created by CHAHAK GUPTA on 22-10-2015.
 */
public class Application extends android.app.Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "cb0ypHVrFQtiOYoIDP2sVhwqp7ALB1cHrAUjBWUJ", "rINUwRI3gwgTbQ61oZNnUqraahcxJE5gv9BRyJAT");

    }
}
