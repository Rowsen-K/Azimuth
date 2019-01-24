package com.rowsen.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
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
import android.widget.Button;
import android.widget.ImageView;
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
    ImageView mkimg;
    Button about;
    Button hold;
    SensorManager sm;
    Sensor m;
    Sensor a;
    Sensor o;
    float[] mv;
    float[] av;
    float[] ov;
    String latitude;
    String longtitude;
    AlertDialog ad;
    SensorEventListener myListener;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("WrongCall")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar()!=null){
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
        pos = findViewById(R.id.position); mkimg = findViewById(R.id.mark); mk = findViewById(R.id.localion);
        state = findViewById(R.id.state);
        about = findViewById(R.id.about);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(MainActivity.this,AboutActivity.class);
              //  startActivity(intent);
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setView(R.layout.wx_qrcode_dialog_layout);
                builder.setCancelable(false);
                ad = builder.create();
                ad.show();
            }
        });
        hold = findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(compass.getRotationState()){
                    hold.setText("锁定");
                    compass.setCompassRotate(false);
                }
                else {
                    hold.setText("释放");
                    compass.setCompassRotate(true);
                }
            }
        });
        s.setCallback(this);
        znzimg.setVisibility(View.GONE);
        compass = findViewById(R.id.compassview);
        compass.setVisibility(View.VISIBLE);
        compass.setCompassRotate(true);
        canvas = new Canvas();
        compass.setNorth(0);
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
                state.setText("当前为上次GPS关闭时最后定位点");
                state.setTextColor(getResources().getColor(R.color.colorAccent));
                mkimg.setImageResource(R.drawable.ic_alertred);
            }
            locationManager.requestLocationUpdates(provide, 5000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location loc) {
                    location = loc;
                    float azimuth = cali.cal(location.getLongitude(),location.getLatitude()).floatValue();
                    state.setTextColor(getResources().getColor(R.color.green));
                    mkimg.setImageResource(R.drawable.icon_location);
                    state.setText("当前为实时位置");
                    set(azimuth);
                    compass.setFlag(true);
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {
                    Toast.makeText(MainActivity.this,"GPS已打开请耐心等待等位!",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onProviderDisabled(String s) {
                    Toast.makeText(MainActivity.this,"请确认是否开启了GPS以及许可了程序获取位置数据的权限！",Toast.LENGTH_LONG).show();
                    mkimg.setImageResource(R.drawable.ic_alertred);
                    state.setText("请确认GPS状态");
                    state.setTextColor(getResources().getColor(R.color.colorAccent));
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION },0);
        }
        if(location!=null){
            set(cali.cal(location.getLongitude(),location.getLatitude()).floatValue());
        }
        else {
            Toast.makeText(this,"请确认是否开启了GPS以及许可了程序获取位置数据的权限！",Toast.LENGTH_LONG).show();
            mkimg.setImageResource(R.drawable.ic_alertred);
        }

        sm = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
      //  m = sm.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
      //  a = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        o = sm.getDefaultSensor(Sensor.TYPE_ORIENTATION);

     //   mv = new float[3];
     //   av = new float[3];
        ov = new float[3];
     //   sm.registerListener(myListener,m,SensorManager.SENSOR_DELAY_UI);
    //    sm.registerListener(myListener,a,SensorManager.SENSOR_DELAY_UI);
        sm.registerListener(myListener,o,SensorManager.SENSOR_DELAY_UI);
        myListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
             /*   if(sensorEvent.sensor.getType()==Sensor.TYPE_ACCELEROMETER)
                    av = sensorEvent.values;
                if(sensorEvent.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD)
                    mv = sensorEvent.values;
                */
             if(sensorEvent.sensor.getType()==Sensor.TYPE_ORIENTATION) {
                 //ov = sensorEvent.values;
               synchronized (MainActivity.class){
                   for(int n = 0;n < ov.length;n++){
                       ov[n] = sensorEvent.values[n];
                      // System.out.println("=======ov"+n+":"+ov[n]);
                   }
                 String north = getNorth();
                 tv.setText(north);
                 compass.setNorth(Float.valueOf(north));
                 compass.invalidate();
                 }
             }
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(location!=null) {
                    set(cali.cal(location.getLongitude(), location.getLatitude()).floatValue());
                    handler.postDelayed(this, 10);
                }
            }
        },100);
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

    @Override
    protected void onPause() {
        sm.unregisterListener(myListener);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // sm.registerListener(myListener,m,SensorManager.SENSOR_DELAY_NORMAL);
       // sm.registerListener(myListener,a,SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(myListener,o,SensorManager.SENSOR_DELAY_UI);
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
        if(location.getLatitude()>0)
            latitude = changeDegree(location.getLatitude())+"N";
        else latitude = changeDegree(Math.abs(location.getLatitude()))+"S";
        if(location.getLongitude()>0)
            longtitude = changeDegree(location.getLongitude())+"E";
        else longtitude = changeDegree(Math.abs(location.getLongitude()))+"W";
        pos.setText("纬度："+latitude+"\n经度："+longtitude);
        //tv.setText(String.format("%.2f",azimuth));
        cw.setText(String.format("%.2f",Math.toDegrees(cali.C41))+"°");
        time_angle.setText(String.format("%.2f",Math.toDegrees(cali.C46))+"°");
        high_angle.setText(String.format("%.2f",Math.toDegrees(cali.C48))+"°");
      //  state.setTextColor(getResources().getColor(R.color.green));
      //  state.setText("当前为实时位置");
       // compass.getSensorValue().c(360.0f-azimuth);
        compass.getSensorValue().b(Float.valueOf(String.format("%.2f",azimuth)));
        compass.invalidate();
    }
    String getNorth(){
       /* float[] result = new float[3];
        float[] r = new float[9];
        sm.getRotationMatrix(r,null,av,mv);
        sm.getOrientation(r,result);
        */
        //Double north = Math.toDegrees(ov[0]);
        float north = ov[0];
        if(north<0) north = north + 360;
        compass.getSensorValue().c(360.0f-north);
        compass.invalidate();
        return String.format("%.0f",north);
    }

    public void close(View view) {
        ad.dismiss();
    }
}
