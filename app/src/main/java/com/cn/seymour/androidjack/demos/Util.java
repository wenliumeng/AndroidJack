package com.cn.seymour.androidjack.demos;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.graphics.PorterDuff.Mode;

public class Util
{
	private static int FREE_SD_SPACE_NEEDED_TO_CACHE = 1;
	private static int MB = 1024 * 1024;

	/**
	 * 
	 * 去除空格回车符换行符等字符方�?
	 * 
	 * @param txt
	 * @return
	 */
	public static String trimAll(String txt)
	{
		try 
		{
			if (null == txt || "".equals(txt))
			{
				return txt;
			}
			String dest = "";
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(txt);
			dest = m.replaceAll("");
			return dest;
		}
		catch (Exception ex) 
		{
			ex.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 旋转图片
	 * @param bitmap
	 * @param angle : 角度�?0�?80,...)
	 * @return
	 */
	public static Bitmap rotaingBitmap(Bitmap bitmap,int angle) 
	{
		Matrix matrix = null;
		try
		{
			if(null==bitmap)return null;
			matrix = new Matrix();
			if(null!=matrix)
			{
				matrix.postRotate(angle); 
				return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
			}
		}
		catch(Exception ex){}
		finally
		{
			matrix = null;
		} 
		return null;
    }
	
		
	/**
     * 伸缩bitmap(lilw)
     * @param bitmap
     * @param sx�?-1
     * @param sy�?-1
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap,float sx,float sy) 
    {
		Matrix matrix = new Matrix();
		matrix.postScale(sx,sy); //长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
		return resizeBmp;
    }
    
	/**
	 * 
	 * 放大缩小图片（重载方法）
	 * 
	 * @param bitmap
	 * @param w
	 * @param h
	 * @return
	 */
	public static Bitmap zoomBitmap(Bitmap bitmap, int w, int h) 
	{
		if(bitmap == null)
		{
			return null;
		}
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidht = ((float) w / width);
		float scaleHeight = ((float) h / height);
		matrix.postScale(scaleWidht, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,matrix, true);
		return newbmp;
	}
    
    /**
	 * 按新的宽高缩放图�?
	 * 
	 * @param bm
	 * @param newWidth
	 * @param newHeight
	 * @return
	 */
	public static Bitmap scaleImage(Bitmap bm, int newWidth, int newHeight)
	{
		if (bm == null)
		{
			return null;
		}

		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		Matrix matrix = new Matrix();
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix,
				true);
		if (bm != null & !bm.isRecycled())
		{
			bm.recycle();
			bm = null;
		}
		return newbm;
	}
	
    
	/**  
     * 将彩色图转换为灰度图  
     * @param img 位图  
     * @return  返回转换好的位图  
     */    
    public static Bitmap convertGreyImg(Bitmap img) 
    {    
    	if(null == img)
    	{
    		return null;
    	}
        int width = img.getWidth();         //获取位图的宽    
        int height = img.getHeight();       //获取位图的高    
            
        int []pixels = new int[width * height]; //通过位图的大小创建像素点数组    
            
        img.getPixels(pixels, 0, width, 0, 0, width, height);    
        int alpha = 0xFF << 24;     
        for(int i = 0; i < height; i++)  
        {    
            for(int j = 0; j < width; j++) 
            {    
                int grey = pixels[width * i + j];    
                    
                int red = ((grey  & 0x00FF0000 ) >> 16);    
                int green = ((grey & 0x0000FF00) >> 8);    
                int blue = (grey & 0x000000FF);    
                    
                grey = (int)((float) red * 0.3 + (float)green * 0.59 + (float)blue * 0.11);    
                grey = alpha | (grey << 16) | (grey << 8) | grey;    
                pixels[width * i + j] = grey;    
            }    
        }    
        Bitmap result = Bitmap.createBitmap(width, height, Config.RGB_565);    
        result.setPixels(pixels, 0, width, 0, 0, width, height);    
        return result;    
    }    
    
