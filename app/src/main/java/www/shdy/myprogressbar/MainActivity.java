package www.shdy.myprogressbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView mStart;
    private TextView mSize;
    private TextView mSpeed;
    private DownLoadProgressbar mProgress;
    private int max = 100; //总的大小
    private int current = 0; //当前下载大小
    private String speed = "1"; //下载速度
    private TextView mStop;
    private TextView mClear;
    private boolean ImStar = true;//默认设置只能点击一次

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mClear = findViewById(R.id.tv_clear);
        mStop = findViewById(R.id.tv_stop);
        mStart = (TextView) findViewById(R.id.tv_start);
        mProgress = (DownLoadProgressbar) findViewById(R.id.dp_game_progress);
        mSize = (TextView) findViewById(R.id.tv_size);
        mSpeed = (TextView) findViewById(R.id.tv_speed);


        //初始化下载进度
        mSize.setText(current + "MB/" + max + "MB");
        //初始化下载速度
        mSpeed.setText(speed + "MB/s");

        if (ImStar) {
            mStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ImStar = false;
                    start();
                }
            });


        } else {

            Toast.makeText(MainActivity.this, "点击", Toast.LENGTH_SHORT).show();
        }


        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.removeCallbacks(runnable);
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                current = 0;
                max = 100;
                mProgress.setMaxValue(max);
                mProgress.setCurrentValue(current);
                mSize.setText(current + "MB/" + max + "MB");
            }
        });
    }

    //循环模拟下载过程
    public void start() {
        if (current <= max) {
            mSize.setText(current + "MB/" + max + "MB");

            mSpeed.setText(speed + "MB/s");

            mProgress.setMaxValue(max);
            mProgress.setCurrentValue(current);
            //Handler循环 +1 进度。
            handler.postDelayed(runnable, 100);
        } else {
            handler.removeCallbacks(runnable);
        }

    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // TODO Auto-generated method stub
            current = current + 1;

            start();
        }
    };
}
