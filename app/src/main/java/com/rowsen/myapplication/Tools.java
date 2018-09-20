package com.rowsen.myapplication;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
    目标：制作一个常用的工具类集合
    1、构造时传入一个上下文，获取程序的环境，再传入一个文件名，拿到raw目录的该文件（txt），
    最好是拿到文件对象的方式，但是现在没有找到方法，只能拿到inputStream流
    2、考试app的文件处理方法file2list，需要提供2个参数，一个是要处理的文件流，第二个是处理后需要保存到的集合对象
    3、list集合对象序列化到xml文件,2个参数，一个是需要序列化的list对象，一个是最终保存的文件对象
    4、抽题工具，随机获取一组题，选择题40个，判断题60个，形成一个list集合
    5、截图保存功能
 */
public class Tools {

    // 获取指定Activity的截屏，保存到png文件
    public static Bitmap takeScreenShot(Activity activity) {
        // View是你需要截图的View
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();
        // 获取状态栏高度
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        Log.i("TAG", "" + statusBarHeight);
        // 获取屏幕长和高
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // 去掉状态栏，如果需要的话
        // Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    // 保存到sdcard
    private static void savePic(Bitmap b, String strFileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strFileName);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.PNG, 90, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 程序入口，外界直接调用此方法即可
    public static void shoot(Activity a) {
        Tools.savePic(Tools.takeScreenShot(a), Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Rowsen.png");
    }
}
