package com.smona.app.propertypayment;

import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.gas.GasActivity;
import com.smona.app.propertypayment.park.ParkActivity;
import com.smona.app.propertypayment.phone.PhoneActivity;
import com.smona.app.propertypayment.power.PowerActivity;
import com.smona.app.propertypayment.property.PropertyActivity;
import com.smona.app.propertypayment.water.WaterActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PayHomeActivity extends PaymentBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_home);
        initViews();
    }

    protected void initHeader() {
        initText(R.id.title, R.string.payment_payment);
        initView(R.id.back);
    }

    @Override
    protected void initBody() {
        initView(R.id.water);
        initImageAndText(R.id.water, R.drawable.home_water,
                R.string.payment_home_water, R.string.payment_home_wo_will);

        initView(R.id.power);
        initImageAndText(R.id.power, R.drawable.home_power,
                R.string.payment_home_power, R.string.payment_home_wo_will);

        initView(R.id.gas);
        initImageAndText(R.id.gas, R.drawable.home_gas,
                R.string.payment_home_gas, R.string.payment_home_wo_will);

        initView(R.id.phone);
        initImageAndText(R.id.phone, R.drawable.home_phone,
                R.string.payment_home_phone, R.string.payment_home_wo_will);

        initView(R.id.park);
        initImageAndText(R.id.park, R.drawable.home_park,
                R.string.payment_home_park, R.string.payment_home_wo_will);

        initView(R.id.property);
        initImageAndText(R.id.property, R.drawable.home_property,
                R.string.payment_home_property, R.string.payment_home_wo_will);
    }

    private void initImageAndText(int parentResId, int imageResId, int textResId, int textResWillId) {
        View parent = mRoot.findViewById(parentResId);
        ImageView image = (ImageView) parent.findViewById(R.id.home_item_image);
        image.setImageResource(imageResId);
        TextView text = (TextView) parent.findViewById(R.id.home_item_text);
        text.setText(textResId);
        text = (TextView) parent.findViewById(R.id.home_item_text_wo_will);
        text.setText(textResWillId);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        if (R.id.back == id) {
            finish();
            return;
        }
        switch (id) {
        case R.id.water:
            gotoSubActivity(WaterActivity.class);
            break;
        case R.id.power:
            gotoSubActivity(PowerActivity.class);
            break;
        case R.id.gas:
            gotoSubActivity(GasActivity.class);
            break;
        case R.id.phone:
            gotoSubActivity(PhoneActivity.class);
            break;
        case R.id.property:
            gotoSubActivity(PropertyActivity.class);
            break;
        case R.id.park:
            gotoSubActivity(ParkActivity.class);
            break;
        }
    }
}
