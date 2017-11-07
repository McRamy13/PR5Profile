package com.example.usuario.pr5_profile.ui.utils;

import android.text.TextUtils;
import android.util.Patterns;

import org.w3c.dom.Text;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phoneNumber) {
        return !TextUtils.isEmpty(phoneNumber) && Patterns.PHONE.matcher(phoneNumber).matches();
    }

    public static boolean isValidUrl(String url) {
        return !TextUtils.isEmpty(url) && Patterns.WEB_URL.matcher(url).matches();
    }

    public static boolean isValidAddress(String address) {
        return !TextUtils.isEmpty(address);
    }

}
