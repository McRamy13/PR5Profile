package com.example.usuario.pr6_profile.ui.data;

import com.example.usuario.pr6_profile.base.model.Avatar;

import java.util.ArrayList;

/**
 * Created by jannu on 5/11/17.
 */


@SuppressWarnings({"WeakerAccess", "unused"})
public interface Repository {

    ArrayList<Avatar> getAvatars();

    void addAvatar(Avatar avatar);

    void deleteAvatar(int position);
}

