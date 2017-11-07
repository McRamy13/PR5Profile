package com.example.usuario.pr6_profile.ui.data;

import com.example.usuario.pr6_profile.ui.model.Avatar;
import com.example.usuario.pr6_profile.ui.model.Database;

import java.util.ArrayList;

/**
 * Created by jannu on 5/11/17.
 */

public class RepositoryImpl implements Repository {

    private static RepositoryImpl instance;

    private final Database database;

    private RepositoryImpl(Database database) {
        this.database = database;
    }

    public static RepositoryImpl getInstance(Database database) {
        if (instance == null) {
            instance = new RepositoryImpl(database);
        }
        return instance;
    }


    @Override
    public ArrayList<Avatar> getAvatars() {
        return database.getAvatars();
    }

    @Override
    public void addAvatar(Avatar avatar) {
        database.addAvatar(avatar);
    }

    @Override
    public void deleteAvatar(int position) {
        database.deleteAvatar(position);
    }

    public void editAvatar(Avatar avatar, int position) {
        database.editAvatar(avatar,  position);
    }

    public void restoreAvatar(Avatar avatar, int position) { database.restoreAvatar(avatar, position); }
}