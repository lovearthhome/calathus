package com.lovearthstudio.calathus.app;

import com.chaowen.commentlibrary.BaseContext;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lovearthstudio.duasdk.Dua;

import org.xutils.x;


/**
 * Created by pro on 16/3/11.
 */
public class ArticleAppliction extends BaseContext {

    @Override
    public void onCreate() {
        super.onCreate();

        Fresco.initialize(this);

        initXutils();
        /**
         * 为保证使用dua的时候dua已经初始化了，在这个地方init一下。
        * */
        Dua.init(this);
    }

    /**
     * init xutils
     */
    private void initXutils() {
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
