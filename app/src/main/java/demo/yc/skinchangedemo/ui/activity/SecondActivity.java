package demo.yc.skinchangedemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import demo.yc.skinchangedemo.BaseSkinActivity;
import demo.yc.skinchangedemo.R;
import demo.yc.skinchangedemo.skin.callback.ISkinChangingCallback;
import demo.yc.skinchangedemo.skin.config.Const;
import demo.yc.skinchangedemo.skin.config.SkinManager;
import demo.yc.skinchangedemo.ui.fragment.OneFragment;

public class SecondActivity extends BaseSkinActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setSupportActionBar((Toolbar) findViewById(R.id.action_bar));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,new OneFragment()).commit();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.skin_menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        SkinManager.getInstance().init(this);
        switch (id)
        {
            case R.id.skin_green:
                loginSkinGreen();
                break;
            case R.id.skin_red:
                loginSkinRed();
                break;
            case R.id.skin_reset:
                reset();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void reset()
    {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,new OneFragment()).commit();
    }

    private void loginSkinRed()
    {
        SkinManager.getInstance().changeSkin(Const.SKIN_RED_APK
                ,Const.SKIN_RED_PACK
                ,new ISkinChangingCallback()
                {
                    @Override
                    public void onStart()
                    {
                        Toast.makeText(SecondActivity.this,"准备换肤...",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onError(Exception e)
                    {
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete()
                    {
                        Toast.makeText(SecondActivity.this,"ok",Toast.LENGTH_SHORT).show();
                        onRestart();
                    }
                });
    }

    private void loginSkinGreen()
    {
        SkinManager.getInstance().changeSkin(Const.SKIN_GREEN_APK
                ,Const.SKIN_GREEN_PACK
                ,new ISkinChangingCallback()
                {
                    @Override
                    public void onStart()
                    {
                    }
                    @Override
                    public void onError(Exception e)
                    {
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete()
                    {

                        Toast.makeText(SecondActivity.this,"ok",Toast.LENGTH_SHORT).show();
                        onRestart();
                    }
                });
    }

    @Override
    public void onSkinChange()
    {
        Toast.makeText(this,"SecondActivity.....收到通知",Toast.LENGTH_LONG).show();
    }




    @Override
    public String toString()
    {
        return "SecondActivity";
    }
}
