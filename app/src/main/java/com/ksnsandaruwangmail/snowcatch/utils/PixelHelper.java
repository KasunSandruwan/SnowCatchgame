package com.ksnsandaruwangmail.snowcatch.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by welcome on 1/4/2017.
 */

public class PixelHelper {

    public static int pixelsToDp(int px, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, px,
                context.getResources().getDisplayMetrics());
    }


}
