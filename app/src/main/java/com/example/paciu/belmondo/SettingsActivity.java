package com.example.paciu.belmondo;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.paciu.belmondo.Profile.Profile;
import com.example.paciu.belmondo.Profile.Sex;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.DecimalMax;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.Arrays;
import java.util.List;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p/>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */

    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if(preference instanceof ListPreference){
                ListPreference listPreference = (ListPreference)preference;
                int index = listPreference.findIndexOfValue(stringValue);
                if(index >= 0)listPreference.setValueIndex(index);
                listPreference.setSummary(index >= 0 ? listPreference.getEntries()[index] : null);
            }
            else
                preference.setSummary(stringValue);
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        bindPreferenceSummaryToValue(preference, sBindPreferenceSummaryToValueListener, "");
    }

    private static void bindPreferenceSummaryToValue(Preference preference, Preference.OnPreferenceChangeListener preferenceChangeListener, String defaultValue) {
        preference.setOnPreferenceChangeListener(preferenceChangeListener);
        Object value;
        if(preference instanceof SwitchPreference){
            value = PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getBoolean(preference.getKey(), Boolean.valueOf(defaultValue));
        } else {
            value = PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), defaultValue);
        }
        preferenceChangeListener.onPreferenceChange(preference, value);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.title_activity_settings);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || ProfilePreferenceFragment.class.getName().equals(fragmentName)
                || UnitsPreferenceFragment.class.getName().equals(fragmentName);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class PreferenceFragmentWithBack extends PreferenceFragment{
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if(id == android.R.id.home){
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class ProfilePreferenceFragment extends PreferenceFragmentWithBack implements Preference.OnPreferenceClickListener, TextWatcher, ValidationListener{
        private Validator validator;

        @NotEmpty(sequence = 1, messageResId = R.string.empty_field)
        @Length(sequence = 2, max=4)
        @DecimalMin(sequence = 3, value = NewProfileActivity.MIN_WEIGHT, messageResId = R.string.min_weight)
        @DecimalMax(sequence = 4, value = NewProfileActivity.MAX_WEIGHT, messageResId = R.string.max_weight)
        private EditText weightEditText;

        @NotEmpty(sequence = 1, messageResId = R.string.empty_field)
        @Length(sequence = 2, max=4)
        @DecimalMin(sequence = 3, value = NewProfileActivity.MIN_HEIGHT, messageResId = R.string.min_height)
        @DecimalMax(sequence = 4, value = NewProfileActivity.MAX_HEIGHT, messageResId = R.string.max_height)
        private EditText heightEditeText;

        private EditTextPreference weightEditTextPreference;
        private EditTextPreference heightEditTextPreference;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_profile);
            setHasOptionsMenu(true);

            validator = new Validator(this);
            validator.setValidationListener(this);

            Profile profile = AppProfileManager.getInstance().getProfile();
            String preferenceKeyPrefix = profile.getFirstName() + profile.getId() + profile.isOnline();

            Preference name = findPreference("profile_name");
            name.setKey(preferenceKeyPrefix + name.getKey());

            Preference birthdate = findPreference("profile_birthdate");
            name.setKey(preferenceKeyPrefix + birthdate.getKey());


            final Preference weight = findPreference("profile_weight");
            weightEditTextPreference = ((EditTextPreference)weight);
            weightEditText = weightEditTextPreference.getEditText();
            weightEditText.addTextChangedListener(this);
            weight.setKey(preferenceKeyPrefix + weight.getKey());
            weight.setOnPreferenceClickListener(this);

            final Preference height = findPreference("profile_height");
            heightEditTextPreference = ((EditTextPreference)height);
            heightEditeText = heightEditTextPreference.getEditText();
            heightEditeText.addTextChangedListener(this);
            height.setKey(preferenceKeyPrefix + height.getKey());
            height.setOnPreferenceClickListener(this);

            Preference sex = findPreference("profile_sex");
            sex.setKey(preferenceKeyPrefix + sex.getKey());

            bindPreferenceSummaryToValue(name, profilePreferenceChangedListener, profile.getFirstName());
            bindPreferenceSummaryToValue(birthdate, profilePreferenceChangedListener, profile.getBirthDate());
            bindPreferenceSummaryToValue(weight, profilePreferenceChangedListener, String.valueOf(profile.getWeight()));
            bindPreferenceSummaryToValue(height, profilePreferenceChangedListener, String.valueOf(profile.getHeight()));
            bindPreferenceSummaryToValue(sex,  profilePreferenceChangedListener, profile.getSex() == Sex.MALE ? "true" : "false");
        }

        private static Preference.OnPreferenceChangeListener profilePreferenceChangedListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                Profile profile = AppProfileManager.getInstance().getProfile();
                if(preference.getKey().contains("weight")){
                    if(((EditTextPreference) preference).getEditText().getError() == null) {
                        profile.setWeight(preference.getContext(),  Integer.valueOf(newValue.toString()));
                    } else {
                        return false;
                    }
                } else if (preference.getKey().contains("height")){
                    if(((EditTextPreference)preference).getEditText().getError() == null) {
                        profile.setHeight(preference.getContext(), Integer.valueOf(newValue.toString()));
                    } else {
                        return false;
                    }
                } else if (preference.getKey().contains("sex")){
                    profile.setSex(preference.getContext(), Sex.fromInt((Boolean) newValue ? 0 : 1));
                    preference.setSummary(profile.getSex().toString());
                    ((SwitchPreference)preference).setChecked(profile.getSex().equals(Sex.MALE));
                    return true;
                }
                preference.setSummary(newValue.toString());
                return true;
            }
        };

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            clearErrors();
            validator.validate(false);
        }

        @Override
        public void onValidationSucceeded() {
            clearErrors();
        }

        @Override
        public void onValidationFailed(List<ValidationError> errors) {
            View view = errors.get(0).getView();
            String message = errors.get(0).getCollatedErrorMessage(heightEditTextPreference.getContext()).split("\\r?\\n")[0];
            if(view.equals(weightEditText)){
                weightEditText.setError(message);
            } else if( view.equals(heightEditeText)){
                heightEditeText.setError(message);
            }
        }

        protected void clearErrors(){
            weightEditText.setError(null);
            heightEditeText.setError(null);
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            if(preference instanceof EditTextPreference){
                if(preference.isEnabled()){
                    EditTextPreference e = (EditTextPreference)preference;
                    e.getEditText().setText(preference.getSummary());
                    return true;
                }
            }
            return false;
        }
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class UnitsPreferenceFragment extends PreferenceFragmentWithBack{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_units);
            setHasOptionsMenu(true);

            Profile profile = AppProfileManager.getInstance().getProfile();
            String keyPrefix = profile.getFirstName() + profile.getId() + profile.isOnline();

            Preference speed = findPreference("units_speed");
            speed.setKey(keyPrefix + speed.getKey());

            Preference distance = findPreference("units_distance");
            distance.setKey(keyPrefix  + distance.getKey());

            bindPreferenceSummaryToValue(speed);
            bindPreferenceSummaryToValue(distance);
        }

        public static int getSpeedUnitIndex(Context context) {
            String speedUnitString = getStringFromPreferences(context, "units_speed");
            String[] array = context.getResources().getStringArray(R.array.speedUnits);
            int index = Arrays.asList(array).indexOf(speedUnitString);
            return index >= 0 ? index : 0;
        }

        public static int getDistanceUnitIndex(Context context) {
            String distanceUnitString = getStringFromPreferences(context, "units_distance");
            String[] array = context.getResources().getStringArray(R.array.distanceUnit);
            int index = Arrays.asList(array).indexOf(distanceUnitString);
            return index >= 0 ? index : 0;
        }

        private static String getStringFromPreferences(Context context, String key){
            Profile profile = AppProfileManager.getInstance().getProfile();
            String keyPrefix = profile.getFirstName() + profile.getId() + profile.isOnline();
            return PreferenceManager.getDefaultSharedPreferences(context).getString(keyPrefix + key, "");
        }
    }
}
