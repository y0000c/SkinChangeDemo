package demo.yc.skinchangedemo.skin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.yc.skinchangedemo.skin.config.SkinManager;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:27
 * @detail:
 */

public enum SkinAttrType
{
    BACKGROUND("background"){
        @Override
        public void apply(View view, String mResName)
        {
            Drawable draw = SkinManager.getInstance().getDrawableBySkinPlugin(mResName);
            if(draw != null)
                view.setBackground(draw);
        }
    },
    SRC("src"){
        @Override
        public void apply(View view, String mResName)
        {
            Drawable draw = SkinManager.getInstance().getDrawableBySkinPlugin(mResName);
            if(draw != null)
                if(view instanceof ImageView)
                {
                    ((ImageView)view).setImageDrawable(draw);
                }
        }
    },
    TEXT_COLOR("textColor"){
        @Override
        public void apply(View view, String mResName)
        {
            ColorStateList list = SkinManager.getInstance().getColorBySkinPlugin(mResName);
            if(list != null)
                if(view instanceof TextView)
                {
                    ((TextView)view).setTextColor(list);
                }
        }
    };

    private String mResType;
    SkinAttrType(String type)
    {
        mResType = type;
    }

    public String getResType()
    {
        return mResType;
    }
    public abstract void apply(View view, String mResName);
}
