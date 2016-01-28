package com.example.hp.musicplayer;

import java.util.List;

/**
 * Created by lwx on 2016/1/28.
 */
public class SearchSongList {
    private SongurlEntity songurl;
    /**
     * songurl : {"url":[{"show_link":"http://zhangmenshiting.baidu.com/data2/music/123297858/123297858.mp3?xcode=394fd37060b4512f35dc224202a6daff","down_type":0,"original":0,"free":1,"replay_gain":"4.930000","song_file_id":123297858,"file_size":1701114,"file_extension":"mp3","file_duration":212,"can_see":1,"can_load":true,"preload":40,"file_bitrate":64,"file_link":"http://yinyueshiting.baidu.com/data2/music/123297858/123297858.mp3?xcode=394fd37060b4512f35dc224202a6daff","is_udition_url":0,"hash":"20b39e1a9d3301c2831bb68a099969801e52953c"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/134379742/134379742.mp3?xcode=394fd37060b4512f35dc224202a6daff","down_type":1,"original":0,"free":1,"replay_gain":"0.790001","song_file_id":125619112,"file_size":3383717,"file_extension":"mp3","file_duration":211,"can_see":1,"can_load":true,"preload":80,"file_bitrate":128,"file_link":"http://yinyueshiting.baidu.com/data2/music/134379742/134379742.mp3?xcode=394fd37060b4512f35dc224202a6daff","is_udition_url":1,"hash":"2b32bb6994c24059648a458c77ed3325417e14be"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/123297866/123297866.mp3?xcode=394fd37060b4512f35dc224202a6daff","down_type":0,"original":0,"free":1,"replay_gain":"4.120003","song_file_id":123297866,"file_size":5164228,"file_extension":"mp3","file_duration":212,"can_see":1,"can_load":true,"preload":120,"file_bitrate":192,"file_link":"http://yinyueshiting.baidu.com/data2/music/123297866/123297866.mp3?xcode=394fd37060b4512f35dc224202a6daff","is_udition_url":0,"hash":"deb6372fe9422967844d715c81026f28785f74be"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/134378990/134378990.mp3?xcode=394fd37060b4512f35dc224202a6daff","down_type":0,"original":0,"free":1,"replay_gain":"0.279999","song_file_id":123297935,"file_size":7763752,"file_extension":"mp3","file_duration":212,"can_see":1,"can_load":true,"preload":160,"file_bitrate":256,"file_link":"http://yinyueshiting.baidu.com/data2/music/134378990/134378990.mp3?xcode=394fd37060b4512f35dc224202a6daff","is_udition_url":0,"hash":"a5ea95448c40f3e5ebadf05ded2d7ac7011df939"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/136279289/136279289.mp3?xcode=394fd37060b4512f35dc224202a6daff","down_type":2,"original":0,"free":1,"replay_gain":"0.389999","song_file_id":136279289,"file_size":8457689,"file_extension":"mp3","file_duration":211,"can_see":1,"can_load":true,"preload":200,"file_bitrate":320,"file_link":"http://yinyueshiting.baidu.com/data2/music/136279289/136279289.mp3?xcode=394fd37060b4512f35dc224202a6daff","is_udition_url":0,"hash":"d3784685868064c04f5fc8f1b006340b59af51d4"},{"show_link":"http://zhangmenshiting.baidu.com/data2/music/136279398/136279398.mp3?xcode=394fd37060b4512f35dc224202a6daff","down_type":0,"original":0,"free":1,"replay_gain":"1.089996","song_file_id":136279398,"file_size":925187,"file_extension":"mp3","file_duration":211,"can_see":1,"can_load":true,"preload":15,"file_bitrate":24,"file_link":"http://yinyueshiting.baidu.com/data2/music/136279398/136279398.mp3?xcode=394fd37060b4512f35dc224202a6daff","is_udition_url":0,"hash":"8bb244dd3bc0d5fb69528f166a580d2adf9c87a4"},{"show_link":"","down_type":0,"original":0,"free":1,"replay_gain":"0.339996","song_file_id":125619077,"file_size":28565726,"file_extension":"flac","file_duration":211,"can_see":1,"can_load":true,"preload":676.875,"file_bitrate":1083,"file_link":"","is_udition_url":0,"hash":"c8cb5d050045c3fe8f4154b33dde37744712b048"}]}
     * error_code : 22000
     * songinfo : {"resource_type_ext":"0","pic_huge":"http://musicdata.baidu.com/data2/pic/122112233/122112233.jpg","resource_type":"0","del_status":"0","album_1000_1000":"http://musicdata.baidu.com/data2/pic/122112233/122112233.jpg","pic_singer":"","album_500_500":"http://musicdata.baidu.com/data2/pic/122112236/122112236.jpg","havehigh":2,"piao_id":"0","song_source":"web","korean_bb_song":"0","compose":"","toneid":"0","area":"0","original_rate":"","bitrate":"64,128,192,256,320,24,1083","artist_500_500":"http://musicdata.baidu.com/data2/pic/1c5d94de31c573919e30d08aa050d24d/246668018/246668018.jpg","multiterminal_copytype":"","has_mv":1,"file_duration":"0","album_title":"老男孩之猛龙过江 电影原声","sound_effect":"0","title":"小苹果","high_rate":"320","pic_radio":"http://musicdata.baidu.com/data2/pic/122112239/122112239.jpg","is_first_publish":0,"hot":"437260","language":"国语","lrclink":"http://musicdata.baidu.com/data2/lrc/240885272/%E5%B0%8F%E8%8B%B9%E6%9E%9C.lrc","distribution":"0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,1111110000,1111110000,0000000000","relate_status":"0","learn":1,"play_type":0,"pic_big":"http://musicdata.baidu.com/data2/pic/122112246/122112246.jpg","pic_premium":"http://musicdata.baidu.com/data2/pic/122112236/122112236.jpg","artist_480_800":"http://musicdata.baidu.com/data2/pic/105445522/105445522.jpg","aliasname":"苹果","country":"内地","artist_id":"57520","album_id":"121556956","original":0,"compress_status":"0","versions":"","expire":36000,"ting_uid":"9295","artist_1000_1000":"http://musicdata.baidu.com/data2/pic/246668004/246668004.jpg","all_artist_id":"57520","artist_640_1136":"http://musicdata.baidu.com/data2/pic/105445506/105445506.jpg","publishtime":"2014-07-22","charge":0,"copy_type":"0","songwriting":"","share_url":"http://music.baidu.com/song/120125029","author":"筷子兄弟","has_mv_mobile":1,"all_rate":"24,64,128,192,256,320,flac","pic_small":"http://musicdata.baidu.com/data2/pic/122112253/122112253.jpg","album_no":"1","song_id":"120125029","is_charge":"0"}
     */

