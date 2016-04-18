package com.huika.hkmall.ui.pugin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huika.hkmall.R;
import com.huika.lib.core.ui.fra.BaseFragment;

public class PuginFra extends BaseFragment {
  @Override
  protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
    return inflater.inflate(R.layout.fra_pugin, container);
  }

  @Override protected void initWidget(View parentView) {
    super.initWidget(parentView);

    parentView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClassName(getActivity(), "com.huika.phfcircle.MainActivity");
        getActivity().startActivity(intent);
      }
    });

    parentView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClassName(getActivity(), "com.huika.pchannel.MainActivity");
        getActivity().startActivity(intent);
      }
    });

    parentView.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClassName(getActivity(), "com.zsy.pugin.game.main.GcContainerActivity");
        getActivity().startActivity(intent);
      }
    });

    parentView.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent intent = new Intent();
        intent.setClassName(getActivity(), "com.zsy.pugin.imageloader.activity.HomeActivity");
        getActivity().startActivity(intent);
      }
    });
  }
}
