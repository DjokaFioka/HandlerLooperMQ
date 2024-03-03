package rs.djokerapps.android.handlerloopermq;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

public class ExampleLooperThread extends Thread {
    private static final String TAG = "ExampleLooperThread";

    public Looper looper;
    public Handler handler;

    @Override
    public void run() {
        Looper.prepare(); //Creates looper and adds the message queue

        looper = Looper.myLooper(); //returns the looper of the current thread

        //Redosled je bitan! Mora prvo da ide Looper.prepare() pre instanciranja Handler-a, jer inace handler ne moze da pronadje looper za thread.
        handler = new ExampleHandler(); //ovako je handler povezan sa MessageQueue od background thread-a, da smo ga instancirali gore gde je deklarisan, onda ne bi bio.
//        handler = new Handler(); //ovako je bilo ranije dok smo handleru prosledjivali runnable, kada smo presli na message, onda smo morali da napravimo svoj handler koji zna kako da interpretira te poruke (message)

        //Redosled je bitan! Mora da ide posle instanciranja handlera jer je ovo zapravo beskonacna petlja tako da se handler ne bi instancirao dok se ona ne zavrsi
        Looper.loop();

        Log.d(TAG, "End of run()");
    }

//    Dok je bilo ovako kada se drugi put klikne na Start pukne app jer bi morao novi Thread da se instancira, jer isti ne moze ponovo da se pokrene
//    , a u postojeci ne mogu da se doda novi zadatak. Ako bi se instancirao novi Thread, to povlaci processor overhead
//    @Override
//    public void run() {
//        for (int i = 0; i < 5; i++) {
//            Log.d(TAG, "run: " + i);
//            SystemClock.sleep(1000);
//        }
//
//        Log.d(TAG, "End of run()");
//    }
}