    private int error_code;
    /**
     * resource_type_ext : 0
     * pic_huge : http://musicdata.baidu.com/data2/pic/122112233/122112233.jpg
     * resource_type : 0
     * del_status : 0
     * album_1000_1000 : http://musicdata.baidu.com/data2/pic/122112233/122112233.jpg
     * pic_singer :
     * album_500_500 : http://musicdata.baidu.com/data2/pic/122112236/122112236.jpg
     * havehigh : 2
     * piao_id : 0
     * song_source : web
     * korean_bb_song : 0
     * compose :
     * toneid : 0
     * area : 0
     * original_rate :
     * bitrate : 64,128,192,256,320,24,1083
     * artist_500_500 : http://musicdata.baidu.com/data2/pic/1c5d94de31c573919e30d08aa050d24d/246668018/246668018.jpg
     * multiterminal_copytype :
     * has_mv : 1
     * file_duration : 0
     * album_title : 老男孩之猛龙过江 电影原声
     * sound_effect : 0
     * title : 小苹果
     * high_rate : 320
     * pic_radio : http://musicdata.baidu.com/data2/pic/122112239/122112239.jpg
     * is_first_publish : 0
     * hot : 437260
     * language : 国语
     * lrclink : http://musicdata.baidu.com/data2/lrc/240885272/%E5%B0%8F%E8%8B%B9%E6%9E%9C.lrc
     * distribution : 0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,0000000000,1111110000,1111110000,0000000000
     * relate_status : 0
     * learn : 1
     * play_type : 0
     * pic_big : http://musicdata.baidu.com/data2/pic/122112246/122112246.jpg
     * pic_premium : http://musicdata.baidu.com/data2/pic/122112236/122112236.jpg
     * artist_480_800 : http://musicdata.baidu.com/data2/pic/105445522/105445522.jpg
     * aliasname : 苹果
     * country : 内地
     * artist_id : 57520
     * album_id : 121556956
     * original : 0
     * compress_status : 0
     * versions :
     * expire : 36000
     * ting_uid : 9295
     * artist_1000_1000 : http://musicdata.baidu.com/data2/pic/246668004/246668004.jpg
     * all_artist_id : 57520
     * artist_640_1136 : http://musicdata.baidu.com/data2/pic/105445506/105445506.jpg
     * publishtime : 2014-07-22
     * charge : 0
     * copy_type : 0
     * songwriting :
     * share_url : http://music.baidu.com/song/120125029
     * author : 筷子兄弟
     * has_mv_mobile : 1
     * all_rate : 24,64,128,192,256,320,flac
     * pic_small : http://musicdata.baidu.com/data2/pic/122112253/122112253.jpg
     * album_no : 1
     * song_id : 120125029
     * is_charge : 0
     */

