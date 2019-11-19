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
import com.dongnaoedu.vip.shiro.entity.ShowTable;
import com.dongnaoedu.vip.shiro.entity.TestInfo;

@Repository
public class ResultDaoImpl implements ResultDao {
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
	
    
    @Override
	public void createResult(Result result) {
    	 final String sql = "insert into sys_result(taskInfo,phoneNumber,taskResult,replyTime,sendTime) values(?,?,?,?,?)";
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
	                psst.setLong(count++, result.getSendTime());
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

	
	
	//管理员显示所有人员信息
	@Override
	public List<ResultShow> managerQueryAllBelong() {
		String sql = "SELECT a.id as id,a.taskInfo as taskInfo,a.phoneNumber AS phoneNumber,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,b.`name` AS replyName\r\n" + 
				"				FROM sys_result AS a  \r\n" + 
				"				LEFT JOIN sys_taskinfo AS b \r\n" + 
				"				on b.phoneNumber = a.phoneNumber and b.`timeStamp` = a.sendTime \r\n" + 
				"				WHERE a.id=a.id GROUP BY a.`id` ORDER BY a.replyTime ASC;\r\n" + 
				"";
        List<ResultShow> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ResultShow.class));
        if (results.size() == 0) {
            return null;
        }
		return results;
	}
	
	//公司显示所有人员信息
	@Override
	public List<ResultShow> queryAllBelong(long comps) {
		String sql = "SELECT a.id as id,a.taskInfo as taskInfo,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,a.phoneNumber AS phoneNumber,b.`name` AS replyName\r\n" + 
				"FROM sys_result AS a \r\n" + 
				"LEFT JOIN sys_taskinfo AS b \r\n" + 
				"on b.phoneNumber = a.phoneNumber and b.timeStamp = a.sendTime\r\n" + 
				"WHERE b.comps=? GROUP BY a.id ORDER BY a.replyTime ASC";
        List<ResultShow> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ResultShow.class),comps);
        if (results.size() == 0) {
            return null;
        }
		return results;
	}
		
	//显示在岗状态人员信息
	@Override
	public List<ResultShow> queryAllResultBelong(long comps) {
		String sql = "SELECT a.id as id,a.taskInfo as taskInfo,a.phoneNumber AS phoneNumber,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,b.`name` AS replyName\r\n" + 
				"FROM sys_result AS a \r\n" + 
				"LEFT JOIN sys_taskinfo AS b on b.phoneNumber = a.phoneNumber and b.timeStamp = a.sendTime\r\n" + 
				"WHERE b.comps=? AND 0<=a.replyTime-b.`timeStamp` AND a.replyTime-b.`timeStamp`<=900  GROUP BY b.id ORDER BY b.`timeStamp` ASC";

		//		String sql = "SELECT b.id as id,a.taskInfo as taskInfo,a.phoneNumber AS phoneNumber,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,b.`name` AS replyName\r\n" + 
//				"FROM sys_result AS a \r\n" + 
//				"LEFT JOIN sys_taskinfo AS b on b.phoneNumber = a.phoneNumber\r\n" + 
//				"WHERE b.comps=? ORDER BY a.phoneNumber asc,a.id desc";
        List<ResultShow> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ResultShow.class),comps);
        if (results.size() == 0) {
            return null;
        }
		return results;
	}

	//管理员导出所有脱岗人员
	@Override
	public List<ResultShow> managerQueryAllAbsent() {
		String sql = "SELECT a.id as id,a.taskInfo as taskInfo,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,a.phoneNumber AS phoneNumber,b.`name` AS replyName\r\n" + 
				"FROM sys_result AS a \r\n" + 
				"LEFT JOIN sys_taskinfo AS b on b.phoneNumber = a.phoneNumber and b.timeStamp = a.sendTime\r\n" + 
				"WHERE 1=1 AND a.replyTime=0 GROUP BY a.id ORDER BY b.`timeStamp` ASC";
        List<ResultShow> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ResultShow.class));
        if (results.size() == 0) {
            return null;
        }
		return results;
	}
	
	//显示脱岗状态人员
	@Override
	public List<ResultShow> queryAllOutofTimeResultBelong(long comps) {
		String sql = "SELECT a.id as id,a.taskInfo as taskInfo,a.phoneNumber AS phoneNumber,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,b.`name` AS replyName\r\n" + 
				"FROM sys_result AS a \r\n" + 
				"LEFT JOIN sys_taskinfo AS b on b.phoneNumber = a.phoneNumber and b.timeStamp = a.sendTime\r\n" + 
				"WHERE b.comps=? AND a.replyTime=0 GROUP BY b.id ORDER BY b.`timeStamp` ASC";
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
		String sql = "select id,taskInfo,phoneNumber,taskResult,replyTime,sendTime from sys_result where taskInfo=? and taskResult='0'";
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

	@Override
	public List<ResultShow> queryBelongByUID(Long id) {
		String sql = "SELECT a.id as id,a.taskInfo as taskInfo,a.phoneNumber AS phoneNumber,a.taskResult AS taskResult,a.replyTime as replyTime,(SELECT name FROM sys_organization WHERE id=b.comps) as  compsName,b.`name` AS replyName\r\n" + 
				"FROM sys_result AS a \r\n" + 
				"LEFT JOIN sys_taskinfo AS b on b.phoneNumber = a.phoneNumber and b.timeStamp = a.sendTime\r\n" + 
				"WHERE b.whoSet=? GROUP BY b.id ORDER BY b.`timeStamp` ASC";
        List<ResultShow> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ResultShow.class),id);
        if (results.size() == 0) {
            return null;
        }
		return results;
	}

	@Override
	public List<ShowTable> queryByTimeStamp(long time,long compsId) {
		String sql = "SELECT a.id,a.name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,b.name AS comps,whoSet \r\n" + 
				"FROM sys_taskinfo AS a\r\n" + 
				"LEFT JOIN sys_organization AS b ON a.comps = b.id\r\n" + 
				"WHERE setDateTimeStamp=? and comps=? GROUP BY dateStr,name";
		List<ShowTable> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ShowTable.class),time,compsId);
        if (results.size() == 0) {
            return new ArrayList<ShowTable>();
        }
		return results;
	}

	
	@Override
	public List<ShowTable> queryByTimeStampPersonal(long time, long uid) {
		String sql = "SELECT a.id,a.name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,b.name AS comps,whoSet \r\n" + 
				"FROM sys_taskinfo AS a\r\n" + 
				"LEFT JOIN sys_organization AS b ON a.comps = b.id\r\n" + 
				"WHERE setDateTimeStamp=? and whoSet=?";
		List<ShowTable> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ShowTable.class),time,uid);
        if (results.size() == 0) {
            return new ArrayList<ShowTable>();
        }
		return results;
	}

	@Override
	public List<ShowTable> queryAllByTimeStamp(long time) {
		String sql = "SELECT a.id,a.name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,b.name AS comps,whoSet \r\n" + 
				"FROM sys_taskinfo AS a\r\n" + 
				"LEFT JOIN sys_organization AS b ON a.comps = b.id\r\n" + 
				"WHERE a.setDateTimeStamp=?";
		List<ShowTable> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ShowTable.class),time);
        if (results.size() == 0) {
            return new ArrayList<ShowTable>();
        }
		return results;
	}

	@Override
	public List<ShowTable> query(long compsId) {
		String sql = "SELECT a.id,a.name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,b.name AS comps,whoSet \r\n" + 
				"FROM sys_taskinfo AS a\r\n" + 
				"LEFT JOIN sys_organization AS b ON a.comps = b.id\r\n" + 
				"WHERE comps=?";
		List<ShowTable> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ShowTable.class),compsId);
        if (results.size() == 0) {
            return new ArrayList<ShowTable>();
        }
		return results;
	}

	
	@Override
	public List<ShowTable> queryByPersonal(long uid) {
		String sql = "SELECT a.id,a.name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,b.name AS comps,whoSet \r\n" + 
				"FROM sys_taskinfo AS a\r\n" + 
				"LEFT JOIN sys_organization AS b ON a.comps = b.id\r\n" + 
				"WHERE whoSet=?";
		List<ShowTable> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ShowTable.class),uid);
        if (results.size() == 0) {
            return new ArrayList<ShowTable>();
        }
		return results;
	}

	@Override
	public List<ShowTable> queryAll() {
		String sql = "SELECT a.id,a.name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,b.name AS comps,whoSet \r\n" + 
				"FROM sys_taskinfo AS a\r\n" + 
				"LEFT JOIN sys_organization AS b ON a.comps = b.id\r\n" + 
				"WHERE 1=1";
		List<ShowTable> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ShowTable.class));
        if (results.size() == 0) {
            return new ArrayList<ShowTable>();
        }
		return results;
	}

	@Override
	public List<ShowTable> queryByTimeStamp(long queryDateTime, Long organizationId, Long id) {
		String sql = "SELECT a.id,a.name,phoneNumber,timeStamp,setDateTimeStamp,dateStr,b.name AS comps,whoSet \r\n" + 
				"FROM sys_taskinfo AS a\r\n" + 
				"LEFT JOIN sys_organization AS b ON a.comps = b.id\r\n" + 
				"WHERE setDateTimeStamp=? and comps=? and whoSet= ? GROUP BY dateStr,name";
		List<ShowTable> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ShowTable.class),queryDateTime,organizationId,id);
        if (results.size() == 0) {
            return new ArrayList<ShowTable>();
        }
		return results;
	}
}
