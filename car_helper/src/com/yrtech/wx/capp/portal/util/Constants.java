package com.yrtech.wx.capp.portal.util;

public class Constants {

	/** 会话标识 */
	public static String LOGIN_SESSIONINFO = "sessionInfo_key";
	/** 用户会话对象，用户信息对象 */
	public static String USER_INFOVO = "userInfoVo";
	/** 用户会话对象，短信验证码 */
	public static String USER_SMS_VERIFYCODE = "verify_code";
	/** 用户会话对象，权限信息 */
	public static String SESSION_AUTH_INFO = "auth_info";
	/** 用户会话对象，一级权限 */
	public static String SESSION_ONE_LEVEL_AUTH = "one_level";
	/** 用户会话对象，二级权限 */
	public static String SESSION_TWO_LEVEL_AUTH = "two_level";
	/** 用户会话对象，三级权限 */
	public static String SESSION_THREE_LEVEL_AUTH = "three_level";
	
	/** 签名附加串码 */
	public static String MD5_RAND_STRING = "hfyrkj";
	/** 验证码验证错误次数限制 */
	public static int CHECK_SMS_RAND_CODE_LIMIT = 5;
	/** 会话中短信验证码保存时间（分钟） */
	public static int SMS_RAND_EFF_TIME = 3;
	
	/** 返回码：成功 */
	public static String RET_CODE_SUCCESS = "0000";
	/** 返回码：系统异常 */
	public static String RET_CODE_EXCEP = "-1";
	/** 返回码：查询失败 */
	public static String RET_CODE_ERROR = "1999";
	/** 返回码：参数错误 */
	public static String RET_CODE_PARAM_ERROR = "1001";
	/** 返回码：签名信息错误 */
	public static String RET_CODE_SIGN_ERROR = "1002";
	/** 返回码：登陆密码错误 */
	public static String RET_CODE_LOGIN_PWD_ERROR = "1102";
	/** 返回码：用户状态异常 */
	public static String RET_CODE_LOGIN_STATE_ERROR = "1103";
	/** 返回码：用户未登录 */
	public static String RET_CODE_USER_NOLOG = "1104";
	/** 返回码：用户注册--用户已存在 */
	public static String RET_CODE_REGIST_USER_EXISTS = "1105";
	/** 返回码：用户银行卡信息校验--银行卡信息已存在 */
	public static String RET_CODE_USER_BANKCARD_EXISTS = "1106";
	/** 返回码：用户银行卡信息校验--银行卡信息不存在 */
	public static String RET_CODE_USER_BANKCARD_NOEXISTS = "1107";
	
	/** 返回码：用户日交易笔数超限  */
	public static String RET_CODE_USER_LIMIT_DAY_CNT_ERROR = "1201";
	/** 返回码：用户日交易总金额超限  */
	public static String RET_CODE_USER_LIMIT_DAY_AMT_ERROR = "1202";
	/** 返回码：用户月交易笔数超限  */
	public static String RET_CODE_USER_LIMIT_MONTH_CNT_ERROR = "1203";
	/** 返回码：用户月交易总金额超限  */
	public static String RET_CODE_USER_LIMIT_MONTH_AMT_ERROR = "1204";
	
	/** 返回码：商户号重复  */
	public static String RET_CODE_MER_CODE_REPEAT = "1301";

	public static String JSON_RETCODE = "retCode";
	public static String JSON_RETMSG = "retMsg";
	public static String JSON_CONTENT = "content";
	public static String AMT_ZERO = "0.00";
	
	public static String NO_PRO="3344";
	
	public static String SELECT_ALL = "ALL";
	
	/** 是否标志：是 */
	public static String YES_OR_NO_FLAG_Y = "Y";
	/** 是否标志：否 */
	public static String YES_OR_NO_FLAG_N = "N";
	
	/** 状态标志：正常 */
	public static String STATE_FLAG_N = "N";
	/** 状态标志：关闭 */
	public static String STATE_FLAG_C = "C";
	
	/** 用户类型：收款用户 */
	public static String USER_TYPE_R = "R";
	/** 用户类型：付款用户 */
	public static String USER_TYPE_P = "P";
	
	/** 用户状态：正常 */
	public static String USER_STATE_N = "N";
	/** 用户状态：被关闭 */
	public static String USER_STATE_C = "C";
	/** 用户状态：锁定，密码错误次数超限 */
	public static String USER_STATE_L = "L";
	/** 用户状态：未激活，刚注册完毕 */
	public static String USER_STATE_D = "D";
	/** 用户状态：待审核，用户身份证信息补充完成 */
	public static String USER_STATE_A = "A";
	
	/** 用户证件类型：身份证 */
	public static String USER_CERT_TYPE_01 = "01";
	/** 用户证件类型：护照 */
	public static String USER_CERT_TYPE_02 = "02";
	/** 用户证件类型：军官证 */
	public static String USER_CERT_TYPE_03 = "03";
	/** 用户证件类型：回乡证 */
	public static String USER_CERT_TYPE_04 = "04";
	/** 用户证件类型：工商登记号 */
	public static String USER_CERT_TYPE_05 = "05";
	/** 用户证件类型：其他 */
	public static String USER_CERT_TYPE_99 = "99";
	
	/** 最大密码错误次数 */
	public static String USER_PWD_ERR_LIMIT = "5";
	
