package com.yun.software.yunlearn.Widget;

import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yun.software.corelib.Tools.MyLogUtils;
import com.yun.software.yunlearn.R;

import org.w3c.dom.Text;

import static com.yun.software.yunlearn.Tools.ScreenUtils.*;

/**
 * Created by yanliang
 * on 2018/5/3 16:46
 */

public class CoustomTestView extends View {
    private Typeface mTagTypeface = Typeface.DEFAULT;
    /** The max line count of TagContainerLayout */
    private int mMaxLines = 0;

    /** The max length for TagView(default max length 23)*/
    private int mTagMaxLength = 23;

    /** TagView Border width(default 0.5dp)*/
    private float mTagBorderWidth = 0.5f;

    /** TagView Border radius(default 15.0dp)*/
    private float mTagBorderRadius = 15.0f;

    /** TagView Text size(default 14sp)*/
    private float mTagTextSize = 14;

    /** Text direction(support:TEXT_DIRECTION_RTL & TEXT_DIRECTION_LTR, default TEXT_DIRECTION_LTR)*/
    private int mTagTextDirection = View.TEXT_DIRECTION_LTR;

    /** Horizontal padding for TagView, include left & right padding(left & right padding are equal, default 10dp)*/
    private int mTagHorizontalPadding = 10;

    /** Vertical padding for TagView, include top & bottom padding(top & bottom padding are equal, default 8dp)*/
    private int mTagVerticalPadding = 10;

    /** TagView border color(default #88F44336)*/
    private int mTagBorderColor = Color.parseColor("#88F44336");

    /** TagView background color(default #33F44336)*/
    private int mTagBackgroundColor = Color.parseColor("#33F44336");

    /** TagView text color(default #FF666666)*/
    private int mTagTextColor = Color.parseColor("#FF666666");

    /** The distance between baseline and descent(default 2.75dp)*/
    private float mTagBdDistance =2.5f;

    private String mTagText="我的字体";
    //最终字体
    private String mAbstractText;
    private float fontH, fontW;
    private Path mPath;
    private Paint mPaint, mRipplePaint;

    private int mTextDirection = View.TEXT_DIRECTION_LTR;
    private RectF mRectF;

    private float bdDistance;
    private boolean isViewClickable=true;
    private float mTouchX, mTouchY;

    private int mLastX, mLastY;


    /** Move slop(default 5dp)*/
    private int mMoveSlop = 5;

    /** Scroll slop threshold 4dp*/
    private int mSlopThreshold = 4;

    /** How long trigger long click callback(default 500ms)*/
    private int mLongPressTime = 500;
    /**
     * 持续时间
     */
    private int mRippleDuration = 200;
    /**
     *半径
     */
    private float mRippleRadius;
    /**
     *点击效果
     */
    private int mRippleColor;
    /**
     *点击效果透明度
     */
    private int mRippleAlpha=128;

    private boolean isUp, isMoved, isExecLongClick;
    public CoustomTestView(Context context) {
        this(context,null);
    }

    public CoustomTestView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CoustomTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray attributes=context.obtainStyledAttributes(attrs, R.styleable.CoustomTestView,defStyleAttr,0);
        mTagBorderWidth = attributes.getDimension(R.styleable.CoustomTestView_border_width,
                dp2px(context, mTagBorderWidth));
        mTagBorderRadius = attributes.getDimension(
                R.styleable.CoustomTestView_corner_radius,dp2px(context, mTagBorderRadius));
        mTagHorizontalPadding = (int) attributes.getDimension(
                R.styleable.CoustomTestView_horizontal_padding,
                dp2px(context, mTagHorizontalPadding));
        mTagVerticalPadding = (int) attributes.getDimension(
                R.styleable.CoustomTestView_vertical_padding, dp2px(context, mTagVerticalPadding));
        mTagTextSize = attributes.getDimension(R.styleable.CoustomTestView_text_size,
                sp2px(context, mTagTextSize));
        mTagText = attributes.getString(R.styleable.CoustomTestView_text);
        mTagBorderColor = attributes.getColor(R.styleable.CoustomTestView_border_color,
                mTagBorderColor);
        // 偏于基准线多少位置 大于0 向上便宜 小于零 向下变异
        mTagBdDistance = attributes.getDimension(R.styleable.CoustomTestView_bd_distance,
                dp2px(context, mTagBdDistance));
        mTagBackgroundColor = attributes.getColor(R.styleable.CoustomTestView_background_color,
                mTagBackgroundColor);
        mTagTextColor = attributes.getColor(R.styleable.CoustomTestView_text_color, mTagTextColor);
        mTagTextDirection = attributes.getInt(R.styleable.CoustomTestView_text_direction, mTagTextDirection);



