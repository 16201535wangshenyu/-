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
        /**�����б�**/
	private static Properties properties = new Properties();
	/**�����ļ���·��*/
	private static String CONFIG = "userInfo.properties";
	
	private static Scanner  sacanner;
	
	private static String username;/**�û���**/
	private static String password;/**�û�����**/

    public static void main(String[] args) {

	createUserInfoFile();/**�����û���¼�ļ�**/
    reciveUserInput();
	login();
    }

	/**
	 * �����㷨
	 * @param input
	 * @return
	 */
	public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }

    /**
	 * �����û������������û�����
	 * @param username
	 */
	private static String getUserPassword(String username) {
		try {   
			
		    File file =new File(CONFIG);
            InputStream ins = new FileInputStream(file) ;

			/**����������**/
			properties.load(ins);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(properties.getProperty(username)!=null)
		     return properties.getProperty(username) ;/**�õ��ü���֮�������**/
		else
             return "";
	}
       /**
	 * �����û�������
	 */
	
	private static void reciveUserInput() {
		
		sacanner =new Scanner(System.in);
		System.out.println("�������˺ţ�");
		while((username=sacanner.nextLine()).trim().equals(""))
			System.out.println("�˺Ų���Ϊ�գ�");
		
		System.out.println("���������룺");
		password=sacanner.nextLine();
			
	}
       /**
	 * ���е�¼
	 */
	private static void login() {
		if(sha256hex(password).equals(getUserPassword(username)))
			System.out.println("��¼�ɹ���");
		else
			System.out.println("��¼ʧ�ܣ�");
	}
	/**
	 *����һ���µ��û��ļ���д��һЩ�û��Լ���������
	 */
	private static void createUserInfoFile(){
		File file =new File("userInfo.properties");

		if(file.exists()==false){
			try {
				file.createNewFile();/**�����ļ�**/
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
