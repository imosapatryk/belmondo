package com.example.paciu.belmondo.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by paciu on 20.04.2016.
 */
public class BitmapUtils {
    public static byte[] toByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap fromByteArray(byte[] array){
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }
}
