package com.huika.hkmall.ui.index.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.huika.hkmall.hkinterface.IServiceClickCallback;
import com.huika.hkmall.ui.model.ServiceAdvBean;
import com.huika.lib.core.ext.widget.convenientbanner.CBPageAdapter;
import com.huika.lib.core.net.OkHttpManager;

/**
 * Created by jixionghui on 2015/12/22.
 */
public class ServiceBannerHolderView implements CBPageAdapter.Holder<ServiceAdvBean> {
  private ImageView img;
  private IServiceClickCallback mServiceBannerClickCallback;
  public ServiceBannerHolderView(IServiceClickCallback serviceBannerClickCallback){
    mServiceBannerClickCallback = serviceBannerClickCallback;

  }
  @Override public View createView(Context context) {
    img = new ImageView(context);
    img.setScaleType(ImageView.ScaleType.CENTER_CROP);
    return img;
  }

  @Override public void UpdateUI(final Context context,final int position, final ServiceAdvBean data) {
    //ImageLoader.getInstance().displayImage(data.imagePath, img);
    OkHttpManager.displayImage(img,data.imagePath);
    img.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        if(null != mServiceBannerClickCallback){
          mServiceBannerClickCallback.onServiceBannerClick(data);
        }
      }
    });
  }

}
