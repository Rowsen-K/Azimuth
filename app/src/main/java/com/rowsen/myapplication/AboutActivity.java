package com.rowsen.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity implements View.OnLongClickListener {
    ImageView img;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_about);
        img = (ImageView) findViewById(R.id.img);
        // img.setImageBitmap(Tools.decodeSampledBitmapFromResource(getResources(),r_round.drawable.about,100,100));
        ImageLoaderConfiguration config = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(config);
        DisplayImageOptions opt = DisplayImageOptions.createSimple();
        ImageLoader.getInstance().displayImage("drawable://" + R.drawable.about, img, opt);

        //旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1600);
        rotateAnimation.setFillAfter(true);
        //缩放
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(2000);
        scaleAnimation.setFillAfter(true);
        //渐变
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        //把每个效果到放进来
        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        //开启动画
        img.startAnimation(animationSet);
        /*
        new Thread() {
            @Override
            public void run() {
                try {
                    Connection connection = Jsoup.connect("https://sc.ftqq.com/SCU32217T8041223572b586408b08c1ba3b5c03135b9b147839307.send?text=用户：" + getSharedPreferences("Personal", MODE_PRIVATE).getString("num", null));
                    connection.method(Connection.Method.POST);
                    connection.data("desp", "永久激活码：" + getIntent().getStringExtra("code"));
                    connection.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        */
        img.setOnLongClickListener(this);
        PermissionUtils.verifyStoragePermissions(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

    }

    @Override
    public boolean onLongClick(View v) {
        Tools.shoot(this);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Rowsen.png"));
        intent.setData(uri);
        sendBroadcast(intent);
        // Tools.saveImageToGallery(this,Tools.takeScreenShot(this));
        Toast.makeText(this, "截图已保存请使用微信扫一扫关注,欢迎上报Bug与捐赠!", Toast.LENGTH_LONG).show();
        try {
            //利用Intent打开微信
            Uri uri1 = Uri.parse("weixin://");
            Intent intent1 = new Intent(Intent.ACTION_VIEW, uri1);
            startActivity(intent1);
        } catch (Exception e) {
            //若无法正常跳转，在此进行错误处理
            //  Toast.makeText(this, "无法跳转到微信，请检查是否安装了微信", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

}