	/** 短信类型：验证码 */
	public static String SMS_LOG_TYPE_YZM = "1";
	/** 短信发送成功 */
	public static String SMS_LOG_STATE_SUCC = "0";
	/** 短信未发送 */
	public static String SMS_LOG_STATE_INIT = "1";
	/** 短信发送失败 */
	public static String SMS_LOG_STATE_ERR = "2";
	
	/** 订单状态：待处理 */
	public static String ORDER_LOG_STATE_A = "A";
	/** 订单状态：初始 */
	public static String ORDER_LOG_STATE_I = "I";
	/** 订单状态：成功 */
	public static String ORDER_LOG_STATE_S = "S";
	/** 订单状态：失败 */
	public static String ORDER_LOG_STATE_F = "F";
	/** 订单来源：客户端提交 */
	public static String ORDER_LOG_ORG_C = "C";
	/** 订单来源：系统导入 */
	public static String ORDER_LOG_ORG_S = "S";
	/** 订单结算状态：已结算 */
	public static String ORDER_LOG_LIQ_STATE_Y = "Y";
	/** 订单结算状态：未结算 */
	public static String ORDER_LOG_LIQ_STATE_N = "N";
	/** 订单对账标志：初始 */
	public static String ORDER_LOG_CHK_FLAG_I = "I";
	/** 订单对账标志：已对账 */
	public static String ORDER_LOG_CHK_FLAG_C = "C";
	
	/** 银行默认状态：是 */
	public static String BANK_DEF_STATE_Y = "Y";
	/** 银行默认状态：否 */
	public static String BANK_DEF_STATE_N = "N";
	/** 银行状态：正常 */
	public static String BANK_STATE_N = "N";
	/** 银行状态：关闭 */
	public static String BANK_STATE_C = "C";
	
	/** 用户快捷支付协议验证状态：成功 */
	public static String USER_BANK_CHK_STATE_S = "S";
	/** 用户快捷支付协议验证状态：失败 */
	public static String USER_BANK_CHK_STATE_F = "F";
	/** 用户快捷支付协议验证方式：短信验证 */
	public static String USER_BANK_CHK_FLAG_S = "S";
	/** 用户快捷支付协议验证方式：联机交易验证 */
	public static String USER_BANK_CHK_FLAG_O = "O";
	/** 用户快捷支付协议验证方式：批处理验证 */
	public static String USER_BANK_CHK_FLAG_B = "B";
	/** 用户快捷支付协议验证方式：控台手工验证 */
	public static String USER_BANK_CHK_FLAG_C = "C";
	/** 用户注册渠道：控台手工注册 */
	public static String USER_INFO_CHANNEL_C = "C";
	/** 用户注册渠道：客户端注册 */
	public static String USER_INFO_CHANNEL_A = "A";
	
	/** 用户支付限额状态：正常 */
	public static String LIMIT_STATE_N = "N";
	/** 用户支付限额状态：关闭 */
	public static String LIMIT_STATE_C = "C";
	
	/** 操作员状态：正常 */
	public static String OPER_STATE_N = "N";
	/** 操作员状态：被关闭 */
	public static String OPER_STATE_C = "C";
	/** 操作员状态：锁定，密码错误次数超限 */
	public static String OPER_STATE_L = "L";
	
	/** 一级权限的父权限ID：-1  */
	public static Integer AUTH_LEVEL_PARENTID = -1;
	/** 权限级别：一级 */
	public static String AUTH_LEVEL_1 = "1";
	/** 权限级别：二级 */
	public static String AUTH_LEVEL_2 = "2";
	/** 权限级别：三级 */
	public static String AUTH_LEVEL_3 = "3";
	
	/** 商品图片类型：小图 */
	public static String GOODS_IMG_TYPE_M = "M";
	/** 权限级别类型：大图 */
	public static String GOODS_IMG_TYPE_L = "L";
	
	/** 省份城市级别类型：省 */
	public static String PROV_CITY_LEVEL_1 = "1";
	/** 省份城市级别类型：市 */
	public static String PROV_CITY_LEVEL_2 = "2";
	/** 省份城市级别类型：区县 */
	public static String PROV_CITY_LEVEL_3 = "3";
	
	/** 商户状态：正常 */
	public static String MER_STATE_N = "N";
	/** 商户状态：被关闭 */
	public static String MER_STATE_C = "C";
	/** 商户状态：正常 */
	public static String MER_STATE_A = "A";
	
	/** 经办复核类型：添加订单 */
	public static String HANDLE_AUDIT_TYPE_10 = "10";
	/** 经办复核状态：经办 */
	public static String HANDLE_AUDIT_STATE_H = "H";
	/** 经办复核状态：审核完成 */
	public static String HANDLE_AUDIT_STATE_A = "A";
	/** 经办复核状态：审核失败 */
	public static String HANDLE_AUDIT_STATE_F = "F";
	
	/** 系统缓存对象：银行信息 */
	public static String CACHE_KEY_BANKINFO = "cache_bank_info";
	/** 系统缓存对象：权限信息 */
	public static String CACHE_KEY_AUTHINFO = "cache_auth_info";
	/** 系统缓存对象：商品分类信息 */
	public static String CACHE_KEY_GOODSCAT = "cache_goods_cat";
	/** 系统缓存对象：省份城市信息 */
	public static String CACHE_KEY_PROVCITY = "cache_prov_city";
	/** 系统缓存对象：快递公司信息 */
	public static String CACHE_KEY_LOGTCORP = "cache_logt_corp";
	
	public static String BLANK = "8801";
	
	public static String NO_LOGIN = "1234";
}
