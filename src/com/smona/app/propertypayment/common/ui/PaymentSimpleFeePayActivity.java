package com.smona.app.propertypayment.common.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentFeeDanInfo;
import com.smona.app.propertypayment.common.data.PaymentSimpleDetailInfo;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;

public class PaymentSimpleFeePayActivity extends PaymentBaseActivity {

	private static final String TAG = "PaymentSimpleFeePayActivity";

	protected PaymentSimpleDetailInfo mParam;

	protected PaymentFeeDanInfo mContent;
	private int mSource;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_simple_fee_pay_detail);
		acquireItemInfo();
		initViews();
	}

	private void acquireItemInfo() {
		mParam = (PaymentSimpleDetailInfo) getIntent().getParcelableExtra(
				PaymentConstants.DATA_ITEM_INFO);
		mSource = getIntent().getIntExtra(PaymentConstants.DATA_SOURCE, -1);
		LogUtil.d(TAG, "acquireItemInfo mItem: " + mParam);
	}

	@Override
	protected void initHeader() {
		initText(R.id.title, R.string.payment_common_detail_list);
		initView(R.id.back);
	}

	@Override
	protected void initBody() {
		View parent = mRoot.findViewById(R.id.user_name);
		initText(parent, R.id.name, R.string.payment_common_huming);
		initText(parent, R.id.value, "小明");

		parent = mRoot.findViewById(R.id.user_huhao);
		initText(parent, R.id.name, R.string.payment_common_huhao);
		initText(parent, R.id.value, mParam.housecode);

		parent = mRoot.findViewById(R.id.pay_to_company);
		initText(parent, R.id.name, R.string.payment_common_company);
		initText(parent, R.id.value, mParam.companyname);

		parent = mRoot.findViewById(R.id.yue);
		if(mSource == PaymentConstants.DATA_SOURCE_WATER) {
			initText(parent, R.id.name, R.string.payment_common_yingjiao_money);
		} else {
			initText(parent, R.id.name, R.string.payment_common_yue);
		}
		initText(parent, R.id.value, 100.3 + "元");

		parent = mRoot.findViewById(R.id.input_fee);
		initTextHint(parent, R.id.value, R.string.payment_common_input_pay_jine);
		initText(parent, R.id.name, R.string.payment_common_pay_jine);

		initText(R.id.next_step, R.string.payment_common_liji_pay);
		initView(R.id.next_step);
	}

	@Override
	protected void clickView(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.back:
			finish();
			break;
		case R.id.next_step:
			clickNextStep();
			break;
		}
	}

	private void clickNextStep() {
		View parent = mRoot.findViewById(R.id.input_fee);
		String input_jine_str = ((TextView) parent.findViewById(R.id.value))
				.getText().toString();
		if (TextUtils.isEmpty(input_jine_str)) {
			showMessage("请输入缴费金额");
			return;
		}

		mContent = new PaymentFeeDanInfo();
		mContent.companycode = mParam.companycode;
		mContent.companyname = mParam.companyname;
		mContent.money = Double.valueOf(input_jine_str);
		if (mContent.money <= 0) {
			showMessage("金额必须大于0");
			return;
		}
		gotoSubActivity(mContent, PaymentConfirmActivity.class);
	}
}
