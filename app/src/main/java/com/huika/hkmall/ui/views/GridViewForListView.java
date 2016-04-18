package com.huika.hkmall.ui.views;

import android.widget.GridView;

/**
 * @version 1.0.0
 * Created by jixionghui on 2015/12/22.
 */
public class GridViewForListView extends GridView {

  public GridViewForListView(android.content.Context context, android.util.AttributeSet attrs) {
    super(context, attrs);
  }

  /**
   * 设置不滚动
   */
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    super.onMeasure(widthMeasureSpec, expandSpec);
  }
}
