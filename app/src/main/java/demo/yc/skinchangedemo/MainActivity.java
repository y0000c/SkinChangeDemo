package demo.yc.skinchangedemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.yc.skinchangedemo.skin.callback.ISkinChangingCallback;
import demo.yc.skinchangedemo.skin.config.Const;
import demo.yc.skinchangedemo.skin.config.SkinManager;


public class MainActivity extends BaseSkinActivity
{
    TextView title;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = (TextView) findViewById(R.id.title);
        image = (ImageView) findViewById(R.id.image);


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
//        image.setBackgroundResource(R.drawable.skin_navig);
//        title.setTextColor(Color.BLACK);
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
                        Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(MainActivity.this,"ok",Toast.LENGTH_SHORT).show();
                    }
                });

    }


}
