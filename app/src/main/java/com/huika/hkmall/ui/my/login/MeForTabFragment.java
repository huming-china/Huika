package com.huika.hkmall.ui.my.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.huika.hkmall.BuildConfig;
import com.huika.hkmall.GlobalApplication;
import com.huika.hkmall.R;
import com.huika.hkmall.ui.api.HuiKaRetrofitApi;
import com.huika.hkmall.ui.model.MoneyTools;
import com.huika.hkmall.ui.model.OtherOrders;
import com.huika.hkmall.ui.model.UserModel;
import com.huika.hkmall.ui.model.WealthInfo;
import com.huika.hkmall.ui.views.RoundImageView;
import com.huika.lib.core.ext.utils.ToastMsg;
import com.huika.lib.core.ext.widget.pulltorefresh.PullableLayout;
import com.huika.lib.core.model.bean.RequestResult;
import com.huika.lib.core.model.bean.ResultCallback;
import com.huika.lib.core.security.RsaHelper;
import com.huika.lib.core.ui.fra.BaseFragment;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author shicm
 * @description：我的页面
 * @date 2015年5月25日 下午4:39:34
 */

public class MeForTabFragment extends BaseFragment {
  private PullableLayout ptrScroll;
  private RoundImageView mRoundImg;
  private TextView tvNickName;
  private TextView guide_huika_tv;
  private ImageView guide_huika_iv;
  private ImageView distributorsLogo;
  private ImageView iv_mycode;
  private TextView orderPayNumTv;
  private TextView orderReceiveNumTv;
  private TextView orderCommentNumTv;
  private TextView my_login_huixin_name;
  private ImageView my_agent_logo_iv;
  private TextView mHmBalance;// 惠米余额
  private TextView mSxBalance;// 授信额度
  private TextView mHfBalance;// 惠粉总收益
  private TextView join_favorites;//收藏夹
  private WealthInfo info;
  private String PackageName = "";// 包名
  private String PackageFlag = "";// html5参数（是否在本机已安装过0，未安装 1，ios 安装，2 android
  // 安装）
  private TextView upgradeTv;// 升级为惠粉（2015年8月10日迭代增加）
  private boolean isFirstComeIn = true;
  private TextView stateTv;// 惠粉借款状态
  private TextView loanTv;// 是借款还是还款TextView
  private TextView otherOrderTv;// 其它订单提示语
  private RelativeLayout relative_fra_me_menoy;// 现金卷
  private TextView hfff_tv_menoy;// 现金券的数量
  private boolean isGetOrders = false;
  private OtherOrders mOrders;
  private LinearLayout my_article_tv;
  private UserModel user=new UserModel();
  /** 用EventBus，通知购物车页面进行刷新 */
  public void onEventMainThread(ProductStateEvent event) {
    getUserInfo();
  }

  ///** 用EventBus,刷新用户余额 */
  //public void onEventMainThread(WealthChangeEvent event) {
  //  if (LoginHelper.getInstance(getActivity()).isLogin()) {
  //    initUserModelView();
  //    executeGetUserBalance();
  //  } else {
  //    initUnLoginInstate();
  //  }
  //  if (!isGetOrders) {
  //    executeGetOtherOrders();
  //  }
  //}

  ///** 退出登录后显示状态 */
  //public void onEventMainThread(LoginOutEvent event) {
  //  initUnLoginInstate();
  ////}

  ///** 升级成功惠粉后改变相应状态 */
  //public void onEventMainThread(UpGradeSuccessEvent event) {
  //  if (event.isSuccess() && LoginHelper.getInstance(getActivity()).isLogin()) {
  //    executeGetUserInfo();
  //    //HKPreferenceUtils.getInstance().saveHfType(1);
  //    //HKEventManager.getInstance().postMyInfoChangeEvent();
  //    distributorsLogo.setVisibility(View.VISIBLE);
  //    upgradeTv.setVisibility(View.GONE);
  //    distributorsLogo.setImageResource(R.drawable.me_distribution_logo);
  //  }
  //}

