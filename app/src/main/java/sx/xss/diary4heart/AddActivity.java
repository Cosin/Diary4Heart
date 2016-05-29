package sx.xss.diary4heart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dog.debug.hmlite.HMLite;

/**
 * Created by Hm on 2016/5/27.
 */

public class AddActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.content_tv)
    AppCompatEditText content_tv;
    @BindView(R.id.emotion_tv)
    AppCompatEditText emotionTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);

        initToolBar(toolbar, "添加", false);
    }

    /**
     * 初始化Toolbar
     *
     * @param toolbar
     * @param title
     * @param has_icon
     */
    public void initToolBar(Toolbar toolbar, String title, boolean has_icon) {
        toolbar.setTitle(title);
        if (has_icon)
            toolbar.setLogo(R.mipmap.heart);
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.submit)
    public void onClick() {
        String content = content_tv.getText().toString();
        String emotion = emotionTv.getText().toString();
        HMLite hm = new HMLite(this, "diary4heart.db", R.raw.diary4heart);
        hm.init();
        hm.insert("insert into d4h_content() values(%s,%s)",new String[]{content,emotion});
    }
}
