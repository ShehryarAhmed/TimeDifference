package com.technexia.user.timer;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Button b1;
    Button b2;
    TextView textView;
    TextView current;
    private TextView txtCurrentTime;

//    private long startTime = 0L;
//
//    private Handler customHandler = new Handler();
//    long timeInMilliseconds = 0L;
//    long timeSwapBuff = 0L;
//    long updatedTime = 0L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.timervalue);


        Thread thread = null;

        Runnable runnable = new CountDownRunner();

        thread = new Thread(runnable);
        thread.start();

//        textView = (TextView)findViewById(R.id.mytext);
//        Timer updateTimer = new Timer();
//        updateTimer.schedule(new TimerTask() {
//            public void run() {
//                try {
//
//                    Date date = new Date();
//                    SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aa");
//                    String dateString = format.format(date);
//                    Date date1 = format.parse("09:00:00 AM");
//                    Date date2 = format.parse(dateString);
//                    long mills = date2.getTime() - date1.getTime();
//                    Log.v("Data1", "" + date1.getTime());
//                    Log.v("Data2", "" + date2.getTime());
//                    int hours = (int) (mills / (1000 * 60 * 60));
//                    int mins = (int) (mills / (1000 * 60)) % 60;
//                    int seconds = (int) (mills / 1000) % 60;
//
//                    String diff = hours + ":" + mins + ":" + seconds; // updated value every1 second
//                    textView.setText(diff);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//
//        }, 0, 1000);
//

//        b1 = (Button) findViewById(R.id.startbutton);
//        b2 = (Button) findViewById(R.id.pausebutton);
//        b1.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//
//                startTime = SystemClock.uptimeMillis();
//
//                customHandler.postDelayed(updateTimerThread, 0);
//
//            }
//
//        });
//
//
//        b2.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View view) {
//
//                timeSwapBuff += timeInMilliseconds;
//
//                customHandler.removeCallbacks(updateTimerThread);
//
//            }
//
//        });
//
//    }
//    private Runnable updateTimerThread = new Runnable() {
//
//        public void run() {
//
//            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
//
//            updatedTime = timeSwapBuff + timeInMilliseconds;
//
//            int secs = (int) (updatedTime / 1000);
//
//            int mins = secs / 60;
//
//            secs = secs % 60;
//            int milliseconds = (int) (updatedTime % 1000);
//
//            textView.setText("" + mins + ":"
//                            + String.format("%02d", secs) + ":"
//                            + String.format("%03d", milliseconds));
//
//            customHandler.postDelayed(this, 0);
//
//        }
//    };
    }
    private void doWork() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss aa");

                    txtCurrentTime= (TextView)findViewById(R.id.timervalue);

                    Date systemDate = Calendar.getInstance().getTime();
                    String myDate = sdf.format(systemDate);
//                  txtCurrentTime.setText(myDate);

                    Date Date1 = sdf.parse(myDate);
                    Date Date2 = sdf.parse("09:00:00 am");

                    long millse = Date1.getTime() - Date2.getTime();
                    long mills = Math.abs(millse);

                    int Hours = (int) (mills/(1000 * 60 * 60));
                    int Mins = (int) (mills/(1000*60)) % 60;
                    long Secs = (int) (mills / 1000) % 60;

                    String diff = Hours + ":" + Mins + ":" + Secs; // updated value every1 second
                    txtCurrentTime.setText(diff);
                }
                catch (Exception e)
                {

                }
            }
        });

    }
    class CountDownRunner implements Runnable
    {
        // @Override
        public void run()
        {
            while(!Thread.currentThread().isInterrupted())
            {
                try
                {
                    doWork();
                    Thread.sleep(1000); // Pause of 1 Second
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
                catch(Exception e)
                {
                }
            }
        }
    }


}