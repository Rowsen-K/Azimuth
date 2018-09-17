package com.rowsen.myapplication;

public class d
{
    public static String a(float paramFloat)
    {
        if (((paramFloat >= 0.0F) && (paramFloat < 22.5F)) || (paramFloat > 337.5F)) {
            return "北";
        }
        if ((paramFloat >= 22.5F) && (paramFloat < 67.5F)) {
            return "东北";
        }
        if ((paramFloat >= 67.5F) && (paramFloat < 112.5F)) {
            return "东";
        }
        if ((paramFloat >= 112.5F) && (paramFloat < 157.5F)) {
            return "东南";
        }
        if ((paramFloat >= 157.5F) && (paramFloat < 202.5F)) {
            return "南";
        }
        if ((paramFloat >= 202.5F) && (paramFloat < 247.5F)) {
            return "西南";
        }
        if ((paramFloat >= 247.5F) && (paramFloat < 292.5F)) {
            return "西";
        }
        if ((paramFloat >= 292.5F) && (paramFloat < 337.5F)) {
            return "西北";
        }
        return "";
    }
}
