package com.cookandroid.gawebawebogame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ImageView handAnimImageView;
    ImageView setHandImageView;

    AnimationDrawable animationDrawable;

    TextToSpeech textToSpeech;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handAnimImageView = findViewById(R.id.hand_anim_image_view);
        setHandImageView = findViewById(R.id.set_hand_image_View);
        handAnimImageView.setVisibility(View.GONE); //없애기
        setHandImageView.setVisibility(View.VISIBLE); //보이게하기

        animationDrawable = (AnimationDrawable) handAnimImageView.getDrawable();


        textToSpeech = new TextToSpeech(getApplicationContext(), (i) -> {
            if (i != TextToSpeech.ERROR) {
                textToSpeech.setLanguage(Locale.KOREAN);
                textToSpeech.setPitch(1.0f);
                textToSpeech.setSpeechRate(1.0f);

            }
        });

    }

    public void button_click(View view) {
        switch (view.getId()){
            case R.id.replay_button:
                setHandImageView.setVisibility(View.GONE);
                handAnimImageView.setVisibility(View.VISIBLE);
                animationDrawable.start();
                textToSpeech.speak("가위바위보",TextToSpeech.QUEUE_FLUSH,null,null);
                break;

            case R.id.gawe_button:
            case R.id.bawe_button:
            case R.id.bo_button:

            break;

            default:
                animationDrawable.stop();
                handAnimImageView.setVisibility(View.GONE);
                setHandImageView.setVisibility(View.VISIBLE);
                setHandImageView.setImageResource(R.drawable.com_gawe);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.shutdown();
    }
}