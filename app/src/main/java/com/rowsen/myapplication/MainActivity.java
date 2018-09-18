package com.rowsen.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.Toast;

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
    SensorManager smg;
    Location location;
    Calibrate cali;
    Canvas canvas;
    TextView pos;
    TextView high_angle;
    TextView time_angle;
    TextView utc;
    TextView cw;
    TextView state;
    TextView mk;
    String degree;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_main);
        rl_cp = findViewById(R.id.rl_cp);
        s = findViewById(R.id.relativeLayout1);
        znzimg = findViewById(R.id.znzImage);
        cw = findViewById(R.id.cw_degree);
        time_angle = findViewById(R.id.time_angle_degree);
        high_angle = findViewById(R.id.high_angle_degree);
        utc = findViewById(R.id.utc);
        pos = findViewById(R.id.position);  mk = findViewById(R.id.localion);
        state = findViewById(R.id.state);
        s.setCallback(this);
        znzimg.setVisibility(View.GONE);
        compass = findViewById(R.id.compassview);
        compass.setVisibility(View.VISIBLE);
        compass.setCompassRotate(true);
        canvas = new Canvas();
        compass.draw(canvas);
        tv = findViewById(R.id.azimuth_textview);
        cali = new Calibrate();

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        provide = LocationManager.GPS_PROVIDER;
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            location = locationManager.getLastKnownLocation(provide);
            if(location!=null) {
                state.setText("当前位置为上次GPS开启时最后定位位置！");
                state.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            locationManager.requestLocationUpdates(provide, 5000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location loc) {
                    location = loc;
                    float azimuth = cali.cal(location.getLongitude(),location.getLatitude()).floatValue();
                   // pos.setText("纬度："+changeDegree(location.getLatitude())+"\n经度："+changeDegree(location.getLongitude()));
                  //  tv.setText(String.format("%.2f",azimuth));
                  //  cw.setText(String.format("%.2f",Math.toDegrees(cali.C41)));
                 //   time_angle.setText(String.format("%.2f",Math.toDegrees(cali.C46)));
                 //   high_angle.setText(String.format("%.2f",Math.toDegrees(cali.C48)));
                    state.setTextColor(getResources().getColor(R.color.green));
                    state.setText("当前为实时位置");
                    set(azimuth);
                //    compass.getSensorValue().c(azimuth);
                   // compass.draw(canvas);
                //    compass.invalidate();
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
         //   compass.getSensorValue().c(cali.cal(location.getLongitude(), location.getLatitude()).floatValue());
         //   compass.invalidate(); //compass.draw(canvas);
            set(cali.cal(location.getLongitude(),location.getLatitude()).floatValue());
        }
        else Toast.makeText(this,"请确认是否开启了GPS以及许可了程序获取位置数据的权限！",Toast.LENGTH_LONG).show();

        smg = (SensorManager) getSystemService(SENSOR_SERVICE);
        smg.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                double temp = Math.toDegrees(sensorEvent.values[0]);
                if (temp<0) {
                    temp = temp +360;
                }
                degree = String.format("%.2f",temp);
                tv.setText(degree);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, smg.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);

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
                //    compass.getSensorValue().c(cali.cal(location.getLongitude(), location.getLatitude()).floatValue());
                //    compass.invalidate();//compass.draw(canvas);
                    set(cali.cal(location.getLongitude(), location.getLatitude()).floatValue());
                    handler.postDelayed(this, 10);
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

    String changeDegree(double l){
        double du = Math.floor(l);
        double fen = (l-du)*60;
        //double miao = Math.floor(((l-du)*60-fen)*60);
        String temp = (int)du+"°"+String.format("%.3f",fen)+"′";
        return temp;
    }//位置数据小数化度分秒

    void set(float azimuth){
        mk.setVisibility(View.GONE);
        pos.setText("纬度："+changeDegree(location.getLatitude())+"\n经度："+changeDegree(location.getLongitude()));
        tv.setText(degree);
        cw.setText(String.format("%.2f",Math.toDegrees(cali.C41))+"°");
        time_angle.setText(String.format("%.2f",Math.toDegrees(cali.C46))+"°");
        high_angle.setText(String.format("%.2f",Math.toDegrees(cali.C48))+"°");
      //  state.setTextColor(getResources().getColor(R.color.green));
      //  state.setText("当前为实时位置");
        compass.getSensorValue().c(360.0f-azimuth);
        compass.getSensorValue().b(Float.valueOf(String.format("%.2f",azimuth)));
        compass.invalidate();
    }
}
