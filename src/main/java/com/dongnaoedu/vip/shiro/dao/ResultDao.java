package com.dongnaoedu.vip.shiro.dao;

import java.util.List;

import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.ShowTable;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

public interface ResultDao {
	void createResult(Result result);
	
	Result updateResult(Result result);
	
	List<Result> queryToUpdate(String rand);
	
	List<Result> queryResultBelongByTime(long curremtTimeStamp,long comps);
	
	List<ResultShow> queryAllResultBelong(long comps);

	List<Result> queryShowResultBelong(long comps);

	List<TestInfo> queryTaskInfo(long comps);

	List<ShowTable> queryByTimeStamp(long time, long comps);

	List<ResultShow> queryAllOutofTimeResultBelong(long comps);

	List<ResultShow> queryAllBelong(long comps);

	List<ResultShow> managerQueryAllBelong();

	List<ResultShow> managerQueryAllAbsent();

	List<ResultShow> queryBelongByUID(Long id);

	List<ShowTable> queryByTimeStampPersonal(long time, long uid);
	
	List<ShowTable> queryAllByTimeStamp(long time);

	List<ShowTable> query(long compsId);

	List<ShowTable> queryByPersonal(long uid);

	List<ShowTable> queryAll();

	List<ShowTable> queryByTimeStamp(long queryDateTime, Long organizationId, Long id);
}
