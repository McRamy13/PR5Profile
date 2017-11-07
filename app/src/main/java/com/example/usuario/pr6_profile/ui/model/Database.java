package com.example.usuario.pr6_profile.ui.model;

import java.util.ArrayList;

/**
 * Created by jannu on 4/11/17.
 */

public class Database {

    private static Database instance;
    private final ArrayList<Avatar> avatars;

    private Database() {
        avatars = new ArrayList<>();
        // Initial data
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public ArrayList<Avatar> getAvatars() {
        return avatars;

    }

    public void
    addAvatar(Avatar avatar) {
        avatars.add(avatar);
    }

    public void deleteAvatar(int position) {
        avatars.remove(position);
    }

    public void editAvatar(Avatar avatar, int position) {
        avatars.set(position, avatar);
    }

    public void restoreAvatar(Avatar avatar, int position) {
        avatars.add(position, avatar);
    }
}
