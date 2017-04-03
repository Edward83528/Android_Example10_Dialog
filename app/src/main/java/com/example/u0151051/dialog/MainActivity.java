package com.example.u0151051.dialog;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//對話方塊是一種小型視窗，可提示使用者做出決定或輸入額外資訊。 對話視窗並不會佔滿整個螢幕，而且通常是供強制回應事件使用，這種事件會要求使用者必須先完成特定動作，才能繼續下一步。
public class MainActivity extends AppCompatActivity {
    Button btn;
    AlertDialog alertDialog;//宣告一個AlertDialog物件接getAlertDialog傳回的AlertDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    void findview() {
        alertDialog = getAlertDialog("這是一個對話框", "請選擇......");
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(c);
    }

    View.OnClickListener c = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            alertDialog.show();//按下button啟動對話框
        }
    };

    //寫一個方法設定對話框
    // AlertDialog單一對話方塊最多只能包含 3 個動作按鈕
    private AlertDialog getAlertDialog(String title, String message) {
        //產生一個Builder物件(要選android app那個)
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //設定Dialog的標題(標題可有可無)
        builder.setTitle(title);
        //設定Dialog的內容(一定要有)
        builder.setMessage(message);
        //設定Positive按鈕資料:AlertDialog.Builder setPositiveButton (CharSequence text,DialogInterface.OnClickListener listener)
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您按下OK按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        //設定Negative按鈕資料
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //按下按鈕時顯示快顯
                Toast.makeText(MainActivity.this, "您按下Cancel按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("Wait", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "您按下Wait按鈕", Toast.LENGTH_SHORT).show();
            }
        });
        //利用Builder的create()方法建立AlertDialog
        return builder.create();
    }
}