    /**
     * 
     * 获取屏幕�?
     * 
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context)
    {
    	if(null == context)
    	{
    		return -1;
    	}
    	DisplayMetrics dm = context.getApplicationContext().getResources()
				.getDisplayMetrics();
        return dm.widthPixels;
    }
    
    /**
     * 
     * 获取屏幕的高
     * 
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context)
    {
    	if(null == context)
    	{
    		return -1;
    	}
    	DisplayMetrics dm = context.getApplicationContext().getResources()
    			.getDisplayMetrics();
    	return dm.heightPixels;
    }
    
    /**
	 * fuction: 设置固定的宽度，高度随之变化，使图片不会变形
	 * 
	 * @param target
	 *            �?��转化bitmap参数
	 * @param newWidth
	 *            设置新的宽度
	 * @return
	 */
	public static Bitmap fitBitmap(Bitmap target, int newWidth)
	{

		int width = target.getWidth();
		int height = target.getHeight();
		Matrix matrix = new Matrix();

		float scaleWidth = ((float) newWidth) / width;
		// float scaleHeight = ((float)newHeight) / height;
		int newHeight = (int) (scaleWidth * height);
		matrix.postScale(scaleWidth, scaleWidth);
		// Bitmap result = Bitmap.createBitmap(target,0,0,width,height,
		// matrix,true);
		Bitmap bmp = Bitmap.createBitmap(target, 0, 0, width, height, matrix,
				true);
		if (target != null && !target.equals(bmp) && !target.isRecycled())
		{
			target.recycle();
			target = null;
		}
		return bmp;
	}
	
	/**
	 * 保存Bitmap到sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 * 				压缩�?
	 */
	public static boolean saveBmpToSd(Bitmap bitmap,String filepath, int quantity) 
	{
		File f = null;
		try
		{
			if(null==bitmap)return false;
			if(null==filepath || "".equals(filepath))return false;
			f = new File(filepath);
			if(null!=f)
			{
				return saveBitmapToFile(bitmap, f, quantity);
			}
		}
		catch(Exception ex)
		{
		}
		finally
		{
			f = null;
			bitmap = null;
			filepath = null;
		}
		return false;
		
		
	}
	
	
	public static boolean saveBitmapToFile(Bitmap bitmap,File file, int quantity)
	{
		OutputStream os = null;
		try
		{
			if(null==bitmap)return false;
			if(null!=file)
			{
				if(file.exists())
				{
					file.delete();
				}
				file.createNewFile();
				os = new FileOutputStream(file);
				if(null!=os)
				{
					bitmap.compress(Bitmap.CompressFormat.JPEG,quantity > 0 ? quantity : 100,os);
					os.flush();
					os.close();
					return true;
				}
			}
		}
		catch(Exception ex)
		{
			if(null!=os)
			{
				try 
				{
					os.close();
				}
				catch (IOException e) 
				{	
				}
			}
		}
		finally
		{
			file = null;
			bitmap = null;
			os = null;
		}
		return false;
	}
	
