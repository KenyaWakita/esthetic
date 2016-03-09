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

    public static BitmapDrawable getQuestion_No(String category_name, int question_No, Activity activity){
        String questionNo_filename = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                //assetsフォルダに入っている問題画像の名前を取り出す
                questionNo_filename = MainActivity.resource.get(k+(question_No-1)).getQuestion_No();
                break;
            }
        }

        Log.d("String",questionNo_filename);

        BitmapDrawable questionNo_bitmapDrawable = Filename_To_BitmapDrawable(questionNo_filename, activity);
        return questionNo_bitmapDrawable;
    }

    public static BitmapDrawable getQuestion_No_solved(String category_name, int question_No, Activity activity){
        String questionNo_solved_filename = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                //assetsフォルダに入っている問題画像の名前を取り出す
                questionNo_solved_filename = MainActivity.resource.get(k+(question_No-1)).getQuestion_No_solved();
                break;
            }
        }

        BitmapDrawable questionNo_solved_bitmapDrawable = Filename_To_BitmapDrawable(questionNo_solved_filename, activity);
        return questionNo_solved_bitmapDrawable;
    }

    public static BitmapDrawable getQuestion(String category_name, int question_No, Activity activity){
        String question_filename = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                //assetsフォルダに入っている問題画像の名前を取り出す
                question_filename = MainActivity.resource.get(k+(question_No-1)).getQuestion();
                break;
            }
        }
        BitmapDrawable question_bitmapDrawable = Filename_To_BitmapDrawable(question_filename, activity);
        return question_bitmapDrawable;
    }

    public static String getQuestion_filename(String category_name, int question_No, Activity activity){
        String question_filename = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                //assetsフォルダに入っている問題画像の名前を取り出す
                question_filename = MainActivity.resource.get(k+(question_No-1)).getQuestion();
                break;
            }
        }
        return question_filename;
    }

    public static String getAnswer(String category_name, int question_No, Activity activity){
        String answer_true = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                answer_true = MainActivity.resource.get(k+(question_No-1)).getAnswer();
                break;
            }
        }

        return answer_true;
    }


    public static BitmapDrawable getDescription(String category_name, int question_No, Activity activity){
        String description_filename = null;
        for(int k = 0; k < MainActivity.resource.size(); k++){
            if(MainActivity.resource.get(k).getCategory().equals(category_name)){
                description_filename = MainActivity.resource.get(k+(question_No-1)).getDescription();
                break;
            }
        }
        BitmapDrawable description_bitmapDrawable = Filename_To_BitmapDrawable(description_filename, activity);
        return description_bitmapDrawable;
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




    //ファイルの名前からbitmapdrawableを出力する関数
    public static BitmapDrawable Filename_To_BitmapDrawable(String filename, Activity activity){

        InputStream image = null;
        try {
            image = activity.getAssets().open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bitmap bitmap = null;
        bitmap = BitmapFactory.decodeStream(image);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(activity.getResources(), bitmap);

        return  bitmapDrawable;
    }

}
