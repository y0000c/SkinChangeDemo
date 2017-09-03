package demo.yc.skinchangedemo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import demo.yc.skinchangedemo.BaseSkinActivity;
import demo.yc.skinchangedemo.R;
import demo.yc.skinchangedemo.ui.fragment.OneFragment;

public class SecondActivity extends BaseSkinActivity
{

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout,new OneFragment()).commit();
    }
}
