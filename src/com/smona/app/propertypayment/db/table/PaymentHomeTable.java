package com.smona.app.propertypayment.db.table;

public class PaymentHomeTable extends PaymentAbstractTable {

    private static final String TABLE_NAME = "home";

    public static final String HOME_TYPE_CODE = "type_code";
    public static final String HOME_TYPE_NAME = "type_name";
    public static final String HOME_COMPANY_CODE = "company_code";
    public static final String HOME_COMPANY_NAME = "company_name";
    public static final String HOME_GROUP_CODE = "group_code";
    public static final String HOME_GROUP_NAME = "group_name";
    public static final String HOME_NUMBER = "home_number";

    protected PaymentHomeTable(String tableName) {
        super(TABLE_NAME);
    }

    private static volatile PaymentHomeTable mInanstance = null;

    public synchronized static PaymentHomeTable getInstance() {
        if (mInanstance == null) {
            mInanstance = new PaymentHomeTable(TABLE_NAME);
        }
        return mInanstance;
    }

    @Override
    public String createTableSql() {
        return "CREATE TABLE " + TABLE_NAME + "(" + _ID
                + " INTEGER PRIMARY KEY, " + HOME_TYPE_CODE + TEXT + DOUHAO
                + HOME_TYPE_NAME + TEXT + DOUHAO + HOME_COMPANY_CODE + TEXT
                + DOUHAO + HOME_COMPANY_NAME + TEXT + DOUHAO + HOME_GROUP_CODE
                + TEXT + DOUHAO + HOME_GROUP_NAME + TEXT + DOUHAO + HOME_NUMBER
                + TEXT + ")";
    }

    @Override
    public String updateTableSql() {
        return null;
    }

}
