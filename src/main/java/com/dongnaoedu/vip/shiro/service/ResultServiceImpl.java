package com.dongnaoedu.vip.shiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dongnaoedu.vip.shiro.dao.ResultDao;
import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
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
		// TODO Auto-generated method stub
		return resultDao.queryTaskInfo(comps);
	}
}
