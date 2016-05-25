package sx.xss.diary4heart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;
import sx.xss.adapter.RecycleViewAdapter;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initToolBar(toolbar, "觅心日记");

        recycleview.setHasFixedSize(true);
        recycleview.setLayoutManager(new LinearLayoutManager(this));
        recycleview.setAdapter(new RecycleViewAdapter(MainActivity.this));

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
}
