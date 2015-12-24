package kenyawakita.sapuri;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SearchResource {

    public static Bitmap getQuestion(String category_name, int question_No, Activity activity){
        String question = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                question = MainActivity.resource.get(k+(question_No-1)).getQuestion();
                break;
            }
        }


        //読み込み用のオプションオブジェクトを生成
        BitmapFactory.Options options = new BitmapFactory.Options();
        // ディスプレイのサイズを取得
        Display display = activity.getWindowManager().getDefaultDisplay();

        InputStream question_image = null;
        try {
            question_image = activity.getAssets().open(question);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap question_bitmap = null;
        question_bitmap = BitmapFactory.decodeStream(question_image);

        return question_bitmap;
    }

    public static String getAnswer(String category_name, int question_No){
        String answer = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                answer = MainActivity.resource.get(k+(question_No-1)).getAnswer();
                break;
            }
        }
        return answer;
    }

    public static String getHint(String category_name, int question_No){
        String hint = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                hint = MainActivity.resource.get(k+(question_No-1)).getHint();
                break;
            }
        }
        return hint;
    }

    
}
