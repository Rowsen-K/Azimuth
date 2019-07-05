package com.rowsen.myapplication;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Log;

import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

public class a {
    private Path A = null;
    private boolean B = false;
    private float C = 0.0F;
    private float D = 0.0F;
    private float E;
    private Paint F = new Paint();
    private Path G = new Path();
    private Path H = new Path();
    private final Paint a = new Paint(1);
    private final Paint b = new Paint(1);
    private final Paint c = new Paint(1);
    private final Paint d = new Paint(1);
    private final Paint e = new Paint(1);
    private final Paint f = new Paint(1);
    private final Paint g = new Paint(1);
    private final Paint h = new Paint(1);
    private final Path i = new Path();
    private final b j = new b();
    // private final float k = 430.0F;
    private boolean l = true;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private int s;

    private Context t;
    private Typeface u;

    private c v = new c(30L, 123L);
    private float w;
    private Point x;
    private float y;

    private Path z = null;

    float north;
    boolean flag = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public a(Context paramContext) {
        this.t = paramContext;
        this.u = k.a(paramContext, "Roboto-Light.ttf");
    }

    private float a(float paramFloat) {
        return paramFloat * this.w;
    }

    private void a(Canvas paramCanvas, float paramFloat1, String paramString, float paramFloat2) {
        Paint.FontMetrics localFontMetrics = this.a.getFontMetrics();
        float f1 = localFontMetrics.bottom;
        float f2 = localFontMetrics.top;
        float f3 = localFontMetrics.leading;
        double d1 = paramFloat1;
        float f4 = (float) Math.cos(Math.toRadians(d1));
        float f5 = (float) Math.sin(Math.toRadians(d1));
        float f6 = a(paramFloat2);
        float f7 = this.x.x;
        paramFloat2 = a(paramFloat2);
        float f8 = this.x.y;
        paramCanvas.save();
        paramCanvas.translate(f4 * f6 + f7, f5 * paramFloat2 + f8);
        paramCanvas.rotate(90.0F + paramFloat1);
        paramCanvas.drawText(paramString, -this.a.measureText(paramString) / 2.0F, f1 - f2 + f3, this.a);
        paramCanvas.restore();
    }

    private void a(Canvas paramCanvas, float paramFloat1, String paramString, float paramFloat2, Paint paramPaint) {
        Paint.FontMetrics localFontMetrics = paramPaint.getFontMetrics();
        float f1 = localFontMetrics.bottom;
        float f2 = localFontMetrics.top;
        float f3 = localFontMetrics.leading;
        double d1 = paramFloat1;
        float f4 = (float) Math.cos(Math.toRadians(d1));
        float f5 = (float) Math.sin(Math.toRadians(d1));
        float f6 = a(paramFloat2);
        float f7 = this.x.x;
        paramFloat2 = a(paramFloat2);
        float f8 = this.x.y;
        paramCanvas.save();
        paramCanvas.translate(f4 * f6 + f7, f5 * paramFloat2 + f8);
        if ((paramFloat1 > 0.0F) && (paramFloat1 < 180.0F)) {
            paramCanvas.rotate(270.0F + paramFloat1);
            paramCanvas.drawText(paramString, -paramPaint.measureText(paramString) / 2.0F, (f1 - f2 + f3) / 2.0F, paramPaint);
        } else {
            paramCanvas.rotate(90.0F + paramFloat1);
            paramCanvas.drawText(paramString, -paramPaint.measureText(paramString) / 2.0F, 0.0F, paramPaint);
        }
        paramCanvas.restore();
    }

    private void a(Canvas paramCanvas, Point paramPoint) {
        this.c.setColor(this.m);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeWidth(a(3.0F));
        if ((this.C != paramCanvas.getWidth()) || (this.D != paramCanvas.getHeight()) || (this.z == null)) {
            this.z = new Path();
            double d1;
            for (float f1 = 0.0F; ; f1 = (float) (d1 + Math.toRadians(2.5F))) {
                d1 = f1;
                if (d1 >= 6.283185307179586D) {
                    break;
                }
                f1 = (float) Math.cos(d1);
                float f2 = (float) Math.sin(d1);
                float f3 = a(350.0F);
                float f4 = a(350.0F);
                this.z.moveTo(f3 * f1 + paramPoint.x, f4 * f2 + paramPoint.y);
                f3 = a(380.0F);
                f4 = a(380.0F);
                this.z.lineTo(f3 * f1 + paramPoint.x, f4 * f2 + paramPoint.y);
            }
        }
        paramCanvas.drawPath(this.z, this.c);
    }

