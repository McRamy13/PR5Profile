package com.example.usuario.pr5_profile.ui.grid_cat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.pr5_profile.R;
import com.example.usuario.pr5_profile.ui.model.Avatar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GridCatsActivity extends AppCompatActivity {

    private static final String PARCELABLE_CAT_AVATAR = "PARCELABLE_CAT_AVATAR";
    private static final String EXTRA_CAT_AVATAR = "EXTRA_CAT_AVATAR";
    private Avatar avatar;
    private ArrayList<ImageView> catTagList;
    // views
    //region
    @BindView(R.id.grid_cats_imgCat1)
    ImageView gridCatsImgCat1;
    @BindView(R.id.grid_cats_lblCat1Name)
    TextView gridCatsLblCat1Name;
    @BindView(R.id.grid_cats_imgCat2)
    ImageView gridCatsImgCat2;
    @BindView(R.id.grid_cats_lblCat2Name)
    TextView gridCatsLblCat2Name;
    @BindView(R.id.grid_cats_imgCat3)
    ImageView gridCatsImgCat3;
    @BindView(R.id.grid_cats_lblCat3Name)
    TextView gridCatsLblCat3Name;
    @BindView(R.id.grid_cats_imgCat4)
    ImageView gridCatsImgCat4;
    @BindView(R.id.grid_cats_lblCat4Name)
    TextView gridCatsLblCat4Name;
    @BindView(R.id.grid_cats_imgCat5)
    ImageView gridCatsImgCat5;
    @BindView(R.id.grid_cats_lblCat5Name)
    TextView gridCatsLblCat5Name;
    @BindView(R.id.grid_cats_imgCat6)
    ImageView gridCatsImgCat6;
    @BindView(R.id.grid_cats_lblCat6Name)
    TextView gridCatsLblCat6Name;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_cats);
        initViews();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        changePressedToolbarItemAction();
        obtainIntentData();
    }
    //intitial methods
    //region
    private void initViews() {
        ButterKnife.bind(this);
        setCatsTag();
    }

    private void changePressedToolbarItemAction() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    //set the cats tag
    private void setCatsTag() {
        gridCatsImgCat1.setTag(R.drawable.cat1);
        gridCatsImgCat2.setTag(R.drawable.cat2);
        gridCatsImgCat3.setTag(R.drawable.cat3);
        gridCatsImgCat4.setTag(R.drawable.cat4);
        gridCatsImgCat5.setTag(R.drawable.cat5);
        gridCatsImgCat6.setTag(R.drawable.cat6);
        createCatList();
    }

    //generate the catList
    private void createCatList() {
        catTagList = new ArrayList<>();
        catTagList.add(gridCatsImgCat1);
        catTagList.add(gridCatsImgCat2);
        catTagList.add(gridCatsImgCat3);
        catTagList.add(gridCatsImgCat4);
        catTagList.add(gridCatsImgCat5);
        catTagList.add(gridCatsImgCat6);
    }
    //endregion
    //onclicks
    //region
    @OnClick({R.id.grid_cats_imgCat1, R.id.grid_cats_lblCat1Name})
    public void onGridCatsImgCat1Clicked() {
        //create the object
        int resId = (int) gridCatsImgCat1.getTag();
        avatar = modifyCatAvatar(gridCatsLblCat1Name, resId);
        putCatExtraData();
    }

    @OnClick({R.id.grid_cats_imgCat2, R.id.grid_cats_lblCat2Name})
    public void onGridCatsImgCat2Clicked() {
        int resId = (int) gridCatsImgCat2.getTag();
        avatar = modifyCatAvatar(gridCatsLblCat2Name, resId);
        putCatExtraData();
    }


    @OnClick({R.id.grid_cats_imgCat3, R.id.grid_cats_lblCat3Name})
    public void onGridCatsImgCat3Clicked() {
        int resId = (int) gridCatsImgCat3.getTag();
        avatar = modifyCatAvatar(gridCatsLblCat3Name, resId);
        putCatExtraData();

    }

    @OnClick({R.id.grid_cats_imgCat4, R.id.grid_cats_lblCat4Name})
    public void onGridCatsImgCat4Clicked() {
        int resId = (int) gridCatsImgCat4.getTag();
        avatar = modifyCatAvatar(gridCatsLblCat4Name, resId);
        putCatExtraData();
    }


    @OnClick({R.id.grid_cats_imgCat5, R.id.grid_cats_lblCat5Name})
    public void onGridCatsImgCat5Clicked() {
        int resId = (int) gridCatsImgCat5.getTag();
        avatar = modifyCatAvatar(gridCatsLblCat5Name, resId);
        putCatExtraData();
    }


    @OnClick({R.id.grid_cats_imgCat6, R.id.grid_cats_lblCat6Name})
    public void onGridCatsImgCat6Clicked() {
        int resId = (int) gridCatsImgCat6.getTag();
        avatar = modifyCatAvatar(gridCatsLblCat6Name, resId);
        putCatExtraData();
    }


    //modify the current avatar
    private Avatar modifyCatAvatar(TextView lblCatName, int catResId) {
        Avatar newCatAvatar = new Avatar();
        String newName = String.valueOf(lblCatName.getText());
        newCatAvatar.setCatTag(catResId);
        newCatAvatar.setCatName(newName);
        return newCatAvatar;
    }

    //endregion
    //intents
    //region
    // this function will add as extra the name of the pressed cat
    private void putCatExtraData() {
        Intent result = new Intent();
        result.putExtra(EXTRA_CAT_AVATAR, avatar);
        this.setResult(RESULT_OK, result);
        finish();
    }

    //we call to this activity
    public static void startForResult(Activity activity, int requestCode, Avatar avatar) {
        Intent intent;
        intent = new Intent(activity, GridCatsActivity.class);
        intent.putExtra(EXTRA_CAT_AVATAR, avatar);
        activity.startActivityForResult(intent, requestCode);
    }

    //obtain the current avatar
    private void obtainIntentData() {
        Intent intent;
        Bundle extras;
        Avatar avatar;

        intent = getIntent();
        extras = intent.getExtras();
        avatar = extras.getParcelable(EXTRA_CAT_AVATAR);
        applySelectedImage(avatar);

    }


    private void applySelectedImage(Avatar avatar) {
        //set the selected image
        Boolean isEqual = false;
        int tag = avatar.getCatTag();
        int listItemTag;
        for (int listItem = 0; listItem < catTagList.size(); listItem++) {
            //if the tag we send is equal to the list one, we change the opacity of the imageView
            listItemTag = (int) catTagList.get(listItem).getTag();
            if (listItemTag == tag) {
                catTagList.get(listItem).setAlpha((float) 0.2);
                isEqual = true;
            }
        }
        //if isEqual is false, is the first time we call to this method
        //we change the first image opacity
        if (!isEqual) {
            catTagList.get(0).setAlpha((float) 0.5);
        }
    }
    //endregion
}
