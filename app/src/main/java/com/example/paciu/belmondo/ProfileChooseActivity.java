package com.example.paciu.belmondo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.paciu.belmondo.DataSource.Profiles.ProfileDataSource;
import com.example.paciu.belmondo.DataSource.Profiles.Validators.ProfileNameValidator;
import com.example.paciu.belmondo.Validators.StringValidator;
import com.example.paciu.belmondo.ViewsExtends.TextInputLayoutWithHideError;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class ProfileChooseActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int MIN_LENGTH_OF_PROFILE_NAME = 3;
    private static final int MAX_LENGTH_OF_PROFILE_NAME = 20;

    private Button enterProfileButton;
    private EditText profileNameEditText;
    private TextInputLayoutWithHideError profileNameTextInputLayout;

    private TextView pleaseFillInformationTextView;
    private boolean logged;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        makeAppFullScreen();

        setContentView(R.layout.activity_profile_choose);

        enterProfileButton = (Button) findViewById(R.id.enter_profile_button);
        enterProfileButton.setOnClickListener(this);

        profileNameEditText = (EditText) findViewById(R.id.profile_name_edit_text);
        profileNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                validateProfileNameAndSetErrorIfExists();
            }
        });

        profileNameTextInputLayout = (TextInputLayoutWithHideError)findViewById(R.id.input_layout_profile_name);
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

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.enter_profile_button){
            try {
                validateProfileNameAndTryLogin();
            } catch (NoSuchProfileException e) {
                startCreateProfileActivity();
            }
        }
    }

    protected void validateProfileNameAndTryLogin() throws NoSuchProfileException {
            validateProfileNameAndSetErrorIfExists();

            if(!profileNameTextInputLayout.hasError()) {
                tryLoginProfile();
            }
    }

    protected void validateProfileNameAndSetErrorIfExists() {
        ProfileNameValidator validator = new ProfileNameValidator(MIN_LENGTH_OF_PROFILE_NAME, MAX_LENGTH_OF_PROFILE_NAME);
        try {
            validator.validate(getProfileNameFromEditTrimmedText());
            profileNameTextInputLayout.setError(null);
        } catch (StringValidator.StringEmptyValidationException e) {
            setProfileNameEditTextErrorAndRequestFocus(getString(R.string.empty_field));
            e.printStackTrace();

        } catch (StringValidator.StringTooShortValidationException e) {
            setProfileNameEditTextErrorAndRequestFocus(String.format(getString(R.string.at_least_chars), MIN_LENGTH_OF_PROFILE_NAME));
            e.printStackTrace();

        } catch (StringValidator.StringTooLongValidationException e) {
            e.printStackTrace();
        }
    }

    protected void setProfileNameEditTextErrorAndRequestFocus(String errorMessage){
        profileNameTextInputLayout.setError(errorMessage);
        profileNameEditText.requestFocus();
    }

    protected void tryLoginProfile() throws NoSuchProfileException {
        ProfileDataSource profileDataSource = new ProfileDataSource(getApplicationContext());

        if(profileDataSource.profileExistsWithOpenAndCloseDB(getProfileNameFromEditTrimmedText())){
            startMainActivityAndFinish();
        } else throw new NoSuchProfileException();
    }

    protected void startCreateProfileActivity() {
        Intent intent = new Intent(this, NewProfileActivity.class);
        intent.putExtra("profile_name", getProfileNameFromEditTrimmedText());
        startActivityFromLeftToRight(intent);
    }

    protected void startMainActivityAndFinish(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivityFromLeftToRightAndFinish(intent);
    }

    protected void startActivityFromLeftToRightAndFinish(Intent intent){
        startActivityFromLeftToRight(intent);
        finish();
    }


    protected void startActivityFromLeftToRight(Intent intent){
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }




    private String getProfileNameFromEditTrimmedText(){
        return profileNameEditText.getText().toString().trim();
    }

}
