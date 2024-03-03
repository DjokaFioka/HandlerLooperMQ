package rs.djokerapps.android.handlerloopermq;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class ExampleHandler extends Handler {
    private static final String TAG = "ExampleHandler";

    public static final int TASK_A = 1;
    public static final int TASK_B = 2;

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case TASK_A:
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "Task A executed " + i);
                    SystemClock.sleep(1000);
                }
                break;
            case TASK_B:
                for (int i = 0; i < 3; i++) {
                    Log.d(TAG, "Task B executed " + i);
                    SystemClock.sleep(2000);
                }
                break;
        }
    }
}
