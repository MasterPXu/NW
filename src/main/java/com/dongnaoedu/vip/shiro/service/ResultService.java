package com.dongnaoedu.vip.shiro.service;

import java.util.List;


import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.ShowTable;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

public interface ResultService {
	void createResult(Result result);
	
	List<Result> queryResultBelongByTime(long curremtTimeStamp, long comps);
	
	List<ResultShow> queryAllResultBelong(long comps);

	List<Result> queryShowResultBelong(long comps);
	
	List<Result> queryToUpdate(String rand);
	
	void update(Result result);
	
	List<TestInfo> queryTaskInfo(long comps);
	
	List<ShowTable> queryByTimeStamp(long time, long compsid);
	
	List<ResultShow> queryAllOutofTimeResultBelong(long comps);

	List<ResultShow> queryAllBelong(long comps);

	List<ResultShow> managerQueryAllBelong();
	
	List<ResultShow> managerQueryAllAbsent();

	List<ResultShow> queryBelongByUID(Long id);
	
	List<ShowTable> queryByTimeStampPersonal(long time, long uid);

	List<ShowTable> queryAllByTimeStamp(long queryDateTime);

	List<ShowTable> queryByPersonal(Long id);

	List<ShowTable> queryAll();

	List<ShowTable> query(Long organizationId);

	List<ShowTable> queryByTimeStamp(long queryDateTime, Long organizationId, Long id);
	
}
