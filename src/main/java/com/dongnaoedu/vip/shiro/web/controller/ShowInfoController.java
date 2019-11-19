package com.dongnaoedu.vip.shiro.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch.TaskInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dongnaoedu.vip.shiro.entity.Organization;
import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.ShowTable;
import com.dongnaoedu.vip.shiro.entity.TestInfo;
import com.dongnaoedu.vip.shiro.entity.User;
import com.dongnaoedu.vip.shiro.service.OrganizationService;
import com.dongnaoedu.vip.shiro.service.ResultService;
import com.dongnaoedu.vip.shiro.service.TestService;
import com.dongnaoedu.vip.shiro.utils.List2Json;
import com.dongnaoedu.vip.shiro.utils.ts.TransferString2TimeStamp;
import com.dongnaoedu.vip.shiro.web.bind.annotation.CurrentUser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

//显示回复信息，及修改轮班信息
@Controller
@RequestMapping("/show")
public class ShowInfoController {
	
	 @Autowired
	 ResultService resultService;
	 @Autowired
	 TestService testService;
	 
	 @Autowired
	 OrganizationService organizationService;
	 
	 @RequestMapping(value = "/vue", method = RequestMethod.GET)
	    public String showVue() {
	        return "show/vue";
	    }
	 
	 @RequestMapping(value = "/previous", method = RequestMethod.GET)
	    public String showPrevious() {
	        return "show/vue";
	    }
	 
	 @RequestMapping(value = "/success", method = RequestMethod.GET)
	    public String showSuccess() {
	        return "show/success";
	    }
	 	
	 @RequestMapping(value = "/show", method = RequestMethod.GET)
	    public String showAdd(Model model,@CurrentUser User u) {
		 	JSONArray object = new JSONArray();
		 	List<TestInfo> results = resultService.queryTaskInfo(u.getOrganizationId());
		 	if(!results.isEmpty()) {
			 	for(TestInfo resultShow:results) {
			 		resultShow.setTime();
			 	}
			    object = JSONArray.fromObject(results);
		 	}
	        model.addAttribute("results", object);
	        model.addAttribute("op", "新增");
	        return "show/create";
	    }
	 
	 @RequestMapping(value = "/infoedit", method = RequestMethod.GET)
	    public String showCreateForm(Model model,@CurrentUser User u) {
		 	List<ShowTable> results = new ArrayList<ShowTable>();
//		 			testService.queryBelong2Comp(u.getOrganizationId());
		 	
	 	    if(u.getRoleIds().get(0) == 4) {
		 		 results = resultService.queryByPersonal(u.getId());
	 	    }
	 	    
	 	    /////////////////////////////////////////////////////////////////////////////////////
	 	    else if(u.getRoleIds().get(0) == 3) {
		 		Long uOrganizationId = organizationService.findOne(u.getOrganizationId()).getId();
		 		results.addAll(resultService.query(uOrganizationId));
		 		List<Organization> organizations = organizationService.findAll();
		 		for(Organization organization : organizations) {
		 			if(organization.getParentIds().contains(""+uOrganizationId)) {
		 				results.addAll(resultService.query(organization.getId()));
		 			}
		 		}
	 	    }
		 	/////////////////////////////////////////////////////////////////////////////////////
	 	    
	 	    else{
	 	    	if(u.getOrganizationId() == 36 || u.getOrganizationId() == 1) {
	 	    		results = resultService.queryAll();
	 	    	}else {	 	    		
	 	    		results = resultService.query(u.getOrganizationId());
	 	    	}
	 	    }
	 	    
		 	JSONArray json = new JSONArray();
		 	if(results != null) {
			 	for(ShowTable resultShow:results) {
			 		resultShow.setTime();
			 	}
			 	json = JSONArray.fromObject(results);  
		 	}
	        model.addAttribute("json", json);
	        return "show/infoedit";
	    }
		 
