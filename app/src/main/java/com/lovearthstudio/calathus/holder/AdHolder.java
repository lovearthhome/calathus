package com.lovearthstudio.calathus.holder;

import android.content.Context;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.lovearthstudio.calathus.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by zhaoliang on 16/4/22.
 */
public class AdHolder extends BaseHolder {
    @ViewInject(R.id.adView)
    private AdView adView;

    public AdHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(Context context, BaseHolder cardHolder, JSONObject jo) throws JSONException {
        bindHead(context, cardHolder, jo);
        //System.out.println("---------------加载广告:");
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
    }

    @Override
    public int setLayoutFile() {
        return R.layout.ad_view_holder;
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {

    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {

    }
}