        //点击效果颜色
        mRippleColor = attributes.getColor(R.styleable.CoustomTestView_ripple_color, Color.parseColor("#ff00ff"));
        //点击透明度
        mRippleAlpha = attributes.getInteger(R.styleable.CoustomTestView_ripple_alpha, mRippleAlpha);
        //动画持续时间
        mRippleDuration = attributes.getInteger(R.styleable.CoustomTestView_ripple_duration, mRippleDuration);
        attributes.recycle();
        init();

    }
    public void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRipplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRipplePaint.setStyle(Paint.Style.FILL);
        mRectF = new RectF();
        mPath = new Path();
        onDealText();

    }

    private void onDealText(){
        if(!TextUtils.isEmpty(mTagText)) {
            mAbstractText = mTagText.length() <= mTagMaxLength ? mTagText
                    : mTagText.substring(0, mTagMaxLength - 3) + "...";
        }else {
            mAbstractText = "";
        }
        mPaint.setTypeface(Typeface.DEFAULT);
        mPaint.setTextSize(mTagTextSize);
        final Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        fontH = fontMetrics.bottom - fontMetrics.top;
        if (mTextDirection == View.TEXT_DIRECTION_RTL){
            fontW = 0;
            for (char c : mAbstractText.toCharArray()) {
                String sc = String.valueOf(c);
                fontW += mPaint.measureText(sc);
            }
        }else {
            fontW = mPaint.measureText(mAbstractText);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = mTagVerticalPadding * 2 + (int) fontH;
        int width = mTagHorizontalPadding * 2 + (int) fontW ;
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.set(mTagBorderWidth, mTagBorderWidth, w - mTagBorderWidth, h - mTagBorderWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画实心椭圆
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTagBackgroundColor);
        canvas.drawRoundRect(mRectF, mTagBorderRadius, mTagBorderRadius, mPaint);

        //画边框
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mTagBorderWidth);
        mPaint.setColor(mTagBorderColor);
        canvas.drawRoundRect(mRectF, mTagBorderRadius, mTagBorderRadius, mPaint);
        //写文字
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mTagTextColor);

        if (mTextDirection == View.TEXT_DIRECTION_RTL){
            float tmpX =getWidth() / 2 + fontW / 2;
            for (char c : mAbstractText.toCharArray()) {
                String sc = String.valueOf(c);
                tmpX -= mPaint.measureText(sc);
                canvas.drawText(sc, tmpX, getHeight() / 2 + fontH / 2-mTagBdDistance, mPaint);
            }
        }else {
            canvas.drawText(mAbstractText,
                    getWidth() / 2 - fontW / 2,
                    getHeight() / 2 + fontH / 2-mTagBdDistance, mPaint);
        }
        drawRipple(canvas);

    }
    // 画点击效果
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void drawRipple(Canvas canvas){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && canvas != null){
            canvas.save();
            mPath.reset();

            canvas.clipPath(mPath);
            mPath.addRoundRect(mRectF, mTagBorderRadius, mTagBorderRadius, Path.Direction.CCW);
//            Region.Op.DIFFERENCE ：是A形状中不同于B的部分显示出来
//
//            Region.Op.REPLACE：是只显示B的形状
//
//            Region.Op.REVERSE_DIFFERENCE ：是B形状中不同于A的部分显示出来，这是没有设置时候默认的
//
//            Region.Op.INTERSECT：是A和B交集的形状
//
//            Region.Op.UNION：是A和B的全集
//
//            Region.Op.XOR：是全集形状减去交集形状之后的部分
            canvas.clipPath(mPath, Region.Op.REPLACE);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.crop);
            int bitmapHeight = bitmap.getHeight()/2;
            int bitmapWidth = bitmap.getWidth()/2;
            mRipplePaint.setFilterBitmap(true);
            mRipplePaint.setDither(true);
            int left= (int) (mTouchX-mRippleRadius/2);
            int right= (int) (mTouchX+mRippleRadius/2);
            int top= (int) (mTouchY-mRippleRadius/2);
            int bottom= (int) (mTouchY+mRippleRadius/2);

            Rect mDestRect = new Rect(left,top,right,bottom);
            canvas.drawBitmap(bitmap,null,mDestRect,mRipplePaint);


