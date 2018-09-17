package com.rowsen.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SizeNotiRelativeLayout.a{

    RelativeLayout rl_cp;
    public static float compassSizeRate = 0.96F;
    SizeNotiRelativeLayout s;
    CompassView compass;
    CompassRotationViews znzimg;
    TextView tv ;

    Handler handler;
    String provide;
    LocationManager locationManager;
    Location location;
    Calibrate cali;
    Canvas canvas;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl_cp = findViewById(R.id.rl_cp);
        s = findViewById(R.id.relativeLayout1);
        znzimg = findViewById(R.id.znzImage);
        s.setCallback(this);
        znzimg.setVisibility(View.GONE);
        compass = findViewById(R.id.compassview);
        compass.setVisibility(View.VISIBLE);
        compass.setCompassRotate(true);
        canvas = new Canvas();
        compass.draw(canvas);
        tv = findViewById(R.id.azimuth_type_textview);
        cali = new Calibrate();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        provide = LocationManager.GPS_PROVIDER;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(provide);
            if(location!=null) {
                tv.setText("当前位置为最后定位位置！");
                tv.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            locationManager.requestLocationUpdates(provide, 10000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location loc) {
                    location = loc;
              //      pos.setText("纬度："+changeDegree(location.getLatitude())+"\n经度："+changeDegree(location.getLongitude()));
               //     azimuth.setText(String.format("%.2f",cali.cal(location.getLongitude(),location.getLatitude())));
               //     param1.setText(String.format("%.2f",Math.toDegrees(cali.C41)));
               //     param2.setText(String.format("%.2f",Math.toDegrees(cali.C46)));
               //     param3.setText(String.format("%.2f",Math.toDegrees(cali.C48)));
               //     state.setTextColor(getResources().getColor(R.color.green));
               //     state.setText("当前为实时位置");
                    compass.getSensorValue().c(cali.cal(loc.getLongitude(), loc.getLatitude()).floatValue());
                    compass.draw(canvas);
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION },0);
        }
        //  param1.setText(String.valueOf(ciwei));
        if(location!=null){
          //  pos.setText("纬度："+changeDegree(location.getLatitude())+"经度："+changeDegree(location.getLongitude()));
       //     azimuth.setText(String.format("%.2f",cali.cal(location.getLongitude(),location.getLatitude())));
        //    param1.setText(String.format("%.2f",Math.toDegrees(cali.C41)));
         //   param2.setText(String.format("%.2f",Math.toDegrees(cali.C46)));
        //    param3.setText(String.format("%.2f",Math.toDegrees(cali.C48)));
            compass.getSensorValue().c(cali.cal(location.getLongitude(), location.getLatitude()).floatValue());
            compass.draw(canvas);
        }
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(location!=null) {
                   // cali.cal(location.getLongitude(), location.getLatitude());
                  //  azimuth.setText(String.format("%.2f", cali.cal(location.getLongitude(), location.getLatitude())));
                 //   param1.setText(String.format("%.2f", Math.toDegrees(cali.C41)));
                 //   param2.setText(String.format("%.2f", Math.toDegrees(cali.C46)));
                 //   param3.setText(String.format("%.2f", Math.toDegrees(cali.C48)));
                    compass.getSensorValue().c(cali.cal(location.getLongitude(), location.getLatitude()).floatValue());
                    compass.draw(canvas);
                    System.out.println(cali.cal(location.getLongitude(), location.getLatitude()));
                    handler.postDelayed(this, 2000);
                }
            }
        },100);


      //  compass.getSensorValue().c(new Calibrate().cal(120.084, 60.422).floatValue());
    }

    @Override
        public void OnSizeChanged(int paramInt1, int paramInt2)
        {
            int i = paramInt1;
            if (paramInt1 > paramInt2) {
                i = paramInt2;
            }
            ViewGroup.LayoutParams localLayoutParams = this.rl_cp.getLayoutParams();
            float f = i;
            localLayoutParams.height = ((int)(compassSizeRate * f));
            this.rl_cp.getLayoutParams().width = ((int)(f * compassSizeRate));
        }

}
