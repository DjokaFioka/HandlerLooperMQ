package rs.djokerapps.android.handlerloopermq;

import androidx.appcompat.app.AppCompatActivity;
import rs.djokerapps.android.handlerloopermq.handlerthread.HandlerThreadActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;

import static rs.djokerapps.android.handlerloopermq.ExampleHandler.TASK_A;
import static rs.djokerapps.android.handlerloopermq.ExampleHandler.TASK_B;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ExampleLooperThread looperThread = new ExampleLooperThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view) {
        looperThread.start();
    }

    public void stopThread(View view) {
        looperThread.looper.quit();
//        looperThread.looper.quitSafely();//will execute our messages that already hit the dispatch barrier (which are below the current time)
    }

    public void startHandlerThread(View view) {
        Intent intent = new Intent(getApplicationContext(), HandlerThreadActivity.class);
        startActivity(intent);
    }

    public void taskA(View view) {
        Message msg = Message.obtain();
        msg.what = TASK_A;
        looperThread.handler.sendMessage(msg);

        /*Handler threadHandler = new Handler(looperThread.looper);
        threadHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "run: " + i);
                    SystemClock.sleep(1000);
                }
            }
        });*/

        /*
        looperThread.handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "run: " + i);
                    SystemClock.sleep(1000);
                }
            }
        });
         */

     }

    public void taskB(View view) {
        Message msg = Message.obtain();
        msg.what = TASK_B;
        looperThread.handler.sendMessage(msg);
    }
}