package com.evertecinc.libraryproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class ATHMovilButtonLight extends AppCompatButton {

     CharSequence result;

    public ATHMovilButtonLight(Context context) {
        super(context);
        init();
    }

    public ATHMovilButtonLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ATHMovilButtonLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        final Spannable.Factory factory = Spannable.Factory.getInstance();

        final String payWith = "Pay with";
        final String ATH = "ATH";
        final String movil = "móvil";
        final String emptySpace = " ";

        Spannable ATHSpan;
        ATHSpan = factory.newSpannable(ATH);
        ATHSpan.setSpan(new StyleSpan(Typeface.BOLD), 0, ATHSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ATHSpan.setSpan(new RelativeSizeSpan(1.25f), 0, ATHSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        Spannable movilSpan = factory.newSpannable(movil);
        movilSpan.setSpan(new StyleSpan(Typeface.BOLD), 0, movilSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        movilSpan.setSpan(new RelativeSizeSpan(1.1f), 0, movilSpan.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        result = TextUtils.concat(payWith, emptySpace, ATHSpan, emptySpace, movilSpan);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        setText(result);
        setTextSize(16);
        setHeight(spToPx(getContext(),52));
        setWidth(FrameLayout.LayoutParams.MATCH_PARENT);
        setBackgroundColor(getResources().getColor(R.color.light_button));
        setTextColor(getResources().getColor(R.color.dark_button));
        setAllCaps(false);
    }

    public static int spToPx(final Context context, final float sp) {
        return Math.round(sp * context.getResources().getDisplayMetrics().scaledDensity);
    }
}
