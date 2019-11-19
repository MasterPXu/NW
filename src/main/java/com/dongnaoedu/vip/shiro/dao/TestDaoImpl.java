package com.dongnaoedu.vip.shiro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch.TaskInfo;

import com.dongnaoedu.vip.shiro.entity.Phone;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

@Repository
public class TestDaoImpl implements TestDao {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
	@Override
	public TestInfo createTestInfo(TestInfo testInfo) {
		 final String sql = "insert into sys_taskinfo(name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,comps,whoSet) values(?,?,?,?,?,?,?)";

	        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	        jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
	                int count = 1;
	                psst.setString(count++, testInfo.getName());
	                psst.setString(count++, testInfo.getPhoneNumber());
	                psst.setLong(count++, testInfo.getTimeStamp());
	                psst.setLong(count++, testInfo.getSetDateTimeStamp());
	                psst.setString(count++, testInfo.getDateStr());
	                psst.setLong(count++, testInfo.getComps());
	                psst.setLong(count++, testInfo.getWhoSet());
	                return psst;
	            }
	        }, keyHolder);
	        testInfo.setId(keyHolder.getKey().longValue());
	        return testInfo;
	}

	@Override
	public List<Phone> queryPhoneNumber(long currentTimestamp) {
		String sql = "select phoneNumber from sys_taskinfo where timeStamp=?";
        List<Phone> phoneNumbers = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Phone.class), currentTimestamp);
        if (phoneNumbers.size() == 0) {
            return null;
        }
		return phoneNumbers;
	}

	
	@Override
	public void deleteTestInfo(long testInfoId) {
        String sql = "delete from sys_taskinfo where id=?";
        jdbcTemplate.update(sql, testInfoId);
	}

	@Override
	public List<TestInfo> queryTestInfoBelong(long compId) {

		String sql = "select id,name,phoneNumber,timeStamp,comps,whoSet from sys_taskinfo where comps=?";
        List<TestInfo> testInfos = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TestInfo.class), compId);
        if (testInfos.size() == 0) {
            return null;
        }
		return testInfos;
	}

	@Override
	public TestInfo findTestInfo(long id) {
		String sql = "select * from sys_taskinfo where id=?";
        List<TestInfo> testInfos = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TestInfo.class), id);
        if (testInfos.size() == 0) {
            return null;
        }
		return testInfos.get(0);
	}

	@Override
	public TestInfo updateTaskInfo(TestInfo info) {
		 String sql = "update sys_taskinfo set name=?, phoneNumber=?,timeStamp=?  where id=?";
	        jdbcTemplate.update(
	                sql,
	                info.getName(),info.getPhoneNumber(),info.getTimeStamp(),info.getId());
	        return info;
	}
	
}
