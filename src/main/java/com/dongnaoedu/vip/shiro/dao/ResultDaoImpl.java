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

import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

@Repository
public class ResultDaoImpl implements ResultDao {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    
    @Override
	public void createResult(Result result) {
    	 final String sql = "insert into sys_result(taskInfo,phoneNumber) values(?,?)";
	        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
	        jdbcTemplate.update(new PreparedStatementCreator() {
	            @Override
	            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
	                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
	                int count = 1;
	                psst.setString(count++, result.getTaskInfo());
	                psst.setString(count++, result.getPhoneNumber());      
	                psst.setString(count++, result.getTaskResult());
	                psst.setLong(count++, result.getReplyTime());
	                return psst;
	            }
	        }, keyHolder);
	        result.setId(keyHolder.getKey().longValue());
	}

    /**
     * TODO 根据时间戳显示回复
     */
	@Override
	public List<Result> queryResultBelongByTime(long curremtTimeStamp, long comps) {
		String sql = "select phoneNumber from sys_result where timeStamp=? and comps=?";
        List<Result> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Result.class), curremtTimeStamp,comps);
        if (results.size() == 0) {
            return null;
        }
		return results;
	}

	@Override
	public List<ResultShow> queryAllResultBelong(long comps) {
//		String sql = "SELECT a.* \r\n" + 
//				"FROM sys_result AS a\r\n" + 
//				"LEFT JOIN sys_taskinfo AS b on b.phoneNumber = a.phoneNumber\r\n" + 
//				"WHERE b.comps=? ORDER BY a.phoneNumber";
		String sql = "SELECT b.id as id,a.taskInfo as taskInfo,a.phoneNumber AS phoneNumber,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,b.`name` AS replyName\r\n" + 
				"FROM sys_result AS a \r\n" + 
				"LEFT JOIN sys_taskinfo AS b on b.phoneNumber = a.phoneNumber\r\n" + 
				"WHERE b.comps=? ORDER BY a.phoneNumber asc,a.id desc";
        List<ResultShow> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ResultShow.class),comps);
        if (results.size() == 0) {
            return null;
        }
		return results;
	}


	@Override
	public Result updateResult(Result result) {
        String sql = "update sys_result set taskResult=?,replyTime=? where id=?";
        jdbcTemplate.update(
                sql,
               result.getTaskResult(),result.getReplyTime(),result.getId());
        return result;
	}

	@Override
	public List<Result> queryToUpdate(String rand) {
		String sql = "select id,taskInfo,phoneNumber,taskResult,replyTime from sys_result where taskInfo=? and taskResult='0' and replyTime=0";
        List<Result> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Result.class),rand);
        if (results.size() == 0) {
            return null;
        }
		return results;
	}

	@Override
	public List<Result> queryShowResultBelong(long comps) {
		String sql = "SELECT * FROM sys_taskinfo WHERE comps=?";
		 List<Result> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Result.class),comps);
        if (results.size() == 0) {
            return null;
        }
		return results;
	}

	
	@Override
	public List<TestInfo> queryTaskInfo(long comps) {
		String sql = "SELECT id,name,phoneNumber,timeStamp,comps,whoSet FROM sys_taskinfo WHERE comps=?";
		List<TestInfo> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(TestInfo.class),comps);
        if (results.size() == 0) {
            return new ArrayList<TestInfo>();
        }
		return results;
	}

}
