package com.example.paciu.belmondo;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.paciu.belmondo.Logging.LoginOffline.LoginOfflineButtonBelmondo;
import com.example.paciu.belmondo.Profile.ProfileModelAdapter;
import com.example.paciu.belmondo.Profile.Sex;
import com.example.paciu.belmondo.SqliteDataSource.Profiles.ProfileDataSource;
import com.example.paciu.belmondo.SqliteDataSource.Profiles.ProfileModel;
import com.example.paciu.belmondo.ViewsExtends.TextInputLayoutWithHideError;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Order;
import com.mobsandgeeks.saripaar.annotation.Past;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

public class NewProfileActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener, TextWatcher{

    public static final int MIN_WEIGHT = 20;
    public static final int MAX_WEIGHT = 300;
    public static final int MIN_HEIGHT = 20;
    public static final int MAX_HEIGHT = 300;

    @Order(1)
    @NotEmpty(sequence = 1, trim = true, messageResId = R.string.empty_field)
    @Past(sequence = 2, messageResId = R.string.date_must_be_before_now, dateFormat = "dd/MM/yyyy")
    private EditText pickDateEditText;

    @Order(2)
    @NotEmpty(sequence = 1, trim = true, messageResId = R.string.empty_field)
    @Length(sequence = 2, max=4)
    @Min(sequence = 3,value = MIN_WEIGHT, messageResId = R.string.min_weight)
    @Max(sequence = 4, value = MAX_WEIGHT, messageResId = R.string.max_weight)
    private EditText weightEditText;

    @Order(3)
    @NotEmpty(sequence = 1, trim = true, messageResId = R.string.empty_field)
    @Length(sequence = 2, max=4)
    @Min(sequence = 3, value = MIN_HEIGHT, messageResId = R.string.min_height)
    @Max(sequence = 4, value = MAX_HEIGHT, messageResId = R.string.max_height)
    private EditText heightEditText;

    private TextInputLayoutWithHideError pickDateTextInputLayout;
    private TextInputLayoutWithHideError weightTextInputLayout;
    private TextInputLayoutWithHideError heightTextInputLayout;

    private RadioButton maleRadioButton;
    private RadioButton femaleRadioButton;
    private String profileName;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeAppFullScreen();
        setContentView(R.layout.new_profile_pane);

        validator = new Validator(this);
        validator.setValidationMode(Validator.Mode.IMMEDIATE);
        validator.setValidationListener(this);

        profileName = getIntent().getStringExtra("profile_name");

        Toolbar toolbarProfileName = (Toolbar)findViewById(R.id.toolbar);
        if (toolbarProfileName != null) {
            toolbarProfileName.setTitle(profileName);
        }

