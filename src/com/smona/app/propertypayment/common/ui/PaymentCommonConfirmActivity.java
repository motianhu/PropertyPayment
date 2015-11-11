package com.smona.app.propertypayment.common.ui;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.submit.PaymentCommonSubmitBean;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class PaymentCommonConfirmActivity extends PaymentConfirmActivity {

    @SuppressLint("InflateParams")
    @Override
    protected void initBody() {
        View view = mRoot.findViewById(R.id.jiaofei_company);
        initText(view, R.id.name, R.string.payment_common_jiaofei_company);
        initText(view, R.id.value,
                ((PaymentCommonSubmitBean) mParam).companyname);

        view = mRoot.findViewById(R.id.jiaofei_money);
        initText(view, R.id.name, R.string.payment_common_yingjiao_money);
        initText(view, R.id.value, ((PaymentCommonSubmitBean) mParam).needfare
                + "元");

        ViewGroup zhifu = (ViewGroup) mRoot.findViewById(R.id.zhifu_channel);
        String[] channels = getResources()
                .getStringArray(R.array.zhifu_channel);
        int size = channels.length;
        View group;

        for (int i = 0; i < size; i++) {
            group = LayoutInflater.from(this).inflate(
                    R.layout.payment_common_zhifu_type, null);
            initTextImage(group, R.id.select_type_value, imageIds[i],
                    channels[i]);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.topMargin = 3;
            group.setId(i);
            initView(group);
            zhifu.addView(group, params);
        }
    }
}
