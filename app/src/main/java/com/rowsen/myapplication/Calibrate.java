package com.rowsen.myapplication;

import java.util.Calendar;
import java.util.TimeZone;

public class Calibrate {
    Double B24;
    Double C25;
    Double C40;
    Double B25;
    Double B30;
    Double B26;
    Double C36;
    Double D26;
    Double B32;
    Double C26;
    Double C46;
    Double C47;
    Double C31;
    Double B31;
    Double B39;
    Double E45;
    Double E46;
    Double E47;
    Double C39;
    Double B36;
    Double C41;
    Double D46;
    Double D48;
    Double C48;
    Double E48;
    Double D47;
    Double C45;
    Double D45;
    Double E49;
    Double C49;
    Double Azimuth;

    // Double longitute;//Double B;Double B24;Double B24;Double B24;Double B24;
    Double cal(Double longitute, Double latitute) {
        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT"));
        Double Y = Double.valueOf(c.get(Calendar.YEAR));
        Double M = Double.valueOf(c.get(Calendar.MONTH) + 1);
        Double D = Double.valueOf(c.get(Calendar.DAY_OF_MONTH));
        Double H = Double.valueOf(c.get(Calendar.HOUR_OF_DAY));
        Double m = Double.valueOf(c.get(Calendar.MINUTE));
        Double s = Double.valueOf(c.get(Calendar.SECOND));
        B24 = 367 * Y - (int) (7 * (Y + (int) ((M + 9) / 12)) / 4) + (int) (275 * M / 9) + D + (H + m / 60 + s / 3600) / 24 - 730531.5;
        Double Mod1 = 280.46061837 + 360.98564736629 * B24 + longitute;
        B25 = Mod1 - 360 * (int) (Mod1 / 360);
        //MOD(280.46061837 + 360.98564736629 * B24 + B11, 360)=n-d*int(n/d)
        C25 = Math.toRadians(B25);
        B26 = B24 / 36525;
        C26 = B26 * B26;
        D26 = B26 * C26;
        //MOD(280.466+36000.8*$B$26, 360)
        Double Mod2 = 280.466 + 36000.8 * B26;
        B30 = Mod2 - 360 * (int) (Mod2 / 360);
        //MOD(357.529+35999*$B$26 - 0.0001536*$C$26 + $D$26/24490000, 360)
        Double Mod3 = 357.529 + 35999 * B26 - 0.0001536 * C26 + D26 / 24490000;
        B31 = Mod3 - 360 * (int) (Mod3 / 360);
        C31 = Math.toRadians(B31);
        //(1.915 - 0.004817*$B$26 - 0.000014*$C$26)*SIN(C31) + (0.01999 - 0.000101 * $B$26)*SIN(2*C31) + 0.00029 * SIN(3*C31)
        B32 = (1.915 - 0.004817 * B26 - 0.000014 * C26) * Math.sin(C31) + (0.01999 - 0.000101 * B26) * Math.sin(2 * C31) + 0.00029 * Math.sin(3 * C31);
        //=(84381.448 - 46.815*B26)/3600
        B39 = (84381.448 - 46.815 * B26) / 3600;
        C39 = Math.toRadians(B39);
        B36 = B30 + B32;
        C36 = Math.toRadians(B36);
        //=ATAN2(COS(C36),SIN(C36)*COS(C39)-TAN(0)*SIN(C39))
        C40 = Math.atan2((Math.sin(C36) * Math.cos(C39) - Math.tan(0) * Math.sin(C39)), Math.cos(C36));
        //=MOD(C25-C40, 2*PI())
        C46 = C25 - C40 - 2 * Math.PI * (int) ((C25 - C40) / (2 * Math.PI));
        //ASIN(SIN(0)*COS(C39) + COS(0)*SIN(C39)*SIN(C36))
        C47 = C41 = Math.asin(Math.sin(0) * Math.cos(C39) + Math.cos(0) * Math.sin(C39) * Math.sin(C36));
        D47 = Math.sin(C47);
        C45 = Math.toRadians(latitute);
        D45 = Math.sin(C45);
        E45 = Math.cos(C45);
        E46 = Math.cos(C46);
        E47 = Math.cos(C47);
        D48 = D47 * D45 + E47 * E45 * E46;
        C48 = Math.asin(D48);
        E48 = Math.cos(C48);
        E49 = ((D47 - D45 * D48) / (E45 * E48));
        C49 = Math.acos(E49);
        D46 = Math.sin(C46);
        if (D46 < 0) {
            Azimuth = Math.toDegrees(C49);
        } else {
            Azimuth = Math.toDegrees(2 * Math.PI - C49);
        }
        //System.out.println("lalalalalala--------" + Azimuth + "---------" + C48 + "----" + C46 + "-----" + C41);
        return Azimuth;
    }
}