	/**
	 * �?��sdcard可用空间
	 * 
	 * @return
	 */
	public static int getFreeSpaceOnSd() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());

		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / MB;

		return (int) sdFreeMB;
	}
	
	/**
	 * 
	 * 删除�?��目录（包括删除该目录下的文件夹和文件�?
	 * 
	 * @param file
	 * @return
	 */
	public static boolean delDir(File file)
	{
		if(null == file)
		{
			return false;
		}
		
		if (file.isFile()) 
		{
			file.delete();
			return true;
		}

		if (file.isDirectory()) 
		{
			File[] childFiles = file.listFiles();
			if (childFiles == null || childFiles.length == 0)
			{
				file.delete();
				return true;
			}

			for (int i = 0; i < childFiles.length; i++) 
			{
				delDir(childFiles[i]);
			}
			file.delete();
			
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 删除指定的一个文�?
	 * 
	 * @param file
	 * @return
	 */
	public static boolean delFile(File file)
	{
		if(null == file)
		{
			return false;
		}
		
		if (file.exists() && file.isFile())
		{
			file.delete();
			return true;
		}
		return false;
	}
	public static boolean delFile(String filePath)
	{
		File f = null;
		try
		{
			if(null==filePath || "".equals(filePath))return false;
			f = new  File(filePath);
			if(null!=f)
			{
				return delFile(f);
			}
		}
		catch(Exception ex){}
		finally
		{
			f = null;
			filePath = null;
		}
		return false;
	}

	
	
	/**
	 * 将内容写到一个指定的文件�?
	 * 
	 * @param path
	 * 				路径
	 * @param filename
	 * 				文件�?
	 * @param context
	 * 				上下�?
	 */
	public static boolean writeFileToSD(String path,String filename,String context) 
	{
		if(path == null || filename == null || context == null)
		{
			return false;
		}
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED))
		{
			return false;
		}
		try 
		{
			File filePath = new File(path);
			File fileName = new File(path + "/" + filename);
			//判断目录是否存在
			if (!filePath.exists()) 
			{
				filePath.mkdirs();
			}
			//判断文件是否存在
			if (!fileName.exists()) {
				fileName.createNewFile();
			}
			else
			{
				fileName.delete();
				fileName.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			raf.seek(fileName.length());
			raf.write(context.getBytes());
			raf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 将内容写到一个指定的文件�?
	 * 
	 * @param path
	 * 				路径
	 * @param filename
	 * 				文件�?
	 * @param context
	 * 				上下�?
	 * 返回值：1写入成功 /0：写入失�?/-1：没有SDCard
	 */
	public static int writeFileToSD2(String path,String filename,String context) 
	{
		if(path == null || filename == null || context == null)
		{
			return 0;
		}
		
		/*
		 * Environment.getExternalStorageState()
			//获得扩展存储设备状�?
			-1 MEDIA_MOUNTED//已挂�?  
			-2 MEDIA_CHECKING//正在扫描
			-3 MEDIA_BAD_REMOVAL//安全卸载前强制拔�?
			-4 MEDIA_MOUNTED_READ_ONLY//只读
			-5 MEDIA_NOFS//sdcar是空的，或文件系统不支持
			-6 MEDIA_REMOVED//已移�?
			-7 MEDIA_SHARED//未挂载，并以USB模式和计算机连接
			-8 MEDIA_UNMOUNTABLE//有sdcard，但无法挂载
			-9 MEDIA_UNMOUNTED//有sdcard，但未挂�?
		 */
		
		//判断SDCard是否可用
		String sdStatus = Environment.getExternalStorageState();
		if (!(sdStatus.equals(Environment.MEDIA_MOUNTED)))
		{
			return -1;
		}
		try 
		{
			File filePath = new File(path);
			File fileName = new File(path + "/" + filename);
			//判断目录是否存在
			if (!filePath.exists()) 
			{
				filePath.mkdirs();
			}
			//判断文件是否存在
			if (!fileName.exists()) {
				fileName.createNewFile();
			}
			else
			{
				fileName.delete();
				fileName.createNewFile();
			}
			RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
			raf.seek(fileName.length());
			raf.write(context.getBytes());
			raf.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	
    /**
    * 使用文件通道的方式复制文�?
    * 
    * @param s
    *            源文�?
    * @param t
    *            复制到的新文�?
    */
    public static void copyFile(File s, File t) 
    {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();
            out = fo.getChannel();
            in.transferTo(0, in.size(), out);
        } catch (IOException e) {
            //e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
               // e.printStackTrace();
            }
        }
    }
	
	/**
	 * 
	 * 文字水印(lilw)
	 * @param aligntype:水印位置类型 	0：全居中�?：全居下�?、全居上
	 * @param bitmap：图片对�?
	 * @param gText：文字信�?
	 * @param textSize：文字大�?
	 * @param textColor：文字颜�?
	 * @param textShadowColor：阴影颜�?
	 * @param bgColor：背景颜�?
	 * @param alpha：�?明度�?-255数�?越小越�?明）
	 * @return
   */
    public static Bitmap waterMarkByString(int aligntype, Bitmap bitmap, String gText, float textSize, int textColor, int textShadowColor,int bgColor,int alpha) 
    {
    	Config bitmapConfig = null;
    	Canvas canvas = null;
    	Paint paint = null;
    	Rect bounds = null;
    	int x = 0;
    	int y = 0;
    	Paint paintbg = null;
    	try
    	{
    		if(null==bitmap)return null;
    		if(null==gText || "".equals(gText))return bitmap;
    		
	        bitmapConfig = bitmap.getConfig();
	        if(null==bitmapConfig)
	        { 
	            bitmapConfig = Config.ARGB_8888;
	        } 
	        bitmap = bitmap.copy(bitmapConfig, true); 
	   
	        canvas = new Canvas(bitmap); 
	        if(null!=canvas)
	        {
		        paint = new Paint(Paint.ANTI_ALIAS_FLAG); 
		        if(null!=paint)
		        {
			        paint.setColor(textColor); 
			        paint.setTextSize(textSize); 
			        if(0<textShadowColor)
			        {
			        	paint.setShadowLayer(1f, 0f, 1f, textShadowColor); 
			        }
			        bounds = new Rect(); 
			        if(null!=bounds)
			        {
				        paint.getTextBounds(gText, 0, gText.length(), bounds); 				        
				       
				      	if(1==aligntype)//全居�?
					    {
				      		x = (bitmap.getWidth() - bounds.width()) / 2; 
					    	y = bitmap.getHeight() - bounds.height()-2;
					    }
					    else if(2==aligntype)//全居�?
					    {
					    	x = (bitmap.getWidth() - bounds.width()) / 2; 
					    	y = 2;
					    }
					    else//居中
					    {
					    	x = (bitmap.getWidth() - bounds.width()) / 2; 
					    	y = bitmap.getHeight()/2 - bounds.height()/2;
					    }
				      	paintbg = new Paint();
				      	if(null!=paintbg)
				      	{
				      		paintbg.setStyle(Style.FILL);
				      		paintbg.setColor(bgColor);
				      		paintbg.setAlpha(alpha);
				      		canvas.drawRect(new RectF(x,y,x+bounds.width(),y+bounds.height()+2), paintbg);
				      	}
				        canvas.drawText(gText, x , y+bounds.height(), paint); 
				   
				        return bitmap; 
			        }
		        }
	        }
    	}
    	catch(Exception ex){}
    	finally
    	{
    		bounds = null;
    		paint = null;
    		paintbg = null;
    		bitmapConfig = null;
    		canvas = null;
    	}
    	return null;
    }
    
  
    /**
     * 图片水印(lilw)
     * @param aligntype:水印位置类型 	0：全居中�?：全居下�?、全居上
     * @param src：源图片
     * @param watermark：水印图�?
     * @param zbx：水印图片缩放度�?-1�?
     * @param zby：水印图片缩放度�?-1�?
     * @return
     */
    public static Bitmap waterMarkByBitmap(int aligntype, Bitmap src, Bitmap watermark, float zbx, float zby)
    {
    	int w ,h,ww,wh;
    	Bitmap newb = null;
    	Canvas cv = null;
    	int left,top;
    	try
    	{
		    if(null!=src && null!=watermark)
		    {
		    	watermark = zoomBitmap(watermark,zbx,zby);
			    w = src.getWidth();
			    h = src.getHeight();
			    ww = watermark.getWidth();
			    wh = watermark.getHeight();
			    
			    if(1==aligntype)//全居�?
			    {
			    	left = (w - ww)/2;
			    	top = h - wh;
			    }
			    else if(2==aligntype)
			    {
			    	left = (w - ww)/2;
			    	top = 0;
			    }
			    else//居中
			    {
			    	left = (w - ww)/2;
			    	top = h/2 - wh/2;
			    }
			    newb = Bitmap.createBitmap( w, h, Config.ARGB_8888 );//创建�?��新的和SRC长度宽度�?��的位�?
			    if(null!=newb)
			    {
				    cv = new Canvas( newb );
				    if(null!=cv)
				    {
					    cv.drawBitmap( src, 0, 0, null );//�?0�?坐标�?��画入src
					    //cv.drawBitmap( watermark, w - ww + 5, h - wh + 5, null );//在src的右下角画入水印
					    cv.drawBitmap( watermark, left,top, null );//在src的中部画入水�?				    
					    cv.save( Canvas.ALL_SAVE_FLAG );//保存
					    cv.restore();//存储					    
					    //return this.waterMarkByString(2, newb, "时间:2014-11-26 16:39:50  车牌/VIN:H78235  保险公司:平安保险", 13f, Color.rgb(61, 61, 61), 0, Color.parseColor("#bebdb9"), 100);
					    return newb;
				    }
			    }
	    	}
    	}
    	catch(Exception ex){}
    	finally
    	{
    		newb = null;
    		cv = null;
    	}
    	return null;
    }
    
    /**
     * 文件加密(lilw)
     * @param src：原文件
     * @param dest：目标文�?
     */
    public static boolean encryFile(String srcFilePath, String desFilePath)
    {
    	File src = null;
    	try
    	{
    		if(null==srcFilePath || "".equals(srcFilePath))return false;
    		if(null==desFilePath || "".equals(desFilePath))return false;
    		src = new File(srcFilePath);
    		if(null!=src)
    		{
    			if(src.exists() && src.isFile())
    			{
    				return encryFile(src,new File(desFilePath));    				
    			}
    		}
    	}
    	catch(Exception ex){}
    	finally
    	{
    		src = null;
    		srcFilePath = null;
    		desFilePath = null;
    	}
    	return false;
    }
    /**
     * 文件加密(lilw)
     * @param src：原文件
     * @param dest：目标文�?
     */
    public static boolean encryFile(File srcFile,File file)
    {
    	InputStream is = null;
    	byte []buff = null;
    	FileOutputStream fos = null;
    	try
    	{
    		if(null==srcFile)return false;		
    		if(!srcFile.exists())return false;
    		if(!srcFile.isFile())return false;
    		if(null==file)return false;
    		
    		is = new BufferedInputStream(new FileInputStream(srcFile));
			if(null!=is)
			{
				buff = new byte[is.available()]; 
				if(null!=buff)
				{
					is.read(buff);
					is.close();		
		    		for(int i=0;i<buff.length;i++)
    				{
						buff[i] = (byte) (buff[i]^ 0x94);
    				}
		    		if(file.exists())
		    		{
		    			file.delete();
		    		}
		    		file.createNewFile();
					fos = new FileOutputStream(file);
    				if(null!=fos)
    				{
    					fos.write(buff);
	    				fos.flush();
	    	    		fos.close();
	    	    		return true;
    				}
				}
			}
    		
    	}
    	catch(Exception ex){}
    	finally
    	{
    		buff = null;
    		is = null;
    		fos = null;
    		file = null;
    		srcFile = null;
    	}
    	return false;
	}
    
    /**
     * 加密Bitmap到文件（适用于几百K以内的小文件�?lilw)
     * @param bitmap
     * @param filepath
     */
    public static boolean encryBitmapToFile(Bitmap bitmap,String filepath,int quantity)
    {
    	try
    	{
    		if(null==bitmap)return false;
    		if(null==filepath || "".equals(filepath))return false;
    		return encryBitmapToFile(bitmap, new File(filepath),quantity);
    	}
    	catch(Exception ex)
    	{
    	}
    	finally
    	{
    		bitmap = null;
    	}
    	return false;
    }
    /**
     * 加密文件（�?用于几百K以内的小文件�?lilw)
     * @param bitmap
     * @param file
     */
    public static boolean encryBitmapToFile(Bitmap bitmap,File file,int quantity)
    {
    	ByteArrayOutputStream baos = null;
    	FileOutputStream fos = null;
    	byte [] bt = null;
    	try
    	{
    		if(null==bitmap)return false;
    		if(null==file)return false;
    		
    		if(file.exists())
    		{
    			file.delete();    			
    		}
    		file.createNewFile();
    		baos = new ByteArrayOutputStream();
    		if(null!=baos)
    		{
    			bitmap.compress(Bitmap.CompressFormat.JPEG, quantity, baos);    			
    			bt = baos.toByteArray();
    			if(null!=bt)
    			{
    				fos = new FileOutputStream(file);
    				if(null!=fos)
    				{
	    				for(int i=0;i<bt.length;i++)
	    				{
	    					bt[i] = (byte) (bt[i]^ 0x94);
	    				} 
	    				fos.write(bt);
	    				fos.flush();
	    	    		fos.close(); 
	    	    		return true;
	    			}
    			}
    		}    		
    	}
    	catch(Exception ex)
    	{
    	}
    	finally
    	{
    		baos = null;
    		bitmap = null;
    		file = null;
    		fos = null;
    		bt = null;
    	}
    	return false;
    }
    
    /**
     * 解密文件（�?用于几百K以内的小文件�?lilw)
     * @param filepath
     * @return
     */
    public static boolean decryFile(String srcfilepath, String desfilepath)
    {
    	File srcfile = null;
    	
    	try
    	{
    		if(null==srcfilepath || "".equals(srcfilepath))return false;
    		srcfile = new File(srcfilepath);
    		if(null!=srcfile && srcfile.exists() && srcfile.isFile())
    		{
    			return decryFile(srcfile,new File(desfilepath));
    		}
    		
    	}
    	catch(Exception ex){}
    	finally
    	{
    		srcfilepath = null;
    		desfilepath = null;
    		srcfile = null;
    	}
    	return false;
    }
    /**
     * 解密文件（�?用于几百K以内的小文件�?
     * @param file
     */
    public static boolean decryFile(File srcfile, File desfile)
    {
    	InputStream is = null;
    	byte[] buff = null;
    	FileOutputStream fos = null;
    	try
    	{
    		if(null==srcfile)return false;
    		if(null==desfile)return false;
    		if(srcfile.exists() && srcfile.isFile())
    		{
    			is = new BufferedInputStream(new FileInputStream(srcfile));
    			if(null!=is)
    			{
    				buff = new byte[is.available()]; 
    				if(null!=buff)
    				{
    					is.read(buff);
    					is.close();
    					for(int i=0;i<buff.length;i++)
	    				{
    						buff[i] = (byte) (buff[i]^ 0x94);
	    				}
    					if(desfile.exists())
    					{
    						desfile.delete();
    					}
    					desfile.createNewFile();
    					fos = new FileOutputStream(desfile);
	    				if(null!=fos)
	    				{
	    					fos.write(buff);
		    				fos.flush();
		    	    		fos.close();
		    	    		return true;
	    				}
    				}
    			}
    		}
    	}
    	catch(Exception ex){}
    	finally
    	{
    		fos = null;
    		srcfile = null;
    		is = null;
    		desfile = null;
    	}
    	return false;
    }
    
    
    
    /**
     * 
     * 读取文件为字节数�?
     */
    public static byte[] getStringByPath(String path)
    {
    	if (null == path || "".equals(path))
    	{
			return null;
		}
		File file = new File(path);
		if (!file.exists()) 
		{
			return null;
		}

		InputStream fis = null;
		ByteArrayOutputStream baos = null;
		InputStream is = null;
		try
		{
			baos = new ByteArrayOutputStream();
			fis = new FileInputStream(file);
			is = new BufferedInputStream(fis);
			// 长度
			int count = 0;
			// �?��性读取的字节
			byte[] buf = new byte[1024];
			// 循环读取，以-1为结束标�?
			while ((count = is.read(buf)) != -1)
			{
				if (count > 0) 
				{
					baos.write(buf, 0, count);
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				if (null != fis) 
				{
					fis.close();
				}
				if (null != is)
				{
					is.close();
				}
				if (null != baos) 
				{
					baos.close();
				}
			} 
			catch (Exception e2) 
			{
			}
		}
		return baos.toByteArray();
    }
    
    /**
	 * PX转成dip
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public int px2dip(Context context, float pxValue) 
	{
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	/**
	 * dip转化成px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue)
	{
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
	
	/**
	 * �?��是否有对应的权限
	 * @param permName
	 * 				权限的名�?
	 * @param pkgName
	 * 				程序的包�?
	 * @param context
	 * 				上下�?
	 * @return
	 */
	public static boolean checkpermission(String permName,String pkgName,Context context)
	{
	    if(null == permName || "".equals(permName) || 
	    		pkgName == null || "".equals(pkgName) || 
	    		null == context)
	    {
	    	return false;
	    }
	    
	    PackageManager pm = context.getPackageManager();
	    return (PackageManager.PERMISSION_GRANTED == pm.checkPermission(permName, pkgName));
	}
	
	//------------------------------对手机内存的读取删除操作方法----------------------------------
	/**
	 * 写入到手机内存中
	 * @param fileName
	 * 			文件�?
	 * @param content
	 * 			写入的内�?
	 * @param context
	 * 			上下文对�?
	 * @return
	 * 			返回是否写入成功
	 */
	public static boolean write(String fileName,String content,Context context) 
	{  
		if(null == fileName || "".equals(fileName) || null == content || "".equals(content) || null == context)
		{
			return false;
		}
		boolean b = true;
		FileOutputStream fileOut = null;
        try
        {  
            // 以追加的方式打开文件输出�? 
            fileOut = context.openFileOutput(fileName,Activity.MODE_APPEND);  
            // 写入数据  
            fileOut.write(content.getBytes());  
        }
        catch (Exception e)
        {  
            e.printStackTrace();  
        }  
        finally
        {
        	 // 关闭文件输出�? 
            try 
            {
            	if(null != fileOut)
            	{
            		fileOut.close();
            	}
            	fileOut = null;
			} 
            catch (Exception e) 
			{
				e.printStackTrace();
				b = false;
			}  
        }
        return b;
    }  
  
	/**
	 * 读取手机内存的文�?
	 * @param fileName
	 * 			文件�?
	 * @param context
	 * 			上下文对�?
	 * @return
	 * 			返回读取的内�?
	 */
    public static String read(String fileName,Context context) 
    {  
    	String txt = null;
    	FileInputStream fileInput = null;
    	BufferedReader br = null;
    	if(null == fileName || "".equals(fileName) || null == context)
    	{
    		return txt;
    	}
        try 
        {  
            // 打开文件输入�? 
            fileInput = context.openFileInput(fileName);  
            br = new BufferedReader(new InputStreamReader(fileInput));  
            String str = null;  
            StringBuilder stb = new StringBuilder();  
            while ((str = br.readLine()) !=null ) 
            {  
                stb.append(str);  
            }  
            str = null;
            txt = stb.toString();
            stb = null;
            
        } 
        catch (Exception e) 
        {  
            e.printStackTrace(); 
            txt = null;
        } 
        finally
        {
        	try 
        	{
				if(null != fileInput)
				{
					fileInput.close();
				}
				fileInput = null;
				
				if(null != br)
				{
					br.close();
				}
				br = null;
			}
        	catch (Exception e2)
			{
			}
        }
  
        return txt;
    }  
      
    //删除指定的文�? 
    public static boolean deleteFiles(String fileName,Context context) 
    {  
    	boolean b = false;
    	if(null == fileName || "".equals(fileName) || null == context)
    	{
    		return b;
    	}
        try 
        {  
            /*// 获取data文件中的�?��文件列表  
            List<String> name = Arrays.asList(context.fileList());  
            if (name.contains(fileName)) {  
            	context.deleteFile(fileName);  
            }*/
        	b = context.deleteFile(fileName);
        } 
        catch (Exception e) 
        {  
            e.printStackTrace();
            b = false;
        }  
        
        return b;
    }  
    
  //----------------------------------------------------------------
   
    /**
     * 获得圆角图片的方�? 
     * @param bitmap
     * @param roundPx
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx){  
          
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap  
                .getHeight(), Config.ARGB_8888);  
        Canvas canvas = new Canvas(output);  
   
        final int color = 0xff424242;  
        final Paint paint = new Paint();  
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());  
        final RectF rectF = new RectF(rect);  
   
        paint.setAntiAlias(true);  
        canvas.drawARGB(0, 0, 0, 0);  
        paint.setColor(color);  
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);  
   
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
        canvas.drawBitmap(bitmap, rect, rect, paint);  
   
        return output;  
    }  
    
    /**
     * 邮箱验证
     * @param address
     * @return
     */
    public static boolean isEMail(String address)
	{
		String check = null;
		Pattern regex = null;
		Matcher matcher = null;
		try
		{
			if(null==address || "".equals(address))return false;
			check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			regex = Pattern.compile(check);
			if(null!=regex)
			{
				matcher = regex.matcher(address);
				if(null!=matcher)
				{
					return matcher.matches();
				}
			}
		}
		catch(Exception ex){}
		finally
		{
			regex = null;
			check = null;
			matcher = null;
		}
		return false;
	}
    
    
    public String getMIME(String end)
    {
        for(int i=0;i<MIME_MapTable.length;i++)
        {

            if(end.equals(MIME_MapTable[i][0]))

               return MIME_MapTable[i][1];

        }
        return "";
    }
    private final String[][] MIME_MapTable = 
    {

	    //{后缀名，    MIME类型}

	    {".3gp",    "video/3gpp"},

	    {".apk",    "application/vnd.android.package-archive"},

	    {".asf",    "video/x-ms-asf"},

	    {".avi",    "video/x-msvideo"},

	    {".bin",    "application/octet-stream"},

	    {".bmp",      "image/bmp"},

	    {".c",        "text/plain"},

	    {".class",    "application/octet-stream"},

	    {".conf",    "text/plain"},

	    {".cpp",    "text/plain"},

	    {".doc",    "application/msword"},

	    {".exe",    "application/octet-stream"},

	    {".gif",    "image/gif"},

	    {".gtar",    "application/x-gtar"},

	    {".gz",        "application/x-gzip"},

	    {".h",        "text/plain"},

	    {".htm",    "text/html"},

	    {".html",    "text/html"},

	    {".jar",    "application/java-archive"},

	    {".java",    "text/plain"},

	    {".jpeg",    "image/jpeg"},

	    {".jpg",    "image/jpeg"},

	    {".js",        "application/x-javascript"},

	    {".log",    "text/plain"},

	    {".m3u",    "audio/x-mpegurl"},

	    {".m4a",    "audio/mp4a-latm"},

	    {".m4b",    "audio/mp4a-latm"},

	    {".m4p",    "audio/mp4a-latm"},

	    {".m4u",    "video/vnd.mpegurl"},

	    {".m4v",    "video/x-m4v"},    

	    {".mov",    "video/quicktime"},

	    {".mp2",    "audio/x-mpeg"},

	    {".mp3",    "audio/x-mpeg"},

	    {".mp4",    "video/mp4"},

	    {".mpc",    "application/vnd.mpohun.certificate"},        

	    {".mpe",    "video/mpeg"},    

	    {".mpeg",    "video/mpeg"},    

	    {".mpg",    "video/mpeg"},    

	    {".mpg4",    "video/mp4"},    

	    {".mpga",    "audio/mpeg"},

	    {".msg",    "application/vnd.ms-outlook"},

	    {".ogg",    "audio/ogg"},

	    {".pdf",    "application/pdf"},

	    {".png",    "image/png"},

	    {".pps",    "application/vnd.ms-powerpoint"},

	    {".ppt",    "application/vnd.ms-powerpoint"},

	    {".prop",    "text/plain"},

	    {".rar",    "application/x-rar-compressed"},

	    {".rc",        "text/plain"},

	    {".rmvb",    "audio/x-pn-realaudio"},

	    {".rtf",    "application/rtf"},

	    {".sh",        "text/plain"},

	    {".tar",    "application/x-tar"},    

	    {".tgz",    "application/x-compressed"}, 

	    {".txt",    "text/plain"},

	    {".wav",    "audio/x-wav"},

	    {".wma",    "audio/x-ms-wma"},

	    {".wmv",    "audio/x-ms-wmv"},

	    {".wps",    "application/vnd.ms-works"},

	    //{".xml",    "text/xml"},

	    {".xml",    "text/plain"},

	    {".z",        "application/x-compress"},

	    {".zip",    "application/zip"},

	    {"",        "*/*"}    

	};
    
    /**
     * 隐藏键盘
     */
    public static void hideSoftInput(Context context)
    {
    	InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);  
        if(inputMethodManager.isActive())
        {  
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), 0);  
        }  
    }
    
    /**
     * 显示键盘
     */
    public static void showSoftInput(Context context)
    {
    	//获取输入法的状�?
    	InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);  
    	imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);	
    }
}
