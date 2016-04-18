package com.huika.hkmall.ui.index.fragment;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.huika.hkmall.R;
import com.huika.hkmall.config.UrlConstants;
import com.huika.hkmall.hkinterface.IServiceClickCallback;
import com.huika.hkmall.ui.index.adapter.ServiceListAdapter;
import com.huika.hkmall.ui.model.HKServiceBean;
import com.huika.hkmall.ui.model.ServiceAdvBean;
import com.huika.hkmall.ui.model.ServiceAppConfig;
import com.huika.hkmall.ui.model.ServiceArrayBean;
import com.huika.lib.core.ext.adapter.layoutmanager.FullyLinearLayoutManager;
import com.huika.lib.core.ext.utils.ToastMsg;
import com.huika.lib.core.ext.widget.pulltorefresh.PullableLayout;
import com.huika.lib.core.ext.widget.pulltorefresh.PullableUtil;
import com.huika.lib.core.model.bean.RequestResult;
import com.huika.lib.core.net.OkHttpManager;
import com.huika.lib.core.ui.fra.BaseFragment;
import com.squareup.okhttp.Request;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 服务模块首页
 * Created by jixionghui on 2015/12/22.
 */
public class HKServiceFragment extends BaseFragment implements IServiceClickCallback {

  private PullableLayout mServicePullableLayout;
  private RecyclerView mServiceRecylerListView;
  private ServiceListAdapter mServiceListAdapter;
  private ArrayList<HKServiceBean> mDataList = new ArrayList<>();

