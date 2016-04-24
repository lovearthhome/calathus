package com.zky.articleproj.adapter.holder.yimingyu;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zky.articleproj.R;
import com.zky.articleproj.adapter.holder.base.BaseHolder;
import com.zky.articleproj.adapter.holder.base.CardHolder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ViewInject;

/**
 * Author：Mingyu Yi on 2016/4/8 09:42
 * Email：461072496@qq.com
 */
public class ConversationsListViewHolder extends CardHolder {

    @ViewInject(R.id.conversations_list_title)
    private TextView title;
    @ViewInject(R.id.ll_content)
    private LinearLayout ll_content;

    public ConversationsListViewHolder(View itemView) {
        super(itemView);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)


    @Override
    public void bindView(Context context, BaseHolder cardHolder, String jsonStr) throws JSONException {
        super.bindBaseView(context, (CardHolder) cardHolder, jsonStr);

//        ConversationsListViewHolder holder=(ConversationsListViewHolder) cardHolder;
        JSONObject jsonObject = new JSONObject(jsonStr);
        String content_str = jsonObject.getString("content");
        JSONObject content_obj = new JSONObject(content_str);

        title.setText(content_obj.optString("title"));

        JSONArray ja = content_obj.optJSONArray("texts");
        for (int i = 0; i < ja.length(); i++) {
            // TextView textView = new TextView(context);
            TextView textView = (TextView) View.inflate(context, R.layout.conver_tv_item, null);
//            textView.setText(Html.fromHtml(ja.get(i).toString().trim()));
            textView.setText(Html.fromHtml(ja.get(i).toString().replace("<p>", "").replace("</p>", "")));
            //textView.setBackgroundColor(0XFFFFFACD);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            textView.setPadding(44, 44, 44, 44);
            params.setMargins(20, 20, 20, 20);
            textView.setLayoutParams(params);
            ll_content.addView(textView);
        }


    }

    @Override
    public int setLayoutFile() {
        return R.layout.conversations_list_item;
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {

    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {

    }
}