    private SonginfoEntity songinfo;

    public void setSongurl(SongurlEntity songurl) {
        this.songurl = songurl;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setSonginfo(SonginfoEntity songinfo) {
        this.songinfo = songinfo;
    }

    public SongurlEntity getSongurl() {
        return songurl;
    }

    public int getError_code() {
        return error_code;
    }

    public SonginfoEntity getSonginfo() {
        return songinfo;
    }

    public static class SongurlEntity {
        /**
         * show_link : http://zhangmenshiting.baidu.com/data2/music/123297858/123297858.mp3?xcode=394fd37060b4512f35dc224202a6daff
         * down_type : 0
         * original : 0
         * free : 1
         * replay_gain : 4.930000
         * song_file_id : 123297858
         * file_size : 1701114
         * file_extension : mp3
         * file_duration : 212
         * can_see : 1
         * can_load : true
         * preload : 40
         * file_bitrate : 64
         * file_link : http://yinyueshiting.baidu.com/data2/music/123297858/123297858.mp3?xcode=394fd37060b4512f35dc224202a6daff
         * is_udition_url : 0
         * hash : 20b39e1a9d3301c2831bb68a099969801e52953c
         */

        private List<UrlEntity> url;

        public void setUrl(List<UrlEntity> url) {
            this.url = url;
        }

        public List<UrlEntity> getUrl() {
            return url;
        }

        public static class UrlEntity {
            private String show_link;
            private int down_type;
            private int original;
            private int free;
            private String replay_gain;
            private int song_file_id;
            private int file_size;
            private String file_extension;
            private int file_duration;
            private int can_see;
            private boolean can_load;
            private int preload;
            private int file_bitrate;
            private String file_link;
            private int is_udition_url;
            private String hash;

            public void setShow_link(String show_link) {
                this.show_link = show_link;
            }

            public void setDown_type(int down_type) {
                this.down_type = down_type;
            }

            public void setOriginal(int original) {
                this.original = original;
            }

            public void setFree(int free) {
                this.free = free;
            }

            public void setReplay_gain(String replay_gain) {
                this.replay_gain = replay_gain;
            }

            public void setSong_file_id(int song_file_id) {
                this.song_file_id = song_file_id;
            }

            public void setFile_size(int file_size) {
                this.file_size = file_size;
            }

            public void setFile_extension(String file_extension) {
                this.file_extension = file_extension;
            }

            public void setFile_duration(int file_duration) {
                this.file_duration = file_duration;
            }

            public void setCan_see(int can_see) {
                this.can_see = can_see;
            }

            public void setCan_load(boolean can_load) {
                this.can_load = can_load;
            }

            public void setPreload(int preload) {
                this.preload = preload;
            }

            public void setFile_bitrate(int file_bitrate) {
                this.file_bitrate = file_bitrate;
            }

            public void setFile_link(String file_link) {
                this.file_link = file_link;
            }

            public void setIs_udition_url(int is_udition_url) {
                this.is_udition_url = is_udition_url;
            }

            public void setHash(String hash) {
                this.hash = hash;
            }

            public String getShow_link() {
                return show_link;
            }

            public int getDown_type() {
                return down_type;
            }

            public int getOriginal() {
                return original;
            }

            public int getFree() {
                return free;
            }

            public String getReplay_gain() {
                return replay_gain;
            }

            public int getSong_file_id() {
                return song_file_id;
            }

            public int getFile_size() {
                return file_size;
            }

            public String getFile_extension() {
                return file_extension;
            }

            public int getFile_duration() {
                return file_duration;
            }

            public int getCan_see() {
                return can_see;
            }

            public boolean isCan_load() {
                return can_load;
            }

            public int getPreload() {
                return preload;
            }

            public int getFile_bitrate() {
                return file_bitrate;
            }

            public String getFile_link() {
                return file_link;
            }

            public int getIs_udition_url() {
                return is_udition_url;
            }

            public String getHash() {
                return hash;
            }
        }
    }

    public static class SonginfoEntity {
        private String resource_type_ext;
        private String pic_huge;
        private String resource_type;
        private String del_status;
        private String album_1000_1000;
        private String pic_singer;
        private String album_500_500;
        private int havehigh;
        private String piao_id;
        private String song_source;
        private String korean_bb_song;
        private String compose;
        private String toneid;
        private String area;
        private String original_rate;
        private String bitrate;
        private String artist_500_500;
        private String multiterminal_copytype;
        private int has_mv;
        private String file_duration;
        private String album_title;
        private String sound_effect;
        private String title;
        private String high_rate;
        private String pic_radio;
        private int is_first_publish;
        private String hot;
        private String language;
        private String lrclink;
        private String distribution;
        private String relate_status;
        private int learn;
        private int play_type;
        private String pic_big;
        private String pic_premium;
        private String artist_480_800;
        private String aliasname;
        private String country;
        private String artist_id;
        private String album_id;
        private int original;
        private String compress_status;
        private String versions;
        private int expire;
        private String ting_uid;
        private String artist_1000_1000;
        private String all_artist_id;
        private String artist_640_1136;
        private String publishtime;
        private int charge;
        private String copy_type;
        private String songwriting;
        private String share_url;
        private String author;
        private int has_mv_mobile;
        private String all_rate;
        private String pic_small;
        private String album_no;
        private String song_id;
        private String is_charge;

        public void setResource_type_ext(String resource_type_ext) {
            this.resource_type_ext = resource_type_ext;
        }

        public void setPic_huge(String pic_huge) {
            this.pic_huge = pic_huge;
        }

        public void setResource_type(String resource_type) {
            this.resource_type = resource_type;
        }

        public void setDel_status(String del_status) {
            this.del_status = del_status;
        }

        public void setAlbum_1000_1000(String album_1000_1000) {
            this.album_1000_1000 = album_1000_1000;
        }

        public void setPic_singer(String pic_singer) {
            this.pic_singer = pic_singer;
        }

        public void setAlbum_500_500(String album_500_500) {
            this.album_500_500 = album_500_500;
        }

        public void setHavehigh(int havehigh) {
            this.havehigh = havehigh;
        }

        public void setPiao_id(String piao_id) {
            this.piao_id = piao_id;
        }

        public void setSong_source(String song_source) {
            this.song_source = song_source;
        }

        public void setKorean_bb_song(String korean_bb_song) {
            this.korean_bb_song = korean_bb_song;
        }

        public void setCompose(String compose) {
            this.compose = compose;
        }

        public void setToneid(String toneid) {
            this.toneid = toneid;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setOriginal_rate(String original_rate) {
            this.original_rate = original_rate;
        }

        public void setBitrate(String bitrate) {
            this.bitrate = bitrate;
        }

        public void setArtist_500_500(String artist_500_500) {
            this.artist_500_500 = artist_500_500;
        }

        public void setMultiterminal_copytype(String multiterminal_copytype) {
            this.multiterminal_copytype = multiterminal_copytype;
        }

        public void setHas_mv(int has_mv) {
            this.has_mv = has_mv;
        }

        public void setFile_duration(String file_duration) {
            this.file_duration = file_duration;
        }

        public void setAlbum_title(String album_title) {
            this.album_title = album_title;
        }

        public void setSound_effect(String sound_effect) {
            this.sound_effect = sound_effect;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setHigh_rate(String high_rate) {
            this.high_rate = high_rate;
        }

        public void setPic_radio(String pic_radio) {
            this.pic_radio = pic_radio;
        }

        public void setIs_first_publish(int is_first_publish) {
            this.is_first_publish = is_first_publish;
        }

        public void setHot(String hot) {
            this.hot = hot;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public void setLrclink(String lrclink) {
            this.lrclink = lrclink;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }

        public void setRelate_status(String relate_status) {
            this.relate_status = relate_status;
        }

        public void setLearn(int learn) {
            this.learn = learn;
        }

        public void setPlay_type(int play_type) {
            this.play_type = play_type;
        }

        public void setPic_big(String pic_big) {
            this.pic_big = pic_big;
        }

        public void setPic_premium(String pic_premium) {
            this.pic_premium = pic_premium;
        }

        public void setArtist_480_800(String artist_480_800) {
            this.artist_480_800 = artist_480_800;
        }

        public void setAliasname(String aliasname) {
            this.aliasname = aliasname;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setArtist_id(String artist_id) {
            this.artist_id = artist_id;
        }

        public void setAlbum_id(String album_id) {
            this.album_id = album_id;
        }

        public void setOriginal(int original) {
            this.original = original;
        }

        public void setCompress_status(String compress_status) {
            this.compress_status = compress_status;
        }

        public void setVersions(String versions) {
            this.versions = versions;
        }

        public void setExpire(int expire) {
            this.expire = expire;
        }

        public void setTing_uid(String ting_uid) {
            this.ting_uid = ting_uid;
        }

        public void setArtist_1000_1000(String artist_1000_1000) {
            this.artist_1000_1000 = artist_1000_1000;
        }

        public void setAll_artist_id(String all_artist_id) {
            this.all_artist_id = all_artist_id;
        }

        public void setArtist_640_1136(String artist_640_1136) {
            this.artist_640_1136 = artist_640_1136;
        }

        public void setPublishtime(String publishtime) {
            this.publishtime = publishtime;
        }

        public void setCharge(int charge) {
            this.charge = charge;
        }

        public void setCopy_type(String copy_type) {
            this.copy_type = copy_type;
        }

        public void setSongwriting(String songwriting) {
            this.songwriting = songwriting;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setHas_mv_mobile(int has_mv_mobile) {
            this.has_mv_mobile = has_mv_mobile;
        }

        public void setAll_rate(String all_rate) {
            this.all_rate = all_rate;
        }

        public void setPic_small(String pic_small) {
            this.pic_small = pic_small;
        }

        public void setAlbum_no(String album_no) {
            this.album_no = album_no;
        }

        public void setSong_id(String song_id) {
            this.song_id = song_id;
        }

        public void setIs_charge(String is_charge) {
            this.is_charge = is_charge;
        }

        public String getResource_type_ext() {
            return resource_type_ext;
        }

        public String getPic_huge() {
            return pic_huge;
        }

        public String getResource_type() {
            return resource_type;
        }

        public String getDel_status() {
            return del_status;
        }

        public String getAlbum_1000_1000() {
            return album_1000_1000;
        }

        public String getPic_singer() {
            return pic_singer;
        }

        public String getAlbum_500_500() {
            return album_500_500;
        }

        public int getHavehigh() {
            return havehigh;
        }

        public String getPiao_id() {
            return piao_id;
        }

        public String getSong_source() {
            return song_source;
        }

        public String getKorean_bb_song() {
            return korean_bb_song;
        }

        public String getCompose() {
            return compose;
        }

        public String getToneid() {
            return toneid;
        }

        public String getArea() {
            return area;
        }

        public String getOriginal_rate() {
            return original_rate;
        }

        public String getBitrate() {
            return bitrate;
        }

        public String getArtist_500_500() {
            return artist_500_500;
        }

        public String getMultiterminal_copytype() {
            return multiterminal_copytype;
        }

        public int getHas_mv() {
            return has_mv;
        }

        public String getFile_duration() {
            return file_duration;
        }

        public String getAlbum_title() {
            return album_title;
        }

        public String getSound_effect() {
            return sound_effect;
        }

        public String getTitle() {
            return title;
        }

        public String getHigh_rate() {
            return high_rate;
        }

        public String getPic_radio() {
            return pic_radio;
        }

        public int getIs_first_publish() {
            return is_first_publish;
        }

        public String getHot() {
            return hot;
        }

        public String getLanguage() {
            return language;
        }

        public String getLrclink() {
            return lrclink;
        }

        public String getDistribution() {
            return distribution;
        }

        public String getRelate_status() {
            return relate_status;
        }

        public int getLearn() {
            return learn;
        }

        public int getPlay_type() {
            return play_type;
        }

        public String getPic_big() {
            return pic_big;
        }

        public String getPic_premium() {
            return pic_premium;
        }

        public String getArtist_480_800() {
            return artist_480_800;
        }

        public String getAliasname() {
            return aliasname;
        }

        public String getCountry() {
            return country;
        }

        public String getArtist_id() {
            return artist_id;
        }

        public String getAlbum_id() {
            return album_id;
        }

        public int getOriginal() {
            return original;
        }

        public String getCompress_status() {
            return compress_status;
        }

        public String getVersions() {
            return versions;
        }

        public int getExpire() {
            return expire;
        }

        public String getTing_uid() {
            return ting_uid;
        }

        public String getArtist_1000_1000() {
            return artist_1000_1000;
        }

        public String getAll_artist_id() {
            return all_artist_id;
        }

        public String getArtist_640_1136() {
            return artist_640_1136;
        }

        public String getPublishtime() {
            return publishtime;
        }

        public int getCharge() {
            return charge;
        }

        public String getCopy_type() {
            return copy_type;
        }

        public String getSongwriting() {
            return songwriting;
        }

        public String getShare_url() {
            return share_url;
        }

        public String getAuthor() {
            return author;
        }

        public int getHas_mv_mobile() {
            return has_mv_mobile;
        }

        public String getAll_rate() {
            return all_rate;
        }

        public String getPic_small() {
            return pic_small;
        }

        public String getAlbum_no() {
            return album_no;
        }

        public String getSong_id() {
            return song_id;
        }

        public String getIs_charge() {
            return is_charge;
        }
    }
}
