package com.example.paciu.belmondo;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.CollapsingToolbarLayout;

import com.example.paciu.belmondo.DataSource.Profiles.Profile;
import com.example.paciu.belmondo.DataSource.Profiles.ProfileDataSource;
import com.example.paciu.belmondo.DataSource.Profiles.Validators.BirthdateValidator;
import com.example.paciu.belmondo.Validators.NumberValidator;
import com.example.paciu.belmondo.DataSource.Profiles.Validators.WeightAndHeightValidator;
import com.example.paciu.belmondo.ViewsExtends.TextInputLayoutWithHideError;
import com.example.paciu.belmondo.utils.DateUtils;
import com.example.paciu.belmondo.utils.NumberUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class NewProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private static final float MIN_WEIGHT = 1;
    private static final float MAX_WEIGHT = 500;
    private static final float MIN_HEIGHT = 20;
    private static final float MAX_HEIGHT = 300;

    private EditText pickDateEditText;
    private EditText weightEditText;
    private EditText heightEditText;

    private TextInputLayoutWithHideError pickDateTextInputLayout;
    private TextInputLayoutWithHideError weightTextInputLayout;
    private TextInputLayoutWithHideError heightTextInputLayout;

    private String profileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeAppFullScreen();
        setContentView(R.layout.new_profile_pane);

        profileName = getIntent().getStringExtra("profile_name");

        Toolbar toolbarProfileName = (Toolbar)findViewById(R.id.toolbar);
        toolbarProfileName.setTitle(profileName);


        Toolbar toolbarCreateProfile = (Toolbar)findViewById(R.id.toolbar2);
        toolbarCreateProfile.setTitle(getString(R.string.your_profile_title));
        setSupportActionBar(toolbarCreateProfile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setExpandedTitleColor(Color.argb(255, 255, 255, 255));
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.argb(0, 255, 255, 255));
        collapsingToolbarLayout.setTitle(profileName);


        pickDateEditText = (EditText)findViewById(R.id.date_pick_edit_text);
        pickDateEditText.setOnClickListener(this);
        pickDateEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateBirthdateAndSetError();
            }
        });

        weightEditText = (EditText)findViewById(R.id.weight_edit_text);
        weightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateWeightAndSetError();
            }
        });

        heightEditText = (EditText)findViewById(R.id.height_edit_text);
        heightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateHeightAndSetError();
            }
        });

        pickDateTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_pick_date);

        weightTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_weight);
        heightTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_height);

        FloatingActionButton applyButton = (FloatingActionButton)findViewById(R.id.apply_fab);
        applyButton.setOnClickListener(this);
    }

    protected void makeAppFullScreen(){
        tryHideSupportActionBar();
    }

    protected void tryHideSupportActionBar(){
        try {
            getSupportActionBar().hide();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    protected boolean validateHeightAndSetError(){

        WeightAndHeightValidator validator = new WeightAndHeightValidator(new Float(MIN_HEIGHT), new Float(MAX_HEIGHT));
        try{
            validator.validate(new Float(getProfileHeightFromEditText()));
            heightTextInputLayout.setError(null);
        } catch (NumberValidator.NumberOutOfRangeException e) {
            heightTextInputLayout.setError(String.format(getString(R.string.height_must_be_between), MIN_HEIGHT, MAX_HEIGHT));
            e.printStackTrace();
        } catch (NumberUtils.NotAcceptableTypeException e) {
            heightTextInputLayout.setError(String.format(getString(R.string.bad_number_format),getString(R.string.float_point)));
            e.printStackTrace();
        } catch (NumberFormatException e){
            heightTextInputLayout.setError(getString(R.string.empty_field));
            e.printStackTrace();
        }
        return true;
    }

    protected void validateWeightAndSetError(){
        WeightAndHeightValidator validator = new WeightAndHeightValidator(new Float(MIN_WEIGHT), new Float(MAX_WEIGHT));
        try{
            validator.validate(new Float(getProfileWeightFromEditText()));
            weightTextInputLayout.setError(null);
        } catch (NumberValidator.NumberOutOfRangeException e) {
            weightTextInputLayout.setError(String.format(getString(R.string.weight_must_be_between), MIN_WEIGHT, MAX_WEIGHT));
            e.printStackTrace();
        } catch (NumberUtils.NotAcceptableTypeException e) {
            weightTextInputLayout.setError(String.format(getString(R.string.bad_number_format),getString(R.string.float_point)));
            e.printStackTrace();
        } catch (NumberFormatException e){
            weightTextInputLayout.setError(getString(R.string.empty_field));
            e.printStackTrace();
        }
    }

    protected float getFloatValueFromEditText(EditText edittext) throws NumberFormatException{
        String stringValue = getEditTextTrimmedText(edittext);
        float weight = Float.parseFloat(stringValue);
        return weight;
    }

    protected void validateBirthdateAndSetError(){
        try{
            BirthdateValidator validator = new BirthdateValidator(getString(R.string.date_format));
            validator.validate(getStringDateFromPickDateEditText());
            pickDateTextInputLayout.setError(null);

        } catch (ParseException e) {
            pickDateTextInputLayout.setError(String.format(getString(R.string.bad_date_format), getString(R.string.date_format)));
            e.printStackTrace();
        } catch (BirthdateValidator.DateInTheFutureException e) {
            pickDateTextInputLayout.setError(getString(R.string.date_must_be_before_now));
            e.printStackTrace();
        }
    }

    protected String getStringDateFromPickDateEditText(){
        return getEditTextTrimmedText(pickDateEditText);
    }

    protected String getEditTextTrimmedText(EditText edittext){
        return edittext.getText().toString().trim();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    @Override
    public void onClick(final View v) {

        if(v.getId() == R.id.date_pick_edit_text){
            createAndShowDatePickerDialog(v);
        }

        if(v.getId() == R.id.apply_fab){
            validateBirthdateAndSetError();
            validateWeightAndSetError();
            validateHeightAndSetError();

            if(!pickDateTextInputLayout.hasError() && !weightTextInputLayout.hasError() && !heightTextInputLayout.hasError()){
                try {
                    tryCreateNewProfileAndInsertIntoDatabase();

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivityFromLeftToRightAndFinish(intent);

                } catch (Exception e) {

                    new android.support.v7.app.AlertDialog.Builder(NewProfileActivity.this)
                            .setTitle(getString(R.string.error_occured))
                            .setMessage(e.getMessage())
                            .setPositiveButton("Ok", null)
                            .show();
                }
            }
        }
    }

    protected void startActivityFromLeftToRightAndFinish(Intent intent){
        startActivityFromLeftToRight(intent);
        finish();
    }

    protected void startActivityFromLeftToRight(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    protected void createAndShowDatePickerDialog(final View v){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog pickerDialog = new DatePickerDialog(NewProfileActivity.this, R.style.DatePickerDialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ((EditText)v).setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        pickerDialog.show();
    }

    protected void tryCreateNewProfileAndInsertIntoDatabase() throws Exception {
        try{
            createNewProfileAndInsertIntoDatabase();
        } catch (ParseException parseException){
            parseException.printStackTrace();
            throw new Exception(String.format(getString(R.string.bad_date_format), getString(R.string.date_format)));

        } catch (SQLiteConstraintException constraintException){
            constraintException.printStackTrace();
            throw new Exception(getString(R.string.profile_exists));

        } catch (SQLiteException ex){
            ex.printStackTrace();
            throw new Exception(getString(R.string.sqlite_error));
        }
    }

    protected void createNewProfileAndInsertIntoDatabase() throws ParseException {
        Profile profile = new Profile(profileName, getProfileWeightFromEditText(), getProfileHeightFromEditText(), getStringDateFromPickDateEditText());

        ProfileDataSource profileDataSource = new ProfileDataSource(getApplicationContext());
        profileDataSource.insertNewWithOpenAndCloseDB(profile);
    }

    protected float getProfileWeightFromEditText(){
        return getFloatValueFromEditText(weightEditText);
    }

    protected float getProfileHeightFromEditText(){
        return getFloatValueFromEditText(heightEditText);
    }

}
