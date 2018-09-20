package com.rowsen.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;


public class CompassRotationViews
        extends AppCompatImageView
{
    private float a = 0.0F;
    private Drawable b = null;

    public CompassRotationViews(Context paramContext)
    {
        super(paramContext);
    }

    public CompassRotationViews(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    public CompassRotationViews(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
    }

 /*
    public void a()
    {
        this.b = getDrawable();
    }

    public void a(float paramFloat)
    {
        this.a = paramFloat;
        invalidate();
    }

    protected void onDraw(Canvas paramCanvas)
    {
        if (this.b == null)
        {
            this.b = getDrawable();
            this.b.setBounds(0, 0, getWidth(), getHeight());
        }
        this.b.setBounds(0, 0, getWidth(), getHeight());
        paramCanvas.save();
        paramCanvas.rotate(this.a, getWidth() / 2, getHeight() / 2);
        this.b.draw(paramCanvas);
        this.b = null;
        paramCanvas.restore();
    }
    */
}

