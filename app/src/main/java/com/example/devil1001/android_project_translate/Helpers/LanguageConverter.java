package com.example.devil1001.android_project_translate.Helpers;

import android.content.Context;
import com.example.devil1001.android_project_translate.R;
import java.util.HashMap;

/**
 * Created by devil1001 on 17.04.17.
 */

public class LanguageConverter {
    private static final LanguageConverter instance = new LanguageConverter();

    private static HashMap<String, String> languageToCode;

    private LanguageConverter() {
        languageToCode = new HashMap<>();
    }

    public static LanguageConverter getInstance() {
        return instance;
    }

    private void initializeMap(Context context) {
        String [] languages = context.getResources().getStringArray(R.array.languages);
        String [] languagesCodes = context.getResources().getStringArray(R.array.languageCodes);
        for (int i = 0; i < languages.length; i++) {
            languageToCode.put(languages[i], languagesCodes[i]);
        }
    }

    public String convert(Context context, String spinner) {
        if (languageToCode.isEmpty())
            initializeMap(context);
        return languageToCode.get(spinner);
    }


}