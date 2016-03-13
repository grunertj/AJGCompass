package com.wg.werner_jensgrunert.ajgcompass;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.NumberFormat;
import java.util.Locale;
import java.text.ParseException;


/**
 * Created by Werner-Jens Grunert on 3/9/2016.
 */
public class Preferences {
    static final int RETURN_AS_STRING = 10;
    static final double RETURN_AS_DOUBLE = 1.0f;
    static final double DEFAULT_DESTINATION_LATITUDE = 45.463890f;
    static final double DEFAULT_DESTINATION_LONGITUDE = 9.189277f;

    SharedPreferences sharedPreferences;

    double LatitudeDestination = DEFAULT_DESTINATION_LATITUDE; // Center Milan
    double LongitudeDestination = DEFAULT_DESTINATION_LONGITUDE;

    Context context;

    public Preferences(double latitudeDestination, double longitudeDestination, Context context) {
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        LatitudeDestination = latitudeDestination;
        LongitudeDestination = longitudeDestination;

        if (sharedPreferences.contains("Latitude") == false) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Latitude", String.format(Locale.getDefault(),"%.8f", latitudeDestination));
            editor.commit();
        }

        if (sharedPreferences.contains("Longitude") == false) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Longitude", String.format(Locale.getDefault(),"%.8f", longitudeDestination));
            editor.commit();
        }

        this.context = context;
    }

    public Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        double latitudeDestination = DEFAULT_DESTINATION_LATITUDE;
        double longitudeDestination = DEFAULT_DESTINATION_LONGITUDE;

        if (sharedPreferences.contains("Latitude") == false) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Latitude", String.format(Locale.getDefault(),"%.8f", latitudeDestination));
            editor.commit();
        }

        if (sharedPreferences.contains("Longitude") == false) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Longitude", String.format(Locale.getDefault(),"%.8f", longitudeDestination));
            editor.commit();
        }

        this.context = context;
    }

    public double getLatitudeDestination(double i) {
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        String latitude;
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number;

        if (sharedPreferences.contains("Latitude")) {
            latitude = (sharedPreferences.getString("Latitude", "Empty"));
            try {
                number = format.parse(latitude);
                LatitudeDestination = number.floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return LatitudeDestination;
    }

    public String getLatitudeDestination(int i) {
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);


        if (sharedPreferences.contains("Latitude")) {
            return (sharedPreferences.getString("Latitude", "Empty"));
        } else {
            return String.format(Locale.getDefault(),"%.6f",LatitudeDestination);
        }
    }

    public void setLatitudeDestination(double latitudeDestination) {
        LongitudeDestination = latitudeDestination;
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Latitude", String.format(Locale.getDefault(),"%.8f", latitudeDestination));
        editor.commit();
    }

    public double getLongitudeDestination(double i) {
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        String longitude;
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number;

        if (sharedPreferences.contains("Longitude")) {
            longitude = (sharedPreferences.getString("Longitude", "Empty"));
            try {
                number = format.parse(longitude);
                LongitudeDestination = number.floatValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return LongitudeDestination;
    }

    public String getLongitudeDestination(int i) {
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("Longitude")) {
            return (sharedPreferences.getString("Longitude", "Empty"));
        } else {
            return String.format(Locale.getDefault(),"%.6f",LongitudeDestination);
        }
    }

    public void setLongitudeDestination(double longitudeDestination) {
        LongitudeDestination = longitudeDestination;
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Longitude", String.format(Locale.getDefault(),"%.8f", longitudeDestination));
        editor.commit();
    }

    public void setDestination(String data) {
        String array[] = data.split(":|,|\\?");
        LatitudeDestination = Float.valueOf(array[1]);
        LongitudeDestination = Float.valueOf(array[2]);
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Latitude", String.format(Locale.getDefault(),"%.8f", LatitudeDestination));
        editor.putString("Longitude", String.format(Locale.getDefault(),"%.8f", LongitudeDestination));
        editor.commit();
    }

    public void setDestination(String latitude, String longitude) {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        Number number;
        try {
            number = format.parse(latitude);
            LatitudeDestination = number.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            number = format.parse(longitude);
            LongitudeDestination = number.floatValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Latitude", String.format(Locale.getDefault(),"%.8f", LatitudeDestination));
        editor.putString("Longitude", String.format(Locale.getDefault(),"%.8f", LongitudeDestination));
        editor.commit();
    }

    public void clear() {
        sharedPreferences = context.getSharedPreferences("Coordinates", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear().commit();
        editor.putString("Latitude", String.format(Locale.getDefault(),"%.8f", DEFAULT_DESTINATION_LATITUDE));
        editor.putString("Longitude", String.format(Locale.getDefault(),"%.8f", DEFAULT_DESTINATION_LONGITUDE));
        editor.commit();
    }
}
