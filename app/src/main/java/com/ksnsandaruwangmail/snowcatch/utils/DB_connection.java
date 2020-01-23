package com.ksnsandaruwangmail.snowcatch.utils;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by welcome on 1/11/2017.
 */

public class DB_connection implements Serializable{

        public static String url = "http://192.168.8.100/Snow_catcher/";
        public static String Save_Score = url+"Save_score";
        public static String Save_user = url+"Save_user";

        public static HttpURLConnection getConnection(String path)throws Exception{
            URL u = new URL(path);
            HttpURLConnection urlconnection =  (HttpURLConnection)u.openConnection();
            urlconnection.setRequestMethod("POST");
            urlconnection.connect();
            return urlconnection;

        }

//    public static

        public static String uid = "";
    }





