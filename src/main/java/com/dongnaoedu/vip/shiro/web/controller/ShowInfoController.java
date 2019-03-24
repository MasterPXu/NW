package com.dongnaoedu.vip.shiro.web.controller;

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

import com.dongnaoedu.vip.shiro.entity.Result;
import com.dongnaoedu.vip.shiro.entity.ResultShow;
import com.dongnaoedu.vip.shiro.entity.TestInfo;
import com.dongnaoedu.vip.shiro.entity.User;
import com.dongnaoedu.vip.shiro.service.ResultService;
import com.dongnaoedu.vip.shiro.service.TestService;
import com.dongnaoedu.vip.shiro.utils.List2Json;
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
	 
	 
	 @RequestMapping(value = "/vue", method = RequestMethod.GET)
	    public String showVue() {
	        return "show/vue";
	    }
	 
	 @RequestMapping(value = "/previous", method = RequestMethod.GET)
	    public String showPrevious() {
	        return "show/vue";
	    }
	 	
	 @RequestMapping(value = "/show", method = RequestMethod.GET)
	    public String showAdd(Model model,@CurrentUser User u) {
		 	List<TestInfo> results = resultService.queryTaskInfo(u.getOrganizationId());
		 	for(TestInfo resultShow:results) {
		 		resultShow.setTime();
		 	}
		 	JSONArray object = JSONArray.fromObject(results);
	        model.addAttribute("results", object);
	        model.addAttribute("op", "新增");
	        return "show/create";
	    }
	 
	 @RequestMapping(value = "/showInfo", method = RequestMethod.GET)
	    public String showCreateForm(Model model,@CurrentUser User u) {
		 	List<ResultShow> results = resultService.queryAllResultBelong(u.getOrganizationId());
		 	if(results != null) {
			 	for(ResultShow resultShow:results) {
			 		resultShow.setTime();
			 	}
		 	}
		 	JSONArray jsonArray = List2Json.List2Json(results);
		 	JSONArray object = JSONArray.fromObject(results);
	        model.addAttribute("json", jsonArray);
	        model.addAttribute("results", object);
	        model.addAttribute("op", "新增");
	        return "show/info";
	    }
		 
	 	@RequestMapping(value = "/showQuery", method = RequestMethod.GET)
	 	@ResponseBody
	    public Map<String, Object> showQuery(@RequestParam(value="id") String id,@RequestParam(value="str") String str,@CurrentUser User u) {
	 		Map<String, Object> model = new HashMap<String, Object>();
	 		List<TestInfo> results = resultService.queryTaskInfo(u.getOrganizationId());
		 	for(TestInfo resultShow:results) {
		 		resultShow.setTime();
		 	}
		 	JSONArray object = JSONArray.fromObject(results);
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
}
