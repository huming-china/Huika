//package com.huika.hkmall.ui.index;
//
//import android.graphics.Color;
//import android.graphics.drawable.Drawable;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.Html;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//import android.widget.PopupWindow.OnDismissListener;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.android.volley.Response.Listener;
//import com.android.volley.VolleyError;
//import com.google.gson.reflect.TypeToken;
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
//import com.handmark.pulltorefresh.library.PullToRefreshListView;
//import com.huika.hkmall.GlobalApp;
//import com.huika.hkmall.R;
//import com.huika.hkmall.config.Constant;
//import com.huika.hkmall.config.UrlConstants;
//import com.huika.hkmall.control.base.BaseFra;
//import com.huika.hkmall.control.index.actvity.ProductDetailsAct;
//import com.huika.hkmall.control.index.actvity.ProductSearchAndListActivity.BackCallBall;
//import com.huika.hkmall.control.index.adapter.ProductListAdapter;
//import com.huika.hkmall.control.index.adapter.ProductMainAdapter;
//import com.huika.hkmall.control.index.adapter.ShopListAdapter;
//import com.huika.hkmall.support.bean.CategoryProductBean;
//import com.huika.hkmall.support.bean.OneClassifyBean;
//import com.huika.hkmall.support.bean.ProductBean;
//import com.huika.hkmall.support.bean.QueryAttrsBean;
//import com.huika.hkmall.support.bean.ShopItems;
//import com.huika.hkmall.support.bean.UserModel;
//import com.huika.hkmall.support.http.FormResultRequest;
//import com.huika.hkmall.support.http.JSONWrapAjaxParams;
//import com.huika.hkmall.support.http.RequestResult;
//import com.huika.hkmall.utils.ChooseFilter;
//import com.huika.hkmall.utils.ChooseFilter.PupWindowRequest;
//import com.huika.hkmall.views.GridViewWithHeaderAndFooter;
//import com.huika.hkmall.views.PullToRefreshGridViewWithHeaderAndFooter;
//import com.huika.hkmall.views.overlay.OverlayLayout;
//import com.huika.hkmall.views.popupwindow.ActionPopupItem;
//import com.huika.hkmall.views.popupwindow.TitlePopup;
//import com.huika.lib.ui.fra.BaseFragment;
//
//import java.util.ArrayList;
//
//public class ProductListFragment extends BaseFragment
//    implements TitlePopup.OnItemOnClickListener, BackCallBall {
//  private View sortRg;
//  private View parentView;
//  private ArrayList<TextView> mTextViews = new ArrayList<TextView>();
//  /** 综合 */
//  private TextView index_tabRb1;
//  private RelativeLayout rl_tabRb1;
//  /** 销量 */
//  private TextView index_tabRb2;
//  private RelativeLayout rl_tabRb2;
//  /** 价格 */
//  private TextView index_tabRb4;
//  private RelativeLayout rl_tabRb4;
//  /** 筛选 */
//  private TextView index_tabRb3;
//  private RelativeLayout rl_tabRb3;
//  private TextView preRb;
//  private View listContainer;
//  private RelativeLayout rl_attachto;
//  private a emtyOverlay;
//  private TextView nosushProductTip;
//  /*把ListView GridView整合为 ReclerView**/
//  private RecyclerView mRecyclerView;
//  //private PullToRefreshListView listview;
//  //private PullToRefreshGridViewWithHeaderAndFooter gridview;
//  private ProductListAdapter adapter;
//  private ShopListAdapter shopListAdapter;
//  private ProductMainAdapter gridadapter;
//  private String keyword;// 关键字（商品名称\商品类别名称）传空不做筛选
//  private String temKeyword;
//  private ChooseFilter chooseFilter;
//  private String[] topSpiners = { "综合", "新品", "人气" };
//  private String tabRb1String = "综合";
//  private TitlePopup titlePopup;
//  /** 排序字段 0-综合 1-最终交易价从低到高 2最终交易价从高到底 3-销量 4-人气5 */
//  private int sortType;
//  private int tmpCategoryId = 0;//用来保存第一次进入时的categoryId
//  private int categoryId = 0;// 商品分类ID
//  private int pubCategoryId = 0;// 商品分类刷新pubwindow时用
//  public int displayMode = 1;// 展现模式
//  private boolean isFirstCategory = false;// 是否是一级分类进入
//  private boolean isCategoryGet = false;// 是否是筛选去获取的请求
//  private String rangeOfPrice;
//  private String brandId;
//  private String skuAttribute;
//  private QueryAttrsBean mQueryAttrsBean;
//  private QueryAttrsBean mPubQueryAttrsBean;
//  private QueryAttrsBean originalQueryAttrsBean;//用来保存第一次获得的sku属性
//  private PupWindowRequestCallBack mPupWindowRequestCallBack;
//  private boolean isSearchShop;
//
//  public interface DisplayMode {
//    int ListViewMode = 0;
//    int GridViewMode = 1;
//  }
//
//  @Override
//  protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
//    return inflater.inflate(R.layout.fra_product_list_layout, container, false);
//  }
//
//  @Override protected void initWidget(View parentView) {
//    this.parentView = parentView;
//    mRecyclerView= (RecyclerView) parentView.findViewById(R.id.recyclerView);
//
//    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//
//    mRecyclerView.setAdapter();
//    listview = (PullToRefreshListView) parentView.findViewById(R.id.listview);
//    gridview = (PullToRefreshGridViewWithHeaderAndFooter) parentView.findViewById(R.id.gridview);
//    adapter = new ProductListAdapter(getActivity());
//    gridadapter = new ProductMainAdapter(getActivity());
//    //listview.setAdapter(adapter);
//    gridview.setAdapter(gridadapter);
//    listview.setVisibility(View.GONE);
//    gridview.setVisibility(View.VISIBLE);
//    listview.setScrollingWhileRefreshingEnabled(true);
//    gridview.setScrollingWhileRefreshingEnabled(true);
//    listContainer = parentView.findViewById(R.id.listContainer);
//    rl_attachto = (RelativeLayout) parentView.findViewById(R.id.rl_attachto);
//    sortRg = parentView.findViewById(R.id.sortRg);
//    index_tabRb1 = (TextView) parentView.findViewById(R.id.index_tabRb1);
//    index_tabRb2 = (TextView) parentView.findViewById(R.id.index_tabRb2);
//    index_tabRb3 = (TextView) parentView.findViewById(R.id.index_tabRb3);
//    index_tabRb4 = (TextView) parentView.findViewById(R.id.index_tabRb4);
//    rl_tabRb1 = (RelativeLayout) parentView.findViewById(R.id.rl_tabRb1);
//    rl_tabRb2 = (RelativeLayout) parentView.findViewById(R.id.rl_tabRb2);
//    rl_tabRb3 = (RelativeLayout) parentView.findViewById(R.id.rl_tabRb3);
//    rl_tabRb4 = (RelativeLayout) parentView.findViewById(R.id.rl_tabRb4);
//    mTextViews.add(index_tabRb1);
//    mTextViews.add(index_tabRb2);
//    mTextViews.add(index_tabRb3);
//    mTextViews.add(index_tabRb4);
//    initPubWindow();
//    initListener(parentView);
//    Bundle arguments = getArguments();
//    if (arguments != null) {
//      isSearchShop=arguments.getBoolean("isSearchShop",false);
//      keyword = arguments.getString("keyword", "");
//      categoryId = arguments.getInt("categoryId", 0);
//      tmpCategoryId = categoryId;
//      isFirstCategory = arguments.getBoolean("isFirstCategory", false);
//    }
//    showLoadingOverLay(listContainer);
//    if (isFirstCategory) {
//      //			rl_tabRb3.setVisibility(View.GONE);
//      excuteClassifyRequest(String.valueOf(categoryId));
//      chooseFilter.setIsShowAllCategory(true);
//    }else if(isSearchShop){
//      refreshShopData(keyword,false);
//    }else {
//      executeGetProductList();
//      chooseFilter.setIsShowAllCategory(false);
//    }
//  }
//
//  private void initPubWindow() {
//    titlePopup =
//        new TitlePopup(baseAct, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, this);
//    titlePopup.addAction(new ActionPopupItem(topSpiners[0], false));// 综合
//    titlePopup.addAction(new ActionPopupItem(topSpiners[1], false));// 新品
//    // titlePopup.addAction(new ActionPopupItem(topSpiners[2], false));// 人气
//    titlePopup.setUIStyle(2);
//    titlePopup.getAction(0).mItemSelected = true;
//    titlePopup.setOnDismissListener(new OnDismissListener() {
//
//      @Override public void onDismiss() {
//        index_tabRb1.setText(tabRb1String);
//        Drawable drawable2 = getResources().getDrawable(R.drawable.product_down_red);
//        // 这一步必须要做,否则不会显示.
//        drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
//        index_tabRb1.setCompoundDrawables(null, null, drawable2, null);
//        changeTextColor(mTextViews, index_tabRb1);
//      }
//    });
//    chooseFilter = new ChooseFilter(getActivity(), parentView.findViewById(R.id.rl_tabRb3),
//        new ChooseFilter.ConfirmCallBack() {
//
//          @Override
//          public void getFilterData(String rangeOfPrice, String brandId, String skuAttribute) {
//            // changeTextColor(mTextViews, index_tabRb3);
//            isCategoryGet = true;
//            ProductListFragment.this.rangeOfPrice = rangeOfPrice;
//            ProductListFragment.this.brandId = brandId;
//            ProductListFragment.this.skuAttribute = skuAttribute;
//            showLoadingDialog(false);
//            executeGetProductList();
//          }
//        });
//    chooseFilter.setPupWindowRequest(new PupWindowRequest() {
//      @Override public void refreshSku(int categoryId) {
//        ProductListFragment.this.categoryId = categoryId;
//        executeGetPubWindowContent();
//      }
//
//      @Override public void backToOriginalState() {
//        categoryId = tmpCategoryId;
//        chooseFilter.setListView1Content(originalQueryAttrsBean);
//      }
//    });
//  }
//
//  private void initListener(View parentView) {
//    rl_tabRb1.setOnClickListener(rl_onSortItemClick);
//    rl_tabRb2.setOnClickListener(rl_onSortItemClick);
//    rl_tabRb3.setOnClickListener(rl_onSortItemClick);
//    rl_tabRb4.setOnClickListener(rl_onSortItemClick);
//    listview.setOnRefreshListener(new OnRefreshListener2<ListView>() {
//      @Override public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
//        CURRENT_PAGE = 1;
//        if(isSearchShop)
//          executeGetShopList();
//        else
//          executeGetProductList();
//      }
//
//      @Override public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
//        CURRENT_PAGE++;
//        if(isSearchShop)
//          executeGetShopList();
//        else
//          executeGetProductList();
//      }
//    });
//    listview.setOnItemClickListener(new OnItemClickListener() {
//
//      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        int realPosition = position - listview.getRefreshableView().getHeaderViewsCount();
//        if (!isSearchShop&&realPosition >= 0 && realPosition < adapter.getCount()) {
//          ProductBean item = adapter.getItem(realPosition);
//          Bundle bundle = new Bundle();
//          bundle.putString(Constant.PRODUCT_ID, item.getProductId());
//          showActivity(getActivity(), ProductDetailsAct.class, bundle);
//        }
//      }
//    });
//
//    gridview.setOnRefreshListener(new OnRefreshListener2<GridViewWithHeaderAndFooter>() {
//      @Override
//      public void onPullDownToRefresh(PullToRefreshBase<GridViewWithHeaderAndFooter> refreshView) {
//        CURRENT_PAGE = 1;
//        executeGetProductList();
//      }
//
//      @Override
//      public void onPullUpToRefresh(PullToRefreshBase<GridViewWithHeaderAndFooter> refreshView) {
//        CURRENT_PAGE++;
//        executeGetProductList();
//      }
//    });
//    gridview.setOnItemClickListener(new OnItemClickListener() {
//
//      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        int realPosition = position - gridview.getRefreshableView().getHeaderViewCount();
//        if (realPosition >= 0 && realPosition < gridadapter.getCount()) {
//          ProductBean item = gridadapter.getItem(realPosition);
//          Bundle bundle = new Bundle();
//          bundle.putString(Constant.PRODUCT_ID, item.getProductId());
//          showActivity(getActivity(), ProductDetailsAct.class, bundle);
//        }
//      }
//    });
//  }
//
//  @Override public void onItemClick(ActionPopupItem item, int position) {
//    for (int i = 0; i < titlePopup.getActionSize(); i++)
//      titlePopup.getAction(i).mItemSelected = false;
//    if (item.mTitle.equals(topSpiners[0])) {
//      sortType = 0;
//      tabRb1String = "综合";
//    } else if (item.mTitle.equals(topSpiners[1])) {
//      // TODO sortType=新品
//      sortType = 5;
//      tabRb1String = "新品";
//    } else if (item.mTitle.equals(topSpiners[2])) {
//      sortType = 4;
//    }
//    index_tabRb1.setText(tabRb1String);
//    Drawable drawable = getResources().getDrawable(R.drawable.product_down_red);
//    // 这一步必须要做,否则不会显示.
//    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//    index_tabRb1.setCompoundDrawables(null, null, drawable, null);
//    changeTextColor(mTextViews, index_tabRb1);
//    item.mItemSelected = true;
//    changeTextColor(mTextViews, index_tabRb1);
//    showLoadingDialog(false);
//    executeGetProductList();
//  }
//
//  private void changeTextColor(ArrayList<TextView> mList, TextView mTextView) {
//    for (TextView mView : mList) {
//      mView.setTextColor(Color.BLACK);
//      if (mTextView == mView) {
//        mView.setTextColor(Color.RED);
//      }
//    }
//  }
//
//  private OnClickListener rl_onSortItemClick = new OnClickListener() {
//    @Override public void onClick(View v) {
//      Drawable drawable1 = getResources().getDrawable(R.drawable.product_down_common);
//      // 这一步必须要做,否则不会显示.
//      drawable1.setBounds(0, 0, drawable1.getMinimumWidth(), drawable1.getMinimumHeight());
//      index_tabRb1.setCompoundDrawables(null, null, drawable1, null);
//      changeTextColor(mTextViews, index_tabRb1);
//      switch (v.getId()) {
//
//        case R.id.rl_tabRb1:
//          titlePopup.show(v);
//          if (titlePopup.isShowing()) {
//            index_tabRb1.setText(tabRb1String);
//          }
//          Drawable drawable = getResources().getDrawable(R.drawable.product_up_red);
//          // 这一步必须要做,否则不会显示.
//          drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//          index_tabRb1.setCompoundDrawables(null, null, drawable, null);
//          changeTextColor(mTextViews, index_tabRb1);
//          return;
//        case R.id.rl_tabRb2:
//          for (int i = 0; i < titlePopup.getActionSize(); i++)
//            titlePopup.getAction(i).mItemSelected = false;
//          changeTextColor(mTextViews, index_tabRb2);
//          if (sortType == 3) return;
//          sortType = 3;
//          break;
//        case R.id.rl_tabRb3:
//          chooseFilter.init(chooseFilter.FIRST_POP, mQueryAttrsBean);
//          changeTextColor(mTextViews, index_tabRb3);
//          return;
//        case R.id.rl_tabRb4:
//          changeTextColor(mTextViews, index_tabRb4);
//          if (sortType == 1) {
//            sortType = 2;// 从高到底 down
//          } else {
//            sortType = 1;// 从低到高 up
//          }
//          break;
//      }
//      CURRENT_PAGE = 1;
//      setPriceSortDrawableRight();
//
//      showLoadingDialog(false);
//      executeGetProductList();
//    }
//  };
//
//  private OnClickListener onSortItemClick = new OnClickListener() {
//    @Override public void onClick(View v) {
//      // TODO 改变对应排序条件，并调接口
//      // if(preRb!=null&&preRb!=index_tabRb4&&preRb==v&&preRb!=index_tabRb1)
//      // return;
//      // preRb.setTextColor(Color.BLACK);
//      switch (v.getId()) {
//        case R.id.index_tabRb1:
//          titlePopup.show(v);
//          if (titlePopup.isShowing()) index_tabRb1.setText(tabRb1String);
//          Drawable drawable = getResources().getDrawable(R.drawable.product_up_red);
//          // 这一步必须要做,否则不会显示.
//          drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//          index_tabRb1.setCompoundDrawables(null, null, drawable, null);
//          changeTextColor(mTextViews, index_tabRb1);
//          return;
//        case R.id.index_tabRb2:
//          for (int i = 0; i < titlePopup.getActionSize(); i++)
//            titlePopup.getAction(i).mItemSelected = false;
//          changeTextColor(mTextViews, index_tabRb2);
//          if (sortType == 3) return;
//          sortType = 3;
//          break;
//        case R.id.index_tabRb3:
//          chooseFilter.init(chooseFilter.FIRST_POP, mQueryAttrsBean);
//          return;
//        case R.id.index_tabRb4:
//          changeTextColor(mTextViews, index_tabRb4);
//          if (sortType == 1) {
//            sortType = 2;// 从高到底 down
//          } else {
//            sortType = 1;// 从低到高 up
//          }
//          break;
//      }
//      CURRENT_PAGE = 1;
//      setPriceSortDrawableRight();
//
//      showLoadingDialog(false);
//      executeGetProductList();
//    }
//  };
//
//  private void setPriceSortDrawableRight() {
//    Drawable drawableRight;
//    if (sortType == 1) {// 从低到高 up
//      drawableRight = getResources().getDrawable(R.drawable.price_sort_up);
//    } else if (sortType == 2) {// 从高到底 down
//      drawableRight = getResources().getDrawable(R.drawable.price_sort_down);
//    } else {// 不以价格来排序
//      drawableRight = getResources().getDrawable(R.drawable.price_sort_normal);
//    }
//    drawableRight.setBounds(0, 0, drawableRight.getMinimumWidth(),
//        drawableRight.getMinimumHeight());
//    index_tabRb4.setCompoundDrawables(null, null, drawableRight, null);
//  }
//
//  public void changeDisplay(int disMode) {
//    if (disMode == DisplayMode.GridViewMode) {
//      displayMode = DisplayMode.GridViewMode;
//      listview.setVisibility(View.GONE);
//      gridview.setVisibility(View.VISIBLE);
//    } else if (disMode == DisplayMode.ListViewMode) {
//      displayMode = DisplayMode.ListViewMode;
//      listview.setVisibility(View.VISIBLE);
//      gridview.setVisibility(View.GONE);
//    }
//  }
//
//  public void refreshData(String keyword) {
//    initSelection();
//    isSearchShop=false;
//    if(displayMode== DisplayMode.GridViewMode&&listview.getVisibility()==View.VISIBLE){
//      changeDisplay(DisplayMode.GridViewMode);
//    }
//    changeSortRg(View.VISIBLE);
//    showLoadingDialog("", false);
//    chooseFilter.setIsShowAllCategory(false);
//    ProductListFragment.this.rangeOfPrice = "";
//    ProductListFragment.this.brandId = "";
//    ProductListFragment.this.skuAttribute = "";
//    CURRENT_PAGE = 1;
//    this.keyword = keyword;
//    categoryId = 0;
//    executeGetProductList();
//  }
//
//  public void refreshData(int categoryId) {
//    initSelection();
//    isSearchShop=false;
//    if(displayMode== DisplayMode.GridViewMode&&listview.getVisibility()==View.VISIBLE){
//      changeDisplay(DisplayMode.GridViewMode);
//    }
//    changeSortRg(View.VISIBLE);
//    showLoadingDialog("", false);
//    CURRENT_PAGE = 1;
//    this.categoryId = categoryId;
//    keyword = "";
//    executeGetProductList();
//  }
//  public void refreshShopData(String keyword) {
//    refreshShopData(keyword,true);
//  }
//  private void refreshShopData(String keyword,boolean ishowloading){
//    isSearchShop=true;
//    initSelection();
//    sortRg.setVisibility(View.GONE);
//    if(ishowloading) {
//      showLoadingDialog("", false);
//    }
//    CURRENT_PAGE = 1;
//    this.keyword = keyword;
//    gridview.setVisibility(View.GONE);
//    listview.setVisibility(View.VISIBLE);
//    if(shopListAdapter==null){
//      shopListAdapter=new ShopListAdapter(getActivity());
//    }
//    listview.setAdapter(shopListAdapter);
//    if(keyword!=null){
//      executeGetShopList();
//    }
//  }
//  //把显示的位置 调整到最顶端
//  private void initSelection(){
//    cancelRequest(this);//取消之前的
//    listview.getRefreshableView().setSelection(0);
//    gridview.getRefreshableView().setSelection(0);
//  }
//
//  private void excuteClassifyRequest(String classifyId) {
//
//    FormResultRequest<ArrayList<OneClassifyBean>> request =
//        new FormResultRequest<ArrayList<OneClassifyBean>>(UrlConstants.GET_PRODUCT_CLASSIFY_LIST,
//            contentLister, this, new TypeToken<RequestResult<ArrayList<OneClassifyBean>>>() {
//        }.getType());
//    JSONWrapAjaxParams ajaxParams = new JSONWrapAjaxParams();
//    UserModel userModel = GlobalApp.getInstance().getUserModel();
//    ajaxParams.putStringTypeParam("userId",
//        userModel == null || TextUtils.isEmpty(userModel.userId) ? "0" : userModel.userId);
//    ajaxParams.putStringTypeParam("classifyId", classifyId);
//    ajaxParams.putStringTypeParam("classifyType", "1");
//    request.setRequestParams(ajaxParams);
//    executeRequest(request);
//  }
//
//  private Listener<RequestResult<ArrayList<OneClassifyBean>>> contentLister =
//      new Listener<RequestResult<ArrayList<OneClassifyBean>>>() {
//        @Override public void onResponse(RequestResult<ArrayList<OneClassifyBean>> arg0) {
//          dismissLoadingDialog();
//          if (arg0.flag == 1 && arg0.getRs() != null) {
//            //TODO 传入数据
//            chooseFilter.allCategoryList = arg0.getRs();
//            executeGetProductList();
//          }
//        }
//      };
//
//  // 刷新pubwindow请求
//  private void executeGetPubWindowContent() {
//    JSONWrapAjaxParams ajaxParams = new JSONWrapAjaxParams();
//    ajaxParams.putStringTypeParam("userId", "0");
//    ajaxParams.putCommonTypeParam("categoryId", categoryId);// 商品分类ID
//    ajaxParams.putCommonTypeParam("pageNo", 1);
//    ajaxParams.putCommonTypeParam("pageSize", PAGE_SIZE);
//    getPubWindowContent.setRequestParams(ajaxParams);
//    executeRequest(getPubWindowContent);
//  }
//
//  private FormResultRequest<CategoryProductBean> getPubWindowContent =
//      new FormResultRequest<CategoryProductBean>(UrlConstants.GET_PRODUCT_LISTV1,
//          new Listener<RequestResult<CategoryProductBean>>() {
//            @Override public void onResponse(RequestResult<CategoryProductBean> result) {
//              if (result.getRs() != null && result.getRs().getProducts() != null
//                  && result.getRs().getProducts().size() > 0) {
//                mPubQueryAttrsBean =
//                    new QueryAttrsBean(result.getRs().getBrands(), result.getRs().getSkuItems());
//                chooseFilter.refreshSkuCallBack(mPubQueryAttrsBean);
//              } else {
//                baseAct.showToastMsg("没有该商品");
//              }
//            }
//          }, this, new TypeToken<RequestResult<CategoryProductBean>>() {
//      }.getType());
//
//  // 商品列表
//  private void executeGetProductList() {
//    JSONWrapAjaxParams ajaxParams = new JSONWrapAjaxParams();
//    ajaxParams.putStringTypeParam("userId", "0");
//    ajaxParams.putCommonTypeParam("categoryId", categoryId);// 商品分类ID
//    ajaxParams.putStringTypeParam("keyword", keyword);// 关键字（商品名称\商品类别名称）传空不做筛选
//    ajaxParams.putCommonTypeParam("priceType",
//        sortType);// 排序字段 0-综合 1-最终交易价从低到高 2最终交易价从高到底 3-销量 4-人气
//    // ajaxParams.putCommonTypeParam("browseType", 0);// browseType 浏览类型: 0-不做筛选 1-足迹
//    ajaxParams.putCommonTypeParam("brandId", brandId);
//    ajaxParams.putCommonTypeParam("skuAttribute", skuAttribute);
//    ajaxParams.putCommonTypeParam("rangeOfPrice", rangeOfPrice);
//    ajaxParams.putCommonTypeParam("pageNo", CURRENT_PAGE);
//    ajaxParams.putCommonTypeParam("pageSize", PAGE_SIZE);
//    getProductList.setRequestParams(ajaxParams);
//    executeRequest(getProductList,false);
//  }
//
//  // 店铺搜索结果列表
//  private void executeGetShopList() {
//    JSONWrapAjaxParams ajaxParams = new JSONWrapAjaxParams();
//    ajaxParams.putStringTypeParam("userId", "0");
//    ajaxParams.putStringTypeParam("keyword", keyword);// 关键字（商品名称\商品类别名称）传空不做筛选
//    ajaxParams.putCommonTypeParam("pageNo", CURRENT_PAGE);
//    ajaxParams.putCommonTypeParam("pageSize", PAGE_SIZE);
//    getShopList.setRequestParams(ajaxParams);
//    executeRequest(getShopList,false);
//  }
//
//  private FormResultRequest<ArrayList<ShopItems>> getShopList =
//          new FormResultRequest<ArrayList<ShopItems>>(UrlConstants.GET_PRODUCT_STORE_LIST,
//                  new Listener<RequestResult<ArrayList<ShopItems>>>() {
//                    @Override public void onResponse(RequestResult<ArrayList<ShopItems>> result) {
//                      listview.onRefreshComplete();
//                      hideOverLayView();
//                      hideEmtyOverLay();
//                      dismissLoadingDialog();
//                      if(result!=null&&result.getRs()!=null){
//                          ArrayList<ShopItems> shopItems= result.getRs();
//                          changeRefreshMode(shopItems.size());
//                          if(CURRENT_PAGE==1) {
//                            shopListAdapter.setGroup(result.getRs());
//                          }else{
//                            shopListAdapter.addItems(result.getRs());
//                          }
//                      }else if (CURRENT_PAGE == 1) {// 无数据
//                         showEmtyOverLay();
//                      }
//                    }
//                  }, this, new TypeToken<RequestResult<ArrayList<ShopItems>>>() {
//          }.getType());
//
//
//
//
//  @Override public void onErrorResponse(VolleyError error) {
//    super.onErrorResponse(error);
//    hideEmtyOverLay();
//    hideOverLayView();
//    dismissLoadingDialog();
//    listview.onRefreshComplete();
//    gridview.onRefreshComplete();
//  }
//
//  private FormResultRequest<CategoryProductBean> getProductList =
//      new FormResultRequest<CategoryProductBean>(UrlConstants.GET_PRODUCT_LISTV1,
//          new Listener<RequestResult<CategoryProductBean>>() {
//            @Override public void onResponse(RequestResult<CategoryProductBean> result) {
//              listview.onRefreshComplete();
//              gridview.onRefreshComplete();
//              hideOverLayView();
//              dismissLoadingDialog();
//              if (result.getRs() != null && result.getRs().getProducts() != null
//                  && result.getRs().getProducts().size() > 0) {
//                if (keyword != null && !keyword.equals(temKeyword)) {
//                  mQueryAttrsBean =
//                      new QueryAttrsBean(result.getRs().getBrands(), result.getRs().getSkuItems());
//                }
//                if (originalQueryAttrsBean == null) {
//                  originalQueryAttrsBean =
//                      new QueryAttrsBean(result.getRs().getBrands(), result.getRs().getSkuItems());
//                }
//                temKeyword = keyword;
//                hideEmtyOverLay();
//                if (CURRENT_PAGE == 1) {
//                  listview.setAdapter(adapter);
//                  adapter.setGroup(result.getRs().getProducts());
//                  gridadapter.setGroup(result.getRs().getProducts());
//                } else {
//                  adapter.addItems(result.getRs().getProducts());
//                  gridadapter.addItems(result.getRs().getProducts());
//                }
//                changeRefreshMode(result.getTotalSize());
//              } else if (CURRENT_PAGE == 1) {// 无数据
//                showEmtyOverLay();
//              }
//            }
//          }, this, new TypeToken<RequestResult<CategoryProductBean>>() {
//      }.getType());
//  private String categoryName;
//
//  private void showEmtyOverLay() {
//    if (emtyOverlay == null) {
//      emtyOverlay = new OverlayLayout(getActivity());
//      nosushProductTip = (TextView) emtyOverlay.setOverlayView(R.layout.overlay_nosuch_product)
//          .findViewById(R.id.nosush_product_tv);
//      emtyOverlay.attachTo(rl_attachto);
//    }
//    if (TextUtils.isEmpty(keyword)) {
//      nosushProductTip.setText("抱歉！没有找到符合条件的商品！");
//    } else {
//      nosushProductTip.setText(
//          Html.fromHtml("暂时搜索不到与“<font color =#ff7815>" + keyword + "</font>”相关的信息"));
//    }
//    if (!isCategoryGet) {
//      changeSortRg(View.GONE);
//    } else {
//      changeSortRg(View.VISIBLE);
//    }
//    emtyOverlay.showOverlay();
//  }
//
//  private void hideEmtyOverLay() {
//    changeSortRg(View.VISIBLE);
//    if (emtyOverlay != null) emtyOverlay.hideOverlay();
//  }
//
//  private void changeSortRg(int visibe){
//    if(isSearchShop){
//      sortRg.setVisibility(View.GONE);
//    }else{
//      sortRg.setVisibility(visibe);
//    }
//  }
//
//  /**
//   * 更改刷新模式
//   *
//   * @author FAN 创建于Nov 27, 2014
//   */
//  private void changeRefreshMode(int totalSize) {
//    if (totalSize == 0) {
//      listview.setMode(Mode.PULL_FROM_START);
//      gridview.setMode(Mode.PULL_FROM_START);
//    } else if (adapter.getCount() >= totalSize) {
//      listview.setMode(Mode.PULL_FROM_START);
//      gridview.setMode(Mode.PULL_FROM_START);
//    } else {
//      listview.setMode(Mode.BOTH);
//      gridview.setMode(Mode.BOTH);
//    }
//  }
//
//  @Override public void setSettings() {
//    isCategoryGet = false;
//    isFirstCategory = false;
//    rl_tabRb3.setVisibility(View.VISIBLE);
//  }
//
//  public void setPupWindowRequestCallBack(PupWindowRequestCallBack mBack) {
//    mPupWindowRequestCallBack = mBack;
//  }
//
//  public interface PupWindowRequestCallBack {
//    void refreshSkuCallBack();
//  }
//}
