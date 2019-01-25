package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import bean.FtpBean;

public class FtpUtils {

	private static FTPClient ftpClient;
	
	/**
	 * 获取ftp连接
	 * @throws IOException 
	 * @throws SocketException 
	 */
	public static FTPClient getFtpConnect(FtpBean ftpBean) throws SocketException, IOException {
		ftpClient = new FTPClient();
	
		//连接ftp服务器
		ftpClient.connect(ftpBean.getHostName(), ftpBean.getDefaultport());
		//登录ftp服务器
		ftpClient.login(ftpBean.getUserName(), ftpBean.getPassword());
		
		if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			System.out.println("未连接到FTP，用户名或密码错误");
			ftpClient.disconnect();
		}else {
			System.out.println("FTP连接成功");
			
		}
		return ftpClient;
	}
	
	
	/**
	 * 文件上传
	 * @throws IOException 
	 */
	public static void upload(File file) throws IOException {
		//若是文件夹
		if(file.isDirectory()) {
			ftpClient.makeDirectory(file.getName());
			ftpClient.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (String childfile : files) {
				File childfilecopy = new File(file.getPath()+File.separator+childfile);
				if (childfilecopy.isDirectory()) {
					upload(childfilecopy);
					ftpClient.changeToParentDirectory();
				}else {
					File childfilecopy2 = new File(file.getPath()+File.separator+childfile);
					FileInputStream inputStream = new FileInputStream(childfilecopy2);
					ftpClient.storeFile(childfilecopy2.getName(), inputStream);
					inputStream.close();
				}
			}
		}else {
			//不是文件夹则直接存储
			File parentfile = new File(file.getPath());
			FileInputStream inputStream = new FileInputStream(parentfile);
			ftpClient.storeFile(parentfile.getName(), inputStream);
			inputStream.close();
		}
	}
	
	
	/**
	 * 文件下载(单个文件下载）
	 * @param ftpFile
	 * @param relativeLocalPath 下载到本地的绝对路径
	 * @param relativeRemotePath 要下载的远程ftp服务器相对路径
	 * @throws IOException 
	 */
	public static void download(File ftpFile,String relativeLocalPath,String relativeRemotePath) {
		//读取文件的输出流
		OutputStream outputStream = null;
		
		
		if (ftpFile.isFile()) {
			try {
				//下载到本地的文件路径
				File localFile = new File(relativeLocalPath+ftpFile.getName());
				//判断文件是否存在，若存在则返回
				if(localFile.exists()) {
					return;
				}else {
					outputStream = new FileOutputStream(relativeLocalPath+ftpFile.getName());
					ftpClient.retrieveFile(ftpFile.getName(), outputStream);
					outputStream.flush();
				}
			}catch (Exception e) {
				// TODO: handle exception
			}finally {
				if(outputStream!=null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
	 
		}
	}	
}
