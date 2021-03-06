package com.isabella.dechat.presenter;


import com.isabella.dechat.base.BasePresenter;
import com.isabella.dechat.bean.NearbyDataBean;
import com.isabella.dechat.contact.RecyclerContact;
import com.isabella.dechat.model.RecyclerModerImlp;

import java.util.List;

/**
 * description
 * Created by 张芸艳 on 2017/7/6.
 */

public class RecyclerPresenter extends BasePresenter<RecyclerContact.RecyView> {
    RecyclerModerImlp registerModerImlp;

    public RecyclerPresenter() {
        registerModerImlp = new RecyclerModerImlp();
    }

    public void getData( long currentTime,int page) {

        registerModerImlp.getData(currentTime,page, new RecyclerContact.RecyclerModelImplResult() {


            @Override
            public void success( List<NearbyDataBean> data ,boolean isData) {
                view.success(data,isData);
            }

            @Override
            public void failed(Throwable e) {
                view.failed(e);
            }
        });
    }
}
