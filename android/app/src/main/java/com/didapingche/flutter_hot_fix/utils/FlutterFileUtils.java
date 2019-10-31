package com.didapingche.flutter_hot_fix.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import dalvik.system.PathClassLoader;

public class FlutterFileUtils {
    ///将文件拷贝到私有目录
    public static String copyLibAndWrite(Context context, String fileName){
        try {
            File dir = context.getDir("libs", Activity.MODE_PRIVATE);
            File destFile = new File(dir.getAbsolutePath() + File.separator + fileName);
            if (destFile.exists() ) {
                destFile.delete();
            }

            if (!destFile.exists()){
                boolean res = destFile.createNewFile();
                if (res){

                    String path = Environment.getExternalStorageDirectory().toString();
                    FileInputStream is = new FileInputStream(new File(path + "/" + fileName));

                    FileOutputStream fos = new FileOutputStream(destFile);
                    byte[] buffer = new byte[is.available()];
                    int byteCount;
                    while ((byteCount = is.read(buffer)) != -1){
                        fos.write(buffer,0,byteCount);
                    }
                    fos.flush();
                    is.close();
                    fos.close();
                    return destFile.getAbsolutePath();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "";
    }

}
