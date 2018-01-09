package cn.ysgroup.ysdai.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 *
 */

public class UserConfig {
    // 用户主键编号
    public static final String KEY_USER_NO = "userNo";

    public static final String KEY_ID = "id";

    public static final String KEY_NAME = "name";
    // 用户昵称
    public static final String KEY_NICKNAME = "nickName";
    // 用户头像地址
    public static final String KEY_HEAD_IMAGE_URL = "headImageUrl";
    // 手机号，137****1234
    public static final String KEY_MOBILE_NO = "cardMobileNo";

    public static final String KEY_TRADE_PASSWORD = "tradePassword";

    public static final String KEY_ENABLE = "enable";

    public static final String KEY_EMAIL = "email";

    public static final String KEY_EMAIL_PASSWORD = "emailPassword";

    public static final String KEY_TYPE = "type";

    public static final String KEY_IDENTITY_NO = "identityNo";

    public static final String KEY_QRC = "qrc";

    public static final String KEY_CREATETIME = "createTime";

    public static final String KEY_UPDATE_TIME = "updateTime";

    public static final String KEY_ISINIT_PSW = "isInitPsw";
    // INIT:申请，REJECT:拒绝，AUDITED:审核通过
    public static final String KEY_RESET_STATE = "resetState";

    public static final String KEY_MEMBER = "member";

    public static final String KEY_ISBIND = "isBind";

    public static final String KEY_ISLOGIN = "isLogin";
    // 银行卡绑定状态
//	public static final String KEY_BINDING_CARD_STATUS = "bindingCardStatus";
    // 支付密码设置状态
//	public static final String KEY_TRADE_PASSWD_STATUS = "tradePasswdStatus";
    // 用户二维码
//	public static final String KEY_QRCODE = "qrcode";
    // TODO
    // 用户分组信息
//    private List<String> userGroup;
    // 绑定银行卡信息
//    private List<CardInfo> bankCard;

    public static final String KEY_CLIENT_ID = "clientId";
    // 登陆授权码
    public static final String KEY_ACCESS_TOKEN = "accessToken";
    public static final String KEY_REFRESH_TOKEN = "refreshToken";
    // 授权码有效期
    public static final String KEY_ACCESS_TOKEN_EXPIRES_IN = "accessTokenExpires";
    public static final String KEY_REFRESH_TOKEN_EXPIRES_IN = "refreshTokenExpires";

    private static final String PREFERENCE_FILE = "user_info";

    private static UserConfig INSTANCE;
    private SharedPreferences mPreferences;
    private Context mContext;

    private UserInfo mUserInfo;
    private AuthInfo mAuthInfo;

