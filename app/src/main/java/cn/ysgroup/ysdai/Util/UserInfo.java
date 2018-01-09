package cn.ysgroup.ysdai.Util;

/**
 * Created by yeyafei on 2017/6/12.
 */

public class UserInfo {
	// 用户主键编号
	public String id;
	// 名字
	public String name;
	// 用户昵称
	public String nickName;
	// 用户头像地址
	public String headImageUrl;
	// 手机号，137****1234
	public String mobileNo;
	
	public String tradePassword;
	// 是否启用
	public boolean enable;
	// 邮箱
	public String email;
	
	public String emailPassword;
	// 账号类型  USER:用户  MERCHANT：商户 COMPANY：集团
	public String type;
	// 身份证号码
	public String identityNo;
	// 二维码
	public String qrc;
	// 创建时间
	public String createTime;
	// 更新时间
	public String updateTime;
	//员工号
	public String member;
	//是否綁卡
	public boolean isBind;
}
