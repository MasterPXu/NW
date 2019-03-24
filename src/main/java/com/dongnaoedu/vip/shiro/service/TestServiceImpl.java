package com.dongnaoedu.vip.shiro.service;


import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch.TaskInfo;

import com.dongnaoedu.vip.shiro.dao.TestDao;
import com.dongnaoedu.vip.shiro.entity.TestInfo;


@Service
public class TestServiceImpl implements TestService {
	@Autowired
	TestDao testDao;
	
	@Override
	public void setTestInfo(TestInfo testInfo) {
		testDao.createTestInfo(testInfo);
	}

	@Override
	public List<String> getBeSetPhoneNumber(long queryTimeStamp) {
		List<String> arrayList = testDao.queryPhoneNumber(queryTimeStamp);
		return arrayList;
	}

	@Override
	public void deleteTestInfo(long testId) {
		testDao.deleteTestInfo(testId);
	}

	@Override
	public TestInfo findOne(long id) {
		return testDao.findTestInfo(id);
	}

	@Override
	public void updateTaskInfo(TestInfo info) {
		testDao.updateTaskInfo(info);
	}
	


}
