package edu.zhangzl.tools;

/**
 * 
 * @author Administrator
 *该类作为Json特定情况下返回JSON数据格式的标记量
 *在edu.zhangzl.action的RegisterAction.checkCountOrName()方法
 *执行时，若验证不存在注册申请的用户名和用户账号时
 *返回该类实例作为标记， 
 *flag在返回时设置为1
 */
public class JsonFlag {
	private int flag = 1;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
	
}
