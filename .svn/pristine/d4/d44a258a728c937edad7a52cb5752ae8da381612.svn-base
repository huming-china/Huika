package com.huika.hkmall.ui.index.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.huika.hkmall.R;
import com.huika.hkmall.hkinterface.IServiceClickCallback;
import com.huika.hkmall.ui.model.ServiceArrayBean;
import com.huika.lib.core.net.OkHttpManager;
import java.util.ArrayList;

/**
 * Created by jixionghui on 2015/12/23.
 */
public class SerivceListGridViewAdapter extends RecyclerView.Adapter<SerivceListGridViewAdapter.ServiceGridViewHolder> {

  private Context mContext;
  private ArrayList<ServiceArrayBean> mDataList;
  private int mColumnNum;
  private IServiceClickCallback mItemClickListener;

  /**
   *
   * @param mContext
   * @param mDataList
   * @param mColumnNum GridView列数
   * @param mItemClickListener GirdView 点击事件的回调
   */
  public SerivceListGridViewAdapter(Context mContext, ArrayList<ServiceArrayBean> mDataList,int mColumnNum,IServiceClickCallback mItemClickListener){
    this.mContext = mContext;
    this.mDataList = mDataList;
    this.mColumnNum = mColumnNum;
    this.mItemClickListener = mItemClickListener;
  }

  @Override public ServiceGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(mContext,R.layout.item_gridview_service_list,null);
    ServiceGridViewHolder holder = new ServiceGridViewHolder(view);
    return holder;
  }

  @Override public void onBindViewHolder(ServiceGridViewHolder holder, final int position) {
    if (position + 1 > mDataList.size()) {
      return;
    }

    final ServiceArrayBean serviceBean = mDataList.get(position);

    OkHttpManager.displayImage(holder.mServiceLogoImageView,serviceBean.iconImg);

    if(serviceBean.appConfigs.openType==2){
      holder.mServiceNameTextView.setText("");
    }else{
      holder.mServiceNameTextView.setText(serviceBean.serviceName);
    }

    if (serviceBean.activeType.equals("1")) {
      holder.newOrHotImageView.setImageResource(R.drawable.icon_hot);
      holder.newOrHotImageView.setVisibility(View.VISIBLE);
    } else if (serviceBean.activeType.equals("2")) {
      holder.newOrHotImageView.setImageResource(R.drawable.icon_new);
      holder.newOrHotImageView.setVisibility(View.VISIBLE);
    } else {
      holder.newOrHotImageView.setVisibility(View.INVISIBLE);
    }
    holder.rootView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if(position < mDataList.size()){
          mItemClickListener.onServiceItemClick(serviceBean);
        }
      }
    });
  }

  @Override public int getItemCount() {
    if (mDataList.size()%mColumnNum==0){
      return mDataList.size();
    }else{
      return (1 + mDataList.size()/mColumnNum) * mColumnNum;
    }
  }

  class ServiceGridViewHolder extends RecyclerView.ViewHolder {
    private ImageView mServiceLogoImageView;
    private TextView mServiceNameTextView;
    private ImageView newOrHotImageView;
    private View rootView;

    ServiceGridViewHolder(View view){
      super(view);
      rootView = view.findViewById(R.id.service_root);
      newOrHotImageView =(ImageView) view.findViewById(R.id.imageview_new_hot_icon);
      mServiceLogoImageView = (ImageView)view.findViewById(R.id.imageview_logo);
      mServiceNameTextView = (TextView)view.findViewById(R.id.textview_content);
    }
  }
}
