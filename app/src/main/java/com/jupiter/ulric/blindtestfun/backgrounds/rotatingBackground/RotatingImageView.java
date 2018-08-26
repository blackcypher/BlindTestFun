package com.jupiter.ulric.blindtestfun.backgrounds.rotatingBackground;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jupiter.ulric.blindtestfun.R;

/**
 * Created by ulric on 29/07/2018.
 */

public class RotatingImageView extends android.support.v7.widget.AppCompatImageView   {

    // Initial position.
    // Initial position.
    private int rotationDegrees = 0;

    public RotatingImageView(Context context) {
        super(context);
        init();
    }

    public RotatingImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RotatingImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.disc);
        setImageBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Translate rotation axe to the center.
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        // Rotate!
        canvas.rotate(rotation(3));
        // Put back to its original place.
        canvas.translate(-canvas.getWidth()/2, -canvas.getHeight()/2);
        // Invalidate the view.
        postInvalidateOnAnimation(this);
        super.onDraw(canvas);
    }

    private int rotation(int delta) {
        rotationDegrees = (rotationDegrees + delta) % 360;
        return rotationDegrees;
    }

    @SuppressLint("NewApi")
    public static void postInvalidateOnAnimation(View view) {
        if (Build.VERSION.SDK_INT >= 16)
            view.postInvalidateOnAnimation();
        else
            view.postInvalidateDelayed(10);
    }
}
