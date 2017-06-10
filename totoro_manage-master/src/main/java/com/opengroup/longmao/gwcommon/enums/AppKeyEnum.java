package com.opengroup.longmao.gwcommon.enums;

/**
 * app
 * @author
 *
 */
public enum AppKeyEnum {
	//appKey  微信公众号key 
	//chiName 微信公众号val
	SDK_AES_KEY_WX_PUB(100001,"WX_PUB"),
	SDK_AES_KEY_WX(100002,"WX"),
	SDK_AES_KEY_ALIPAY(100003,"ALIPAY"),
	;
	private Integer appKey;
	private String chiName;
	
	AppKeyEnum(Integer appKey, String chiName) {
		this.appKey = appKey;
		this.chiName = chiName;
	}
	
	public Integer getAppKey() {
		return appKey;
	}
	
	public String getChiName() {
		return chiName;
	}

	public static AppKeyEnum getEnumByType(Integer appKey){
		if (appKey == null)
            return null;
        for (AppKeyEnum chlInfoTmp : AppKeyEnum.values()) {
            if (chlInfoTmp.getAppKey().equals(appKey))
                return chlInfoTmp;
        }
        return null;
	}
	
	public static AppKeyEnum[] getSdkChlKeyList(){
		return AppKeyEnum.values();
	}

}
