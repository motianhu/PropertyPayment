package com.smona.app.propertypayment.common.ui;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.submit.PaymentSubmitBean;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public abstract class PaymentConfirmActivity extends PaymentBaseActivity {
    private static final String TAG = "PaymentConfirmActivity";

    protected PaymentSubmitBean mParam;
    protected int mSource;
    protected int[] imageIds;

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
        mSource = getIntent().getIntExtra(PaymentConstants.DATA_SOURCE, -1);
        LogUtil.d(TAG, "acquireItemInfo mSource: " + mSource + ", mParam: "
                + mParam);
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_common_zhifu);
        initView(R.id.back);
    }

    protected void initTextImage(View parent, int childId, int imgId, String str) {
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
