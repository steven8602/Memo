package com.example.memo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Main2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText editText1 = findViewById(R.id.text1);
        final EditText editText2 = findViewById(R.id.text2);

        Intent intent = getIntent();
        final int memo_number = intent.getIntExtra("memo_number",-1); //위치 받기


        SharedPreferences a = getSharedPreferences("a", MODE_PRIVATE);  //데이터 불러오기
        String title1 = a.getString("Title"+memo_number,"");
        String content2 = a.getString("Content"+memo_number,"");

        editText1.setText(title1);
        editText2.setText(content2);

        final SharedPreferences.Editor editor = a.edit();
        final int memo_count = a.getInt("count", 0);

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() { //데이터 저장
            @Override
            public void onClick(View v) { //저장 버튼을 눌렀을 때 데이터 저장
                if(memo_number == -1){
                    editor.putString("Title"+memo_count, editText1.getText().toString());
                    editor.putString("Content"+memo_count, editText2.getText().toString());
                    editor.putInt("count", memo_count+1);
                }else{
                    editor.putString("Title"+memo_number, editText1.getText().toString());
                    editor.putString("Content"+memo_number, editText2.getText().toString());
                }

                editor.commit(); //완료한다.

                finish();
            }
        });
    }


}
