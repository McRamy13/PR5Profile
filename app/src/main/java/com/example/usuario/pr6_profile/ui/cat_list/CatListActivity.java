package com.example.usuario.pr6_profile.ui.cat_list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.usuario.pr6_profile.R;
import com.example.usuario.pr6_profile.ui.data.RepositoryImpl;
import com.example.usuario.pr6_profile.ui.main.MainActivity;
import com.example.usuario.pr6_profile.base.model.Avatar;
import com.example.usuario.pr6_profile.base.model.Database;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;

public class CatListActivity extends AppCompatActivity {

    private static final String STATE_LIST = "STATE_LIST";
    private CatListAdapter mAdapter;
    private static final int RC_CAT_LIST_ACTIVITY = 2;
    private static final short DEFAULT_POSITION = -1;
    private static final String EXTRA_PROFILE = "EXTRA_PROFILE";
    private static final String EXTRA_AVATAR_POSITION = "EXTRA_AVATAR_POSITION";
    private RepositoryImpl mRepository;
    Parcelable mListState;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_cat_list_lstCats)
    ListView contentCatListLstCats;
    @BindView(R.id.cat_list_fabAddProfile)
    FloatingActionButton catListFabAddProfile;
    @BindView(R.id.empty_view_parentConstraint)
    ConstraintLayout empty_view_parentConstraint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);
        initViews();
        setSupportActionBar(toolbar);
        setListData();

    }

    //initial methods
    //region
    private void setListData() {
        //set first the empty view
        mRepository = RepositoryImpl.getInstance(Database.getInstance());
        contentCatListLstCats.setEmptyView(empty_view_parentConstraint);
        mAdapter = new CatListAdapter(this, mRepository.getAvatars());
        contentCatListLstCats.setAdapter(mAdapter);
    }

    private void initViews() {
        ButterKnife.bind(this);
    }
    //endregion

    //onclicks
    //region
    @OnClick(R.id.cat_list_fabAddProfile)
    public void onFabAddProfileClick(View v) {
        callActivity();
    }

    //on list item click
    @OnItemClick(R.id.content_cat_list_lstCats)
    public void onCatlistItemClick(int position) {
        //obtain the data
        Avatar avatar = (Avatar) mAdapter.getItem(position);
        //Start MainActivity
        MainActivity.startForResult(this, RC_CAT_LIST_ACTIVITY, avatar, position);
    }

    //onLongClick
    @OnItemLongClick(R.id.content_cat_list_lstCats)
    boolean onCatlistLongItemClick(final int position) {
        final Avatar profile;
        //delete data but, before we need to save the avatar
        profile = (Avatar) mAdapter.getItem(position);
        mRepository.deleteAvatar(position);
        mAdapter.notifyDataSetChanged();
        Snackbar.make(contentCatListLstCats, R.string.catListActivity_deletedProfileMessage, Snackbar.LENGTH_LONG)
                .setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        restoreProfile(profile, position);
                    }
                }).show();
        return true;
    }

    private void callActivity() {
        Avatar avatar = new Avatar();
        MainActivity.startForResult(this, RC_CAT_LIST_ACTIVITY, avatar, DEFAULT_POSITION);
    }
    //endregion
    //region
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Avatar avatar;
        int position = DEFAULT_POSITION;
        if (resultCode == RESULT_OK && requestCode == RC_CAT_LIST_ACTIVITY) {
            if (data.hasExtra(EXTRA_PROFILE)) {
                if (data.hasExtra(EXTRA_AVATAR_POSITION)) {
                    avatar = data.getParcelableExtra(EXTRA_PROFILE);
                    //add the avatar to the DB
                    position = data.getIntExtra(EXTRA_AVATAR_POSITION, DEFAULT_POSITION);
                    mRepository.editAvatar(avatar, position);
                    //notify to the adapter
                    mAdapter.notifyDataSetChanged();
                } else {
                    avatar = data.getParcelableExtra(EXTRA_PROFILE);
                    //add the avatar to the DB
                    mRepository.addAvatar(avatar);
                    //notify to the adapter
                    mAdapter.notifyDataSetChanged();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //save the state
        mListState = contentCatListLstCats.onSaveInstanceState();
        outState.putParcelable(STATE_LIST, mListState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mListState = savedInstanceState.getParcelable(STATE_LIST);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Se retaura el estado de la lista.
        if (mListState != null) {
            contentCatListLstCats.onRestoreInstanceState(mListState);
        }
    }

    //data manage methdos

    private void restoreProfile(Avatar avatar, int position) {
        mRepository.restoreAvatar(avatar,position);
        mAdapter.notifyDataSetChanged();
    }
    //endregion
}
