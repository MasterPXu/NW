package com.dongnaoedu.vip.shiro.utils.rand;

public class RandNum {
	public static int getRand() {
	 return (int)((Math.random()*9+1)*10000000);
	}
}
