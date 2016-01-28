package com.example.hp.musicplayer;

import java.util.List;

/**
 * Created by lwx on 2016/1/28.
 */
public class Search {
    /**
     * song : [{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"Beyond","control":"0000000000","songid":"877578","has_mv":"1","encrypted_songid":"4205d640a0956210255L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"信乐团","control":"0000000000","songid":"391713","has_mv":"0","encrypted_songid":"69055fa210956a1c972L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"黄家驹","control":"0000000000","songid":"14795583","has_mv":"1","encrypted_songid":"3306e1c33f09561d3ee3L"},{"bitrate_fee":"{\"0\":\"129|-1\",\"1\":\"-1|-1\"}","yyr_artist":"0","songname":"海阔天空","artistname":"林忆莲","control":"0000000000","songid":"14880013","has_mv":"0","encrypted_songid":"1606e30d0d0956236cd3L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"汤晶锦","control":"0000000000","songid":"246509600","has_mv":"0","encrypted_songid":"9507eb1702009563323ddL"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"1","songname":"海阔天空","artistname":"幼稚园杀手","control":"0000000000","songid":"73896409","has_mv":"0","encrypted_songid":""},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"谢安琪","control":"0000000000","songid":"1418288","has_mv":"0","encrypted_songid":"140615a43009561d2b06L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"叶倩文","control":"0000000000","songid":"23276935","has_mv":"0","encrypted_songid":"55071632d8709561d1853L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"陶喆","control":"0000000000","songid":"105670709","has_mv":"1","encrypted_songid":"890764c68350956210181L"},{"bitrate_fee":"{\"0\":\"0|0\",\"1\":\"0|0\"}","yyr_artist":"0","songname":"海阔天空","artistname":"张惠妹","control":"0000000000","songid":"99035490","has_mv":"1","encrypted_songid":"48075e7296209568ba886L"}]
     * album : [{"albumname":"海阔天空","artistpic":"http://qukufile2.qianqian.com/data2/pic/88582736/88582736.jpg","albumid":"197864","artistname":"Beyond"},{"albumname":"海阔天空","artistpic":"http://qukufile2.qianqian.com/data2/pic/88406885/88406885.jpg","albumid":"72973","artistname":"信乐团"},{"albumname":"天路","artistpic":"http://qukufile2.qianqian.com/data2/pic/032a5340d8a3376beceba749f95a1217/261964210/261964210.jpg","albumid":"14469195","artistname":"海阔天空"},{"albumname":"海阔天空任我闯","artistpic":"http://a.hiphotos.baidu.com/ting/pic/item/6a600c338744ebf8aa2ec829dbf9d72a6059a74d.jpg","albumid":"25534678","artistname":"张明敏"},{"albumname":"单曲 - 天路","artistpic":"http://qukufile2.qianqian.com/data2/pic/39965814/39965814.jpg","albumid":"15378905","artistname":"海阔天空组合"},{"albumname":"单曲 - 海阔天空","artistpic":"http://qukufile2.qianqian.com/data2/pic/43514877/43514877.jpg","albumid":"22164314","artistname":"亮亮"}]
     * order : artist,song,album
     * error_code : 22000
     * artist : [{"yyr_artist":"0","artistid":"2345733","artistpic":"http://a.hiphotos.baidu.com/ting/abpic/item/6d81800a19d8bc3eb42695cc808ba61ea8d3458d.jpg","artistname":"海阔天空"},{"yyr_artist":"0","artistid":"87930765","artistpic":"http://b.hiphotos.baidu.com/ting/abpic/item/d788d43f8794a4c2fb089b370cf41bd5ad6e3986.jpg","artistname":"海阔天空组合"}]
     */

    private String order;
    private int error_code;
    /**
     * bitrate_fee : {"0":"0|0","1":"0|0"}
     * yyr_artist : 0
     * songname : 海阔天空
     * artistname : Beyond
     * control : 0000000000
     * songid : 877578
     * has_mv : 1
     * encrypted_songid : 4205d640a0956210255L
     */

    private List<SongEntity> song;
    /**
     * albumname : 海阔天空
     * artistpic : http://qukufile2.qianqian.com/data2/pic/88582736/88582736.jpg
     * albumid : 197864
     * artistname : Beyond
     */

    private List<AlbumEntity> album;
    /**
     * yyr_artist : 0
     * artistid : 2345733
     * artistpic : http://a.hiphotos.baidu.com/ting/abpic/item/6d81800a19d8bc3eb42695cc808ba61ea8d3458d.jpg
     * artistname : 海阔天空
     */

    private List<ArtistEntity> artist;

    public void setOrder(String order) {
        this.order = order;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public void setSong(List<SongEntity> song) {
        this.song = song;
    }

    public void setAlbum(List<AlbumEntity> album) {
        this.album = album;
    }

    public void setArtist(List<ArtistEntity> artist) {
        this.artist = artist;
    }

    public String getOrder() {
        return order;
    }

    public int getError_code() {
        return error_code;
    }

    public List<SongEntity> getSong() {
        return song;
    }

    public List<AlbumEntity> getAlbum() {
        return album;
    }

    public List<ArtistEntity> getArtist() {
        return artist;
    }

    public static class SongEntity {
        private String bitrate_fee;
        private String yyr_artist;
        private String songname;
        private String artistname;
        private String control;
        private String songid;
        private String has_mv;
        private String encrypted_songid;

        public void setBitrate_fee(String bitrate_fee) {
            this.bitrate_fee = bitrate_fee;
        }

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public void setSongname(String songname) {
            this.songname = songname;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public void setControl(String control) {
            this.control = control;
        }

        public void setSongid(String songid) {
            this.songid = songid;
        }

        public void setHas_mv(String has_mv) {
            this.has_mv = has_mv;
        }

        public void setEncrypted_songid(String encrypted_songid) {
            this.encrypted_songid = encrypted_songid;
        }

        public String getBitrate_fee() {
            return bitrate_fee;
        }

        public String getYyr_artist() {
            return yyr_artist;
        }

        public String getSongname() {
            return songname;
        }

        public String getArtistname() {
            return artistname;
        }

        public String getControl() {
            return control;
        }

        public String getSongid() {
            return songid;
        }

        public String getHas_mv() {
            return has_mv;
        }

        public String getEncrypted_songid() {
            return encrypted_songid;
        }
    }

    public static class AlbumEntity {
        private String albumname;
        private String artistpic;
        private String albumid;
        private String artistname;

        public void setAlbumname(String albumname) {
            this.albumname = albumname;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public void setAlbumid(String albumid) {
            this.albumid = albumid;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getAlbumname() {
            return albumname;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public String getAlbumid() {
            return albumid;
        }

        public String getArtistname() {
            return artistname;
        }
    }

    public static class ArtistEntity {
        private String yyr_artist;
        private String artistid;
        private String artistpic;
        private String artistname;

        public void setYyr_artist(String yyr_artist) {
            this.yyr_artist = yyr_artist;
        }

        public void setArtistid(String artistid) {
            this.artistid = artistid;
        }

        public void setArtistpic(String artistpic) {
            this.artistpic = artistpic;
        }

        public void setArtistname(String artistname) {
            this.artistname = artistname;
        }

        public String getYyr_artist() {
            return yyr_artist;
        }

        public String getArtistid() {
            return artistid;
        }

        public String getArtistpic() {
            return artistpic;
        }

        public String getArtistname() {
            return artistname;
        }
    }
}
