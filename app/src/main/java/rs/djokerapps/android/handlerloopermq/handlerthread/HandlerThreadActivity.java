package rs.djokerapps.android.handlerloopermq.handlerthread;

import androidx.appcompat.app.AppCompatActivity;
import rs.djokerapps.android.handlerloopermq.R;

import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

public class HandlerThreadActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ExampleHandlerThread handlerThread = new ExampleHandlerThread();

    private ExampleRunnable1 runnable1 = new ExampleRunnable1();
    private Object token = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_thread);

        handlerThread.start();
    }

    public void doWork(View view) {
        Message msg = Message.obtain(handlerThread.getHandler());
        msg.what = ExampleHandlerThread.EXAMPLE_TASK;
        msg.arg1 = 23;
        msg.obj = "Obj String";
        //msg.setData();

        msg.sendToTarget();
        //handlerThread.getHandler().sendEmptyMessage(1);

        handlerThread.getHandler().postAtTime(runnable1, token, SystemClock.uptimeMillis());
        //handlerThread.getHandler().post(runnable1);
        handlerThread.getHandler().post(new ExampleRunnable1());
        handlerThread.getHandler().postAtFrontOfQueue(new ExampleRunnable2());
    }

    public void removeMessages(View view) {
        handlerThread.getHandler().removeCallbacks(runnable1, token);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    static class ExampleRunnable1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "Runnable1: " + i);
                SystemClock.sleep(1000);
            }
        }
    }

    static class ExampleRunnable2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                Log.d(TAG, "Runnable2: " + i);
                SystemClock.sleep(1000);
            }
        }
    }
}