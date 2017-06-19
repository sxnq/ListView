package xunqaing.bwie.com.listview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import xunqaing.bwie.com.listview.adapter.CheckBoxAdapter;
import xunqaing.bwie.com.listview.bean.CheckBean;

public class MainActivity extends Activity {

    List<CheckBean> list = new ArrayList<CheckBean>() ;
    private ListView listView;


    boolean checked ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generData();

        listView = (ListView) findViewById(R.id.listview_id);


        //加个头
        View v = View.inflate(MainActivity.this,R.layout.head,null);
        listView.addHeaderView(v);


        final CheckBoxAdapter adapter = new CheckBoxAdapter(list,this);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();

            }
        });


        findViewById(R.id.btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for(int i=0;i<list.size();i++){
                    if(!checked){
                        list.get(i).setIscheck(true);
                    }else {
                        list.get(i).setIscheck(false);
                    }
                }
                adapter.notifyDataSetChanged();

                if(!checked){
                    checked = true;
                }else {
                    checked = false;
                }

            }
        });



        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


                if(scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE ){

                    //滚动停止   如果剩余的小于5个，就加载资源
                    if(mFotalItemCount - mVisibleItemCount - mFirstVisibleItem <= 5){

                        for(int i=51;i<100;i++){
                            CheckBean checkBean = new CheckBean();
                            checkBean.setContent(i+"");
                            list.add(checkBean);
                        }
                        adapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                mFirstVisibleItem = firstVisibleItem;
                mVisibleItemCount = visibleItemCount;
                mFotalItemCount = totalItemCount;

                System.out.println("firstVisibleItem = " + firstVisibleItem);
                System.out.println("visibleItemCount = " + visibleItemCount);
                System.out.println("totalItemCount = " + totalItemCount);
            }
        });
    }

    public  int mFirstVisibleItem ; int mVisibleItemCount; int mFotalItemCount;

    public void generData(){
        for(int i=0;i<50;i++){
            CheckBean checkBean = new CheckBean();
            checkBean.setContent(i+"");
            list.add(checkBean);
        }
    }

}