        Toolbar toolbarCreateProfile = (Toolbar)findViewById(R.id.toolbar2);
        if (toolbarCreateProfile != null) {
            toolbarCreateProfile.setTitle(getString(R.string.your_profile_title));
            setSupportActionBar(toolbarCreateProfile);
        }
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        if (collapsingToolbarLayout != null) {
            collapsingToolbarLayout.setExpandedTitleColor(Color.argb(255, 255, 255, 255));
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.argb(0, 255, 255, 255));
            collapsingToolbarLayout.setTitle(profileName);
        }

        pickDateEditText = (EditText)findViewById(R.id.date_pick_edit_text);
        if (pickDateEditText != null) {
            pickDateEditText.setOnClickListener(this);
            pickDateEditText.addTextChangedListener(this);
        }

        weightEditText = (EditText)findViewById(R.id.weight_edit_text);
        if (weightEditText != null) {
            weightEditText.addTextChangedListener(this);
        }

        heightEditText = (EditText)findViewById(R.id.height_edit_text);
        if (heightEditText != null) {
            heightEditText.addTextChangedListener(this);
        }

        pickDateTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_pick_date);

        weightTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_weight);
        heightTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_height);

        maleRadioButton = (RadioButton)findViewById(R.id.maleRadioButton);
        if (maleRadioButton != null) {
            maleRadioButton.setOnClickListener(this);
        }
        femaleRadioButton = (RadioButton)findViewById(R.id.femaleRadioButton);
        if (femaleRadioButton != null) {
            femaleRadioButton.setOnClickListener(this);
        }

        FloatingActionButton applyButton = (FloatingActionButton)findViewById(R.id.apply_fab);
        if (applyButton != null) {
            applyButton.setOnClickListener(this);
        }
    }

    protected void makeAppFullScreen(){
        tryHideSupportActionBar();
    }

    protected void tryHideSupportActionBar(){
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }

    protected int getFloatValueFromEditText(EditText edittext) throws NumberFormatException{
        String stringValue = getEditTextTrimmedText(edittext);
        int weight = Integer.parseInt(stringValue);
        return weight;
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
        if(v.getId() == maleRadioButton.getId()){
            femaleRadioButton.setChecked(false);
        }
        else if(v.getId() == femaleRadioButton.getId()){
            maleRadioButton.setChecked(false);
        }
        else if(v.getId() == R.id.date_pick_edit_text){
            createAndShowDatePickerDialog(v);
        }
        else if(v.getId() == R.id.apply_fab){
            clearErrors();
            validator.validate(false);
            if(!pickDateTextInputLayout.hasError() && !weightTextInputLayout.hasError() && !heightTextInputLayout.hasError()){
                try {
                    tryCreateNewProfileAndInsertIntoDatabase();
                    setResult(LoginOfflineButtonBelmondo.LoginResultEnum.toInteger(LoginOfflineButtonBelmondo.LoginResultEnum.SUCCEED));
                    finish();
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
            insertNewProfileIntoDatabaseAndInitOfflineProfile();
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

    protected void insertNewProfileIntoDatabaseAndInitOfflineProfile() throws ParseException {
        Sex sex = maleRadioButton.isChecked() ? Sex.MALE : Sex.FEMALE;
        ProfileModel profile = new ProfileModel(profileName, getProfileWeightFromEditText(), getProfileHeightFromEditText(), getStringDateFromPickDateEditText(), sex);

        ProfileDataSource profileDataSource = new ProfileDataSource(getApplicationContext());
        profileDataSource.openDB();
        profileDataSource.insertNew(profile);
        long id = profileDataSource.getIdByName(profileName);
        profileDataSource.closeDB();

        profile.setId((int)id);
        ProfileModelAdapter profileModelAdapter = ProfileModelAdapter.getInstance();
        profileModelAdapter.setProfileModel(profile);
    }

    protected int getProfileWeightFromEditText(){
        return getFloatValueFromEditText(weightEditText);
    }

    protected int getProfileHeightFromEditText(){
        return getFloatValueFromEditText(heightEditText);
    }

    @Override
    public void onValidationSucceeded() {
        clearErrors();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for(ValidationError error : errors) {
            View view = error.getView();
            final String errorMessage = error.getCollatedErrorMessage(this).split("\\r?\\n")[0];
            int id = view.getId();
            if (id == R.id.date_pick_edit_text) {
                pickDateTextInputLayout.setError(errorMessage);
            } else if (id == R.id.weight_edit_text) {
                weightTextInputLayout.setError(errorMessage);
            } else if (id == R.id.height_edit_text) {
                heightTextInputLayout.setError(errorMessage);
            }
            Log.i("Validation", error.getCollatedErrorMessage(this));
        }
    }

    protected void clearErrors(){
        pickDateTextInputLayout.setError(null);
        weightTextInputLayout.setError(null);
        heightTextInputLayout.setError(null);
        pickDateTextInputLayout.setErrorEnabled(false);
        weightTextInputLayout.setErrorEnabled(false);
        heightTextInputLayout.setErrorEnabled(false);
    }

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
}
