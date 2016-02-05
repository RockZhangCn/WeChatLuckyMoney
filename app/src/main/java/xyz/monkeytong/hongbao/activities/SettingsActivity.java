package xyz.monkeytong.hongbao.activities;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.provider.Settings;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.View;

import xyz.monkeytong.hongbao.R;
import xyz.monkeytong.hongbao.utils.UpdateTask;

/**
 * Created by Zhongyi on 1/19/16.
 * Settings page.
 */
public class SettingsActivity extends PreferenceActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.i("SettingsActivity", "activity oncreated.");
        loadUI();
        setPrefListeners();
    }

    private void setPrefListeners()
    {
        // Check for updates
        Preference updatePref = findPreference("pref_etc_check_update");
        updatePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            public boolean onPreferenceClick(Preference preference)
            {
                new UpdateTask(getApplicationContext(), true).update();
                return false;
            }
        });

        // Open issue
        Preference issuePref = findPreference("pref_etc_issue");
        issuePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            public boolean onPreferenceClick(Preference preference)
            {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/geeeeeeeeek/WeChatLuckyMoney/issues"));
                browserIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                SettingsActivity.this.startActivity(browserIntent);
                return false;
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState)
    {
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState, persistentState);
    }

    private void loadUI()
    {
        addPreferencesFromResource(R.xml.preferences);

        // Get rid of the fucking additional padding
        getListView().setPadding(0, 0, 0, 0);
        getListView().setBackgroundColor(0xfffaf6f1);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    public void performBack(View view)
    {
        NavUtils.navigateUpFromSameTask(this);
        //super.onBackPressed();
    }

    public void enterAccessibilityPage(View view)
    {
        Intent mAccessibleIntent =
                new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(mAccessibleIntent);
    }
}
