package com.game.sbehe_000.blast.Extra;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.game.sbehe_000.blast.R;


public class MusicService extends Service {
    private static final String TAG = null;
    MediaPlayer player;
    public IBinder onBind(Intent arg0) {

        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.supersaiyanmusic);
        player.setLooping(true);
        player.setVolume(70, 70);
        player.start();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

}
