package demo.yc.skinchangedemo.skin.config;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.yc.skinchangedemo.skin.attr.SkinView;
import demo.yc.skinchangedemo.skin.callback.ISkinChangeListener;
import demo.yc.skinchangedemo.skin.callback.ISkinChangingCallback;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 10:27
 * @detail:
 */

public class SkinManager
{
    private  AssetManager manager ;
    private Resources resources;
    private static SkinManager mInstance;
    private Context mContext;

    private String mPackName;
    private String mApkPath;

    private Map<ISkinChangeListener,List<SkinView>> mSkinViewMaps = new HashMap<>();

    private List<ISkinChangeListener> listenerList = new ArrayList<>();

    private SkinManager(){
    }

    public static SkinManager getInstance()
    {
        if(mInstance == null)
        {
            synchronized (SkinManager.class)
            {
                if(mInstance == null)
                {
                    mInstance = new SkinManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context)
    {
        mContext = context.getApplicationContext();
    }

    private void loadPlugin(String apkPath,String packName) throws Exception
    {
        if(!apkPath.equals(mApkPath) || !packName.equals(mPackName))
        {
            mApkPath = apkPath;
            mPackName = packName;

            manager = AssetManager.class.newInstance();
            Method method = manager.getClass()
                    .getMethod("addAssetPath",String.class);
            method.invoke(manager, apkPath);
            Resources superRes = mContext.getResources();
            resources = new Resources(manager,superRes.getDisplayMetrics(),superRes.getConfiguration());
        }


    }

    // 获取drawable
    public  Drawable getDrawableBySkinPlugin(String name)
    {

        Log.w("get","drawble-----------------"+name);
        return resources.getDrawable(
                resources.getIdentifier(name,"drawable",mPackName));
    }

    // 获取color
    public ColorStateList getColorBySkinPlugin(String name)
    {
        Log.w("get","color-----------------"+name);
        return resources.getColorStateList
                (resources.getIdentifier(name,"color",mPackName));
    }


    public List<SkinView> getSkinViews(ISkinChangeListener listener)
    {
        return mSkinViewMaps.get(listener);
    }
    public void addSkinViews(ISkinChangeListener listener,List<SkinView> views)
    {
        mSkinViewMaps.put(listener,views);
    }


    public void registerSkinListener(ISkinChangeListener listener)
    {
        listenerList.add(listener);
    }

    public void unregisterSkinListener(ISkinChangeListener listener)
    {
        listenerList.remove(listener);
        mSkinViewMaps.remove(listener);
    }


    public void changeSkin(final String apkPath,final String packName,ISkinChangingCallback callback)
    {
        if(callback == null)
        {
            callback = ISkinChangingCallback.DEFAULT_CALLBACK;
        }

        final ISkinChangingCallback call = callback;
        new AsyncTask<Void,Void,Void>()
        {
            @Override
            protected void onPreExecute()
            {
                call.onStart();
            }

            @Override
            protected Void doInBackground(Void... params)
            {
                try
                {
                    loadPlugin(apkPath,packName);
                } catch (Exception e)
                {
                    call.onError(e);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                try
                {
                    notifyAllListener();
                    call.onComplete();
                }catch (Exception e)
                {
                    call.onError(e);
                }
            }
        }.execute();

    }

    private void notifyAllListener()
    {
        for(ISkinChangeListener listener :listenerList)
        {
            skinChange(listener);
            listener.onSkinChange();
        }
    }

    private void skinChange(ISkinChangeListener listener)
    {
        List<SkinView> skinViews = mSkinViewMaps.get(listener);
        for(SkinView view: skinViews)
        {
            view.apply();
        }

    }
}