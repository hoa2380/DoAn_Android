package com.example.fashi_shop.view_custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.fashi_shop.R;

public class PasswordEditText extends androidx.appcompat.widget.AppCompatEditText{

    Drawable eye, eyeStrike, drawable;
    Boolean visible = false;
    Boolean useStrike = false;
    Boolean useValidate = false;
    int ALPHA = (int) (255 * .70f);

    public PasswordEditText(@NonNull Context context) {
        super(context);
        createView(null);
    }

    public PasswordEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createView(attrs);
    }

    public PasswordEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createView(attrs);
    }

    private void createView(AttributeSet attributeSet){
        if(attributeSet != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attributeSet,R.styleable.PasswordEditText,0,0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
        }

        eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

        setting();
    }

    private void setting(){
        setInputType(InputType.TYPE_CLASS_TEXT |(visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            setting();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
