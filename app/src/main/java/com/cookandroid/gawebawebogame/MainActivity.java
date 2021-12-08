package com.cookandroid.gawebawebogame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView handAnimImageView;
    ImageView setHandImageView;

    ImageButton gaweButton;
    ImageButton baweButton;
    ImageButton boButton;
    ImageButton replayButton;
    //리플레이버튼을 눌러야만 실행이되게 만들기

    AnimationDrawable animationDrawable;

    TextToSpeech textToSpeech;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handAnimImageView = findViewById(R.id.hand_anim_image_view);
        setHandImageView = findViewById(R.id.set_hand_image_View);

        gaweButton=findViewById(R.id.gawe_button);
        baweButton=findViewById(R.id.bawe_button);
        boButton=findViewById(R.id.bo_button);
        replayButton=findViewById(R.id.replay_button);


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
                voicePlay("안내면 지는거다 가위바위보");
                replayButton.setEnabled(false);
                gaweButton.setEnabled(true);
                baweButton.setEnabled(true);
                boButton.setEnabled(true);
                break;
                //리플레이버튼을 누르면 가위바위보버튼을 누를수있음 (리플레이버튼은 더안눌림)


            case R.id.gawe_button:
            case R.id.bawe_button:
            case R.id.bo_button:
                replayButton.setEnabled(true);
                gaweButton.setEnabled(false);
                baweButton.setEnabled(false);
                boButton.setEnabled(false);
                //리플레이버튼을 누르지않으면 가위바위보버튼을 못누름
                animationDrawable.stop();
                handAnimImageView.setVisibility(View.GONE);
                setHandImageView.setVisibility(View.VISIBLE);
                int getComHand = new Random().nextInt(3)+1;
                switch (getComHand){
                    case 1:
                        setHandImageView.setImageResource(R.drawable.com_gawe);
                        if (view.getId() == R.id.gawe_button){
                            voicePlay("비겼습니당. 다시 시작하세요");
                        }else if(view.getId() == R.id.bawe_button){
                            voicePlay("당신이 이겼습니당 아쉽네요 쳇쳇");
                        }else {
                            voicePlay("제가 이겼네요 우하하크크크키키킥켁켁크키키케크키키키");
                        }
                        break;
                    case 2:
                        setHandImageView.setImageResource(R.drawable.com_bawe);
                        if (view.getId() == R.id.gawe_button){
                            voicePlay("제가 이겼네요 우하하크크크키키킥켁켁크키키케크키키키");
                        }else if(view.getId() == R.id.bawe_button){
                            voicePlay("비겼습니당. 다시 시작하세요");
                        }else {
                            voicePlay("당신이 이김 축하함");
                        }
                        break;
                    case 3:
                        setHandImageView.setImageResource(R.drawable.com_bo);
                        if (view.getId() == R.id.gawe_button){
                            voicePlay("당신이 이겼습니다 아쉽게됐네여");
                        }else if(view.getId() == R.id.bawe_button){
                            voicePlay("제가 이겼네요 우하하크크크키키킥켁켁크키키케크키키키");
                        }else {
                            voicePlay("비겼습니당. 다시 해요");
                        }
                        break;
                }
                break;

            default:
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        textToSpeech.shutdown();
    }

    public void voicePlay(String voiceText){
        textToSpeech.speak(voiceText,TextToSpeech.QUEUE_FLUSH,null,null);
    }
}