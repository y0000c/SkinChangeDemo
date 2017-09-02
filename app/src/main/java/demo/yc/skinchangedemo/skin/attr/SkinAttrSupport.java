package demo.yc.skinchangedemo.skin.attr;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import demo.yc.skinchangedemo.skin.config.Const;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:51
 * @detail:
 */

public class SkinAttrSupport
{
    public static List<SkinAttr> getSkinAttrs(AttributeSet attrs, Context context)
    {
        List<SkinAttr> list = new ArrayList<>();
        SkinAttr attrItem = null;
        SkinAttrType attrType = null;

        for(int i=0,n=attrs.getAttributeCount();i<n;i++)
        {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);

              Log.w("skin",attrName+"============="+attrValue);

            if (attrValue.startsWith("@"))
            {
                int id = -1;
                try
                {
                    id = Integer.parseInt(attrValue.substring(1));
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
                if (id == -1)
                    continue;

                String resName = context.getResources().getResourceEntryName(id);


                if (resName.startsWith(Const.SKIN_PREFIX))
                {
                    attrType = getSupportAttrType(attrName);

                    Log.w("skin", "attrType-----------"+attrType);
                    if (attrType == null)
                        continue;
                    attrItem = new SkinAttr(resName, attrType);
                    list.add(attrItem);
                }
            }
        }
            return list;
    }

    private static SkinAttrType getSupportAttrType(String resName)
    {
        for(SkinAttrType type : SkinAttrType.values())
        {
            if(type.getResType().equals(resName))
                return type;
        }
        return null;
    }
}
