package com.example.usuario.pr6_profile.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.pr6_profile.R;
import com.example.usuario.pr6_profile.ui.grid_cat.GridCatsActivity;
import com.example.usuario.pr6_profile.ui.model.Avatar;
import com.example.usuario.pr6_profile.ui.utils.Constants;
import com.example.usuario.pr6_profile.ui.utils.IntentsUtils;
import com.example.usuario.pr6_profile.ui.utils.MessageUtils;
import com.example.usuario.pr6_profile.ui.utils.NetworkUtils;
import com.example.usuario.pr6_profile.ui.utils.ValidationUtils;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;

import static butterknife.OnTextChanged.Callback.TEXT_CHANGED;

public class MainActivity extends AppCompatActivity {
    private static final int RC_GRID_CATS_ACTIVITY = 1;
    private static final int DEFAULT_VALUE = -1;
    private static final String STATE_CAT_AVATAR = "STATE_CAT_AVATAR";
    private static final String EXTRA_CAT_AVATAR = "EXTRA_CAT_AVATAR";
    private static final String EXTRA_PROFILE = "EXTRA_PROFILE";
    private static final String EXTRA_AVATAR_POSITION = "EXTRA_AVATAR_POSITION";
    private static final String STATE_EDITION = "STATE_EDITION";
    private static int adapterPosition = -1;
    private int catTag;
    //vars
    //views
    //region

    @BindView(R.id.activity_main_txtName)
    EditText activityMainTxtName;
    @BindView(R.id.activity_main_txtEmail)
    EditText activityMainTxtEmail;
    @BindView(R.id.activity_main_txtPhone)
    EditText activityMainTxtPhone;
    @BindView(R.id.activity_main_lblName)
    TextView activityMainLblName;
    @BindView(R.id.activity_main_lblEmail)
    TextView activityMainLblEmail;
    @BindView(R.id.tivity_main_lblPhone)
    TextView activityMainLblPhone;
    @BindView(R.id.activity_main_txtAddress)
    EditText activityMainTxtAddress;
    @BindView(R.id.activity_main_lblAddress)
    TextView activityMainLblAddress;
    @BindView(R.id.activity_main_txtWeb)
    EditText activityMainTxtWeb;
    @BindView(R.id.activity_main_lblWeb)
    TextView activityMainLblWeb;
    @BindView(R.id.activity_main_imgCat)
    ImageView activityMainImgCat;
    @BindView(R.id.activity_main_lblCatName)
    TextView activityMainLblCatName;
    @BindView(R.id.activity_main_imgEmail)
    ImageView activityMainImgEmail;
    @BindView(R.id.activity_main_imgPhone)
    ImageView activityMainImgPhone;
    @BindView(R.id.activity_main_ImgMap)
    ImageView activityMainImgMap;
    @BindView(R.id.activity_main_imgWeb)
    ImageView activityMainImgWeb;
    @BindView(R.id.activity_main_toolbar)
    Toolbar activity_main_toolbar;
    //endregion

