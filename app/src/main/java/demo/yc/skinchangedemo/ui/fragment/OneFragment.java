package demo.yc.skinchangedemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.yc.skinchangedemo.R;
import demo.yc.skinchangedemo.skin.callback.ISkinChangeListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment implements ISkinChangeListener
{
    public OneFragment()
    {

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
      //  SkinManager.getInstance().registerSkinListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

//        if(SkinManager.getInstance().isNeedLoadPlugin())
//            SkinManager.getInstance().skinChange(this);

        return inflater.inflate(R.layout.fragment_one,container,false);
    }

    @Override
    public void onDestroyView()
    {
       // SkinManager.getInstance().unregisterSkinListener(this);
        super.onDestroyView();
    }

    @Override
    public void onSkinChange()
    {

    }
}
