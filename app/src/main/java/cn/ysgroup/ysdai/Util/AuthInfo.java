package cn.ysgroup.ysdai.Util;

/**
 *
 */

public class AuthInfo {
	public String clientId;
	public String userNo;
	public String mobileNo;
	public String accessToken;
	public String refreshToken;
	public String accessTokenExpires;
	public String refreshTokenExpires;
	
	public void reset() {
		clientId = null;
		accessToken = null;
		refreshToken = null;
		accessTokenExpires = "";
		refreshTokenExpires = "";
	}
}
