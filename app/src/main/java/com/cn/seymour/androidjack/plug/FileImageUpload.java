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
 * 上传图片
 * <br>Created by seyMour on 2016/3/30.</br>
 * <br>☞120465271@qq.com☜</br>
 */
public class FileImageUpload {
    private static final int TIME_OUT = 10*1000000;
    private static final String CHARSET = "utf-8";
    public static final String SUCCESS = "1";
    public static final String FAILURE = "0";

    public static String FileImageUpload(File file,String RequestURL) {
        String BOUNDARY = UUID.randomUUID().toString();//边界标识，随机生成，这个用来标识每一段内容的开始
        String PREFIX = "--";//每个分隔符的结尾跟上，标识结束
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
                //name为服务器需要的key值，通过key取得文件
                //filename是文件的名称
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

/*1.HTTP multipart/form-data 上传报文格式
        假设接受文件的网页程序位于 http://192.168.24.56/logsys/home/uploadIspeedLog!doDefault.html.假设我们要发送一个图片文件，文件名为“kn.jpg”，
        　　首先客户端链接 192.168.24.56 后, 应该发送如下http 请求：
        　　POST/logsys/home/uploadIspeedLog!doDefault.html HTTP/1.1
        　　Accept: text/plain,
　　Accept-Language: zh-cn
　　Host: 192.168.24.56
　　Content-Type:multipart/form-data;boundary=-----------------------------7db372eb000e2
　　User-Agent: WinHttpClient
　　Content-Length: 3693
　　Connection: Keep-Alive
　　-------------------------------7db372eb000e2
　　Content-Disposition: form-data; name="file"; filename="kn.jpg"
　　Content-Type: image/jpeg
　　(此处省略jpeg文件二进制数据...）
　　-------------------------------7db372eb000e2--
　　此内容必须一字不差，包括最后的回车，红色字体部分就是协议的头。给服务器上传数据时，并非协议头每个字段都得说明，其中，content-type是必须的，它包括一个类似标志性质的名为boundary的标志，它可以是随便输入的字符串。对后面的具体内容也是必须的。它用来分辨一段内容的开始。Content-Length: 3693 ，这里的3693是要上传文件的总长度。绿色字体部分就是需要上传的数据，可以是文本，也可以是图片等。数据内容前面需要有Content-Disposition, Content-Type以及Content-Transfer-Encoding等说明字段。最后的紫色部分就是协议的结尾了。
　　注意这一行：
　　Content-Type: multipart/form-data; boundary=---------------------------7db372eb000e2　　
　　根据 rfc1867, multipart/form-data是必须的.
　　---------------------------7db372eb000e2 是分隔符，分隔多个文件、表单项。其中b372eb000e2 是即时生成的一个数字，用以确保整个分隔符不会在文件或表单项的内容中出现。Form每个部分用分隔符分割，分隔符之前必须加上"--"着两个字符(即--{boundary})才能被http协议认为是Form的分隔符，表示结束的话用在正确的分隔符后面添加"--"表示结束。
　　前面的 ---------------------------7d 是 IE 特有的标志,Mozila 为---------------------------71.
　　


　　每个分隔的数据的都可以用Content-Type来表示下面数据的类型，可以参考rfc1341 (http://www.ietf.org/rfc/rfc1341.txt)
　　例如 ：Contect-Type:image/jpeg 表示下面的数据是jpeg文件数据*/
