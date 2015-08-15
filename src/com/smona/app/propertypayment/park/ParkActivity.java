package com.smona.app.propertypayment.park;

import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.ui.PaymentComplexFeectivity;

public class ParkActivity extends PaymentComplexFeectivity {

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_home_park);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.select_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_park_select_cheliang_info);
        initText(parent, R.id.select_type_value,
                R.string.payment_park_cheliang_info);
        initView(R.id.select_info);

        parent = mRoot.findViewById(R.id.property_company);
        initText(parent, R.id.name, R.string.payment_park_company);

        parent = mRoot.findViewById(R.id.yingjiao_jine);
        initText(parent, R.id.name, R.string.payment_park_yingjiao_jine);

        parent = mRoot.findViewById(R.id.dazhe_info);
        initText(parent, R.id.select_type,
                R.string.payment_park_dazhe_info_hint);
        initText(parent, R.id.select_type_value,
                R.string.payment_park_dazhe_info);
        initView(R.id.dazhe_info);

        parent = mRoot.findViewById(R.id.yucun_jine);
        initText(parent, R.id.name, R.string.payment_park_yucun_info);

        parent = mRoot.findViewById(R.id.heji_jine);
        initText(parent, R.id.name, R.string.payment_park_heji_info);

        initText(R.id.next_step, R.string.payment_common_liji_pay);
        initView(R.id.next_step);
    }
}
