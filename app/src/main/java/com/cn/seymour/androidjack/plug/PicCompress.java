package com.cn.seymour.androidjack.plug;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 图片压缩
 * <br>Created by seyMour on 2016/4/6.</br>
 * <br>☞120465271@qq.com☜</br>
 */
public class PicCompress {
    /**
     * 图片质量压缩
     * @param bitmap
     * @return
     */
    private Bitmap compressImage(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //质量压缩方式，100标识不压缩
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,bos);
        int options = 100;
        //循环压缩直到低于100kb
        while(bos.toByteArray().length/1024 > 100){
            bos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, bos);
            options -= 10;
        }
        ByteArrayInputStream is = new ByteArrayInputStream(bos.toByteArray());
        return BitmapFactory.decodeStream(is);
    }

    /**
     * 图片大小压缩
     * @param srcPath
     * @return
     */
    private Bitmap getBitmap(String srcPath){
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //通知BitmapFactory只返回图像的范围，而不尝试解码该图像
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);
        newOpts.inJustDecodeBounds = false;

        int w = newOpts.outWidth;
        int h = newOpts.outWidth;

        float hh = 800f;
        float ww = 480f;
        int be = 1;
        if(w > h && w > ww){
            be = (int)(w/ww);
        }else if (w < h && h > hh){
            be = (int)(h/hh);
        }
        if(be < 0)be=1;
        newOpts.inSampleSize = be;
        //重新读入图片
        bitmap = BitmapFactory.decodeFile(srcPath,newOpts);
        //大小压缩后，在进行质量压缩
        return compressImage(bitmap);
    }
}
