package com.klinker.android.twitter_l.utils.redirects;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.klinker.android.twitter_l.settings.AppSettings;
import com.klinker.android.twitter_l.ui.MainActivity;

/**
 * Created by luke on 2/22/14.
 */
public class RedirectToDMs extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        overridePendingTransition(0,0);

        SharedPreferences sharedPrefs = getSharedPreferences("com.klinker.android.twitter_world_preferences",
                Context.MODE_WORLD_READABLE + Context.MODE_WORLD_WRITEABLE);

        int currentAccount = sharedPrefs.getInt("current_account", 1);

        int page1Type = sharedPrefs.getInt("account_" + currentAccount + "_page_1", AppSettings.PAGE_TYPE_NONE);
        int page2Type = sharedPrefs.getInt("account_" + currentAccount + "_page_2", AppSettings.PAGE_TYPE_NONE);

        int extraPages = 0;
        if (page1Type != AppSettings.PAGE_TYPE_NONE) {
            extraPages++;
        }

        if (page2Type != AppSettings.PAGE_TYPE_NONE) {
            extraPages++;
        }

        Intent dm = new Intent(this, MainActivity.class);

        sharedPrefs.edit().putBoolean("open_a_page", true).commit();
        sharedPrefs.edit().putInt("open_what_page", extraPages + 2).commit();

        finish();

        overridePendingTransition(0,0);

        startActivity(dm);
    }
}