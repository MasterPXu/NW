package com.dongnaoedu.vip.shiro.utils;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ServiceLoader extends ContextLoaderListener{
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		super.contextDestroyed(event);
		try{
			BackGroundScheduler.stop();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		initWebApplicationContext(event.getServletContext());
		try{
			BackGroundScheduler.run();
		}catch(Exception ex){
		System.out.println(ex.getMessage());
		}
	}
	
}
