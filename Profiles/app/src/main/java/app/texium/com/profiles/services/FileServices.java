package app.texium.com.profiles.services;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.util.Base64OutputStream;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import app.texium.com.profiles.R;

/**
 * Created by texiumuser on 08/06/2016.
 */
public class FileServices {

    public static String attachImg(Activity activity, Uri uri) throws Exception {
        String imageEncoded;
        Context context = activity.getApplicationContext();

        try {

            InputStream is = activity.getContentResolver()
                    .openInputStream(uri);
            Bitmap img = BitmapFactory.decodeStream(is);
            ByteArrayOutputStream convert = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.JPEG, 20, convert);
            byte[] b = convert.toByteArray();
            imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

            //FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
            /*FileInputStream inputStream = new FileInputStream(file.getAbsolutePath());
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            Base64OutputStream output64 = new Base64OutputStream(output, Base64.DEFAULT);
            try {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output64.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            output64.close();

            imageEncoded = output.toString();*/

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AttachImg Exception",e.getMessage());
            throw  new Exception(context.getString(R.string.default_attaching_img_error));
        }

        return imageEncoded;
    }


}
