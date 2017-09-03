package demo.yc.skinchangedemo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import demo.yc.skinchangedemo.skin.attr.SkinAttr;
import demo.yc.skinchangedemo.skin.attr.SkinAttrSupport;
import demo.yc.skinchangedemo.skin.attr.SkinView;
import demo.yc.skinchangedemo.skin.callback.ISkinChangeListener;
import demo.yc.skinchangedemo.skin.config.SkinManager;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:23
 * @detail:
 */

public class BaseSkinActivity extends AppCompatActivity implements ISkinChangeListener
{
    final Object[] mConstructorArgs = new Object[2];
    static final Class<?>[] mConstructorSignature = new Class[]{
            Context.class, AttributeSet.class};
    private static final HashMap<String, Constructor<? extends View>> sConstructorMap =
            new HashMap<String, Constructor<? extends View>>();

    private Method mCreateViewMethod =  null;
    static final Class<?>[] sCreateViewSignature =
            new Class[]{View.class, String.class, Context.class, AttributeSet.class};
    final Object[] mCreateViewArgs = new Object[4];

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        SkinManager.getInstance().registerSkinListener(this);
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        Log.w("skin","================="+this.getClass().getSimpleName()+"======================");
        LayoutInflaterCompat.setFactory2(inflater, new LayoutInflater.Factory2()
        {

            @Override
            public View onCreateView(String name, Context context, AttributeSet attrs)
            {
                return null;
            }

            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs)
            {

                AppCompatDelegate delegate = getDelegate();
                List<SkinAttr> attrList = null;
                View view = null;
                try
                {
                    if(mCreateViewMethod == null)
                    {
                        mCreateViewMethod = delegate.getClass().getMethod("createView",sCreateViewSignature);
                    }
                    mCreateViewArgs[0] = parent;
                    mCreateViewArgs[1] = name;
                    mCreateViewArgs[2] = context;
                    mCreateViewArgs[3] = attrs;
                    view = (View) mCreateViewMethod.invoke(delegate,mCreateViewArgs);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

                attrList = SkinAttrSupport.getSkinAttrs(attrs,context);

                if(attrList.isEmpty())
                    return null ;

                if (view == null)
                {
                    view = createViewFromTag(name, context, attrs);
                    Log.w("skin","view-----is null");
                }else
                {
                    Log.w("skin","view-----is not null");
                }

                if(view != null)
                {
                    injectSkin(view,attrList);
                }
                return view;
            }
        });

        super.onCreate(savedInstanceState);

        if(ContextCompat.checkSelfPermission
                (this,android.Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}
                    ,100);
        }




    }

    private void injectSkin(View view, List<SkinAttr> attrList)
    {

        List<SkinView> skinViews = SkinManager.getInstance().getSkinViews(this);
        if(skinViews == null)
        {
            skinViews = new ArrayList<>();
            SkinManager.getInstance().addSkinViews(this,skinViews);
        }
        skinViews.add(new SkinView(view,attrList));
        Log.w("plugin",this.getClass().getSimpleName()+"is add views");

        //判断当前是否需要马上换肤
        if(SkinManager.getInstance().isNeedLoadPlugin())
            SkinManager.getInstance().skinChange(this);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.w("skin",this.getClass().getSimpleName()+"=================start===============");

    }

    private View createViewFromTag(String name, Context context, AttributeSet attrs)
    {
        if (name.equals("view"))
        {
            name = attrs.getAttributeValue(null, "class");
        }

        mConstructorArgs[0] = context;
        mConstructorArgs[1] = attrs;

        try
        {
            if (-1 == name.indexOf('.'))
            {
                return createView(context, name, "android.widget.");
            } else
            {
                return createView(context, name, null);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        } finally
        {
            mConstructorArgs[0] = null;
            mConstructorArgs[1] = null;
        }
    }


    public final View createView(Context context, String name, String prefix)
            throws ClassNotFoundException, InflateException
    {

        Constructor<? extends View> constructor = sConstructorMap.get(name);
        Class<? extends View> clazz = null;

        try
        {
            if (constructor == null)
            {
                // Class not found in the cache, see if it's real, and try to add it
                clazz = context.getClassLoader().loadClass(
                        prefix != null ? (prefix + name) : name).asSubclass(View.class);

                constructor = clazz.getConstructor(mConstructorSignature);
                sConstructorMap.put(name, constructor);
            }
            constructor.setAccessible(true);
            return constructor.newInstance(mConstructorArgs);
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onSkinChange()
    {
    }


    @Override
    public void finish()
    {
        SkinManager.getInstance().unregisterSkinListener(this);
        super.finish();
    }
}
