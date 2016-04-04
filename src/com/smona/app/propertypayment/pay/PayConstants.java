package com.smona.app.propertypayment.pay;

/**
 * Created by Moth on 16/4/4.
 */
public class PayConstants {
    public static final int PAY_ENV_OK = 1;
    public static final int PAY_NOT_INSTALL = 2;
    public static final int PAY_LOW_VERSION = 3;

    public static final String PKG_WX = "com.tencent.mm";

    /*wechat*/
    //real env
    private static final String REAL_WX_APP_ID = "wx337891d7db48d44d";
    private static final String REAL_WX_PARTERN_ID = "wx337891d7db48d44d";
    private static final String REAL_WX_APP_SECRET = "a1b6810ffa45236eca82a59958f3206f";
    private static final String REAL_WX_RANDOM_STR = "wx337891d7db48d44d";
    private static final String REAL_WX_PREPAY_ID = "wx201604041637342f672628ce0955055897";
    private static final String REAL_WX_SIGN= "171AFE4526B22AF1FDF86827CFE5D96C";

    //test env
    private static final String TEST_WX_APP_ID = "wxb4ba3c02aa476ea1";
    private static final String TEST_WX_RANDOM_STR= "af520ace58491e108531bcf68eb7437f";
    private static final String TEST_WX_PARTERN_ID= "10000100";
    private static final String TEST_WX_PREPAY_ID = "wx201604041637342f672628ce0955055897";
    private static final String TEST_WX_SIGN= "171AFE4526B22AF1FDF86827CFE5D96C";

    //debug
    private static final boolean DEBUG = true;
    public static final String WX_APP_ID = DEBUG?TEST_WX_APP_ID:REAL_WX_APP_ID;
    public static final String WX_PARTERN_ID = DEBUG?TEST_WX_PARTERN_ID:REAL_WX_PARTERN_ID;
    public static final String WX_APP_SECRET = REAL_WX_APP_SECRET;
    public static final String WX_PACKAGE="Sign=WXPay";
    public static final String WX_RANDOM_STR = DEBUG?TEST_WX_RANDOM_STR:REAL_WX_RANDOM_STR;
    public static final String WX_PREPAY_ID = DEBUG?TEST_WX_PREPAY_ID:REAL_WX_PREPAY_ID;
    public static final String WX_SIGN= DEBUG?TEST_WX_SIGN:REAL_WX_SIGN;

    //alipay

}
