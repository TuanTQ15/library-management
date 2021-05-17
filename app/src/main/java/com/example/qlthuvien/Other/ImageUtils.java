package com.example.qlthuvien.Other;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class ImageUtils {
    @NonNull
    private static String fileName() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    @NonNull
    public static Uri saveImagePrivate(@NotNull Activity activity, @NonNull Uri selectedImageUri) {
        FileOutputStream fos = null;
        InputStream iStream = null;
        String name = fileName();
        try {
            iStream = activity.getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = getBytes(iStream);
            fos = activity.openFileOutput(name, Context.MODE_PRIVATE);
            fos.write(inputData);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
                iStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(activity.getFilesDir(), name);
        return Uri.fromFile(file);
    }

    @NotNull
    public static byte[] getBytes(@NonNull InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    public static void deleteImage(@NonNull Uri uri){
        File myFile = new File(uri.toString());
        if(myFile.exists()){
            myFile.delete();
        }
    }
}
