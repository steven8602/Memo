package com.example.memo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    Button write_button;
    private ListView m_oListView = null; //private = 자기 클래스에서만 사용가능
    ListAdapter oAdapter;
    ArrayList<ItemData> oData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write_button = findViewById(R.id.write_button); //write 버튼 연결
        oData = new ArrayList<>();   //ItemData 클래스의 객체를 list에 타입으로 설정
        m_oListView = findViewById(R.id.listView); //listview 연결

        m_oListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //position 으로 몇번째 것이 선택됐는지 값을 넘겨준다
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), Main3Activity.class); // main1 에서 main 3으로 이동
                intent.putExtra("memo_number", position);
                startActivity(intent); //액티비티 띄우기
            }
        });

        write_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class); //main1 에서 main 2 이동
                startActivity(intent); //액티비티 띄우기
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        oData = get_memoData();
        oAdapter = new ListAdapter(oData);
        m_oListView.setAdapter(oAdapter);
    }

    ArrayList<ItemData> get_memoData() {
        ArrayList<ItemData> data = new ArrayList<>();

        SharedPreferences sharedPreferences = getSharedPreferences("a", MODE_PRIVATE); //a키에 있는 데이터를 가져오고 없다면 0을 리턴
        int count = sharedPreferences.getInt("count", 0);

        String title = null;
        String content = null;

        for (int i = 0; i < count; i++) {
            title = sharedPreferences.getString("Title" + i, "");
            content = sharedPreferences.getString("Content" + i, "");


            if(title.equals("") && content.equals(""))
                continue;

            Log.d("title", title);
            Log.d("content", content);

            ItemData itemData = new ItemData();
            itemData.strTitle = title;
            itemData.strContent = content;

            data.add(itemData);
        }
        return data;
    }

}


