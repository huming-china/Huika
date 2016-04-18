package com.huika.hkmall.ui.index.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.huika.hkmall.R;
import com.huika.hkmall.ui.model.OneClassifyBean;

import java.util.ArrayList;

/**
 * Created by huming on 2015/11/24.
 */
public class NavigateShopAdapter extends RecyclerView.Adapter<NavigateShopAdapter.ViewHolder>{
    private ArrayList<OneClassifyBean> beans;
    private int clickIndex=0;
    private AdapterView.OnItemClickListener itemClickListener;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.index_tab_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }
    public void setGroup(ArrayList<OneClassifyBean> beans){
        this.beans=beans;
        System.out.println("set setGroup ");
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position) {
        OneClassifyBean item =beans.get(position);
        viewHolder.tvTabTitle.setText(item.rootName);
        if(clickIndex==position){
            viewHolder.tvTabTitle.setTextColor(viewHolder.tvTabTitle.getContext().getResources().getColor(R.color.color_df3d3e));
            TextPaint tp = viewHolder.tvTabTitle.getPaint();
            tp.setFakeBoldText(true);

        }else{
            viewHolder.tvTabTitle.setTextColor(viewHolder.tvTabTitle.getContext().getResources().getColor(R.color.color_333333));
            TextPaint tp = viewHolder.tvTabTitle.getPaint();
            tp.setFakeBoldText(false);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIndex=position;
                notifyDataSetChanged();
                if(itemClickListener!=null)
                    itemClickListener.onItemClick(null,v,position,0);
            }
        });
    }

   public OneClassifyBean getItem(int position){
        return beans.get(position);
    }

    @Override
    public int getItemCount() {
        if(beans==null){
            return 0;
        }
        return (beans.size() > 13) ? 13 : beans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        /**商品图片*/
        TextView tvTabTitle;
        View itemView;
        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView=itemView;
            tvTabTitle = (TextView) itemView.findViewById(R.id.tv_tab_tile);
        }
    }
    public void setOnItemClickListener(AdapterView.OnItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
}
