package com.dongnaoedu.vip.shiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnaoedu.vip.shiro.dao.ResultDao;
import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.ShowTable;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	ResultDao resultDao;
	
	@Override
	public void createResult(Result result) {
		resultDao.createResult(result);
	}

	@Override
	public List<Result> queryResultBelongByTime(long curremtTimeStamp, long comps) {
		return resultDao.queryResultBelongByTime(curremtTimeStamp, comps);
	}

	@Override
	public List<ResultShow> queryAllResultBelong(long comps) {
		return resultDao.queryAllResultBelong(comps);
	}

	@Override
	public List<Result> queryToUpdate(String rand) {
		return resultDao.queryToUpdate(rand);
	}

	@Override
	public void update(Result result) {
		resultDao.updateResult(result);
	}

	@Override
	public List<Result> queryShowResultBelong(long comps) {
		return resultDao.queryShowResultBelong(comps);
	}

	@Override
	public List<TestInfo> queryTaskInfo(long comps) {
		return resultDao.queryTaskInfo(comps);
	}

	@Override
	public List<ShowTable> queryByTimeStamp(long time,long comps) {
		return resultDao.queryByTimeStamp(time,comps);
	}

	@Override
	public List<ResultShow> queryAllOutofTimeResultBelong(long comps) {
		return resultDao.queryAllOutofTimeResultBelong(comps);
	}

	@Override
	public List<ResultShow> queryAllBelong(long comps) {
		return resultDao.queryAllBelong(comps);
	}

	@Override
	public List<ResultShow> managerQueryAllBelong() {
		return resultDao.managerQueryAllBelong();
	}

	@Override
	public List<ResultShow> managerQueryAllAbsent() {
		return resultDao.managerQueryAllAbsent();
	}

	@Override
	public List<ResultShow> queryBelongByUID(Long id) {
		return resultDao.queryBelongByUID(id);
	}

	@Override
	public List<ShowTable> queryByTimeStampPersonal(long time, long uid) {
		return resultDao.queryByTimeStampPersonal(time,uid);
	}

	@Override
	public List<ShowTable> queryAllByTimeStamp(long queryDateTime) {
		return resultDao.queryAllByTimeStamp(queryDateTime);
	}

	@Override
	public List<ShowTable> queryByPersonal(Long id) {
		return resultDao.queryByPersonal(id);
	}

	@Override
	public List<ShowTable> queryAll() {
		return resultDao.queryAll();
	}

	@Override
	public List<ShowTable> query(Long organizationId) {
		return resultDao.query(organizationId);
	}

	@Override
	public List<ShowTable> queryByTimeStamp(long queryDateTime, Long organizationId, Long id) {
		return resultDao.queryByTimeStamp(queryDateTime,organizationId,id);
	}
}
