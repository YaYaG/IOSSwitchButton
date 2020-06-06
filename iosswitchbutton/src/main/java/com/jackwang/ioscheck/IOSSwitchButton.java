package com.jackwang.ioscheck;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.annotation.Nullable;

/**
 * 姓名: YayaG
 * 时间： 2020-06-05
 * 描述：
 */
public class IOSSwitchButton extends View implements View.OnClickListener {
    private int mCheckColor = Color.GREEN;
    private int mUnCheckColor = Color.parseColor("#CCCCCC");
    private Paint mOutPaint;
    private Paint mSwitchPaint;
    private RectF mDrawRoundRectF = new RectF();
    private int mSwitchColor = Color.WHITE;
    private boolean mCheck = false;
    private int mRadius;
    private int mRx;
    private SwitchListener mSwitchListener;
    private boolean mDisable;
    private int startX;

    public IOSSwitchButton(Context context) {
        this(context, null);
    }

    public IOSSwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public IOSSwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.IOSSwitchButton);

            mCheckColor = typedArray.getColor(R.styleable.IOSSwitchButton_check_color, Color.GREEN);
            mUnCheckColor = typedArray.getColor(R.styleable.IOSSwitchButton_uncheck_color, Color.parseColor("#CCCCCC"));
            mSwitchColor = typedArray.getColor(R.styleable.IOSSwitchButton_switchColor, Color.WHITE);
            mCheck = typedArray.getBoolean(R.styleable.IOSSwitchButton_check, false);
            mDisable = typedArray.getBoolean(R.styleable.IOSSwitchButton_disable, false);

            typedArray.recycle();
        }
        mOutPaint = new Paint();
        mSwitchPaint = new Paint();

        mOutPaint.setColor(mUnCheckColor);
        mOutPaint.setAntiAlias(true);
        mSwitchPaint.setColor(mSwitchColor);
        mSwitchPaint.setAntiAlias(true);

        setOnClickListener(this);
        setClickable(!mDisable);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mDrawRoundRectF.set(0, 0, getWidth(), getHeight());
        mRadius = getHeight() / 2 - dipToPx(2);

        if (mDisable) {
            mOutPaint.setAlpha(10);
        }

        mOutPaint.setColor(mCheck ? mCheckColor : mUnCheckColor);
        canvas.drawRoundRect(mDrawRoundRectF, getHeight() / 2,
                getHeight() / 2, mOutPaint);

        if (mRx == 0) {
            if (!mCheck) {
                mRx = mRadius + dipToPx(2);
            } else {
                mRx = getWidth() - getHeight() / 2;
            }
        }
        canvas.drawCircle(mRx, getHeight() / 2, mRadius, mSwitchPaint);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {//warp_content 给个默认的
            width = dipToPx(80);
            height = width / 2;
        } else if (heightMode == MeasureSpec.AT_MOST && widthMode == MeasureSpec.EXACTLY) {
            height = width / 2;
        }

        height = Math.min(width / 2, height);//高度不能大于 宽度的一半

        setMeasuredDimension(width, height);
    }


    private int dipToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getContext().getResources().getDisplayMetrics());
    }

    private void drawAnim() {
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(mCheck ? getHeight()/2 : getWidth() - getHeight()/2
                , mCheck ? getWidth() - getHeight()/2 : getHeight()/2);
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setDuration(300);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRx = (int) animation.getAnimatedValue();
                invalidate();
            }
        });

        valueAnimator.start();
        if (mSwitchListener != null) {
            mSwitchListener.changeCheck(mCheck);
        }
    }


    public void setSwitchListener(SwitchListener switchListener) {
        mSwitchListener = switchListener;
    }

    @Override
    public void onClick(View v) {
        if (!mDisable) {
            mCheck = !mCheck;
            drawAnim();
        }
    }

    public void setSwitchColor(int checkColor, int unCheckColor) {
        mCheckColor = checkColor;
        mUnCheckColor = unCheckColor;
        invalidate();
    }

    public void setDisable(boolean disable) {
        mDisable = disable;
        setOnClickListener(this);
        setClickable(!mDisable);
    }
}
