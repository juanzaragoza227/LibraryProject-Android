package com.evertecinc.libraryproject;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class PayButton extends AppCompatImageButton {

    ButtonStyle selectedStyle = ButtonStyle.ORIGINAL;

    private enum ButtonStyle{
        ORIGINAL, LIGHT, DARK
    }

    public PayButton(Context context) {
        super(context);
    }

    public PayButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PayButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(final Context context, final AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PayButton);
        selectedStyle = ButtonStyle.values()[typedArray.getInt(R.styleable.PayButton_style, 0)];
        typedArray.recycle();
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        switch (selectedStyle) {
            case ORIGINAL:
                setOriginalButton();
                break;
            case LIGHT:
                setLightButton();
                break;
            case DARK:
                setDarkButton();
                break;
            default:
                setOriginalButton();
                break;
        }

        ViewCompat.setElevation(this, spToPx(getContext(), 2));
        setScaleType(ScaleType.CENTER_INSIDE);
        getLayoutParams().height = spToPx(getContext(), 60);
        getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
        requestLayout();
    }

    private int spToPx(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }

    private void setOriginalButton() {
        setImageDrawable(getResources().getDrawable(R.drawable.athm_white));
        ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(getResources().getColor(R.color.orange_button)));
    }

    private void setLightButton() {
        setImageDrawable(getResources().getDrawable(R.drawable.athm_black));
        ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(getResources().getColor(R.color.light_button)));
    }

    private void setDarkButton() {
        setImageDrawable(getResources().getDrawable(R.drawable.athm_white));
        ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(getResources().getColor(R.color.dark_button)));
    }
}