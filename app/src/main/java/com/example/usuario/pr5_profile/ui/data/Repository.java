package com.example.usuario.pr5_profile.ui.data;

import com.example.usuario.pr5_profile.ui.model.Avatar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jannu on 5/11/17.
 */


@SuppressWarnings({"WeakerAccess", "unused"})
public interface Repository {

    ArrayList<Avatar> getAvatars();

    void addAvatar(Avatar avatar);

    void deleteAvatar(int position);
}

