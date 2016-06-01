package sx.xss.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import dog.debug.hmlite.HMLite;
import sx.xss.diary4heart.R;

/**
 * Created by Hm on 2016/5/25.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.CustomViewHolder> {

    public Context context;
    public List<Map<String, String>> list;


    public RecycleViewAdapter(Context context, List<Map<String, String>> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomViewHolder holder = new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_recycleview, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final CustomViewHolder holder, int position) {
        holder.content.setText(list.get(position).get("content"));
        holder.time.setText(list.get(position).get("create_time"));
        holder.pid.setText(list.get(position).get("id"));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                removeItem(holder.getLayoutPosition(),holder.pid.getText().toString());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItem(int position, Map<String, String> item) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public void removeItem(final int position, final String pid) {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(context);
        dialogBuilder.withTitle("删除")
                .withMessage("你确定要删除这条？")
                .withDialogColor("#66b1d5")
                .withButton1Text("OK")
                .withButton2Text("NO")
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HMLite hm = new HMLite(context, "diary4heart.db", R.raw.diary4heart);
                        hm.init();
                        hm.delete("DELETE FROM d4h_diary WHERE id = ?",new String[]{pid});
                        list.remove(position);
                        notifyItemRemoved(position);
                        dialogBuilder.dismiss();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                    }
                })
                .show();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content)
        TextView content;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.pid)
        TextView pid;

        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
