package com.rowsen.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.RequiresApi;

public class CompassView
        extends View {
    private a a;
    private boolean b;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public CompassView(Context paramContext) {
        super(paramContext);
        a(paramContext);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CompassView(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        a(paramContext);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public CompassView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        a(paramContext);
    }


    //标记屏幕的高宽比以及初始化a对象获得Roboto-Light.ttf字体.在asset文件夹中
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void a(Context paramContext) {
        DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
        boolean bool;
        if (localDisplayMetrics.heightPixels / localDisplayMetrics.widthPixels > 1.4F) {
            bool = true;
        } else {
            bool = false;
        }
        this.b = bool;
        this.a = new a(paramContext);
    }

    public b getSensorValue() {
        return this.a.a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onDraw(Canvas paramCanvas) {
        super.onDraw(paramCanvas);
        this.a.a(paramCanvas);
    }

    protected void onMeasure(int paramInt1, int paramInt2) {
        super.onMeasure(paramInt1, paramInt2);
        int m = MeasureSpec.getMode(paramInt1);
        int k = MeasureSpec.getMode(paramInt2);
        paramInt1 = MeasureSpec.getSize(paramInt1);
        paramInt2 = MeasureSpec.getSize(paramInt2);
        float f1;
        if (this.b) {
            f1 = 1.0F;
        } else {
            f1 = 0.8F;
        }
        float f2 = paramInt1;
        int j = (int) (f1 * f2);
        int i = (int) (f2 * 0.86F);
        if (m != 1073741824) {
            if (m == -2147483648) {
                paramInt1 = Math.min(j, paramInt1);
            } else {
                paramInt1 = j;
            }
        }
        if (k != 1073741824) {
            if (k == -2147483648) {
                paramInt2 = Math.min(i, paramInt2);
            } else {
                paramInt2 = i;
            }
        }
        setMeasuredDimension(paramInt1, paramInt2);
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        this.a.a(paramInt1, paramInt2, paramInt3, paramInt4);
    }

    public void setCompassRotate(boolean paramBoolean) {
        this.a.a(paramBoolean);
    }
     public void setNorth(float north){
        a.setNorth(north);
     }
}
