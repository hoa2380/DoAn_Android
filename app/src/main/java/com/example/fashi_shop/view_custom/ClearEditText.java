package com.example.fashi_shop.view_custom;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.fashi_shop.R;

public class ClearEditText extends androidx.appcompat.widget.AppCompatEditText {

    Drawable crossX, noneCrossX, drawable;
    Boolean visible = false;

    public ClearEditText(@NonNull Context context) {
        super(context);
        createView();
    }

    public ClearEditText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        createView();
    }

    public ClearEditText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        createView();
    }

    private void createView() {
        crossX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        noneCrossX = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();
        configure();
    }

    private void configure(){
        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = visible? crossX:noneCrossX;
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);

        if(lengthAfter == 0 && start == 0){
            visible = false;
            configure();
        }else{
            visible = true;
            configure();
        }
    }
}
