package demo.yc.skinchangedemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import demo.yc.skinchangedemo.BaseSkinActivity;
import demo.yc.skinchangedemo.R;


public class Main2Activity extends BaseSkinActivity
{
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.image);
        image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(Main2Activity.this,SecondActivity.class);
                    startActivity(intent);
            }
        });

    }

    @Override
    public String toString()
    {
        return "Main2Activity";
    }

    @Override
    public void onSkinChange()
    {
        Toast.makeText(this,"Main2Activity.....收到通知",Toast.LENGTH_LONG).show();

    }
}
