package com.dongnaoedu.vip.shiro.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StopWatch.TaskInfo;

import com.dongnaoedu.vip.shiro.entity.Phone;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

public interface TestDao {

	TestInfo createTestInfo(TestInfo testInfo);
	
	List<Phone> queryPhoneNumber(long currentTimestamp);
	
	void deleteTestInfo(long testInfoId);
	
	List<TestInfo> queryTestInfoBelong(long compId);

	TestInfo findTestInfo(long id);

	TestInfo updateTaskInfo(TestInfo info);
}
