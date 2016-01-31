package com.example.hp.musicplayer.API;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by lwx on 2016/1/28.
 */
public interface ApiService {
    @GET("ting")
    Observable<Search> search(@Query("format") String format,
                              @Query("callback") String callback,
                              @Query("from") String from,
                              @Query("method") String method,
                              @Query("query") String query );

    @GET("ting")
    Observable<Play> play(@Query("format") String format,
                          @Query("callback") String callback,
                          @Query("from") String from,
                          @Query("method") String method,
                          @Query("songid") String songId );
}