    public static UserConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserConfig();
        }

        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;

        mPreferences = mContext.getSharedPreferences(PREFERENCE_FILE, 0);
    }

    public UserInfo getUserInfo() {
        if (mUserInfo == null)
            mUserInfo = new UserInfo();

        mUserInfo.id = mPreferences.getString(KEY_ID, null);
        mUserInfo.name = mPreferences.getString(KEY_NAME, null);
        mUserInfo.nickName = (mPreferences.getString(KEY_NICKNAME, null));
        mUserInfo.headImageUrl = (mPreferences.getString(KEY_HEAD_IMAGE_URL, null));
        mUserInfo.mobileNo = (mPreferences.getString(KEY_MOBILE_NO, null));
        mUserInfo.tradePassword = mPreferences.getString(KEY_TRADE_PASSWORD, null);
        mUserInfo.enable = mPreferences.getBoolean(KEY_ENABLE, false);
        mUserInfo.email = mPreferences.getString(KEY_EMAIL, null);
        mUserInfo.emailPassword = mPreferences.getString(KEY_EMAIL_PASSWORD, null);
        mUserInfo.type = mPreferences.getString(KEY_TYPE, null);
        mUserInfo.identityNo = mPreferences.getString(KEY_IDENTITY_NO, null);
        mUserInfo.qrc = mPreferences.getString(KEY_QRC, null);
        mUserInfo.createTime = mPreferences.getString(KEY_CREATETIME, null);
        mUserInfo.updateTime = mPreferences.getString(KEY_UPDATE_TIME, null);
        mUserInfo.member = mPreferences.getString(KEY_MEMBER, null);
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        if (mUserInfo == null)
            mUserInfo = new UserInfo();
        //mUserInfo.userGroup = (userInfo.userGroup);
        //mUserInfo.bankCardList = (userInfo.bankCardList);
    }

    public AuthInfo getAuthInfo() {
        if (mAuthInfo == null)
            mAuthInfo = new AuthInfo();

        mAuthInfo.userNo = (mPreferences.getString(KEY_ID, null));
        mAuthInfo.clientId = (mPreferences.getString(KEY_CLIENT_ID, null));
        mAuthInfo.accessToken = (mPreferences.getString(KEY_ACCESS_TOKEN, null));
        mAuthInfo.refreshToken = (mPreferences.getString(KEY_REFRESH_TOKEN, null));
        mAuthInfo.accessTokenExpires = (mPreferences.getString(KEY_ACCESS_TOKEN_EXPIRES_IN, null));
        mAuthInfo.refreshTokenExpires = (mPreferences.getString(KEY_REFRESH_TOKEN_EXPIRES_IN, null));

        return mAuthInfo;
    }

    public void storeLoginInfo(LoginInfo info) {
        if (info == null)
            return;

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, info.accessToken);
        editor.putString(KEY_REFRESH_TOKEN, info.refreshToken);
        editor.putString(KEY_USER_NO, info.userNo);
        editor.putString(KEY_CLIENT_ID, info.clientId);
        editor.putString(KEY_REFRESH_TOKEN_EXPIRES_IN, info.refreshTokenExpires);
        editor.putString(KEY_ACCESS_TOKEN_EXPIRES_IN, info.accessTokenExpires);

        editor.commit();
        storeUserInfo(info.data);

        getUserInfo();
        getAuthInfo();
    }

    public void storeAuthInfo(AuthInfo info) {
        if (info == null)
            return;

        SharedPreferences.Editor editor = mPreferences.edit();

        editor.putString(KEY_USER_NO, info.userNo);
        editor.putString(KEY_CLIENT_ID, info.clientId);
        editor.putString(KEY_ACCESS_TOKEN, info.accessToken);
        editor.putString(KEY_REFRESH_TOKEN, info.refreshToken);
        editor.putString(KEY_ACCESS_TOKEN_EXPIRES_IN, info.accessTokenExpires);
        editor.putString(KEY_REFRESH_TOKEN_EXPIRES_IN, info.refreshTokenExpires);

        editor.commit();

        getUserInfo();
        getAuthInfo();
    }

    public void removeLoginInfo() {
        //Config.getInstance().setLastMobileNo(UserConfig.getInstance().getMobileNo());

        if (mUserInfo != null)
            //mUserInfo.reset();

            if (mAuthInfo != null)
                mAuthInfo.reset();

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(KEY_USER_NO);
        editor.remove(KEY_NAME);
        editor.remove(KEY_NICKNAME);
        editor.remove(KEY_HEAD_IMAGE_URL);
        editor.remove(KEY_MOBILE_NO);
        editor.remove(KEY_TRADE_PASSWORD);
        editor.remove(KEY_ENABLE);
        editor.remove(KEY_EMAIL);
        editor.remove(KEY_EMAIL_PASSWORD);
        editor.remove(KEY_TYPE);
        editor.remove(KEY_IDENTITY_NO);
        editor.remove(KEY_QRC);
        editor.remove(KEY_CREATETIME);
        editor.remove(KEY_UPDATE_TIME);

        editor.remove(KEY_ID);
        editor.remove(KEY_CLIENT_ID);
        editor.remove(KEY_ACCESS_TOKEN);
        editor.remove(KEY_REFRESH_TOKEN);
        editor.remove(KEY_ACCESS_TOKEN_EXPIRES_IN);
        editor.remove(KEY_REFRESH_TOKEN_EXPIRES_IN);

        editor.commit();
    }

    public void storeUserInfo(UserInfo info) {
        if (info == null)
            return;

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_ID, info.id);
        editor.putString(KEY_NAME, info.name);
        editor.putString(KEY_NICKNAME, info.nickName);
        editor.putString(KEY_HEAD_IMAGE_URL, info.headImageUrl);
        editor.putString(KEY_MOBILE_NO, info.mobileNo);
        editor.putString(KEY_TRADE_PASSWORD, info.tradePassword);
        editor.putBoolean(KEY_ENABLE, info.enable);
        editor.putString(KEY_EMAIL, info.email);
        editor.putString(KEY_EMAIL_PASSWORD, info.emailPassword);
        editor.putString(KEY_TYPE, info.type);
        editor.putString(KEY_IDENTITY_NO, info.identityNo);
        editor.putString(KEY_QRC, info.qrc);
        editor.putString(KEY_CREATETIME, info.createTime);
        editor.putString(KEY_UPDATE_TIME, info.updateTime);
        editor.putString(KEY_MEMBER, info.member);


        editor.commit();

        getUserInfo();
        getAuthInfo();
    }

    public String getAccessToken() {
        checkAuthInfo();

        return mAuthInfo.accessToken;
    }

    public void setAccessToken(String accessToken) {
        if (mAuthInfo != null)
            mAuthInfo.accessToken = (accessToken);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    public String getUserNo() {
        checkUserInfo();

        return mUserInfo.id;
    }

    public void setUserNo(String userNo) {
        if (mUserInfo != null)
            mUserInfo.id = (userNo);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_ID, userNo);
        editor.commit();
    }

    /**
     * 设置真实姓名
     *
     * @return
     */
    public void setName(String name) {
        if (mUserInfo == null)
            mUserInfo = new UserInfo();

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_NAME, name);
        editor.commit();
    }

    /**
     * 返回真实姓名
     *
     * @return
     */
    public String getName() {
        checkUserInfo();

        return mUserInfo.name;
    }

    /**
     * 设置员工号
     *
     * @param member
     */
    public void setMember(String member) {
        if (mUserInfo == null)
            mUserInfo = new UserInfo();

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_MEMBER, member);
        editor.commit();
    }

    /**
     * 返回员工号
     *
     * @return
     */
    public String getMember() {
        checkUserInfo();

        return mUserInfo.member;
    }

    /**
     * 设置身份证号
     *
     * @return
     */
    public void setIdentityNo(String identityNo) {
        if (mUserInfo == null)
            mUserInfo = new UserInfo();

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_IDENTITY_NO, identityNo);
        editor.commit();
    }

    /**
     * 返回身份证号
     *
     * @return
     */
    public String getIdentityNo() {
        checkUserInfo();

        return mUserInfo.identityNo;
    }

    /**
     * 判断是否已经存储身份证号
     *
     * @return
     */
    public boolean hasIdentifyNo() {
        checkUserInfo();

        if (TextUtils.isEmpty(mUserInfo.identityNo))
            return false;

        return true;
    }

    public boolean isLogin() {
        String accessToken = getAccessToken();
        if (TextUtils.isEmpty(accessToken))
            return false;

        return true;
    }

    public String getNickname() {
        checkUserInfo();

        return mUserInfo.nickName;
    }

    public String getHeadImageUrl() {
        checkUserInfo();

        return mUserInfo.headImageUrl;
    }

    public void setNickname(String nickname) {
        if (mUserInfo != null)
            mUserInfo.nickName = (nickname);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_NICKNAME, nickname);
        editor.commit();
    }

    public void setHeadImageUrl(String headImageUrl) {
        if (mUserInfo != null)
            mUserInfo.headImageUrl = headImageUrl;

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_HEAD_IMAGE_URL, headImageUrl);
        editor.commit();
    }

    public void setEmail(String email) {
        if (mUserInfo != null)
            mUserInfo.email = (email);

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_EMAIL, email);
        editor.commit();
    }

    public String getMobileNo() {
        checkUserInfo();

        return mUserInfo.mobileNo;
    }

    public Boolean getIsInitPsw(String userNo) {
        return mPreferences.getBoolean(userNo, false);
    }

    public void setIsInitPsw(String userNo, Boolean isInitPsw) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(userNo, isInitPsw);

        editor.commit();
    }

    public String getResetState() {
        return mPreferences.getString(KEY_RESET_STATE, "");
    }

    public void setResetState(String resetState) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(KEY_RESET_STATE, resetState);

        editor.commit();
    }

    private void checkUserInfo() {
        if (mUserInfo == null)
            mUserInfo = getUserInfo();
    }

    private void checkAuthInfo() {
        if (mAuthInfo == null)
            mAuthInfo = getAuthInfo();
    }

    public String getTreescore(String userNo) {
        if (userNo == null)
            return null;

        return mPreferences.getString(userNo + "_score", null);
    }

    public void setTreeScore(String userNo, String score) {
        if (userNo == null)
            return;

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(userNo + "_score", score);
        editor.commit();
    }

    public String getInitTreescore(String userNo) {
        if (userNo == null)
            return null;

        return mPreferences.getString(userNo + "init_score", null);
    }

    public void setInitTreeScore(String userNo, String score) {
        if (userNo == null)
            return;

        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(userNo + "init_score", score);
        editor.commit();
    }

    public void setIsBind(boolean isBind) {
        SharedPreferences.Editor editor = mPreferences.edit();
        String userId = mContext.getSharedPreferences("UserId", Context.MODE_PRIVATE).
                getString("UserId", "000");
        editor.putBoolean(KEY_ISBIND + userId, isBind);
        editor.commit();
    }

    public boolean getIsBind() {
        String userId = mContext.getSharedPreferences("UserId", Context.MODE_PRIVATE).
                getString("UserId", "000");
        return mPreferences.getBoolean(KEY_ISBIND + userId, false);
    }

    public boolean getIsLogin() {
        SharedPreferences preferences = mContext.getSharedPreferences("AppToken", mContext.MODE_PRIVATE);
        String faRongToken = preferences.getString("loginToken", null);
        if (faRongToken == null) {
            return false;
        } else {
            return true;
        }
    }
}
