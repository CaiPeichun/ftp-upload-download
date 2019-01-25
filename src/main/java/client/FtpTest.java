package client;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPFile;

import bean.FtpBean;
import utils.FtpUtils;

public class FtpTest {

	public static void main(String[] args) {

		FtpBean ftpBean = new FtpBean();
		FtpUtils ftpUtils = new FtpUtils();
		
		//初始化ftp信息
		ftpBean.setHostName("192.168.101.33");
		ftpBean.setUploadDir("C:\\ftpserver");
		ftpBean.setUserName("Administrator");
		ftpBean.setPassword("1721");
		
		//测试文件
		File file = new File("C:\\bk");
		
	
		try {
			ftpUtils.getFtpConnect(ftpBean);
			//测试上传
			ftpUtils.upload(file);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(file.getName());
		//测试下载
		ftpUtils.download(file, "C:\\Users\\Administrator\\Desktop\\", ftpBean.getUploadDir());
		}

}
