package com.bw.listviewdome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ListView mListView;
    private List<Data> mList;
    private MyAdapter adapter;
    private boolean isHide = false;
    private Button mAll,mNo,mHide;
//    private String[] arr = {"刘东东0","刘东东1","刘东东2","刘东东3","刘东东4","刘东东5","刘东东6","刘东东7","刘东东8","刘东东9",
//            "刘东东10","刘东东11","刘东东12","刘东东13","刘东东14","刘东东15","刘东东16","刘东东17","刘东东18","刘东东19",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }


    private void initData() {
        mList = new ArrayList<Data>();
        for (int i=0;i<30;i++){
            Data data = new Data();
            data.index = i;
            data.text = "郭涛大帅哥"+i;

            mList.add(data);
        }

    }

    private void initView() {
        mListView = (ListView) findViewById(R.id.listView);
        mAll = (Button) findViewById(R.id.btn_all);
        mNo = (Button) findViewById(R.id.btn_no);
        mHide = (Button) findViewById(R.id.btn_hide);

        ((ScrollView)findViewById(R.id.scrollView)).smoothScrollTo(0,0);

        adapter = new MyAdapter(mList,this,false);
        mListView.setAdapter(adapter);

        mAll.setOnClickListener(this);
        mNo.setOnClickListener(this);
        mHide.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_all:
                check(true);
                break;
            case R.id.btn_no:
                check(false);
                break;
            case R.id.btn_hide:
                isHide = isHide==true?false:true;
                hide(isHide);
                break;

        }

    }
    private void hide(boolean isHide) {
        Button btn = (Button)findViewById(R.id.btn_hide);
        if(isHide)
            btn.setText("取消隐藏");
        else
            btn.setText("隐藏已选");

        adapter.setHide(isHide);
        adapter.check();
    }


    private void check(boolean flag) {
        for (Data data: mList)
            data.isCheck = flag;
        adapter.check();
    }

}
