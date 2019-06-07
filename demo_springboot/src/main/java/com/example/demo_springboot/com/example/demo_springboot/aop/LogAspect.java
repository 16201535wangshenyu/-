package com.example.demo_springboot.com.example.demo_springboot.aop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.example.demo_springboot.User;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindingResult;

@Aspect
@Configuration
public class LogAspect {
	 private Logger logger = LoggerFactory.getLogger(LogAspect.class);
	   
	    //@Pointcut("execution(public * webadv.example.controller.HomeController.check(..))")
	    //public void webLog(){}
	    
	    //@Before("webLog()")
	    @Before("execution(public * com.example.demo_springboot..HomeController.check(..)) && args(user,result,..)")
	    public void doBefore(User user, BindingResult result) throws Throwable {
	    	logger.info(String.format("Account:%s, login %s.", user.getAccount(),result.hasErrors()?"failed":"succeeded"));
	    	writeToFile("Account:"+user.getAccount()+","+"login:"+(result.hasErrors()?"failed":"succeeded"));
	    }

	    private boolean writeToFile(String content) {
	    	try {
		    	File file =new  File("log.txt");
		    	if(!file.exists()) {
		    		file.createNewFile();
		    	}
		        BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
		        bw.append(content+"\n");
		        bw.flush();
		        bw.close();
		        return true;
		    	
	    	}catch (Exception e) {
				System.out.print("日志写入文件失败");
				e.printStackTrace();
				return false;
			}
	    	
	    }

}