    //assets
    //region
    @BindDrawable(R.drawable.ic_action_email)
    Drawable imgEmail;
    @BindDrawable(R.drawable.ic_action_mail_tinted)
    Drawable imgEmailTinted;
    @BindDrawable(R.drawable.ic_action_phone)
    Drawable imgPhone;
    @BindDrawable(R.drawable.ic_action_phone_tinted)
    Drawable imgPhoneTinted;
    @BindDrawable(R.drawable.ic_action_map)
    Drawable imgMap;
    @BindDrawable(R.drawable.ic_action_map_tinted)
    Drawable imgMapTinted;
    @BindDrawable(R.drawable.ic_action_web)
    Drawable imgWeb;
    @BindDrawable(R.drawable.ic_action_web_tinted)
    Drawable imgWebTinted;
    @BindColor(R.color.colorPrimaryDark)
    int darkTextColor;
    @BindColor(R.color.colorAccent)
    int accentTextColor;


    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        obtainProfileData(savedInstanceState);
        setSupportActionBar(activity_main_toolbar);
        if (savedInstanceState != null) {
            restoreCatData(savedInstanceState);
        } else {
            //if not, we set the default tag
            if(adapterPosition == -1)
                activityMainImgCat.setTag(R.drawable.cat1);
            else
                activityMainImgCat.setTag(catTag);
        }
        initViews();
    }

    private void obtainProfileData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        Avatar avatar;
        if(intent.hasExtra(EXTRA_AVATAR_POSITION)){
            adapterPosition = extras.getInt(EXTRA_AVATAR_POSITION);
            avatar = extras.getParcelable(EXTRA_PROFILE);
            fillData(avatar);
        }
    }

    private void initViews() {
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_add_profile_menu, menu);
        return true;
    }

    //onclicks
    //region
    //if we click on cat Image or text field, we will open GridCatsActivity
    @OnClick({R.id.activity_main_imgCat, R.id.activity_main_lblCatName})
    public void OnClickedCatImage() {
        createAvatarParcelable(activityMainLblCatName, activityMainImgCat);
    }

    @OnClick(R.id.activity_main_imgEmail)
    public void onActivityMainImgEmailClicked() {
        String mail = String.valueOf(activityMainTxtEmail.getText());
        if (checkData(activityMainTxtEmail, Constants.Option.EMAIL))
            sendMail(mail);

    }


    @OnClick(R.id.activity_main_imgPhone)
    public void onActivityMainImgPhoneClicked() {
        String phone = String.valueOf(activityMainTxtPhone.getText());
        if (checkData(activityMainTxtPhone, Constants.Option.PHONE))
            dial(phone);

    }

    @OnClick(R.id.activity_main_ImgMap)
    public void onActivityMainImgMapClicked() {
        String address = String.valueOf(activityMainTxtAddress.getText());
        if (checkData(activityMainTxtAddress, Constants.Option.ADDRESS))
            searchInMap(address);

    }

    @OnClick(R.id.activity_main_imgWeb)
    public void onActivityMainImgWebClicked() {
        String url = String.valueOf(activityMainTxtWeb.getText());
        if (checkData(activityMainTxtWeb, Constants.Option.WEB))
            showOnWeb(url);
    }


    private void sendMail(String mail) {
        Intent intent = IntentsUtils.newEmailIntent(mail);
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            MessageUtils.noEmailApp(this);
        }
    }

    private void searchInMap(String address) {
        Intent intent = IntentsUtils.newSearchInMapIntent(address);
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            MessageUtils.noMapsApp(this);
        }
    }

    private void showOnWeb(String url) {
        if (NetworkUtils.isConnectionAvailable(getApplicationContext())) {
            Intent intent = IntentsUtils.newViewUriIntent(Uri.parse(url));
            if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
                startActivity(intent);
            } else {
                MessageUtils.wrongURL(this);
            }
        } else {
            //no browser
            MessageUtils.noBrowser(this);
        }
    }

    private void dial(String phone) {
        Intent intent = IntentsUtils.newDialIntent(phone);
        if (IntentsUtils.isActivityAvailable(getApplicationContext(), intent)) {
            startActivity(intent);
        } else {
            MessageUtils.noPhoneApp(this);
        }
    }

    private void createAvatarParcelable(TextView lblCatName, ImageView lblCatImage) {
        Avatar catAvatar;
        String nCatName, name, url, email, phone;
        int nCatImageId;
        //obtain data
        nCatImageId = (int) lblCatImage.getTag();
        nCatName = lblCatName.getText().toString();
        name = String.valueOf(activityMainLblName.getText());
        url = String.valueOf(activityMainLblWeb.getText());
        email = String.valueOf(activityMainLblEmail.getText());
        phone = String.valueOf(activityMainLblPhone.getText());
        //fill the avatar
        catAvatar = new Avatar();
        catAvatar.setCatTag(nCatImageId);
        catAvatar.setCatName(nCatName);
        catAvatar.setName(name);
        catAvatar.setWeb(url);
        catAvatar.setEmail(email);
        catAvatar.setPhone(phone);
        //send the result
        GridCatsActivity.startForResult(this, RC_GRID_CATS_ACTIVITY, catAvatar);
    }

    //endregion

    //onFocus
    //region
    //change text
    @OnFocusChange(R.id.activity_main_txtName)
    public void onTxtNameFocusChanged(boolean hasFocus) {
        if (hasFocus)
            changeTextColor(activityMainLblName);
        else
            restoreTextColor(activityMainLblName);

    }

    @OnFocusChange(R.id.activity_main_txtEmail)
    public void onTxtEmailFocusChanged(boolean hasFocus) {
        if (hasFocus)
            changeTextColor(activityMainLblEmail);
        else
            restoreTextColor(activityMainLblEmail);

    }

    @OnFocusChange(R.id.activity_main_txtPhone)
    public void onTxtPhoneFocusChanged(boolean hasFocus) {
        if (hasFocus)
            changeTextColor(activityMainLblPhone);
        else
            restoreTextColor(activityMainLblPhone);

    }

    @OnFocusChange(R.id.activity_main_txtAddress)
    public void onTxtAddressFocusChanged(boolean hasFocus) {
        if (hasFocus)
            changeTextColor(activityMainLblAddress);
        else
            restoreTextColor(activityMainLblAddress);

    }

    @OnFocusChange(R.id.activity_main_txtWeb)
    public void onTxtWebFocusChanged(boolean hasFocus) {
        if (hasFocus)
            changeTextColor(activityMainLblWeb);
        else
            restoreTextColor(activityMainLblWeb);

    }

    //endregion

    //region
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuApply:
                if (!checkEmpty()) {
                    createProfile();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void createProfile() {
        //obtain all the data
        Intent result = new Intent();
        Avatar avatar = new Avatar();
        avatar.setCatName(String.valueOf(activityMainLblCatName.getText()));
        avatar.setName(String.valueOf(activityMainTxtName.getText()));
        avatar.setCatTag((int) activityMainImgCat.getTag());
        avatar.setEmail(String.valueOf(activityMainTxtEmail.getText()));
        avatar.setPhone(String.valueOf(activityMainTxtPhone.getText()));
        avatar.setWeb(String.valueOf(activityMainTxtWeb.getText()));
        avatar.setAddress(String.valueOf(activityMainTxtAddress.getText()));
        if (adapterPosition != -1)
            result.putExtra(EXTRA_AVATAR_POSITION, adapterPosition);
        //send data on intent

        result.putExtra(EXTRA_PROFILE, avatar);
        this.setResult(RESULT_OK, result);
        finish();

    }

    private boolean checkEmpty() {
        Boolean isEmpty = true;
        if (!TextUtils.isEmpty(activityMainTxtName.getText()) && !TextUtils.isEmpty(activityMainTxtPhone.getText())
                && !TextUtils.isEmpty(activityMainTxtAddress.getText()) && !TextUtils.isEmpty(activityMainTxtEmail.getText())
                && !TextUtils.isEmpty(activityMainTxtWeb.getText())) {
            isEmpty = false;
        }
        return isEmpty;
    }
    //endregion

    //onTextChange
    //region
    @OnTextChanged(value = R.id.activity_main_txtEmail, callback = TEXT_CHANGED)
    public void onTxtEmailTextChanged() {
        //we check data
        if (checkData(activityMainTxtEmail, Constants.Option.EMAIL))
            activateIcon(Constants.Option.EMAIL);
        else
            deactivateIcon(Constants.Option.EMAIL);
    }

    @OnTextChanged(value = R.id.activity_main_txtPhone, callback = TEXT_CHANGED)
    public void onTxtPhoneTextChanged() {
        //we check data
        if (checkData(activityMainTxtPhone, Constants.Option.PHONE))
            activateIcon(Constants.Option.PHONE);
        else
            deactivateIcon(Constants.Option.PHONE);
    }

    @OnTextChanged(value = R.id.activity_main_txtWeb, callback = TEXT_CHANGED)
    public void onTxtWebTextChanged() {
        //we check data
        if (checkData(activityMainTxtWeb, Constants.Option.WEB))
            activateIcon(Constants.Option.WEB);
        else
            deactivateIcon(Constants.Option.WEB);
    }

    @OnTextChanged(value = R.id.activity_main_txtAddress, callback = TEXT_CHANGED)
    public void onTxtAddressTextChanged() {
        //we check data
        if (checkData(activityMainTxtAddress, Constants.Option.ADDRESS))
            activateIcon(Constants.Option.ADDRESS);
        else
            deactivateIcon(Constants.Option.ADDRESS);
    }

    //change data
    private void changeTextColor(TextView lblToChange) {
        lblToChange.setTextColor(accentTextColor);
        lblToChange.setAllCaps(true);
        lblToChange.setTypeface(null, Typeface.BOLD);
    }

    private void restoreTextColor(TextView lblToChange) {
        lblToChange.setTextColor(darkTextColor);
        lblToChange.setAllCaps(false);
        lblToChange.setTypeface(null, Typeface.NORMAL);
    }

    //we will activate the icon replacing the current image
    private void activateIcon(Constants.Option option) {
        switch (option) {
            case EMAIL:
                activityMainImgEmail.setImageDrawable(imgEmailTinted);
                break;

            case PHONE:
                activityMainImgPhone.setImageDrawable(imgPhoneTinted);
                break;
            case WEB:
                activityMainImgWeb.setImageDrawable(imgWebTinted);
                break;
            case ADDRESS:
                activityMainImgMap.setImageDrawable(imgMapTinted);
                break;
        }
    }

    //deactivate the icon replacing the image
    private void deactivateIcon(Constants.Option option) {
        switch (option) {
            case EMAIL:
                activityMainImgEmail.setImageDrawable(imgEmail);
                break;

            case PHONE:
                activityMainImgPhone.setImageDrawable(imgPhone);
                break;
            case WEB:
                activityMainImgWeb.setImageDrawable(imgWeb);
                break;
            case ADDRESS:
                activityMainImgMap.setImageDrawable(imgMap);
                break;
        }
    }


    private void writeCatData(String catName, int catImageTag) {
        activityMainLblCatName.setText(catName);
        activityMainImgCat.setImageResource(catImageTag);
        //we change the tag too to the image
        activityMainImgCat.setTag(catImageTag);
    }

    //check data
    private boolean checkData(EditText text, Constants.Option option) {
        boolean valid = false;
        switch (option) {
            case EMAIL:
                if (ValidationUtils.isValidEmail(text.getText().toString()))
                    valid = true;

                break;

            case PHONE:
                if (ValidationUtils.isValidPhone(text.getText().toString()))
                    valid = true;

                break;
            case WEB:
                if (ValidationUtils.isValidUrl(text.getText().toString()))
                    valid = true;

                break;

            case ADDRESS:
                if (ValidationUtils.isValidAddress(text.getText().toString()))
                    valid = true;

                break;
        }
        return valid;
    }

    //text checking


    //endregion
    //intents
    //region
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String catName;
        int catImageId;
        Avatar avatar;
        if (resultCode == RESULT_OK && requestCode == RC_GRID_CATS_ACTIVITY) {
            if (data.hasExtra(EXTRA_CAT_AVATAR)) {
                avatar = data.getParcelableExtra(EXTRA_CAT_AVATAR);
                catName = avatar.getCatName();
                catImageId = avatar.getCatTag();
                setCatAvatar(catName, catImageId);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    private void fillData(Avatar avatar) {
        activityMainLblCatName.setText(avatar.getCatName());
        activityMainImgCat.setImageResource(avatar.getCatTag());
        activityMainTxtName.setText(avatar.getName());
        activityMainTxtEmail.setText(avatar.getEmail());
        activityMainTxtWeb.setText(avatar.getWeb());
        activityMainTxtAddress.setText(avatar.getAddress());
        activityMainTxtPhone.setText(avatar.getPhone());
        catTag = avatar.getCatTag();
    }


    private void setCatAvatar(String catName, int catImageTag) {
        writeCatData(catName, catImageTag);
    }

    public static void startForResult(Activity activity, int requestCode, Avatar avatar, int position) {
        Intent intent;
        if (position != -1) {
            intent = new Intent(activity, MainActivity.class);
            intent.putExtra(EXTRA_PROFILE, avatar);
            intent.putExtra(EXTRA_AVATAR_POSITION, position);
            activity.startActivityForResult(intent, requestCode);
            //change position value
            adapterPosition = position;
        } else {
            intent = new Intent(activity, MainActivity.class);
            intent.putExtra(EXTRA_PROFILE, avatar);
            activity.startActivityForResult(intent, requestCode);
            adapterPosition = DEFAULT_VALUE;
        }
    }

    //endregion

    //saved views
    //region
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveCurrentAvatar(outState);

    }

    //save the cat Avatar
    private void saveCurrentAvatar(Bundle destination) {
        Avatar avatar;
        boolean edited;
        avatar = new Avatar();
        avatar.setCatName(String.valueOf(activityMainLblCatName.getText()));
        avatar.setCatTag((int) activityMainImgCat.getTag());
        edited = false;
        destination.putParcelable(STATE_CAT_AVATAR, avatar);
    }

    //restore the current cat avatar
    private void restoreCatData(Bundle data) {
        Avatar avatar;
        boolean edited;

        avatar = data.getParcelable(STATE_CAT_AVATAR);
        writeCatData(avatar.getCatName(), avatar.getCatTag());

    }
//endregion

}
