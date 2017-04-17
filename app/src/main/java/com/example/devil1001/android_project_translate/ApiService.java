package com.example.devil1001.android_project_translate;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by devil1001 on 17.04.17.
 */

public interface ApiService {

    @POST("/api/v1.5/tr.json/translate")
    Call<Answer> getMyJSON(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);
}
