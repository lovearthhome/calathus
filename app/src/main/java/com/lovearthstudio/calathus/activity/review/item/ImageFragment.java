package com.lovearthstudio.calathus.activity.review.item;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.lovearthstudio.calathus.R;
import com.lovearthstudio.calathus.fragment.BaseFragment;
import com.lovearthstudio.calathus.widget.cardview.ImageCard;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_image)
public class ImageFragment extends BaseFragment {

    private JSONObject data;

    @ViewInject(R.id.image_card)
    private ImageCard image_card;

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try{
                data = new JSONObject(getArguments().getString("json"));
            }catch (JSONException e){
                e.printStackTrace();

            }
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            if (data != null)
                image_card.parseData(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建Fragment的实例
     *
     * @param json
     * @return
     */
    public static ImageFragment newInstance(String json) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString("json", json);
        fragment.setArguments(args);
        return fragment;
    }
}