	 //显示所有属于该公司的人员
	 @RequestMapping(value = "/showInfo", method = RequestMethod.GET)
	    public String showInfoEdit(Model model,@CurrentUser User u) {
		 	List<ResultShow> results = new ArrayList<>();
		 	JSONArray jsonArray = new JSONArray();
		 	JSONArray object = new JSONArray();
		 	if(u.getRoleIds().get(0) == 4) {
		 		results = resultService.queryBelongByUID(u.getId());
		 	}
		 	/////////////////////////////////////////////////////////////////////////////////////
		 	else if(u.getRoleIds().get(0) == 3) {
		 		Long uOrganizationId = organizationService.findOne(u.getOrganizationId()).getId();
		 		if(resultService.queryAllBelong(uOrganizationId) != null) {
		 			results.addAll(resultService.queryAllBelong(uOrganizationId));		 			
		 		}
		 		List<Organization> organizations = organizationService.findAll();
		 		if(null != organizations) {
			 		for(Organization organization : organizations) {
			 			if(organization.getParentIds().contains(""+uOrganizationId)) {
			 				if(resultService.queryAllBelong(organization.getId()) != null) {
			 					results.addAll(resultService.queryAllBelong(organization.getId()));		 					
			 				}
			 			}
			 		}
		 		}
		 	}
		 	/////////////////////////////////////////////////////////////////////////////////////
		 	else {
			 	if(u.getOrganizationId() == 36 || u.getOrganizationId() == 1) {
			 		results = resultService.managerQueryAllBelong();
			 	}else {
			 		results = resultService.queryAllBelong(u.getOrganizationId());
				}
			}
		 	if(results != null) {
			 	for(ResultShow resultShow:results) {
			 		resultShow.setTime();
			 	}
			 	jsonArray = List2Json.List2Json(results);
			 	object = JSONArray.fromObject(results);
		 	}
	        model.addAttribute("json", jsonArray);
	        model.addAttribute("results", object);
	        model.addAttribute("op", "新增");
	        return "show/info";
	    }
		 
	 
	 @RequestMapping(value = "/export", method = RequestMethod.GET)
	 @ResponseBody
	    public Map<String, Object> export(@CurrentUser User u) {
		 	Map<String, Object> model = new HashMap<String, Object>();
		 	List<ResultShow> results = new ArrayList<>();
		 	JSONArray object = new JSONArray();
		 	if(u.getOrganizationId() == 36 || u.getOrganizationId() == 1) {
		 		results = resultService.managerQueryAllBelong();
		 	}else {
		 		Long uOrganizationId = organizationService.findOne(u.getOrganizationId()).getId();
		 		if(resultService.queryAllBelong(uOrganizationId) != null) {
		 			results.addAll(resultService.queryAllBelong(uOrganizationId));		 			
		 		}
		 		List<Organization> organizations = organizationService.findAll();
		 		if(null != organizations) {
			 		for(Organization organization : organizations) {
			 			if(organization.getParentIds().contains(""+uOrganizationId)) {
			 				if(resultService.queryAllBelong(organization.getId()) != null) {
			 					results.addAll(resultService.queryAllBelong(organization.getId()));		 					
			 				}
			 			}
			 		}
		 		}
//		 		results = resultService.queryAllBelong(u.getOrganizationId());
			}
		 	if(!results.isEmpty()) {
			 	for(ResultShow resultShow:results) {
			 		resultShow.setTime();
			 	}
			 	object = JSONArray.fromObject(results);
		 	}
	        model.put("exports", object);
	        return model;
	    }
	 
	 @RequestMapping(value = "/exportAbs", method = RequestMethod.GET)
	 @ResponseBody
	    public Map<String, Object> exportAbsent(@CurrentUser User u) {
		 	Map<String, Object> model = new HashMap<String, Object>();
		 	List<ResultShow> results = new ArrayList<>();
		 	JSONArray object = new JSONArray();
		 	if(u.getOrganizationId() == 36 || u.getOrganizationId() == 1) {
		 		results = resultService.managerQueryAllAbsent();
		 	}else {
		 		Long uOrganizationId = organizationService.findOne(u.getOrganizationId()).getId();
		 		if(resultService.queryAllBelong(uOrganizationId) != null) {
		 			results.addAll(resultService.queryAllBelong(uOrganizationId));		 			
		 		}
		 		List<Organization> organizations = organizationService.findAll();
		 		if(null != organizations) {
			 		for(Organization organization : organizations) {
			 			if(organization.getParentIds().contains(""+uOrganizationId)) {
			 				if(resultService.queryAllBelong(organization.getId()) != null) {
			 					results.addAll(resultService.queryAllOutofTimeResultBelong(organization.getId()));		 					
			 				}
			 			}
			 		}
		 		}
//		 		results = resultService.queryAllOutofTimeResultBelong(u.getOrganizationId());
			}
		 	if(!results.isEmpty()) {
			 	for(ResultShow resultShow:results) {
			 		resultShow.setTime();
			 	}
			 	object = JSONArray.fromObject(results);
		 	}
	        model.put("exportAbs", object);
	        return model;
	    }
	 
	 	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	 	@ResponseBody
	    public Map<String, Object> delete( @RequestParam Map<String,Object> param,@CurrentUser User u) {
	 		Map<String, Object> model = new HashMap<String, Object>();
	 	    try {
	 	    	String testId = (String)param.get("json");
	 	    	JSONObject myjObject = JSONObject.fromObject(testId);
	 	    	int id = (int) myjObject.get("id");
	 	    	long testid = id;
	 	    	testService.deleteTestInfo(testid);	 	    
			} catch (Exception e) {
				e.printStackTrace();
	 	    	model.put("error", "error");
			}
	 		List<TestInfo> results = testService.queryBelong2Comp(u.getOrganizationId());
		 	if(!results.isEmpty()) {
			 	for(TestInfo resultShow:results) {
			 		resultShow.setTime();
			 	}
		 	}
		 	JSONArray json = JSONArray.fromObject(results);  
	        model.put("json", json);
	        return model;
	    }
	 
