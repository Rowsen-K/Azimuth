package com.rowsen.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class SizeNotiRelativeLayout
        extends RelativeLayout
{
    private a a;
    private boolean b = false;

    public SizeNotiRelativeLayout(Context paramContext)
    {
        this(paramContext, null);
    }

    public SizeNotiRelativeLayout(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet, 0);
    }

    public a getCallback()
    {
        return this.a;
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
        if (!this.b)
        {
            int i = MeasureSpec.getSize(paramInt1);
            int j = MeasureSpec.getSize(paramInt2);
            this.a.OnSizeChanged(i, j);
        }
        super.onMeasure(paramInt1, paramInt2);
    }

    protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
        super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
        this.a.OnSizeChanged(paramInt1, paramInt2);
        if (!this.b) {
            this.b = true;
        }
    }

    public void setCallback(a parama)
    {
        this.a = parama;
    }

    public static abstract interface a
    {
        public abstract void OnSizeChanged(int paramInt1, int paramInt2);
    }
}

