package com.example.paciu.belmondo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.paciu.belmondo.Logging.LoginOffline.LoginOfflineButtonBelmondo;
import com.example.paciu.belmondo.Profile.ProfileModelAdapter;
import com.example.paciu.belmondo.SqliteDataSource.Profiles.NoSuchProfileException;
import com.example.paciu.belmondo.SqliteDataSource.Profiles.ProfileDataSource;
import com.example.paciu.belmondo.SqliteDataSource.Profiles.ProfileModel;
import com.example.paciu.belmondo.ViewsExtends.TextInputLayoutWithHideError;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ProfileChooseActivity extends AppCompatActivity implements View.OnClickListener, Validator.ValidationListener, TextWatcher{

    private static final int MIN_LENGTH_OF_PROFILE_NAME = 3;
    private static final int MAX_LENGTH_OF_PROFILE_NAME = 20;

    @NotEmpty(sequence = 1, trim = true, messageResId = R.string.empty_field)
    @Length(sequence = 2, min=MIN_LENGTH_OF_PROFILE_NAME, max=MAX_LENGTH_OF_PROFILE_NAME, messageResId = R.string.profile_name_length_validation)
    private EditText profileNameEditText;
    private TextInputLayoutWithHideError profileNameTextInputLayout;

    private Validator validator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeAppFullScreen();
        setContentView(R.layout.activity_profile_choose);

        validator = new Validator(this);
        validator.setValidationListener(this);

        Button enterProfileButton = (Button) findViewById(R.id.enter_profile_button);
        if (enterProfileButton != null) {
            enterProfileButton.setOnClickListener(this);
        }

        profileNameEditText = (EditText) findViewById(R.id.profile_name_edit_text);
        if (profileNameEditText != null) {
            profileNameEditText.addTextChangedListener(this);
        }
        profileNameTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_name);
    }

    protected void makeAppFullScreen(){
        tryHideSupportActionBar();
    }

    protected void tryHideSupportActionBar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.enter_profile_button){
            try {
                validateProfileNameAndTryLogin();
                if(!profileNameTextInputLayout.hasError()) {
                    setResult(LoginOfflineButtonBelmondo.LoginResultEnum.toInteger(LoginOfflineButtonBelmondo.LoginResultEnum.SUCCEED));
                    finish();
                }
            } catch (NoSuchProfileException e) {
                startCreateProfileActivity();
            }
        }
    }

    protected void validateProfileNameAndTryLogin() throws NoSuchProfileException {
            validator.validate(false);
            if(!profileNameTextInputLayout.hasError()) {
                tryLoginProfile();
            }
    }

    protected void setProfileNameEditTextErrorAndRequestFocus(String errorMessage){
        profileNameTextInputLayout.setError(errorMessage);
        profileNameEditText.requestFocus();
    }

    protected void tryLoginProfile() throws NoSuchProfileException {
        ProfileDataSource profileDataSource = new ProfileDataSource(getApplicationContext());

        if(!profileDataSource.profileExistsWithOpenAndCloseDB(getProfileNameFromEditTrimmedText())){
            throw new NoSuchProfileException();
        } else {
            profileDataSource.openDB();
            ProfileModel loggedProfile = profileDataSource.getProfile(getProfileNameFromEditTrimmedText());
            profileDataSource.closeDB();

            ProfileModelAdapter profileModelAdapter = ProfileModelAdapter.getInstance();
            profileModelAdapter.setProfileModel(loggedProfile);
        }
    }

    protected void startCreateProfileActivity() {
        Intent intent = new Intent(this, NewProfileActivity.class);
        intent.putExtra("profile_name", getProfileNameFromEditTrimmedText());
        startActivityFromLeftToRightForResult(intent, LoginOfflineButtonBelmondo.LOGGING_OFFLINE_REQUEST);
    }

    protected void startActivityFromLeftToRightForResult(Intent intent, int requestCode){
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private String getProfileNameFromEditTrimmedText(){
        return profileNameEditText.getText().toString().trim();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(LoginOfflineButtonBelmondo.LoginResultEnum.fromInteger(resultCode) != null) {
            setResult(resultCode);
            finish();
        }
    }

    @Override
    public void onValidationSucceeded() {
        profileNameTextInputLayout.setError(null);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        ValidationError error = errors.get(0);
        View view = error.getView();
        String message = error.getCollatedErrorMessage(this).split("\\r?\\n")[0];
        if(view.getId() == R.id.profile_name_edit_text) {
            setProfileNameEditTextErrorAndRequestFocus(message);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        validator.validate(false);
    }
}