//            canvas.drawCircle(mTouchX, mTouchY, mRippleRadius, mRipplePaint);
//            ImageView imageview;




            bitmap.recycle();
            canvas.restore();
        }
    }

    //长安监听
    private Runnable mLongClickHandle = new Runnable() {
        @Override
        public void run() {
            if (!isMoved && !isUp){
//                int state = ((TagContainerLayout)getParent()).getTagViewState();
//                if (state == ViewDragHelper.STATE_IDLE){
                    isExecLongClick = true;
                    mOnClickListener.onLongClick(CoustomTestView.this);
//                }
            }
        }
    };
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (isViewClickable){
            int y = (int) event.getY();
            int x = (int) event.getX();
            int action = event.getAction();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    if (getParent() != null) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    mLastY = y;
                    mLastX = x;
                    break;

                case MotionEvent.ACTION_MOVE:

                    //如果滑动距离大于mslopThreshold 拦截点击行为 并设置为滑动
                    if (Math.abs(mLastY - y) > mSlopThreshold
                            || Math.abs(mLastX - x) > mSlopThreshold){
                        if (getParent() != null) {
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }
                        isMoved = true;
                        return false;
                    }
                    break;
            }
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            mRippleRadius = 0.0f;
            mTouchX = event.getX();
            mTouchY = event.getY();
            splashRipple();
        }
        if (isViewClickable && mOnClickListener != null){
            int x = (int) event.getX();
            int y = (int) event.getY();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                    mLastY = y;
                    mLastX = x;
                    isMoved = false;
                    isUp = false;
                    isExecLongClick = false;
                    postDelayed(mLongClickHandle, mLongPressTime);
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (isMoved){
                        break;
                    }
                    if (Math.abs(mLastX - x) > mMoveSlop || Math.abs(mLastY - y) > mMoveSlop){
                        isMoved = true;
                    }
                    break;

                case MotionEvent.ACTION_UP:

                    if (!isExecLongClick && !isMoved) {
                        mOnClickListener.onClick(this);

                    }
                    isUp = true;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    private ValueAnimator mRippleValueAnimator;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void splashRipple(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB && mTouchX > 0 && mTouchY > 0){
            mRipplePaint.setColor(mRippleColor);
            mRipplePaint.setAlpha(mRippleAlpha);
            final float maxDis = Math.max(Math.max(Math.max(mTouchX, mTouchY),
                    Math.abs(getMeasuredWidth() - mTouchX)), Math.abs(getMeasuredHeight() - mTouchY));

            mRippleValueAnimator = ValueAnimator.ofFloat(0.0f, maxDis).setDuration(mRippleDuration);
            mRippleValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animValue = (float) animation.getAnimatedValue();

                        mRippleRadius = animValue >=maxDis ? 0 : animValue;
                        MyLogUtils.i("coustomTestView","半径变化范围----->"+mRippleRadius);
                        postInvalidate();


                }
            });
            mRippleValueAnimator.start();
        }
    }

    public interface OnClickListener{
        void onClick(CoustomTestView view);
        void onLongClick(CoustomTestView view);
    }
    public void setOnClickListener(OnClickListener listener){
        this.mOnClickListener = listener;
    }
    private OnClickListener mOnClickListener;
}
