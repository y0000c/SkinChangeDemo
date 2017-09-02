package demo.yc.skinchangedemo.skin.callback;

/**
 * @author: YC
 * @date: 2017/9/2 0002
 * @time: 13:20
 * @detail:
 */

public interface ISkinChangingCallback
{
    void onStart();

    void onError(Exception e);

    void onComplete();

    public static DefaultCallback DEFAULT_CALLBACK = new DefaultCallback();

    public class DefaultCallback implements ISkinChangingCallback
    {

        @Override
        public void onStart()
        {

        }

        @Override
        public void onError(Exception e)
        {

        }

        @Override
        public void onComplete()
        {

        }
    }

}
