package sx.xss.diary4heart;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
import java.util.List;
import java.util.Map;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dog.debug.hmlite.HMLite;
import sx.xss.adapter.RecycleViewAdapter;
import sx.xss.utils.Copyer;
import sx.xss.utils.T;

public class MainActivity extends AppCompatActivity {

    //region 初始化控件ID
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.add_post)
    FloatingActionButton add_post;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //region 复制数据库到本地
        Copyer c = new Copyer(this);
        c.copyer();
        //endregion

        //region 获取列表内容
        final HMLite hm = new HMLite(this, "diary4heart.db", R.raw.diary4heart);
        hm.init();
        List<Map<String, String>> list = hm.select("select * from d4h_diary order by id desc", null);
        //endregion

        initToolBar(toolbar, "觅心日记");

        //region RecycleView初始化
        recycleview.setHasFixedSize(true);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        final RecycleViewAdapter adapter = new RecycleViewAdapter(MainActivity.this, list);
        recycleview.setAdapter(adapter);
        //endregion

        //region 下拉刷新
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                List<Map<String, String>> list = hm.select("select * from d4h_diary order by id desc", null);
                recycleview.setAdapter(new RecycleViewAdapter(MainActivity.this, list));
                T.showShort(MainActivity.this,"已更新列表");
                refresh.setRefreshing(false);
            }
        });
        //endregion

    }

    //region OnResume
    @Override
    protected void onResume() {
        super.onResume();
        HMLite hm = new HMLite(this, "diary4heart.db", R.raw.diary4heart);
        hm.init();
        List<Map<String, String>> list = hm.select("select * from d4h_diary order by id desc", null);
        recycleview.setAdapter(new RecycleViewAdapter(MainActivity.this, list));
    }
    //endregion

    //region 初始化菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    //endregion

    //region 初始化Toolbar
    public void initToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        toolbar.setLogo(R.mipmap.heart);
        setSupportActionBar(toolbar);
    }
    //endregion

    //region 添加按钮点击事件
    @OnClick(R.id.add_post)
    public void onClick() {
        Intent i = new Intent(this, AddActivity.class);
        startActivity(i);
    }
    //endregion
}
