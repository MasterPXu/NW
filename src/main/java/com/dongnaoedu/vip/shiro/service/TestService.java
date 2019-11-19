package com.dongnaoedu.vip.shiro.service;


import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicTabbedPaneUI.TabbedPaneLayout;

import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch.TaskInfo;

import com.dongnaoedu.vip.shiro.entity.Phone;
import com.dongnaoedu.vip.shiro.entity.TestInfo;


public interface TestService {
//	//预先加入所有测评信息-条
//	void setTestInfo(TestInfo testInfo);
//	
//	//存入分数
//	void updateTestInfo(TestInfo testInfo);
//	
//	//计算总分用
//	List<TestInfo> findTestInfoByUserId(Long beanTestId,Long tableId);
//	
//	List<TestInfo> findTestableTableItem(Long tableId,String beanTestName,String whoTest);
//	
//	List<TestInfo> findAllTestableTableItem(Long tableId,String whoTest);
//
//	boolean ifUserAssessable(Long tableId, Long whoTest, Long beanTest);
//
//	List<TestInfo> findAllTestableTableItem(Long tableId, Long id, Long beantestpeopleid);
//	
//	void setScore(long id,int score,Timestamp timestamp);
//
//	List<DownloadExcel> findAllTestItemSum();
//
//	List<TotalEntity> findTotalScore();
//	
//	void setGiveup(long id, Timestamp currentTimestamp);
	
	void setTestInfo(TestInfo testInfo);
	
	List<Phone> getBeSetPhoneNumber(long queryTimeStamp);
	
	void deleteTestInfo(long testId);
	
	TestInfo findOne(long id);

	void updateTaskInfo(TestInfo info);
	
	List<TestInfo> queryBelong2Comp(long compid);
}
