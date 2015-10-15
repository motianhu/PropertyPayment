package com.smona.app.propertypayment.common.ui;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PaymentConfirmActivity extends PaymentBaseActivity {
    private static final String TAG = "PaymentConfirmActivity";

    protected PaymentSubmitBean mParam;
    private int[] imageIds;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_confirm);
        aquireData();
        initViews();
    }

    private void aquireData() {
        imageIds = new int[] { R.drawable.payment_zhifubao,
                R.drawable.payment_weixin, R.drawable.payment_yanlian };
        mParam = (PaymentSubmitBean) getIntent().getParcelableExtra(
                PaymentConstants.DATA_ITEM_INFO);
        LogUtil.d(TAG, "acquireItemInfo mItem: " + mParam);
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_zhifu);
        initView(R.id.back);
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initBody() {
        View view = mRoot.findViewById(R.id.jiaofei_company);
        initText(view, R.id.name, R.string.payment_common_jiaofei_company);
        initText(view, R.id.value, mParam.companyname);

        view = mRoot.findViewById(R.id.jiaofei_money);
        initText(view, R.id.name, R.string.payment_common_yingjiao_money);
        initText(view, R.id.value, mParam.needfare + "元");

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

    private void initTextImage(View parent, int childId, int imgId, String str) {
        TextView text = (TextView) parent.findViewById(childId);
        text.setCompoundDrawablesWithIntrinsicBounds(getResources()
                .getDrawable(imgId), null, null, null);
        text.setText(str);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }
        switch (id) {
        case 0:
            this.showMessage("0");
            break;
        case 1:
            this.showMessage("1");
            break;
        case 2:
            this.showMessage("2");
            break;
        }
    }

}
