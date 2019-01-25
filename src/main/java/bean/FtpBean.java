package bean;
/**
 * 定义ftp服务器的相关属性
 * @author caipch
 * @date 2019年1月25日
 */
public class FtpBean {

	//服务器地址
	private String hostName;
	
	//ftp服务器默认端口
	private static int defaultport = 21;
	
	//登录名
	private String userName;
	
	//登录密码
	private String password;
	
	//需要访问的远程目录
	private String uploadDir="C://ftpserver";

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public static int getDefaultport() {
		return defaultport;
	}

	public static void setDefaultport(int defaultport) {
		FtpBean.defaultport = defaultport;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	
}
