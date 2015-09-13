package com.smona.app.propertypayment.db.table;

public class PaymentFeeDetailTable extends PaymentAbstractTable {

    public static final String TABLE_NAME = "fee_detail";

    public static final String FEE_TYPE_CODE = "type_code";
    public static final String FEE_TYPE_NAME = "type_name";
    public static final String FEE_COMPANY_CODE = "company_code";
    public static final String FEE_COMPANY_NAME = "company_name";
    public static final String FEE_GROUP_CODE = "group_code";
    public static final String FEE_GROUP_NAME = "group_name";
    public static final String FEE_MONEY = "fee_money";
    public static final String FEE_TIME = "fee_time";
    public static final String FEE_HUOUSE_CODE = "house_code";

    protected PaymentFeeDetailTable(String tableName) {
        super(TABLE_NAME);
    }

    private static volatile PaymentFeeDetailTable mInanstance = null;

    public synchronized static PaymentFeeDetailTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PaymentFeeDetailTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + FEE_TYPE_CODE + TEXT + DOUHAO
                + FEE_TYPE_NAME + TEXT + DOUHAO + FEE_COMPANY_CODE + TEXT
                + DOUHAO + FEE_COMPANY_NAME + TEXT + DOUHAO + FEE_GROUP_CODE
                + TEXT + DOUHAO + FEE_GROUP_NAME + TEXT + DOUHAO + FEE_TIME
                + TEXT + DOUHAO + FEE_MONEY + TEXT + DOUHAO + FEE_HUOUSE_CODE
                + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }

}
