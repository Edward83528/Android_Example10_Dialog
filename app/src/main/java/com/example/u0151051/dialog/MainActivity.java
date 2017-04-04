package com.example.u0151051.dialog;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//對話方塊是一種小型視窗，可提示使用者做出決定或輸入額外資訊。 對話視窗並不會佔滿整個螢幕，而且通常是供強制回應事件使用，這種事件會要求使用者必須先完成特定動作，才能繼續下一步。
public class MainActivity extends AppCompatActivity {
    Button btn, btn2, btn3;
    AlertDialog alertDialog1, alertDialog2, alertDialog3;//宣告一個AlertDialog物件接getAlertDialog傳回的AlertDialog
    //宣告一個字串陣列,方便我們等等要將這陣列塞入對話框
    String s[] = {"奶茶", "綠茶", "紅茶"};
    //宣告一個view,方便我們等等要將自訂的Layout塞入對話框
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findview();
    }

    void findview() {
        alertDialog1 = getAlertDialog("這是一個對話框", "請選擇......");
        alertDialog2 = getAlertDialog_List("這是一個清單對話框");
        alertDialog3 = getAlertDialog_our_Layout("這是一個塞入我們自訂的Layout的對話框");
        btn = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(c);
        btn2.setOnClickListener(c);
        btn3.setOnClickListener(c);
    }

    View.OnClickListener c = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button:
                    alertDialog1.show();//按下button啟動對話框
                    break;
                case R.id.button2:
                    alertDialog2.show();
                    break;
                case R.id.button3:
                    alertDialog3.show();
                    break;
            }
        }
    };

    //1.寫一個方法設定基本對話框
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

    //2.寫一個方法建立對話框清單視窗(我們先建一個陣列，並且把字串丟進去AlertDialog，這樣它就會自動變成一個List)
    private AlertDialog getAlertDialog_List(String title) {
        //產生一個Builder物件(要選android app那個)
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //設定Dialog的標題
        builder.setTitle(title);
        //必須使用 setTitle() 為對話方塊設定標題。 如要指定清單列出的項目，請呼叫 setItems() 來傳送陣列
        builder.setItems(s, new DialogInterface.OnClickListener() {
            @Override
            //只要你在onClick處理事件內，使用which參數，就可以知道按下陣列裡的哪一個了
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "你選擇的是" + s[which], Toast.LENGTH_SHORT).show();
            }
        });

        //利用Builder的create()方法建立AlertDialog
        return builder.create();
    }

    //3.寫一個方法建立能塞入我們自訂的Layout對話框
    //載入的介面，當被載入Activity後才可以用findViewById來獲得其中界面的元素
    private AlertDialog getAlertDialog_our_Layout(String title) {
        //產生一個Builder物件(要選android app那個)

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //設定Dialog的標題
        builder.setTitle(title);
        // LayoutInflater的inflate目的是把自己設計xml的Layout轉成View，作用類似於findViewById，它用於一個沒有被載入或者想要動態
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        v = inflater.inflate(R.layout.our, null);
        //設定我們自訂的view
        builder.setView(v);
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //注意底下是v.findViewById
                EditText et = (EditText) v.findViewById(R.id.editText);
                Toast.makeText(MainActivity.this, "你輸入的密碼是:" + et.getText().toString(), Toast.LENGTH_SHORT).show();
                //輸入完按下確定後要把密碼欄清空
                et.setText("");
            }
        });
        //利用Builder的create()方法建立AlertDialog
        return builder.create();
    }


}
