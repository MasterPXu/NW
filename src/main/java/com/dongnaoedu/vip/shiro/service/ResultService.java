package com.dongnaoedu.vip.shiro.service;

import java.util.List;


import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

public interface ResultService {
	void createResult(Result result);
	
	List<Result> queryResultBelongByTime(long curremtTimeStamp, long comps);
	
	List<ResultShow> queryAllResultBelong(long comps);

	List<Result> queryShowResultBelong(long comps);
	
	List<Result> queryToUpdate(String rand);
	
	void update(Result result);
	
	List<TestInfo> queryTaskInfo(long comps);
}
