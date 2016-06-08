package app.texium.com.profiles.services;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import app.texium.com.profiles.R;

/**
 * Created by texiumuser on 08/06/2016.
 */
public class FileServices {

    public static String attachImg(Activity activity, Bitmap img) throws Exception {
        String imageEncoded;
        Context context = activity.getApplicationContext();

        try {
            ByteArrayOutputStream convert = new ByteArrayOutputStream();
            img.compress(Bitmap.CompressFormat.JPEG, 50, convert);
            byte[] b = convert.toByteArray();
            imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AttachImg Exception",e.getMessage());
            throw  new Exception(context.getString(R.string.default_attaching_img_error));
        }

        return imageEncoded;
    }


}
