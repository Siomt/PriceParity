package com.zhouchatian.priceparity;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.zhouchatian.priceparity.bean.ParityBean;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText keyWord;
    private ListView listView;
    private Button search;
    List<ParityBean> list = new ArrayList();
    private Activity mActivity = MainActivity.this;
    private String wordStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) this.findViewById(R.id.lv);
        keyWord = (EditText) this.findViewById(R.id.keyword_et);
        search = (Button) this.findViewById(R.id.search_btn);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(mActivity,ArticleActivity.class);
//                intent.putExtra("url",list.get(position).getUrl());
//                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordStr= keyWord.getText().toString();
                MainActivity.LoadHtml loadHtml = new MainActivity.LoadHtml();
                loadHtml.execute();
            }
        });

    }
    //异步获取信息
    private class LoadHtml extends AsyncTask<String, String, List<ParityBean>> {
        @Override
        protected List<ParityBean> doInBackground(String... params) {
            try {
                Connection connect = HttpConnection.connect(Constans.JD_URL + wordStr);
                connect.timeout(3000);
                connect.header("Accept-Encoding", "gzip,deflate,sdch");
                connect.header("Connection", "close");
                connect.validateTLSCertificates(false);
                System.setProperty ("jsse.enableSNIExtension", "false");
                connect.execute();
                Log.d("html", connect.get().html());
                Document content = Jsoup.parse(connect.get().html().toString());

            } catch (IOException e) {
                e.printStackTrace();
            }

            return list;
        }

        @Override
        protected void onPostExecute(List result) {
            super.onPostExecute(result);
//            if(mActivity == null){
//                mActivity = MainActivity.this;
//            }
//            AdapterList adapterList = new AdapterList(result , MainActivity.this);
//            listView.setAdapter(adapterList);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

    }
}
