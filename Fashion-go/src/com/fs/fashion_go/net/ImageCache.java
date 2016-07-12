package com.fs.fashion_go.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fs.fashion_go.utils.MD5;



import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageCache {
    /* 
     * 从网络上获取图片，如果图片在本地存在的话就直接拿，如果不存在再去服务器上下载图片 
     * 这里的path是图片的地址 
     */  
    public Uri getImageURI(String path, File cache) throws Exception {  
    	/**
    	 * path.lastIndexOf("."):获得path字符串中最后出现"."这个字符的索引
    	 * path.substring:截取字符串
    	 */
        String name = MD5.getMD5(path) + path.substring(path.lastIndexOf("."));//给文件命名
        File file = new File(cache, name);  
        // 如果图片存在本地缓存目录，则不去服务器下载   
        if (file.exists()) {  
            return Uri.fromFile(file);//Uri.fromFile(path)这个方法能得到文件的URI  
        } else {  
            // 从网络上获取图片  
            URL url = new URL(path);  
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setRequestMethod("GET");  
            conn.setDoInput(true);  
            if (conn.getResponseCode() == 200) {  
  
                InputStream is = conn.getInputStream();//获得网络输入流  
                FileOutputStream fos = new FileOutputStream(file);//文件输出流  
                byte[] buffer = new byte[1024];//byte数组，长度1024字节  
                int len = 0;  
                while ((len = is.read(buffer)) != -1) { //is.read(buffer)返回-1表示数据已经读取完 
                    fos.write(buffer, 0, len);//写入文件  
                }  
                is.close();  
                fos.close();  
                // 返回一个URI对象  
                return Uri.fromFile(file);  
            }  
        }  
        return null;  
    }  
    
    public static void asyncloadImage(final ImageView iv_header, final String path,final File cache) { 
        final Handler mHandler = new Handler() { 
            @Override 
            public void handleMessage(Message msg) { 
                super.handleMessage(msg); 
                if (msg.what == 0) { 
                    Uri uri = (Uri) msg.obj; 
                    if (iv_header != null && uri != null) { 
                        iv_header.setImageURI(uri); 
                    } 
 
                } 
            } 
        }; 
        // 子线程，开启子线程去下载或者去缓存目录找图片，并且返回图片在缓存目录的地址 
        Runnable runnable = new Runnable() { 
            @Override 
            public void run() { 
                ImageCache service = new ImageCache(); 
                try { 
                    //这个URI是图片下载到本地后的缓存目录中的URI 
                    Uri uri = service.getImageURI(path, cache); 
                    Message msg = new Message(); 
                    msg.what = 0; 
                    msg.obj = uri; 
                    mHandler.sendMessage(msg); 
                } catch (Exception e) { 
                    e.printStackTrace(); 
                } 
            } 
        }; 
        new Thread(runnable).start(); 
    }
    //删除缓存
    public static void delCache(File cache){
    	File[] files = cache.listFiles();  
        for(File file :files){  
            file.delete();  
        }  
        cache.delete();
    }
}