  @Override
  protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
    return inflater.inflate(R.layout.fragment_my_service, container, false);
  }

  @Override
  protected void initWidget(View parentView){
    mServicePullableLayout = (PullableLayout)parentView.findViewById(R.id.service_pullablelayout);
    mServicePullableLayout.setRefreshModel(PullableUtil.DIRECTION.HEAD_ONLY);

    //设置监听器
    mServicePullableLayout.setOnRefreshListener(new PullableLayout.OnRefreshListener() {
      @Override public void onRefresh(PullableLayout pullableLayout) {
        mHandler.sendEmptyMessageDelayed(1, 3000);
        getetServiceAdvertisment();
        getServiceList();
      }

      @Override public void onLoadMore(PullableLayout pullableLayout) {
        mHandler.sendEmptyMessageDelayed(2, 3000);
      }
    });
    mServiceRecylerListView = (RecyclerView)parentView.findViewById(R.id.service_recycler_listview);
    mServiceRecylerListView.setLayoutManager(new FullyLinearLayoutManager(getActivity()));
    mServiceListAdapter = new ServiceListAdapter(HKServiceFragment.this.getActivity(),mDataList,HKServiceFragment.this);
    mServiceRecylerListView.setAdapter(mServiceListAdapter);
    getetServiceAdvertisment();
    getServiceList();
  }

  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      if (msg.what == 1) {
        //刷新成功关闭
        mServicePullableLayout.refreshFinish(PullableLayout.SUCCEED);
      } else if (msg.what == 2) {

        mServicePullableLayout.loadmoreFinish(PullableLayout.SUCCEED);
      }
    }
  };


  private void getetServiceAdvertisment(){

    String uuid = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
    //loginSource	来源 1-网站 3-android 5-ios
    HashMap<String, String> param = new HashMap<>();
    param.put("loginSource", "3");
    param.put("userId", "0");
    param.put("mac", uuid);

    OkHttpManager.postAsyn(UrlConstants.GET_SERVICE_BANNER, param,
        new OkHttpManager.ResultCallback<RequestResult<ArrayList<ServiceAdvBean>>>() {
          @Override public void onError(Request request, Exception e) {
            ToastMsg.showToastMsg(HKServiceFragment.this.getActivity(), "获取BANNER 失败");
          }

          @Override public void onResponse(RequestResult<ArrayList<ServiceAdvBean>> response) {
            if (null != response && 1 == response.flag && null != response.getRs()) {
              View headerView = View.inflate(HKServiceFragment.this.getActivity(),
                  R.layout.item_header_hk_service, null);
              mServiceListAdapter.addHeadView(headerView, response.getRs());
            }
          }
        });
  }


  private void getServiceList(){
    String uuid = Settings.Secure.getString(getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
    HashMap<String,String> params = new HashMap<>();
    params.put("userId","0");
    params.put("mac", uuid);
    //loginSource	来源 1-网站 3-android 5-ios
    params.put("loginSource", "3");
    OkHttpManager.postAsyn(UrlConstants.GET_SERVICE_LIST_V1, params,
        new OkHttpManager.ResultCallback<RequestResult<ArrayList<HKServiceBean>>>() {
          @Override public void onError(Request request, Exception e) {

          }

          @Override public void onResponse(RequestResult<ArrayList<HKServiceBean>> response) {
            if (null != response && 1 == response.flag && null != response.getRs()) {
              mDataList.clear();
              mDataList.addAll(removeHideSerive(response.getRs()));
              mServiceListAdapter.notifyDataSetChanged();
            }
          }
        });
  }

  @Override public void onServiceItemClick(ServiceArrayBean serviceBean) {
    ToastMsg.showToastMsg(getActivity(), serviceBean.serviceName);

  }

  @Override public void onServiceBannerClick(ServiceAdvBean serviceAdvBean) {
    Toast.makeText(getActivity(), "点击了 " + serviceAdvBean.service.serviceName, Toast.LENGTH_SHORT).show();
  }

  /**
   * 移除低于服务列表当中低于当前显示版本的服务选项
   * @param serivceList
   * @return
   */

  private List<HKServiceBean> removeHideSerive(List<HKServiceBean> serivceList){
    String appCurrentVersion =  getCurrentVersionName();
    List<HKServiceBean> resultSerivceList = new ArrayList<HKServiceBean>();
    for (HKServiceBean bean :serivceList){
      ArrayList<ServiceArrayBean> showSerivceList = new ArrayList<ServiceArrayBean>();
      for(ServiceArrayBean serviceArrayBean :  bean.serviceArray){
        ServiceAppConfig appConfig = serviceArrayBean.appConfigs;
        if (showVersionCompare(appConfig,appCurrentVersion)){
          showSerivceList.add(serviceArrayBean);
        }
      }
      bean.serviceArray = showSerivceList;
      if ( bean.serviceArray.size()>0){   //当栏目不存在栏目选项的时候该栏目不显示
        resultSerivceList.add(bean);
      }
    }
    return resultSerivceList;
  }

  /**
   * 获取APP当前的版本
   * @return
   */
  private String getCurrentVersionName() {
    PackageManager pm = getActivity().getPackageManager();
    // getPackageName()是你当前类的包名，0代表是获取版本信息
    try {
      PackageInfo packInfo = pm.getPackageInfo(getActivity().getPackageName(), 0);
      return packInfo.versionName;
    } catch (Exception e) {
    }
    return "1.0";
  }

  /**
   * 比较版本号
   * @param appConfig  后台配置服务配置信息
   * @param currentVersion  APP当前版本号
   * @return false 表示过滤,不显示在UI中, true 表示显示
   * @author jixionghui
   */
  private boolean showVersionCompare( ServiceAppConfig appConfig,String currentVersion){
    if(null == appConfig){  //当后台没配置 config字段
      return false;
    }
    if ( appConfig.isShowOnAndroid == 0){// 0在Android不显示   1 在android端显示
      return  false;
    }
    String  appConfigVersion  = appConfig.androidShowVersion;
    if(null == appConfigVersion || appConfigVersion.isEmpty()){ //config 中未配置则显示
      return true;
    }

    int compareResult = appConfigVersion.compareTo(currentVersion);
    if (compareResult>0){//后台配置版本大于当前版本则不显示
      return  false;
    }


    return true;
  }
}