    //画笔初始化
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void b() {
        this.a.setTextSize(a(30.0F));
        this.a.setColor(this.o);
        this.a.setTypeface(this.u);
        if (this.B) {
            return;
        }
        this.b.setTextSize(a(60.0F));
        this.b.setTypeface(this.u);
        float f1 = a(820.0F);
        Object localObject = Shader.TileMode.CLAMP;
        localObject = new LinearGradient(0.0F, 0.0F, 0.0F, f1, new int[]{-65536, -16711936}, new float[]{0.3F, 1.0F}, (Shader.TileMode) localObject);
        this.d.setShader((Shader) localObject);
        this.d.setStrokeWidth(a(25.0F));
        this.d.setStyle(Paint.Style.STROKE);
        this.d.setStrokeCap(Paint.Cap.ROUND);
        this.m = ContextCompat.getColor(this.t, R.color.a);   //数字圈的颜色
        this.n = ContextCompat.getColor(this.t, R.color.b);  //主表盘的颜色,中心圈和外圈
        this.o = ContextCompat.getColor(this.t, R.color.white);   //磁单位\东西南文字\360等度数数字及中心显示实时数据文字的颜色
        this.p = ContextCompat.getColor(this.t, R.color.e);   //magfield\东北等三个偏角方向文字及外圈描边的颜色
        this.q = ContextCompat.getColor(this.t, R.color.c);   //北字的标记色
        this.r = ContextCompat.getColor(this.t, R.color.colorAccent);   //正北指针标记色
        this.s = ContextCompat.getColor(this.t, R.color.green);
        this.e.setColor(this.n);
        this.e.setStyle(Paint.Style.FILL);
        this.c.setStrokeCap(Paint.Cap.ROUND);
        this.f.setColor(this.p);
        this.f.setTypeface(this.u);
        this.g.setColor(this.o);
        this.g.setTypeface(this.u);
        this.h.setColor(this.o);
        this.h.setTypeface(this.u);
        this.B = true;
    }

    //绘制圆盘及外圈
    private void b(Canvas paramCanvas) {
        float f1 = a(430.0F);
        paramCanvas.drawCircle(this.x.x, this.x.y, f1, this.e);
        this.c.reset();
        this.c.setColor(this.p);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(a(3.0F));
        paramCanvas.drawCircle(this.x.x, this.x.y, f1, this.c);
        this.c.setColor(this.m);
        Object localObject = this.a.getFontMetrics();
        f1 = ((Paint.FontMetrics) localObject).bottom;
        float f2 = ((Paint.FontMetrics) localObject).top;
        float f3 = ((Paint.FontMetrics) localObject).leading;
        f2 = a(20.0F) + (f1 - f2 + f3);
        this.c.setStrokeWidth(f2);
        f1 = a(350.0F);
        f2 /= 2.0F;
        f3 = a(this.y);
        paramCanvas.drawCircle(this.x.x, this.x.y, f1 - f2 - f3, this.c);
        this.c.reset();
        this.c.setColor(this.s);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setAntiAlias(true);
        this.c.setStrokeWidth(a(10.0F));
        /*f1 = a(430.0F);
        localObject = new RectF(this.x.x - f1, this.x.y - f1, this.x.x + f1, this.x.y + f1);
        f2 = this.j.c();
        if (f2 < -180.0F) {
            f1 = f2 + 360.0F;
        } else {
            f1 = f2;
            if (f2 > 180.0F) {
                f1 = f2 - 360.0F;
            }
        }*/
        this.i.reset();
        /*Path localPath = this.i;
     *//*   if (!this.l) {
            f1 = -f1;
        }  *//*
        //可以在此处绘制一个外圈标记。预计加入磁北的标记
        this.c.setColor(this.r);
        // localPath.addArc((RectF) localObject, 270.0f - north - 1.5f, 3f);
        localPath.addArc((RectF) localObject, north - 91.5f, 3f);
        paramCanvas.drawPath(localPath, this.c);
        this.c.setColor(this.s);*/
    }

