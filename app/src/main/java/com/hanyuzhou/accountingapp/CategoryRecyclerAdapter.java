package com.hanyuzhou.accountingapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;

    private LinkedList<CategoryResBean> cellList = GlobalUtil.getInstance().costRes;

    public String getSelected() {
        return selected;
    }

    private String selected="";

    public void setOnCategoryClickListener(OnCategoryClickListener onCategoryClickListener) {
        this.onCategoryClickListener = onCategoryClickListener;
    }

    private OnCategoryClickListener onCategoryClickListener;

    public CategoryRecyclerAdapter(Context context){
        GlobalUtil.getInstance().setContext(context);
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        selected = cellList.get(0).title;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cell_category,parent,false);
        CategoryViewHolder myViewHolder = new CategoryViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final CategoryResBean res = cellList.get(position);
        holder.imageView.setImageResource(res.resBlack);
        holder.textView.setText(res.title);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = res.title;
                notifyDataSetChanged();

                if (onCategoryClickListener!=null){
                    onCategoryClickListener.onClikc(res.title);
                }

            }
        });

        if (holder.textView.getText().toString().equals(selected)){
            holder.background.setBackgroundResource(R.drawable.bg_edit_text);
        }
        else {
            holder.background.setBackgroundResource(R.color.colorPrimary);
        }

    }

    public void changeType(RecordBean.RecordType type){
        if (type == RecordBean.RecordType.RECORD_TYPE_EXPENSE){
            cellList = GlobalUtil.getInstance().costRes;
        }else {
            cellList = GlobalUtil.getInstance().earnRes;
        }

        selected = cellList.get(0).title;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return cellList.size();
    }

    public interface OnCategoryClickListener{
        void onClikc(String category);
    }

}

class CategoryViewHolder extends RecyclerView.ViewHolder{

    RelativeLayout background;
    ImageView imageView;
    TextView textView;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        background = itemView.findViewById(R.id.cell_background);
        imageView = itemView.findViewById(R.id.imageView_category);
        textView = itemView.findViewById(R.id.textView_category);
    }
}
