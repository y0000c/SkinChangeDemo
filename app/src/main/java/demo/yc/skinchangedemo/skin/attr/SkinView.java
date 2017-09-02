package demo.yc.skinchangedemo.skin.attr;

import android.util.Log;
import android.view.View;

import java.util.List;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:26
 * @detail:
 */

public class SkinView
{

    private View mView;
    private List<SkinAttr> mAttrs;

    public SkinView(View view,List<SkinAttr> attrs)
    {
        if(view == null)
            throw new IllegalArgumentException("view can not be null");

        if(attrs == null)
            throw new IllegalArgumentException("attrs can not be null");

        mAttrs = attrs;
        mView = view;

        Log.w("attr","attrlist size is "+attrs.size());
    }

    public void apply()
    {

        for(SkinAttr attr:mAttrs)
        {
            Log.w("attr",attr.getResName()+"----"+attr.getResType());
            attr.apply(mView);
        }
    }

    public View getmView()
    {
        return mView;
    }

    public void setmView(View mView)
    {
        this.mView = mView;
    }

    public List<SkinAttr> getmAttrs()
    {
        return mAttrs;
    }

    public void setmAttrs(List<SkinAttr> mAttrs)
    {
        this.mAttrs = mAttrs;
    }


}