    private void b(Canvas paramCanvas, float paramFloat1, String paramString, float paramFloat2, Paint paramPaint) {
        Paint.FontMetrics localFontMetrics = paramPaint.getFontMetrics();
        float f1 = localFontMetrics.bottom;
        float f2 = localFontMetrics.top;
        float f3 = localFontMetrics.leading;
        double d1 = paramFloat1;
        float f4 = (float) Math.cos(Math.toRadians(d1));
        float f5 = (float) Math.sin(Math.toRadians(d1));
        float f6 = this.x.x;
        float f7 = this.x.y;
        paramCanvas.save();
        paramCanvas.translate(f4 * paramFloat2 + f6, f5 * paramFloat2 + f7);
        paramCanvas.rotate(90.0F + paramFloat1);
        paramCanvas.drawText(paramString, -paramPaint.measureText(paramString) / 2.0F, f1 - f2 + f3, paramPaint);
        paramCanvas.restore();
    }

    private void b(Canvas paramCanvas, Point paramPoint) {
        this.c.setStrokeWidth(a(7.0F));
        if ((this.C != paramCanvas.getWidth()) || (this.D != paramCanvas.getHeight()) || (this.A == null)) {
            this.A = new Path();
            float d1 = 0.0f;
            for (float f1 = 0.0F; ; f1 = (float) (d1 + Math.toRadians(30.0F))) {
                d1 = f1;
                if (d1 >= 6.283185307179586D) {
                    break;
                }
                f1 = (float) Math.cos(d1);
                float f2 = (float) Math.sin(d1);
                float f3 = a(330.0F);
                float f4 = a(330.0F);
                this.A.moveTo(f3 * f1 + paramPoint.x, f4 * f2 + paramPoint.y);
                f3 = a(380.0F);
                f4 = a(380.0F);
                this.A.lineTo(f1 * f3 + paramPoint.x, f2 * f4 + paramPoint.y);
            }
        }
        this.c.setColor(-1);
        paramCanvas.drawPath(this.A, this.c);
        this.i.reset();
        double d1 = (float) Math.toRadians(270.0D);
        float f1 = (float) Math.cos(d1);
        float f2 = (float) Math.sin(d1);
        float f3 = a(320.0F);
        float f4 = a(320.0F);
        this.i.moveTo(this.x.x + f3 * f1, this.x.y + f4 * f2);
        f3 = a(400.0F);
        f4 = a(400.0F);
        this.i.lineTo(f3 * f1 + this.x.x, f4 * f2 + this.x.y);
        this.c.setColor(this.q);
        this.c.setStrokeWidth(a(9.0F));
        paramCanvas.drawPath(this.i, this.c);
    }

    //绘制外圈的单位和磁场方向
    private void c(Canvas paramCanvas) {
        if ((this.C != paramCanvas.getWidth()) || (this.D != paramCanvas.getHeight()) || (this.E != this.j.a())) {
            float f1 = a(450.0F);
            this.F.reset();
            this.F.setColor(this.p);
            this.F.setStyle(Paint.Style.STROKE);
            this.F.setAntiAlias(true);
            this.d.setAntiAlias(true);
            this.F.setStrokeWidth(a(0.0F));
            this.F.setColor(this.n);
            RectF localRectF = new RectF(this.x.x - f1, this.x.y - f1, this.x.x + f1, this.x.y + f1);
            this.G.reset();
            //最外的圆弧指示magfield
            //  this.G.addArc(localRectF, 00.0F, 360.0F);
            f1 = Math.min(1.0F, this.j.a() / ' ') * 100;
            this.H.reset();
            this.H.addArc(localRectF, 410 - f1, f1);
            this.h.setTextSize(a(30.0F));
            this.f.setTextSize(a(30.0F));
            this.E = this.j.a();
        }
        // paramCanvas.drawPath(this.G, this.F);
        //  paramCanvas.drawPath(this.H, this.d);
        this.h.setColor(this.s);
        //  a(paramCanvas, 270.0f-north, String.format(Locale.US, "Rowsen", new Object[]{Integer.valueOf((int) this.j.a())}), 445.0F, this.h);
        if(getRotationState())
            a(paramCanvas,  - 90.0f, "Rowsen", 445.0F, this.h);
        else a(paramCanvas, north - 90.0f, "Rowsen", 445.0F, this.h);
        //this.h.setColor(this.o);
        this.f.setColor(this.o);
        this.h.setColor(this.o);
        this.f.setColor(this.o);
        a(paramCanvas, 90.0F, "Design By Rowsen", 445.0F, this.f);
        this.f.setColor(this.p);
    }

