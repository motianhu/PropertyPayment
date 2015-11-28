package com.smona.app.propertypayment;

import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.gas.GasActivity;
import com.smona.app.propertypayment.heat.HeatActivity;
import com.smona.app.propertypayment.nontax.NonTaxActivity;
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
        setContentView(R.layout.payment_home);
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
                "郑州高新区自来水公司\n我家-6666", R.string.payment_home_wo_will);

        initView(R.id.power);
        initImageAndTextHint(R.id.power, R.drawable.home_power,
                R.string.payment_home_power, R.string.payment_home_wo_will_hint);

        initView(R.id.gas);
        initImageAndTextHint(R.id.gas, R.drawable.home_gas,
                R.string.payment_home_gas, R.string.payment_home_wo_will_hint);

        initView(R.id.phone);
        initImageAndTextHint(R.id.phone, R.drawable.home_phone,
                R.string.payment_home_phone, R.string.payment_home_wo_will_hint);

        initView(R.id.park);
        initImageAndTextHint(R.id.park, R.drawable.home_park,
                R.string.payment_home_park, R.string.payment_home_wo_will_hint);

        initView(R.id.property);
        initImageAndTextHint(R.id.property, R.drawable.home_property,
                R.string.payment_home_property,
                R.string.payment_home_wo_will_hint);

        initView(R.id.nontax);
        initImageAndTextHint(R.id.nontax, R.drawable.home_nontax,
                R.string.payment_home_nontax,
                R.string.payment_home_wo_will_hint);
        
        initView(R.id.heat);
        initImageAndTextHint(R.id.heat, R.drawable.home_nontax,
                R.string.payment_home_heat,
                R.string.payment_home_wo_will_hint);
    }

    private void initImageAndTextHint(int parentResId, int imageResId,
            int textResId, int textResWillId) {
        View parent = mRoot.findViewById(parentResId);
        ImageView image = (ImageView) parent.findViewById(R.id.home_item_image);
        image.setImageResource(imageResId);
        TextView text = (TextView) parent.findViewById(R.id.home_item_text);
        text.setText(textResId);
        text = (TextView) parent.findViewById(R.id.home_item_text_wo_will);
        text.setHint(textResWillId);
    }

    private void initImageAndText(int parentResId, int imageResId, String text,
            int textResWillId) {
        View parent = mRoot.findViewById(parentResId);
        ImageView image = (ImageView) parent.findViewById(R.id.home_item_image);
        image.setImageResource(imageResId);
        TextView title = (TextView) parent.findViewById(R.id.home_item_text);
        title.setText(text);
        title = (TextView) parent.findViewById(R.id.home_item_text_wo_will);
        title.setText(textResWillId);
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
        case R.id.nontax:
            gotoSubActivity(NonTaxActivity.class);
            break;
        case R.id.heat:
            gotoSubActivity(HeatActivity.class);
            break;
        }
    }
}
