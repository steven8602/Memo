package com.example.memo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Main3Activity extends AppCompatActivity {
    int memo_number = 0;
    SharedPreferences a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent intent = getIntent();
        memo_number = intent.getIntExtra("memo_number", -1);

        a = getSharedPreferences("a", MODE_PRIVATE);  //데이터 불러오기
    }

    @Override
    protected void onStart() {
        super.onStart();

        TextView textView1 = findViewById(R.id.bt2);
        TextView textView2 = findViewById(R.id.bt3);



        String title1 = a.getString("Title" + memo_number, "");
        String content2 = a.getString("Content" + memo_number, "");

        textView1.setText(title1); //데이터 화면에 보이게 만들기
        textView2.setText(content2);


        Button rewrite = findViewById(R.id.rewrite);
        rewrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //수정 버튼을 누르면 main2로 이동
                Intent intent3 = new Intent(getApplicationContext(), Main2Activity.class); // main3 에서 main 2으로 이동
                intent3.putExtra("memo_number", memo_number);
                startActivity(intent3); //액티비티 띄우기
            }
        });

        Button delete = findViewById(R.id.delete); //버튼 연결
        delete.setOnClickListener(new View.OnClickListener() { //선택된 메모가 삭제  된다
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = a.edit();

                int memo_count = a.getInt("count",0);


                for(int i = memo_number; i < memo_count; i++){
                    editor.putString("Title" + i, a.getString("Title" + (i+1), ""));
                    editor.putString("Content" + i, a.getString("Content" + (i+1), ""));
                }

                editor.putInt("count", memo_count-1);

                editor.commit();
                finish();
            }
        });
    }
}

