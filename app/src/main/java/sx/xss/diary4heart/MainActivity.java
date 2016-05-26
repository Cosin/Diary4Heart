package sx.xss.diary4heart;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

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

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.add_post)
    FloatingActionButton add_post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Copyer c = new Copyer(this);
        c.copyer();

        HMLite hm = new HMLite(this, "diary4heart.db", R.raw.diary4heart);
        hm.init();
        List<Map<String, String>> list = hm.select("select * from d4h_diary order by id desc", null);

        initToolBar(toolbar, "觅心日记");

        recycleview.setHasFixedSize(true);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(new RecycleViewAdapter(MainActivity.this, list));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void initToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        toolbar.setLogo(R.mipmap.ic_launcher);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.add_post)
    public void onClick() {
        T.show(MainActivity.this,"lalalala",1000);
    }
}
