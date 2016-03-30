package com.cn.seymour.androidjack.plug;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

/**
 * Created by seyMour on 2016/3/30.
 * ☞120465271@qq.com☜
 */
public class FileImageUpload {
    private static final int TIME_OUT = 10*1000000;
    private static final String CHARSET = "utf-8";
    public static final String SUCCESS = "1";
    public static final String FAILURE = "0";

    public static String FileImageUpload(File file,String RequestURL) {
        String BOUNDARY = UUID.randomUUID().toString();//边界标识，随机生成
        String PREFIX = "--";
        String LINE_END = "\r\n";
        String ConnectType = "multipart/form-data";
        try{
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Charset", CHARSET);
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Connect-type", ConnectType + ";boundary=" + BOUNDARY);
            if(file != null){
                OutputStream os = conn.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                StringBuffer s = new StringBuffer();
                s.append(PREFIX);
                s.append(LINE_END);
                s.append("Content-Disposition:from/data;name=\"img\";filename=\""+file.getName()+"\"" + LINE_END);
                s.append("Content-type:application/octet-stream;charset="+CHARSET+LINE_END);
                s.append(LINE_END);
                dos.write(s.toString().getBytes());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while((len = is.read(bytes))!=-1){
                    dos.write(bytes,0,len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                dos.write((PREFIX + BOUNDARY + LINE_END).getBytes());
                dos.flush();

                int res = conn.getResponseCode();
                if(res == 200){
                    return SUCCESS;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return FAILURE;
    }
}