    //正北指针标记
    private void d(Canvas paramCanvas) {
        paramCanvas.save();
        if (this.l) {
            paramCanvas.rotate(j.c() + j.b(), this.x.x, this.x.y);
        } else {
            paramCanvas.rotate(j.b(), this.x.x, this.x.y);
        }
        float f1 = this.x.x;
        float f3 = a(30.0F);
        float f4 = this.x.y;
        float f5 = a(430.0F);
        float f2 = f3 / 2.0F;
        f4 = f4 - f5 + f2;
        this.i.reset();
        Path localPath = this.i;
        f5 = f1 - f2;
        f3 = f4 - f3;
        localPath.lineTo(f5, f3);
        this.i.lineTo(f2 + f1, f3);
        this.i.lineTo(f1, f4);
        this.i.lineTo(f5, f3);
        this.c.setStyle(Paint.Style.FILL);
        //this.c.setColor(this.r);
        c.setColor(this.q);
        this.c.setShadowLayer(a(4.0F), 0.0F, 0.0F, -65536);
        //绘制三角标
        paramCanvas.drawPath(this.i, this.c);
        this.i.reset();
        double d1 = (float) Math.toRadians(270.0D);
        f1 = (float) Math.cos(d1);
        f2 = (float) Math.sin(d1);
        f3 = a(320.0F);
        f4 = a(320.0F);
        this.i.moveTo(this.x.x + f3 * f1, this.x.y + f4 * f2);
        f3 = a(400.0F);
        f4 = a(400.0F);
        this.i.lineTo(f3 * f1 + this.x.x, f4 * f2 + this.x.y);
        this.c.setStyle(Paint.Style.STROKE);
        this.c.setStrokeWidth(a(9.0F));
        //绘制红色指针标
        paramCanvas.drawPath(this.i, this.c);
        paramCanvas.restore();
        c.setColor(this.r);
    }


