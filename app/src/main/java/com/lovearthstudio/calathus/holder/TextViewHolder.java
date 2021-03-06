package com.lovearthstudio.calathus.holder;

import android.content.Context;
import android.view.View;

import com.lovearthstudio.calathus.R;
import com.lovearthstudio.calathus.widget.cardview.TextCard;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by zhaoliang on 16/4/6.
 */
public class TextViewHolder extends CardHolder {

    @ViewInject(R.id.text_card)
    private TextCard text_card;

    public TextViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bindView(Context context, BaseHolder cardHolder, JSONObject jsonStr) throws JSONException {
        super.bindBaseView(context, (CardHolder) cardHolder, jsonStr);
        text_card.parseData(jsonStr);
    }

    @Override
    public int setLayoutFile() {
        return R.layout.card_text_holder;
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {

    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {

    }
}