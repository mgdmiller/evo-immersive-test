package com.bws.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int SYSTEM_UI_FLAG_IMMERSIVE_HIDDEN = 64; // блокирует “вытягивание” навбара и статусбара в режиме immersive
    private static final int STATUS_BAR_DISABLE_HOME = 0x00200000; // отключает кнопку Домой
    private static final int STATUS_BAR_DISABLE_RECENT = 0x01000000; // отключает вызов меню недавних приложений
    private static final int STATUS_BAR_DISABLE_NOTIFICATION_ALERTS = 0x00040000; // отключает отображение нотификаций
    private static final int STATUS_BAR_DISABLE_CLOCK = 0x00800000; //

    private boolean isLocked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(view -> {
            if (isLocked) {
                lockEnd();
            } else {
                lockStart();
            }
        });
        lockStart();
    }

    private void lockStart() {
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | SYSTEM_UI_FLAG_IMMERSIVE_HIDDEN
                        | STATUS_BAR_DISABLE_HOME
                        | STATUS_BAR_DISABLE_RECENT
                        | STATUS_BAR_DISABLE_NOTIFICATION_ALERTS
                        | STATUS_BAR_DISABLE_CLOCK
        );
        startLockTask();
        isLocked = true;
    }

    private void lockEnd() {
        getWindow().getDecorView().setSystemUiVisibility(0);
        stopLockTask();
        isLocked = false;
    }
}