    //中心实时数据的绘制
    private void e(Canvas paramCanvas) {
        this.c.reset();
        this.c.setAntiAlias(true);
        this.c.setStrokeCap(Paint.Cap.ROUND);
        this.g.setTextSize(a(100.0F));
        float f1;
        if (this.j.b() >= 360.0F) {
            f1 = this.j.b() % 360.0F;
        } else if (this.j.b() > 0.0F) {
            f1 = this.j.b();
        } else {
            f1 = (this.j.b() + 720.0F) % 360.0F;
        }
        StringBuilder localObject = new StringBuilder();
        //  localObject.append(com.rowsen.myapplication.d.a(f1));
        // localObject.append(" ");
        if (flag)
            localObject.append("太阳方位" + f1 + "°");
        else localObject.append("---°");
        Rect localRect = new Rect();
        this.g.getTextBounds(localObject.toString(), 0, localObject.length(), localRect);
        float f3 = this.x.y;
        float f2 = localRect.height() / 2.0F;
        if (!flag) {
            g.setColor(r);
            paramCanvas.drawText(localObject.toString(), this.x.x - this.g.measureText(localObject.toString()) / 2.0F, f3 + f2, this.g);
            g.setColor(o);
        } else {
            TextPaint tp = new TextPaint();
            tp.setColor(r);
            tp.setStyle(Paint.Style.FILL);
            tp.setTextSize(90);
            StaticLayout myStaticLayout = new StaticLayout(localObject.toString(), tp, (int) (this.g.measureText(localObject.toString()) / 2.0F), Layout.Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
            paramCanvas.save();
            paramCanvas.translate(this.x.x - this.g.measureText(localObject.toString()) / 4, f3 - localRect.height());
            myStaticLayout.draw(paramCanvas);
            paramCanvas.restore();

            Path localPath = this.i;
     /*   if (!this.l) {
            f1 = -f1;
        }  */
            //可以在此处绘制一个外圈标记。预计加入磁北的标记
            this.c.setColor(this.r);
            float k1 = a(430.0F);
            // localPath.addArc((RectF) localObject, 270.0f - north - 1.5f, 3f);
            localPath.addArc(new RectF(this.x.x - k1, this.x.y - k1, this.x.x + k1, this.x.y + k1), f1 - 91.5f, 3f);
            paramCanvas.drawPath(localPath, this.c);
            this.c.setColor(this.s);
        }
    }

    //旋转绘制功能
    private void f(Canvas paramCanvas) {
        paramCanvas.save();
        if (this.l) {
            paramCanvas.rotate(this.j.c(), this.x.x, this.x.y);
        } else {
            paramCanvas.rotate(0.0F, this.x.x, this.x.y);
        }
        a(paramCanvas, this.x);
        b(paramCanvas, this.x);
        g(paramCanvas);
        h(paramCanvas);
        paramCanvas.restore();
    }

    //360度等数字
    private void g(Canvas paramCanvas) {
        a(paramCanvas, 300.0F, "30", 330.0F);
        a(paramCanvas, 330.0F, "60", 330.0F);
        a(paramCanvas, 360.0F, "90", 330.0F);
        a(paramCanvas, 30.0F, "120", 330.0F);
        a(paramCanvas, 60.0F, "150", 330.0F);
        a(paramCanvas, 90.0F, "180", 330.0F);
        a(paramCanvas, 120.0F, "210", 330.0F);
        a(paramCanvas, 150.0F, "240", 330.0F);
        a(paramCanvas, 180.0F, "270", 330.0F);
        a(paramCanvas, 210.0F, "300", 330.0F);
        a(paramCanvas, 240.0F, "330", 330.0F);
    }

    //八个方位的文字
    private void h(Canvas paramCanvas) {
        Paint.FontMetrics localFontMetrics = this.a.getFontMetrics();
        float f1 = localFontMetrics.bottom;
        float f2 = localFontMetrics.top;
        float f3 = localFontMetrics.leading;
        f1 = a(330.0F) - (f1 - f2 + f3) - a(this.y);
        this.b.setColor(this.q);
        this.b.setTextSize(a(60.0F));
        b(paramCanvas, 270.0F, "北", f1, this.b);
        this.b.setColor(this.o);
        b(paramCanvas, 0.0F, "东", f1, this.b);
        b(paramCanvas, 90.0F, "南", f1, this.b);
        b(paramCanvas, 180.0F, "西", f1, this.b);
        this.b.setTextSize(a(40.0F));
        this.b.setColor(this.p);
        //  b(paramCanvas, 315.0F, "东北", f1, this.b);
        //   b(paramCanvas, 45.0F, "东南", f1, this.b);
        //   b(paramCanvas, 135.0F, "西南", f1, this.b);
        //  b(paramCanvas, 225.0F, "西北", f1, this.b);
    }

    public b a() {
        return this.j;
    }


    //用于打印记录的函数
    public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("onSizeChanged() called with: w = [");
        localStringBuilder.append(paramInt1);
        localStringBuilder.append("], h = [");
        localStringBuilder.append(paramInt2);
        localStringBuilder.append("], oldw = [");
        localStringBuilder.append(paramInt3);
        localStringBuilder.append("], oldh = [");
        localStringBuilder.append(paramInt4);
        localStringBuilder.append("]");
        //   com.lyracss.supercompass.c.a.a("CanvasHelper", localStringBuilder.oString());
        Log.e("CanvasHelper", localStringBuilder.toString());
        this.B = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void a(Canvas paramCanvas) {
        this.w = (Math.min(paramCanvas.getWidth(), paramCanvas.getHeight()) / 934.0F);
        this.x = new Point(paramCanvas.getWidth() / 2, paramCanvas.getHeight() / 2);
        this.y = a(5.0F);
        b();
        b(paramCanvas);
        c(paramCanvas);
        f(paramCanvas);
        e(paramCanvas);
        d(paramCanvas);
        this.C = paramCanvas.getWidth();
        this.D = paramCanvas.getHeight();
    }

    public void a(boolean paramBoolean) {
        this.l = paramBoolean;
    }

    public void setNorth(float north) {
        this.north = north;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getRotationState() {
        return l;
    }
}
