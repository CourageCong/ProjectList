package com.fc.projectlist.basic.define_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zuoyebang on 2019/8/8.
 */
public class Zxing_define_view extends View {

    private Bitmap mTempBitmap;
    private Canvas mTempCanvas;
    private Paint mPaint;
    private float mDensity;
    private Context mContext;

    private float mRadius = 30;
    private int mBackgroundColor = Color.parseColor("#33ff0000");
    private float mRx=0;//默认在中心位置
    private float mRy=0;

    public Zxing_define_view(Context context) {
        this(context,null);
    }

    public Zxing_define_view(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Zxing_define_view(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
       /* setWillNotDraw(false);
        mDensity = mContext.getResources().getDisplayMetrics().density;

        Point size = new Point();
        size.x = mContext.getResources().getDisplayMetrics().widthPixels;
        size.y = mContext.getResources().getDisplayMetrics().heightPixels;

        mRx = mRx*mDensity;
        mRy = mRy*mDensity;

        mRx = mRx !=0 ? mRx: size.x/2;
        mRy = mRy !=0 ? mRy: size.y/2;

        mRadius = mRadius !=0 ?mRadius:150;

        mRadius = mRadius*mDensity;

        mBackgroundColor  = mBackgroundColor !=-1?mBackgroundColor: Color.parseColor("#55000000");
*/


        mPaint = new Paint();
        mPaint.setColor(0xFFFFFFFF);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);


    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTempBitmap = Bitmap.createBitmap(getMeasuredWidth() ,getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        mTempCanvas = new Canvas(mTempBitmap);

        mTempBitmap.eraseColor(Color.TRANSPARENT);
        mTempCanvas.drawColor(mBackgroundColor);

        RectF rectF = new RectF(50, 100, 250, 300);

        mTempCanvas.drawRect(rectF, mPaint);

        canvas.drawBitmap(mTempBitmap, 0, 0, null);

    }
}
