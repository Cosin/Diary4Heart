package sx.xss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sx.xss.diary4heart.R;

/**
 * Created by Hm on 2016/5/25.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.CustomViewHolder> {

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public RecycleViewAdapter(Context context) {
        this.context = context;
    }

    public Context context;


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomViewHolder holder = new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycleview, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        holder.test.setText("333333");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.test)
        TextView test;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
