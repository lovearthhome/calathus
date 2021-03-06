package com.lovearthstudio.calathus.widget.cardview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.lovearthstudio.duasdk.util.LogUtil;
import com.lovearthstudio.calathus.R;
import com.lovearthstudio.calathus.constant.Constant;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by zhaoliang on 16/6/8.
 */
public class CommentCard extends BaseCardView {
    private Context context;

    public TextView tv_comment;

    public CommentCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void inflaterLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.card_comment, this, true);
    }

    @Override
    public void findView() {
        tv_comment = (TextView) findViewById(R.id.comment_content);
    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void parseData(JSONObject jsonObject) throws JSONException {
        try {



            /**
             * 获取item需要的基本信息
             * */
            //String editor_name = jsonObject.optString("editor_name");
            mTid = jsonObject.optLong("inc");
            /**
             * 头部信息
             */
            String editorName = jsonObject.getString("editor_name");
            String avatarUrl = jsonObject.getString("editor_avatar");
            //***回来的Json数据里"editor_name":null,getString这个函数就会把null解释成带双音号的“null”
            if (editorName == null || editorName.equals("null") || TextUtils.isEmpty(editorName)) {
                editorName = "匿名";
            }
            if (avatarUrl == null || avatarUrl.equals("null") || TextUtils.isEmpty(avatarUrl)) {
                avatarUrl = Constant.defaultAvatarUrl;
            }

            //editer_name.setText("");
            LogUtil.e(avatarUrl);
            tv_comment.setText("哈哈哈");

                  /*
        内容信息
        */
            String content_str = jsonObject.optString("content");
            JSONObject content_obj = new JSONObject(content_str);


            String brief = content_obj.getString("brief");
            if("".equals(brief) )
            {
                System.out.println("----------brief"+brief);
                //tv_comment.setText(brief);
                tv_comment.setText("获取不到评论");
            }else{

                tv_comment.setText(brief);
            }
            //Picasso.with(context).load(avatar).into(cardHolder.editer_icon);

//            Glide.with(context).load(jsonObject.getString("editor_avatar")).into(cardHolder.editer_icon);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
