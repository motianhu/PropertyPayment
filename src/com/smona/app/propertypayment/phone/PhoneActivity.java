package com.smona.app.propertypayment.phone;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;

import com.smona.app.propertypayment.R;
import com.smona.app.propertypayment.common.data.PaymentItemInfo;
import com.smona.app.propertypayment.common.ui.PaymentBaseActivity;
import com.smona.app.propertypayment.common.ui.PaymentSimpleDetailListActivity;
import com.smona.app.propertypayment.common.util.LogUtil;
import com.smona.app.propertypayment.common.util.PaymentConstants;

public class PhoneActivity extends PaymentBaseActivity {

    private static final String TAG = "PhoneActivity";
    private static final int ACTION_START_CONTACTS = 1;

    private ArrayList<PaymentItemInfo> mYouhuis = new ArrayList<PaymentItemInfo>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_phone);
        aquireDatas();
        initViews();
    }

    private void aquireDatas() {
        for (int i = 0; i < 10; i++) {
            PaymentPhonePriceInfo item = new PaymentPhonePriceInfo();
            item.code = i + "";
            item.price = 10.0f * (i + 1);
            item.discount = 0.9f;
            item.type_name = item.price + "元" + "(优惠价: " + item.price
                    * item.discount + "元)";
            mYouhuis.add(item);
        }
    }

    @Override
    protected void initHeader() {
        initText(R.id.title, R.string.payment_phone_pay);
        initView(R.id.back);
        initText(R.id.detail, R.string.payment_common_query);
        initView(R.id.detail);
    }

    @Override
    protected void initBody() {
        View parent = mRoot.findViewById(R.id.input_phone);
        initView(R.id.input_phone);
        initView(R.id.goto_app);

        parent = mRoot.findViewById(R.id.phone_guishu);
        initText(parent, R.id.name, "河南电信");

        initText(R.id.next_step, R.string.payment_phone_liji_pay);
        initView(R.id.next_step);

        parent = mRoot.findViewById(R.id.dazhe_info);
        initTextHint(parent, R.id.select_type,
                R.string.payment_phone_select_jine);
        initView(R.id.dazhe_info);
    }

    @Override
    protected void clickView(View v) {
        int id = v.getId();
        switch (id) {
        case R.id.back:
            finish();
            break;
        case R.id.dazhe_info:
            clickPaymoney();
            break;
        case R.id.detail:
            gotoSubActivity(PaymentConstants.DATA_SOURCE_PHONE,
                    PaymentSimpleDetailListActivity.class);
            break;
        case R.id.input_phone:
            gotoSubActivity(PhoneHistoryListActivity.class);
            break;
        case R.id.next_step:
            clickNextStep();
            break;
        case R.id.goto_app:
            gotoContants();
            break;
        }
    }

    private void clickNextStep() {
    }

    private void clickPaymoney() {
        final ArrayList<PaymentItemInfo> datas = mYouhuis;
        showSingleChoiceType(datas, new IChoiceCallback() {
            @Override
            public void onChoice(int which) {
                PaymentItemInfo info = datas.get(which);
                LogUtil.d(TAG, "clickPaymoney: info: " + info);
                View parent = mRoot.findViewById(R.id.dazhe_info);
                float price = ((PaymentPhonePriceInfo) info).price;
                float discount = ((PaymentPhonePriceInfo) info).discount;
                initText(parent, R.id.select_type_value, price + "元");
                initText(parent, R.id.select_type, "优惠价: " + price * discount
                        + "元");
            }
        });

    }

    private void gotoContants() {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                ContactsContract.Contacts.CONTENT_URI), ACTION_START_CONTACTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RESULT_OK == resultCode) {
            if (ACTION_START_CONTACTS == requestCode) {
                // getPhoneAndName(data);
            }
        }
    }

    /*
     * private void getPhoneAndName(Intent data) { ContentResolver
     * reContentResolverol = getContentResolver(); Uri contactData =
     * data.getData(); Cursor cursor = managedQuery(contactData, null, null,
     * null, null); cursor.moveToFirst(); String username =
     * cursor.getString(cursor
     * .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)); String
     * contactId = cursor.getString(cursor
     * .getColumnIndex(ContactsContract.Contacts._ID)); Cursor phone =
     * reContentResolverol.query(
     * ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
     * ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId,
     * null, null); String usernumber = null; while (phone.moveToNext()) {
     * usernumber = phone .getString(phone
     * .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)); }
     * phone.close(); cursor.close(); LogUtil.d(TAG, "username: " + username +
     * ", usernumber: " + usernumber); querySim(); }
     * 
     * private void querySim() { Uri uri = Uri.parse("content://icc/adn");
     * Cursor cursor = getContentResolver().query(uri, null, null, null, null);
     * LogUtil.d(TAG, ">>>>>>" + cursor.getCount()); while (cursor.moveToNext())
     * { String id = cursor .getString(cursor
     * .getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID)); String name
     * = cursor .getString(cursor
     * .getColumnIndex(ContactsContract.CommonDataKinds.Relation.NAME)); String
     * phoneNumber = cursor .getString(cursor
     * .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
     * LogUtil.d("1023", ">>>>>>" + "_id, " + id); LogUtil.d("1023", ">>>>>>" +
     * "name, " + name); LogUtil.d("1023", ">>>>>>" + "phone number, " +
     * phoneNumber); } cursor.close(); }
     */
}
