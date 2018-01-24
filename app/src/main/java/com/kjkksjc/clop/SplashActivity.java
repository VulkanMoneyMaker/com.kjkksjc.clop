package com.kjkksjc.clop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.kjkksjc.clop.utils.InternetUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private static final int TIME_SPLASH_SEC = 3;

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_splash);

        mDisposable = Observable.timer(TIME_SPLASH_SEC, TimeUnit.SECONDS)
                .subscribe(__ -> openNextScreen(), Throwable::printStackTrace);
    }

    @Override
    public void onDestroy() {
        if (mDisposable != null) mDisposable.dispose();
        super.onDestroy();
    }

    private void openNextScreen() {
        if (InternetUtils.checkNetworkConnection(this)) {
            openScreenMainActivity();
        } else {
            openScreenGameActivity();
        }
    }

    private void openScreenMainActivity() {
        Log.d(TAG, "openScreenMainActivity");
        startActivity(MainActivity.getMainActivityIntent(this));
        finish();
    }

    private void openScreenGameActivity() {
        Log.d(TAG, "openScreenMainActivity");
        startActivity(GameActivity.getGameActivityIntent(this));
        finish();
    }
}
