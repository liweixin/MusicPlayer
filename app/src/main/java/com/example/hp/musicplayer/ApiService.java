package com.example.hp.musicplayer;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by lwx on 2016/1/28.
 */
public interface ApiService {
    //"v1/restserver/ting?from=qianqian&version=2.1.0&method=baidu.ting.search.common&format=json&query=小苹果&page_no=1&page_size=30"
    @GET("v1/restserver/ting")
    Observable<SearchResult> getSearchResult(@Query("from") String from,
                                             @Query("version") String version,
                                             @Query("method") String method,
                                             @Query("format") String format,
                                             @Query("query") String query,
                                             @Query("page_no") int page_no,
                                             @Query("page_size") int page_size );

    @GET("ting")
    Observable<ErrorMsg> getSongUrl(@Query("from") String from,
                                        @Query("version") String version,
                                        @Query("method") String method,
                                        @Query("format") String format,
                                        @Query("songid") String songid,
                                        @Query("ts") String ts,
                                        @Query("e") String e,
                                        @Query("nw") String nw,
                                        @Query("ucf") String ucf,
                                        @Query("res") String res );
    @GET("ting?from=qianqian&version=2.1.0&method=baidu.ting.song.getInfos&format=json&songid=120125029&ts=1408284347323&e=JoN56kTXnnbEpd9MVczkYJCSx%2FE1mkLx%2BPMIkTcOEu4%3D&nw=2&ucf=1&res=1")
    Observable<SearchSongList> getUrl();

    @GET("ting")
    Observable<Search> search(@Query("format") String format,
                              @Query("callback") String callback,
                              @Query("from") String from,
                              @Query("method") String method,
                              @Query("query") String query );

    @GET("ting?format=json&calback=&from=webapp_music&method=baidu.ting.song.play&songid=877578")
    Observable<Play> play(@Query("format") String format,
                          @Query("callback") String callback,
                          @Query("from") String from,
                          @Query("method") String method,
                          @Query("songid") String songId );
}
