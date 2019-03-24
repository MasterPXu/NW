package com.dongnaoedu.vip.shiro.dao;

import java.util.List;

import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

public interface ResultDao {
	void createResult(Result result);
	
	Result updateResult(Result result);
	
	List<Result> queryToUpdate(String rand);
	
	List<Result> queryResultBelongByTime(long curremtTimeStamp,long comps);
	
	List<ResultShow> queryAllResultBelong(long comps);

	List<Result> queryShowResultBelong(long comps);

	List<TestInfo> queryTaskInfo(long comps);
}
