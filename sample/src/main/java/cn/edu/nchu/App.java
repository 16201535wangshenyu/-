package cn.edu.nchu;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.security.MessageDigest;
import java.util.Properties;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
        /**属性列表**/
	private static Properties properties = new Properties();
	/**配置文件的路径*/
	private static String CONFIG = "userInfo.properties";
	
	private static Scanner  sacanner;
	
	private static String username;/**用户名**/
	private static String password;/**用户密码**/

    public static void main(String[] args) {

	createUserInfoFile();/**创建用户记录文件**/
    reciveUserInput();
	login();
    }

	/**
	 * 加密算法
	 * @param input
	 * @return
	 */
	public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }

    /**
	 * 根据用户姓名来加载用户密码
	 * @param username
	 */
	private static String getUserPassword(String username) {
		try {   
			
		    File file =new File(CONFIG);
            InputStream ins = new FileInputStream(file) ;

			/**加载输入流**/
			properties.load(ins);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(properties.getProperty(username)!=null)
		     return properties.getProperty(username) ;/**得到用加密之后的密码**/
		else
             return "";
	}
       /**
	 * 接收用户的输入
	 */
	
	private static void reciveUserInput() {
		
		sacanner =new Scanner(System.in);
		System.out.println("请输入账号：");
		while((username=sacanner.nextLine()).trim().equals(""))
			System.out.println("账号不能为空！");
		
		System.out.println("请输入密码：");
		password=sacanner.nextLine();
			
	}
       /**
	 * 进行登录
	 */
	private static void login() {
		if(sha256hex(password).equals(getUserPassword(username)))
			System.out.println("登录成功！");
		else
			System.out.println("登录失败！");
	}
	/**
	 *创建一个新的用户文件，写入一些用户以及密码数据
	 */
	private static void createUserInfoFile(){
		File file =new File("userInfo.properties");

		if(file.exists()==false){
			try {
				file.createNewFile();/**创建文件**/
				FileOutputStream fos=new FileOutputStream(file);
				BufferedWriter bw =new BufferedWriter(new OutputStreamWriter(fos));
				bw.write("16201501="+sha256hex("123")+"\n");
				bw.write("16201502="+sha256hex("123")+"\n");
				bw.write("16201503="+sha256hex("123")+"\n");
				bw.write("16201504="+sha256hex("123")+"\n");
				bw.write("16201505="+sha256hex("123")+"\n");
				bw.write("16201535="+sha256hex("123")+"\n");
				bw.flush();
				bw.close();
				bw.close();

			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}





}