	 	@RequestMapping(value = "/showQuery", method = RequestMethod.GET)
	 	@ResponseBody
	    public Map<String, Object> showQuery( @RequestParam Map<String,Object> param,@CurrentUser User u) {
	 		Map<String, Object> model = new HashMap<String, Object>();
	 		List<ShowTable> results = new ArrayList<ShowTable>();
	 		JSONArray object = new JSONArray();
	 	    String jsons = (String)param.get("date") + " 0:0:0";
	 	    long queryDateTime = TransferString2TimeStamp.transfer(jsons)-86400;
	 		results = resultService.queryByTimeStamp(queryDateTime,u.getOrganizationId(),u.getId());
	 		if(!results.isEmpty()) {
			 	for(ShowTable resultShow:results) {
			 		resultShow.setTime();
			 	}
			 	object = JSONArray.fromObject(results);
	 		}
	        model.put("result", object);
	        model.put("op", "新增");
	        return model;
	    }
	 
	 	
	 @RequestMapping(value = "/showTask", method = RequestMethod.GET)
	    public String showTaskForm(Model model,@CurrentUser User u) {
		 	List<Result> results = resultService.queryShowResultBelong(u.getOrganizationId());
	        model.addAttribute("results", results);
	        model.addAttribute("op", "新增");
	        return "show/task";
	    }
	 
	 

	    @RequestMapping(value = "/{id}/deleteTask", method = RequestMethod.GET)
	    public String showDeleteForm(@PathVariable("id") Long id, Model model) {
	        model.addAttribute("info", testService.findOne(id));
	        model.addAttribute("op", "删除");
	        return "show/edit";
	    }

//	    @RequiresPermissions("user:delete")
	    @RequestMapping(value = "/{id}/deleteTask", method = RequestMethod.POST)
	    public String delete(@PathVariable("id") Long testId, RedirectAttributes redirectAttributes) {
	        testService.deleteTestInfo(testId);
	        redirectAttributes.addFlashAttribute("msg", "删除成功");
	        return "redirect:/show/showTask";
	    }
	    
	    
	    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
	        model.addAttribute("info", testService.findOne(id));
	        model.addAttribute("op", "修改");
	        return "user/edit";
	    }

	    @RequiresPermissions("user:update")
	    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
	    public String update(TestInfo info, RedirectAttributes redirectAttributes) {
	    	testService.updateTaskInfo(info);
	        redirectAttributes.addFlashAttribute("msg", "修改成功");
	        return "redirect:/user";
	    }
	    
	 	@RequestMapping(value = "/showDateQuery", method = RequestMethod.GET)
	 	@ResponseBody
	    public Map<String, Object> showDateQuery( @RequestParam Map<String,Object> param,@CurrentUser User u) {
	 		Map<String, Object> model = new HashMap<String, Object>();
	 		List<ShowTable> results= new ArrayList<ShowTable>();
	 		JSONArray object = new JSONArray();
	 		String jsons = (String)param.get("date") + " 0:0:0";
	 	    //当天数据
	 	    long queryDateTime = TransferString2TimeStamp.transfer(jsons);
	 	    if(u.getRoleIds().get(0) == 4) {
		 		 results = resultService.queryByTimeStampPersonal(queryDateTime,u.getId());
	 	    }
	 	    else{
	 	    	if(u.getOrganizationId() == 36 || u.getOrganizationId() == 1) {
	 	    		results = resultService.queryAllByTimeStamp(queryDateTime);
	 	    	}else {	 	    		
	 	    		results = resultService.queryByTimeStamp(queryDateTime,u.getOrganizationId());
	 	    	}
	 	    }
	 	    if(!results.isEmpty()) {
			 	for(ShowTable resultShow:results) {
			 		resultShow.setTime();
			 	}
			 	object = JSONArray.fromObject(results);
	 	    }
	        model.put("result", object);
	        model.put("op", "新增");
	        return model;
	    } 	 
	    
		
}
