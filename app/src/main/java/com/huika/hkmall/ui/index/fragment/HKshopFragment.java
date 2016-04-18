package com.huika.hkmall.ui.index.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.huika.hkmall.R;
import com.huika.hkmall.ui.api.HuiKaRetrofitApi;
import com.huika.hkmall.ui.index.adapter.NavigateShopAdapter;
import com.huika.hkmall.ui.model.CartChangeNum;
import com.huika.hkmall.ui.model.ClassifyBean;
import com.huika.hkmall.ui.model.OneClassifyBean;
import com.huika.lib.core.model.bean.RequestResult;
import com.huika.lib.core.model.bean.ResultCallback;
import com.huika.lib.core.model.helper.RxBus;
import com.huika.lib.core.security.RsaHelper;
import com.huika.lib.core.ui.fra.BaseFragment;
import java.util.ArrayList;
import java.util.HashMap;
import retrofit.RetrofitError;
import retrofit.client.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/***
 * tab3   购物
 */
public class HKshopFragment extends BaseFragment {
    public final String RX_TAG="RXHKshopFragment";
    private ArrayList<OneClassifyBean>  classifyBeans=new ArrayList<OneClassifyBean>();
    private FragmentManager mFragmentManager;
    private String onepieceUrl;// 配置信息中获取一元拼写的URL
    private String defaultSearch;// 配置信息中获取默认的搜索关键字
    private String realSearchKey;// 配置信息中获取默认的搜索关键字
    private RecyclerView tabRecyclerView;
    private LinearLayoutManager layoutManager;
    private CartChangeNum cartChangeNumBean;//购物车
    private TextView searchTv;
    private ImageView shopCarIv;
    private TextView shopcartNumTv;
    private SharedPreferences.Editor edit;
    private NavigateShopAdapter mNavigateShopAdapter;
    private Fragment ProductSearchfrag;
    private Observable<Object> rxBus;


//    public void onEventMainThread(CartChangeEvent event) {
//        executeGetCartNum();
//    }
//    public void onEventMainThread(NotifyShopFragEvent event) {
//        if(classifyBeans.size()==0)
//            executeGetClassifyList();
//        if(defaultSearch==null)
//            walletConfig();
//        if(cartChangeNumBean==null)
//            executeGetCartNum();
//    }
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
       // EventBus.getDefault().register(this);
        RxBus.get().register(RX_TAG,HKshopFragment.class);
        SharedPreferences sp = getActivity().getSharedPreferences("SAVE_SHOP_CART_FILE", 0);
       // SharedPreferences sp = getActivity().getSharedPreferences(Constant.SAVE_SHOP_CART_FILE, 0);
        edit = sp.edit();
        mFragmentManager=((FragmentActivity)getActivity()).getSupportFragmentManager();
        //activity之间的消息传递，注册rxbus
        rxBus = RxBus.get().register(RX_TAG, Object.class);
        //订阅
        rxBus.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Object obj) {
                        //事件发布后，接收到消息，更改UI
                        if(obj instanceof ClassifyBean){

                        }else if(obj instanceof CartChangeNum){

                        }
                    }
                });

        return inflater.inflate(R.layout.fragment_index_home,null);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        tabRecyclerView=(RecyclerView) parentView.findViewById(R.id.recyclerView_index_tab);
        parentView.findViewById(R.id.titleleft).setOnClickListener(this);
        parentView.findViewById(R.id.search).setOnClickListener(this);
        searchTv=(TextView) parentView.findViewById(R.id.searchTv);
        shopCarIv = (ImageView) parentView.findViewById(R.id.index_shopcar);
        shopCarIv.setOnClickListener(this);
        shopcartNumTv = (TextView) parentView.findViewById(R.id.cart_number_tv);
        mNavigateShopAdapter=new NavigateShopAdapter();
        // 创建一个线性布局管理器
        layoutManager = new LinearLayoutManager(getActivity());
        // 默认是Vertical，可以不写
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        // 设置布局管理器
        tabRecyclerView.setLayoutManager(layoutManager);
        tabRecyclerView.setAdapter(mNavigateShopAdapter);
        mNavigateShopAdapter.setOnItemClickListener(itemClickListener);
        initFristData();
    }

    @Override
    protected void widgetClick(View v) {/**
        super.widgetClick(v);
        switch (v.getId()){
            case R.id.titleleft:
                Bundle bundle3 = new Bundle();
                bundle3.putString("defaultSearch", realSearchKey);
                showActivity(getActivity(), ClassifyAct.class, bundle3);
                break;
            case R.id.search:
                // 跳搜索
                Bundle bundle4 = new Bundle();
                bundle4.putBoolean(Constant.IS_JUMP_TO_PRODUCT_LIST, false);
                bundle4.putString("defaultSearch", defaultSearch);
                bundle4.putString("realSearchKey", realSearchKey);
                showActivity(getActivity(), ProductSearchAndListActivity.class, bundle4);
                break;
            case R.id.index_shopcar:
//				// 跳转到购物车
                showActivity(getActivity(), ProductCartAct.class);
                break;
        }**/
    }
    OnItemClickListener itemClickListener=new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if(position==0){
                RxBus.get().post(RX_TAG,new ClassifyBean());
                //mFragmentManager.beginTransaction().replace(R.id.fragment_layout,HKmallHomeFragment.getInstance()).commit();
            }else{
                RxBus.get().post(RX_TAG,new CartChangeNum());
                OneClassifyBean classBean=mNavigateShopAdapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putInt("categoryId", classBean.rootId);//
                bundle.putBoolean("isFirstCategory", true);
                bundle.putString("defaultSearch", defaultSearch);//
                bundle.putString("categoryName", classBean.rootName);//
                ProductSearchfrag = new  Fragment();
                ProductSearchfrag.setArguments(bundle);
                //mFragmentManager.beginTransaction().replace(R.id.fragment_layout,ProductSearchfrag).commit();
            }
            int lastVisible=layoutManager.findLastVisibleItemPosition();
            int firstVisible=layoutManager.findFirstVisibleItemPosition();
            if(position>=lastVisible-1&&position+1<layoutManager.getItemCount()){
                tabRecyclerView.smoothScrollToPosition(position+1);
            }else if(position<=firstVisible+1&&position-1>=0) {
                tabRecyclerView.smoothScrollToPosition(position - 1);
            }
        }
    };


    //@Override
    public void initFristData() {
        // TODO Auto-generated method stub
        //super.initFristData();
        executeGetClassifyList();
        getConfigInfo();
        //executeGetCartNum();
        //mFragmentManager.beginTransaction().replace(R.id.fragment_layout,HKmallHomeFragment.getInstance()).commit();
    }
    /**
     * 获取分类信息接口
     * @description：
     * @author ldm
     * @date 2015-7-24 上午9:47:29
     */
    private void executeGetClassifyList() {
        String userId= RsaHelper.encryptDataFromStr("0", RsaHelper.publicKey);
        HuiKaRetrofitApi.getInstance().get().GetProductFristClassifyList(userId,new ResultCallback<RequestResult<ArrayList<OneClassifyBean>>>(){
            @Override
            public void success(RequestResult<ArrayList<OneClassifyBean>> arrayResult, Response response) {
                if (arrayResult.getRs() != null && arrayResult.getRs().size() > 0) {
                    classifyBeans.clear();
                    classifyBeans.add(new OneClassifyBean("首页"));
                    classifyBeans.addAll(arrayResult.getRs());
                    mNavigateShopAdapter.setGroup(classifyBeans);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("失败 class");
            }
        });
    }

    /*************获取配置信息******************************************/
    public void getConfigInfo() {
        HuiKaRetrofitApi.getInstance().get().GetConfigInfo("", new ResultCallback<RequestResult<HashMap<String, String>>>() {
            @Override
            public void success(RequestResult<HashMap<String, String>> mapResult, Response response) {
                if (mapResult != null && mapResult.flag == 1 && mapResult.getRs() != null) {
                    onepieceUrl = mapResult.getRs().get("onepieceUrl");// 配置信息中获取一元拼写的URL
                    defaultSearch = mapResult.getRs().get("defaultSearch");// 默认搜索商品名称
                    realSearchKey = mapResult.getRs().get("realSearchKey");// 真正要搜索的关键字
                    searchTv.setText(defaultSearch);
                    edit.putString("ONE_PIN_URL", onepieceUrl);
                    edit.commit();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                getConfigInfo();
            }
        });

    }
    /**获取购物车信息*/
    private void getCartNum(){
        String userId="0";
        //获取用户id
        //if (!LoginHelper.getInstance(getActivity()).isAutoLogin()) { return; }
        //userId=GlobalApp.getInstance().getUserModel().userId;
        userId= RsaHelper.encryptDataFromStr(userId, RsaHelper.publicKey);
        HuiKaRetrofitApi.getInstance().get().GetCartNum(userId,new ResultCallback<RequestResult<CartChangeNum>>(){
            @Override
            public void success(RequestResult<CartChangeNum> cartChangeNumRequestResult, Response response) {
//                cartChangeNumBean = result.getRs();
//            PreferHelper.getInstance()
//                    .setInt(PreferHelper.KEY_SHOP_CART_NUM,
//                            Integer.valueOf(result.getRs().getNumber()));
//            if (cartChangeNumBean != null) {
//                if (!TextUtils.isEmpty(cartChangeNumBean.number) && Integer.valueOf(cartChangeNumBean.number) > 0) {
//                    shopcartNumTv.setVisibility(View.VISIBLE);
//                    shopcartNumTv.setText(cartChangeNumBean.number);
//                }
//                else {
//                    shopcartNumTv.setVisibility(View.GONE);
//                }
//            }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //EventBus.getDefault().unregister(this);
        RxBus.get().unregister(RX_TAG,rxBus);

    }

}
