package com.rowsen.myapplication;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import java.util.HashMap;

import androidx.annotation.RequiresApi;

public class k {
    private static HashMap<String, Typeface> a = new HashMap();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static Typeface a(Context paramContext, String paramString) {
        if (a.containsKey(paramString)) {
            return (Typeface) a.get(paramString);
        }
        try {
            //  paramContext = paramContext.getAssets();
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("fonts/");
            localStringBuilder.append(paramString);
            a.put(paramString, Typeface.createFromAsset(paramContext.getAssets(), localStringBuilder.toString()));
            return (Typeface) a.get(paramString);
        } catch (Exception e) {
            return a.get(paramString);
        }
    }
}
