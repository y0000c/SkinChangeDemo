package demo.yc.skinchangedemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import demo.yc.skinchangedemo.BaseSkinActivity;
import demo.yc.skinchangedemo.R;
import demo.yc.skinchangedemo.ui.fragment.OneFragment;


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

        image.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
            }
        });

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,new OneFragment()).commit();
    }

    @Override
    public String toString()
    {
        return "MainActivity";
    }

    @Override
    public void onSkinChange()
    {
        Toast.makeText(this,"MainActivity.....收到通知",Toast.LENGTH_LONG).show();

    }
}
