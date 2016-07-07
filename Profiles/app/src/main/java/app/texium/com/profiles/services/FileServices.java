package app.texium.com.profiles.services;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AttachImg Exception",e.getMessage());
            throw  new Exception(context.getString(R.string.default_attaching_img_error));
        }

        return imageEncoded;
    }

    public static Bitmap getBitmapFromURL(String src) {
        Bitmap myBitmap = null;
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(input);
        } catch (java.net.MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return myBitmap;
    }

    public static String attachImgFromBitmap(Bitmap bitmap) throws Exception {
        String imageEncoded = null;
        try {
            ByteArrayOutputStream convert = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, convert);
            byte[] b = convert.toByteArray();
            imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AttachImg Exception",e.getMessage());
        }

        return imageEncoded;
    }

    public static Bitmap compressBitmap(Bitmap original) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        original.compress(Bitmap.CompressFormat.PNG, 50, out);
        return BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));
    }

    public static Bitmap rotateBmp(Bitmap bmp, float angle){
        Matrix matrix = new Matrix();
        //set image rotation value to 90 degrees in matrix.
        matrix.postRotate(angle);
        //supply the original width and height, if you don't want to change the height and width of bitmap.
        bmp = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),bmp.getHeight(), matrix, true);
        return bmp;
    }

    public static Bitmap setRadius(Bitmap actualBitmap) {

        Bitmap imageRounded = Bitmap.createBitmap(actualBitmap.getWidth(), actualBitmap.getHeight(), actualBitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint bmPaint = new Paint();
        bmPaint.setAntiAlias(true);
        bmPaint.setShader(new BitmapShader(actualBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, actualBitmap.getWidth(), actualBitmap.getHeight())), 140, 140, bmPaint);// Round Image Corner 100 100 100 100

        return imageRounded;
    }




}
