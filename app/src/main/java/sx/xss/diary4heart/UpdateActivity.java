package sx.xss.diary4heart;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dog.debug.hmlite.HMLite;
import sx.xss.utils.T;
import sx.xss.utils.Tools;

/**
 * Created by Hm on 2016/6/1.
 */

public class UpdateActivity extends AppCompatActivity {

    //region 初始化ID
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.content_tv)
    AppCompatEditText contentTv;
    @BindView(R.id.emotion_tv)
    AppCompatEditText emotionTv;
    public String id;
    //endregion

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        ButterKnife.bind(this);

        //region 初始化item信息
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        HMLite hm = new HMLite(this, "diary4heart.db", R.raw.diary4heart);
        hm.init();
        List<Map<String, String>> item = hm.select("select * from d4h_diary where id = ?",new String[]{id});
        contentTv.setText(item.get(0).get("content"));
        emotionTv.setText(item.get(0).get("emotion"));
        //endregion

        initToolBar(toolbar,"设置");
    }

    //region 初始化Toolbar
    public void initToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
    }
    //endregion

    @OnClick(R.id.submit)
    public void onClick() {
        String content = contentTv.getText().toString();
        String emotion = emotionTv.getText().toString();
        HMLite hm = new HMLite(this, "diary4heart.db", R.raw.diary4heart);
        hm.init();
        try {
            hm.update("update d4h_diary set content = ?,emotion = ?,update_time = ? where id = ?",new String[]{content,emotion, Tools.getTime(),id});
            T.showShort(this,"修改成功！");
        } catch (Exception e) {
            T.showShort(this,"修改失败！");
            e.printStackTrace();
        } finally {
            this.finish();
        }
    }
}
