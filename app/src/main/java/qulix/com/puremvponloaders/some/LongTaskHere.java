package qulix.com.puremvponloaders.some;

import android.os.Handler;
import android.os.Looper;

@SuppressWarnings("WeakerAccess")
public class LongTaskHere {

    interface Callback {
        void longTaskDone(String data);
    }

    public static void doLongTask(Callback callback) {
        final Handler mHandler = new Handler(Looper.getMainLooper());
        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            final String resultData = " --- some long work result ---";
            mHandler.post(() -> callback.longTaskDone(resultData));
        }).start();


    }


}
