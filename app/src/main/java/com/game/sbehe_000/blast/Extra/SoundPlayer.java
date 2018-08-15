package com.game.sbehe_000.blast.Extra;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.game.sbehe_000.blast.R;

public class SoundPlayer {

    private static SoundPool soundPool;
    private AudioAttributes audioAttributes;
    final int SOUND_POOL_MAX = 2;
    private static int gainItemsound;
    private static int shootsound;
    private static int explosionsound;
    private static int punchsound;

    public SoundPlayer(Context context){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(audioAttributes)
                    .setMaxStreams(SOUND_POOL_MAX)
                    .build();
        }else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }
        shootsound = soundPool.load(context, R.raw.shoot , 1);
        gainItemsound = soundPool.load(context, R.raw.teleport,1);
        explosionsound = soundPool.load(context, R.raw.kiplosion,1);
        punchsound = soundPool.load(context,R.raw.punch, 1);

    }

    public void playShootSound(){
        soundPool.play(shootsound, 1.0f, 1.0f, 1 , 0, 1.0f);
    }

    public void playGainItemSound(){
        soundPool.play(gainItemsound,1.0f,1.0f,1, 0,1.0f);

    }
    public void playExplosionSound(){
        soundPool.play(explosionsound,1.0f,1.0f,1,0,1.0f);
    }
    public void playPunchSound(){
        soundPool.play(punchsound,1.0f,1.0f,1,0, 1.0f);
    }
}
