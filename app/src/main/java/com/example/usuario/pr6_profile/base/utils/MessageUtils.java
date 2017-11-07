package com.example.usuario.pr6_profile.base.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.usuario.pr6_profile.R;

/**
 * Created by jannu on 1/11/17.
 */

public class MessageUtils {


    public static void noEmailApp(Context context) {
        Toast.makeText(context, R.string.message_utils_noMailAppMessage, Toast.LENGTH_SHORT).show();
    }

    public static void noPhoneApp(Context context) {
        Toast.makeText(context, R.string.message_utils_noPhoneAppMessage, Toast.LENGTH_SHORT).show();
    }


    public static void noMapsApp(Context context) {
        Toast.makeText(context, R.string.message_utils_NoMapsAppMessage, Toast.LENGTH_SHORT).show();
    }

    public static void wrongURL(Context context) {
        Toast.makeText(context, R.string.message_utils_wrongUrlMessage, Toast.LENGTH_SHORT).show();
    }

    public static void noBrowser(Context context) {
        Toast.makeText(context, R.string.message_utils_noBrowserAppMessage, Toast.LENGTH_SHORT).show();
    }
}
