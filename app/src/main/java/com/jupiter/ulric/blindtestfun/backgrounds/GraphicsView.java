package com.jupiter.ulric.blindtestfun.backgrounds;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by ulric on 16/09/2018.
 */


public class GraphicsView extends View {
    private static final String AnimatedTxt = "Fun Blind Test";
    public Context context;

    private Animation rotateAnim;

    public GraphicsView(Context context) {
        super(context);
        this.context = context;
    }

    private void createAnim(Canvas canvas) {
        rotateAnim = new RotateAnimation(-3, 5, canvas.getWidth() / 2, canvas.getHeight() / 2);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        rotateAnim.setRepeatCount(Animation.INFINITE);
        rotateAnim.setDuration(500L);
        rotateAnim.setInterpolator(new AccelerateDecelerateInterpolator());

        startAnimation(rotateAnim);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // creates the animation the first time
        if (rotateAnim == null) {
            createAnim(canvas);
        }

        Path circle = new Path();

        int centerX = 0;
        int centerY = canvas.getHeight() / 2;
        int r = Math.min(centerX, centerY);

        circle.addCircle(centerX, centerY, r, Path.Direction.CW);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Typeface chops = Typeface.createFromAsset(context.getAssets(), "titleStyle.ttf");
        Typeface bold = Typeface.create(chops, Typeface.BOLD);
        paint.setTypeface(bold);
        paint.setTextSize(45);
        paint.setAntiAlias(true);

        canvas.drawText(AnimatedTxt, centerX, centerY, paint);
        //canvas.drawTextOnPath(AnimatedTxt, circle, 0, 25, paint);
    }
}