  @Override
  protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
    //EventBus.getDefault().register(this);
    return inflater.inflate(R.layout.fra_me_for_tab_layout, container, false);
  }

  @Override protected void initWidget(View parentView) {
    ptrScroll = (PullableLayout) parentView.findViewById(R.id.ptr_scroll);
    mRoundImg = (RoundImageView) parentView.findViewById(R.id.my_login_state_avater);
    mRoundImg.setOnClickListener(this);
    tvNickName = (TextView) parentView.findViewById(R.id.my_login_state_nick);
    distributorsLogo = (ImageView) parentView.findViewById(R.id.my_distributors_logo_iv);
    join_favorites=(TextView)parentView.findViewById(R.id.join_favorites);
    iv_mycode = (ImageView) parentView.findViewById(R.id.iv_mycode);
    guide_huika_iv = (ImageView) parentView.findViewById(R.id.guide_huika_iv);
    guide_huika_tv = (TextView) parentView.findViewById(R.id.guide_huika_tv);
    orderPayNumTv = (TextView) parentView.findViewById(R.id.my_order_pay_num_tv);
    orderReceiveNumTv = (TextView) parentView.findViewById(R.id.my_order_receive_num_tv);
    orderCommentNumTv = (TextView) parentView.findViewById(R.id.my_order_comment_num_tv);
    my_login_huixin_name = (TextView) parentView.findViewById(R.id.my_login_huixin_name);
    my_agent_logo_iv = (ImageView) parentView.findViewById(R.id.my_agent_logo_iv);
    mHmBalance = (TextView) parentView.findViewById(R.id.hm_tv_balance);
    mSxBalance = (TextView) parentView.findViewById(R.id.sx_tv_balance);
    mHfBalance = (TextView) parentView.findViewById(R.id.hf_tv_balance);
    upgradeTv = (TextView) parentView.findViewById(R.id.upgrade_hf_tv);
    stateTv = (TextView) parentView.findViewById(R.id.sx_tv_state);
    loanTv = (TextView) parentView.findViewById(R.id.me_loan_tv);
    otherOrderTv = (TextView) parentView.findViewById(R.id.my_other_tv);
    my_article_tv = (LinearLayout) parentView.findViewById(R.id.my_article_tv);
    relative_fra_me_menoy = (RelativeLayout) parentView.findViewById(R.id.relative_fra_me_menoy);
    hfff_tv_menoy = (TextView) parentView.findViewById(R.id.hfff_tv_menoy);
    parentView.findViewById(R.id.join_favorites).setOnClickListener(this);
    parentView.findViewById(R.id.see_all_order).setOnClickListener(this);
    parentView.findViewById(R.id.my_footprint_tv).setOnClickListener(this);
    parentView.findViewById(R.id.join_huika_tv).setOnClickListener(this);
    parentView.findViewById(R.id.my_order_rl).setOnClickListener(this);
    parentView.findViewById(R.id.my_order_pay_rl).setOnClickListener(this);
    parentView.findViewById(R.id.my_order_receive_rl).setOnClickListener(this);
    parentView.findViewById(R.id.my_order_comment_rl).setOnClickListener(this);
    parentView.findViewById(R.id.my_account_tv).setOnClickListener(this);
    parentView.findViewById(R.id.my_hkmall_adress_tv).setOnClickListener(this);
    parentView.findViewById(R.id.my_hkmall_bank_tv).setOnClickListener(this);
    parentView.findViewById(R.id.my_hkmall_safe_tv).setOnClickListener(this);
    parentView.findViewById(R.id.my_hkmall_hx_enterprise).setOnClickListener(this);
    parentView.findViewById(R.id.hx_local_merchant_rela).setOnClickListener(this);
    parentView.findViewById(R.id.hx_rl_purse).setOnClickListener(this);
    parentView.findViewById(R.id.hxb_rl_treasure).setOnClickListener(this);
    parentView.findViewById(R.id.cyb_rl_carve).setOnClickListener(this);
    parentView.findViewById(R.id.other_linearlayout_order).setOnClickListener(this);
    parentView.findViewById(R.id.sx_rl_balance).setOnClickListener(this);
    my_article_tv.setOnClickListener(this);
    guide_huika_iv.setOnClickListener(this);
    guide_huika_tv.setOnClickListener(this);
    upgradeTv.setOnClickListener(this);
    tvNickName.setOnClickListener(this);
    iv_mycode.setOnClickListener(this);
    relative_fra_me_menoy.setOnClickListener(this);
    ptrScroll.setOnRefreshListener(new PullableLayout.OnRefreshListener() {
      @Override public void onRefresh(PullableLayout pullableLayout) {
        mHandler.sendEmptyMessageDelayed(1, 3000);
        getUserInfo();
      }
      @Override public void onLoadMore(PullableLayout pullableLayout) {
        mHandler.sendEmptyMessageDelayed(2, 3000);
      }
    });
    //options = new DisplayImageOptions.Builder().showImageOnLoading(
    //    R.drawable.icon_default_avater) // resource
    //    .showImageForEmptyUri(R.drawable.icon_default_avater) // resource
    //    .showImageOnFail(R.drawable.icon_default_avater) // resource or
    //    .cacheInMemory(true)// 开启内存缓存
    //    .cacheOnDisk(true) // 开启硬盘缓存
    //    .resetViewBeforeLoading(false).build();
    super.initWidget(parentView);
  }
  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      if (msg.what == 1) {
        //刷新成功关闭
        ptrScroll.refreshFinish(PullableLayout.SUCCEED);
      } else if (msg.what == 2) {
        ptrScroll.loadmoreFinish(PullableLayout.SUCCEED);
      }
    }
  };
  //@Override public void onResume() {
  //  initLoginState();
  //  super.onResume();
  //}

  // 初始化我的财富余额
  private void setInitBalance() {
    mHmBalance.setText("0.00");
    mHfBalance.setText("0.00");
    mSxBalance.setText("0");
    hfff_tv_menoy.setText("0");
  }

  //private void initLoginState() {
  //  if (GlobalApplication.getInstance().isLogin()) { // 已经登录
  //    executeGetUserInfo();
  //    executeGetUserBalance();
  //    if (!isGetOrders) {
  //      executeGetOtherOrders();
  //    }else {
  //      initUnLoginInstate();
  //    }
  //  }
  //}

  /** 未登录初始化UI */
  private void initUnLoginInstate() {
    mRoundImg.setImageResource(R.drawable.icon_default_avater);
    tvNickName.setText(R.string.my_login);
    distributorsLogo.setVisibility(View.GONE);
    my_login_huixin_name.setVisibility(View.GONE);
    my_agent_logo_iv.setVisibility(View.GONE);
    upgradeTv.setVisibility(View.GONE);
    stateTv.setVisibility(View.GONE);
    loanTv.setText("申请借款");
    setPopNum(orderPayNumTv, 0);
    setPopNum(orderReceiveNumTv, 0);
    setPopNum(orderCommentNumTv, 0);
    setInitBalance();
  }

  /** 用户UI更新 */
  private void initUserModelView() {
    if (TextUtils.isEmpty(user.imageUrl)) {
      mRoundImg.setImageResource(R.drawable.icon_default_avater);
    } else {
      //ImageLoader.getInstance().displayImage(user.imageUrl, mRoundImg, options);
    }
    my_login_huixin_name.setText("惠信号：" + user.account);
    // TODO 没有昵称，显示惠信号
    if (TextUtils.isEmpty(user.nickName)) {
      tvNickName.setText(user.account); // 设置昵称
    } else {
      tvNickName.setText(user.nickName);
    }
    int hfType = user.hfType;
    distributorsLogo.setVisibility(View.VISIBLE);
    if (hfType == 2) {// 准惠粉标志
      upgradeTv.setVisibility(View.VISIBLE);
      distributorsLogo.setImageResource(R.drawable.me_hf_logo);
    } else {
      upgradeTv.setVisibility(View.GONE);
      distributorsLogo.setImageResource(R.drawable.me_distribution_logo);
    }
    my_agent_logo_iv.setVisibility(user.isDistribution > 1 ? View.VISIBLE : View.GONE);
    if (user.isDistribution == 2) {
      my_agent_logo_iv.setImageResource(R.drawable.agents_district_logo);
    } else if (user.isDistribution == 3) {
      my_agent_logo_iv.setImageResource(R.drawable.agents_city_logo);
    } else if (user.isDistribution == 4) {
      my_agent_logo_iv.setImageResource(R.drawable.agents_provincial_logo);
    } else if (user.isDistribution == 5) {
      my_agent_logo_iv.setImageResource(R.drawable.globe_provincial_logo);
    }
    setPopNum(orderPayNumTv, user.waitPayCount);
    setPopNum(orderReceiveNumTv, user.waitReceiveCount);
    setPopNum(orderCommentNumTv, user.waitComment);
  }

  private void setPopNum(TextView v, int num) {
    v.setVisibility(num <= 0 ? View.GONE : View.VISIBLE);
    v.setText("" + num);
  }

  private void getUserInfo() {
    if (GlobalApplication.getInstance().isLogin()) {
      executeGetUserInfo();
    } else {
      initUnLoginInstate();
    }
  }

  private void executeGetUserInfo() {
    String userId= RsaHelper.encryptDataFromStr("0", RsaHelper.publicKey);
    HuiKaRetrofitApi.getInstance().get().GetUserInfo(userId, BuildConfig.VERSION_CODE,
        new ResultCallback<RequestResult<UserModel>>() {
          @Override public void success(RequestResult<UserModel> result, Response response) {
            // 获取商品的列表
            if (result != null && result.getRs() != null) {
              executeGetUserBalance();
              user = result.getRs();
              initUserModelView();
            } else {
              initUnLoginInstate();
            }
          }
          @Override public void failure(RetrofitError error) {
            System.out.println("失败 class");
          }
        });
  }

  private void executeGetUserBalance() {
    String userId= RsaHelper.encryptDataFromStr("0", RsaHelper.publicKey);
    HuiKaRetrofitApi.getInstance().get().GetUserBalance(userId,
        new ResultCallback<RequestResult<WealthInfo>>() {
          @Override public void success(RequestResult<WealthInfo> result, Response response) {
            // 获取用户余额信息
            if (result != null && result.getRs() != null) {
              // ToastMsg.showToastMsg(getActivity(), "刷新成功");
              info = result.getRs();
              setBalanceInfo(info);
            } else {
              ToastMsg.showToastMsg(getActivity(), "获取用户数据失败");
              setInitBalance();
            }
          }

          @Override public void failure(RetrofitError error) {
          }
        });
  }

  private void setBalanceInfo(WealthInfo info) {// 授信额度状态（-1未借款0 失败 1 审核中 2
    // 还款中 3还款完成）
    stateTv.setVisibility(View.GONE);
    hfff_tv_menoy.setText(info.getCouponCount());
    if (info.getCreditStatus() == 2) {
      loanTv.setText("还款中");
      stateTv.setText("(还款中)");
    } else if (info.getCreditStatus() == 1) {
      stateTv.setText("(审核中)");
      loanTv.setText("借款审核中");
    } else if (info.getCreditStatus() == 3 || info.getCreditStatus() == 0
        || info.getCreditStatus() == -1) {
      loanTv.setText("申请借款");
    } else if (info.getCreditStatus() == 4) {
      loanTv.setText("立即还款");
    }

    if (Double.parseDouble(info.getBalance()) >= 0) {
      if(!TextUtils.isEmpty(info.consumeSubsidy)){
        if(Double.parseDouble(info.consumeSubsidy)<=0){
          mHmBalance.setText(MoneyTools.coreMatchCutMoney(info.getBalance()));
        }else{
          mHmBalance.setText(MoneyTools.coreMatchCutMoney(info.getBalance())+"\n("+MoneyTools.coreMatchCutMoney(info.consumeSubsidy)+")");
        }
      }else{
        mHmBalance.setText(MoneyTools.coreMatchCutMoney(info.getBalance()));
      }
    } else {
      mHmBalance.setText(info.getBalance());
    }
    mSxBalance.setText(getCreditFormat(info.getSurplusCredit()));
      if (Double.parseDouble(info.getBalance()) >= 0) {
        mHfBalance.setText(MoneyTools.coreMatchCutMoney(info.getTotleIncome()));
      } else {
        mHfBalance.setText(info.getTotleIncome());
      }
  }

  private String getCreditFormat(String creditLimit) {
    String format = "";
    if (creditLimit.indexOf(".") > 0) {
      format = creditLimit.substring(0, creditLimit.indexOf("."));
    } else {
      format = creditLimit;
    }
    return format;
  }

  public void onInitShow() {
    if (GlobalApplication.getInstance().isLogin()) {
      //initUserModelView();
      executeGetUserBalance();
    }
    if (!isGetOrders) {
      executeGetOtherOrders();
    }
  }

  private void executeGetOtherOrders() {
    String userId= RsaHelper.encryptDataFromStr("0", RsaHelper.publicKey);
    HuiKaRetrofitApi.getInstance().get().GetOtherOrders(userId,new ResultCallback<RequestResult<OtherOrders>>(){
      @Override
      public void success(RequestResult<OtherOrders> result, Response response) {
        // 获取用户余额信息
        if (result != null && result.getRs() != null) {
          // ToastMsg.showToastMsg(getActivity(), "刷新成功");
          mOrders = result.getRs();
          otherOrderTv.setText(mOrders.getOrderSumName());
        }
      }
      @Override public void failure(RetrofitError error) {
        System.out.println("失败");
      }
    });
  }
}
