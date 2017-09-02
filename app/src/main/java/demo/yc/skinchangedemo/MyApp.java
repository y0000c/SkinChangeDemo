package demo.yc.skinchangedemo;

import android.app.Application;

import demo.yc.skinchangedemo.skin.config.SkinManager;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 11:41
 * @detail:
 */

public class MyApp extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        SkinManager.getInstance().init(this);
    }
}
