package com.zky.articleproj.activity.review.item;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zky.articleproj.R;
import com.zky.articleproj.base.BaseFragment;
import com.zky.articleproj.view.cardview.MusicCard;

import org.json.JSONException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_music)
public class MusicFragment extends BaseFragment {

    private String data;

    @ViewInject(R.id.music_card)
    private MusicCard music_card;

    public MusicFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getString("json");
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        try {
            if (data != null)
                music_card.parseData(data);
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
    public static MusicFragment newInstance(String json) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();
        args.putString("json", json);
        fragment.setArguments(args);
        return fragment;
    }
}
