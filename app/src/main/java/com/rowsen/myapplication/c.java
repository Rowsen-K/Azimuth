package com.rowsen.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class c
{
    private static final SimpleDateFormat a = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
    private long b;
    private long c;

    public c(long paramLong1, long paramLong2)
    {
        this.b = paramLong1;
        this.c = paramLong2;
    }

    public String a()
    {
        return a.format(new Date(this.c));
    }

    public String b()
    {
        return a.format(new Date(this.b));
    }

    public String toString()
    {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("SunTime{sunset=");
        localStringBuilder.append(b());
        localStringBuilder.append(", sunrise=");
        localStringBuilder.append(a());
        localStringBuilder.append('}');
        return localStringBuilder.toString();
    }
}
