package com.huika.hkmall.ui.index.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.huika.hkmall.R;
import com.huika.hkmall.hkinterface.IServiceClickCallback;
import com.huika.hkmall.ui.model.HKServiceBean;
import com.huika.hkmall.ui.model.ServiceAdvBean;
import com.huika.lib.core.ext.adapter.layoutmanager.FullyGridLayoutManager;
import com.huika.lib.core.ext.widget.convenientbanner.CBViewHolderCreator;
import com.huika.lib.core.ext.widget.convenientbanner.ConvenientBanner;
import java.util.ArrayList;

/**
 * Created by jixionghui on 2015/12/23.
 */
public class ServiceListAdapter
    extends RecyclerView.Adapter<ServiceListAdapter.ServiceFirstViewHolder> {

  public static final int TYPE_HEADER = 0;
  public static final int TYPE_NORMAL = 1;
  private ArrayList<HKServiceBean> mServiceDataList;
  private Context mContext;
  private IServiceClickCallback mIServiceClickCallback;
  private View mHeaderView;
  private ArrayList<ServiceAdvBean> mBannerDataList;
  private final float PIC_PROPORTION = 2.56f;

  /***
   * @param mServiceItermCallback 服务选项点击回调
   */
  public ServiceListAdapter(Context mContext, ArrayList<HKServiceBean> mServiceDataList,
      IServiceClickCallback mServiceItermCallback) {

    this.mContext = mContext;
    this.mServiceDataList = mServiceDataList;
    this.mIServiceClickCallback = mServiceItermCallback;
  }

  @Override public ServiceFirstViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ServiceFirstViewHolder holder = null;
    if (null != mHeaderView && viewType == TYPE_HEADER) {
      holder = new ServiceFirstViewHolder(mHeaderView);
    } else {
      View view = View.inflate(mContext, R.layout.item_service_first, null);
      holder = new ServiceFirstViewHolder(view);
    }
    return holder;
  }

  @Override public void onBindViewHolder(ServiceFirstViewHolder holder, int position) {
    if (TYPE_HEADER == getItemViewType(position)) {
      ConvenientBanner convenientBanner =
          (ConvenientBanner) mHeaderView.findViewById(R.id.advertisement_convenient_banner);
      DisplayMetrics dm = new DisplayMetrics();
      ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);
      int width = dm.widthPixels;//宽度height = dm.heightPixels ;/
      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
              (int) (width / PIC_PROPORTION));
      convenientBanner.setLayoutParams(params);
      convenientBanner.setPages(new CBViewHolderCreator<ServiceBannerHolderView>() {
        @Override public ServiceBannerHolderView createHolder() {
          return new ServiceBannerHolderView(mIServiceClickCallback);
        }
      }, mBannerDataList).setPageIndicator(
          new int[] { R.drawable.common_dot_normal, R.drawable.common_dot_selected })
          //设置翻页的效果，不需要翻页效果可用不设，效果形式需要注意ConvenientBanner中反射包名
          .setPageTransformer(ConvenientBanner.Transformer.AccordionTransformer);
    } else {
      if (null == mHeaderView){
        onBindNormalViewHolder(holder, position);
      }else{
        onBindNormalViewHolder(holder, position-1);
      }

    }
  }

  private void onBindNormalViewHolder(ServiceFirstViewHolder holder, int position) {
    HKServiceBean serviceBean = mServiceDataList.get(position);
    holder.serviceTypeNameTextView.setText(serviceBean.typeName);
    holder.serviceTypeGridView.setLayoutManager(
        new FullyGridLayoutManager(mContext, serviceBean.num));
    SerivceListGridViewAdapter mGridViewAdapter =
        new SerivceListGridViewAdapter(mContext, serviceBean.serviceArray, serviceBean.num,
            mIServiceClickCallback);
    holder.serviceTypeGridView.setAdapter(mGridViewAdapter);
  }

  public void addHeadView(View mHeaderView, ArrayList<ServiceAdvBean> bannerDataList) {
    if (null == bannerDataList || bannerDataList.isEmpty()) return;
    this.mHeaderView = mHeaderView;
    mBannerDataList = bannerDataList;
    notifyDataSetChanged();
  }

  @Override public int getItemViewType(int position) {
    if (null == mHeaderView) return TYPE_NORMAL;
    if (0 == position) return TYPE_HEADER;
    return TYPE_NORMAL;
  }

  @Override public int getItemCount() {
    if (null == mHeaderView) {
      return null != mServiceDataList ? mServiceDataList.size() : 0;
    } else {
      return (null != mServiceDataList ? mServiceDataList.size() : 0) + 1;
    }
  }

  class ServiceFirstViewHolder extends RecyclerView.ViewHolder {
    private TextView serviceTypeNameTextView;
    private RecyclerView serviceTypeGridView;

    public ServiceFirstViewHolder(View itemView) {
      super(itemView);
      if (mHeaderView == itemView) return;
      serviceTypeNameTextView = (TextView) itemView.findViewById(R.id.service_type_name_textview);
      serviceTypeGridView = (RecyclerView) itemView.findViewById(R.id.service_type_gridview);
    }
  }
}
