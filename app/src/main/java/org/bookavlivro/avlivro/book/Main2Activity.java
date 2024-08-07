package org.bookavlivro.avlivro.book;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements
        TextToSpeech.OnInitListener {

    private Button mButton;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_main2);
        WebView webView = (WebView) findViewById(R.id.webView);

        Intent intent = getIntent();
        //получаем строку и формируем имя ресурса
        final String resName = "h" + intent.getIntExtra("head", 0);
        Log.i("name", resName);
        Context context = getBaseContext(); //получаем контекст

        //читаем текстовый файл из ресурсов по имени
        String text = readRawTextFile(context, getResources().getIdentifier(resName, "raw", "org.bookavlivro.avlivro.book"));

        webView.loadDataWithBaseURL(null, text, "text/html", "ru_RU", null);

        mTTS = new TextToSpeech(this, this);

        mButton = (Button) findViewById(R.id.button1);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getBaseContext();
                String newtext = readRawTextFile(context, getResources().getIdentifier(resName, "raw", "org.bookavlivro.avlivro.book"));
               // Log.e("TTS", newtext);
                String rawtext = android.text.Html.fromHtml(newtext).toString();
               // Log.e("TTS", rawtext);
                //mTTS.speak(rawtext, TextToSpeech.QUEUE_FLUSH, null);
                speech(rawtext);
            }
        });

    }

    @Override
    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {

            Locale locale = new Locale("ru");

            int result = mTTS.setLanguage(locale);
            //int result = mTTS.setLanguage(Locale.getDefault());

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                //Log.e("TTS", "Извините, этот язык не поддерживается");
            } else {
                mButton.setEnabled(true);
            }

        } else {
            Log.e("TTS", "Ошибка!");
        }

    }


    @Override
    public void onDestroy() {
        // Don't forget to shutdown mTTS!
        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }

    //читаем текст из raw-ресурсов
    public static String readRawTextFile(Context context, int resId)
    {
        InputStream inputStream = context.getResources().openRawResource(resId);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader buffReader = new BufferedReader(inputReader);
        String line;
        StringBuilder builder = new StringBuilder();

        try {
            while (( line = buffReader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            return null;
        }
        return builder.toString();
    }

    private void speech(String charSequence) {

        int position ;
        position = 0;

        int sizeOfChar= charSequence.length();
        String testStri= charSequence.substring(position,sizeOfChar);


        int next = 3900;
        int pos =0;
        while(true) {
            String temp="";
            Log.e("in loop", "" + pos);

            try {

                temp = testStri.substring(pos, next);
                HashMap<String, String> params = new HashMap<>();
                params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, temp);
                mTTS.speak(temp, TextToSpeech.QUEUE_ADD, params);

                pos = pos + 3900;
                next = next + 3900;

            } catch (Exception e) {
                temp = testStri.substring(pos, testStri.length());
                mTTS.speak(temp, TextToSpeech.QUEUE_ADD, null);
                break;

            }

        }

    }

}

