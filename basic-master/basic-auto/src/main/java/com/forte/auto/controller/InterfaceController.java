package com.forte.auto.controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.forte.auto.entity.ForteInterface;
import com.forte.auto.entity.ForteRunner;
import com.forte.auto.entity.HRBatch;
import com.forte.auto.service.ForteInterfaceService;

@Controller
public class InterfaceController {

	@Resource
	ForteInterfaceService service;
	
	@RequestMapping(value="toInterfaceJsp")
	public String toInterfaceJsp(HttpServletRequest request){
		List<ForteInterface> interfacelist = service.showInterface(request.getParameter("f_source"));
		request.setAttribute("f_source", request.getParameter("f_source"));
		request.setAttribute("interfaceList", interfacelist);
		return "interfaceList";
	}
	
	@RequestMapping(value="toSaveInterfaceJsp")
	public String toSaveInterfaceJsp(HttpServletRequest request,Model model){
		request.setAttribute("f_source", request.getParameter("f_source"));
		ForteInterface forteInterface = new ForteInterface();
		model.addAttribute(forteInterface);
		return "saveInterface";
	}
	
	@RequestMapping(value="deleteInterface")
	public String deleteInterface(HttpServletRequest request){
		int idf_interface = Integer.parseInt(request.getParameter("idf_interface"));
		String f_source = service.getInterfaceDetailById(idf_interface).getF_source();
		ForteInterface jiekou = new ForteInterface();
		jiekou.setIdf_interface(Integer.parseInt(request.getParameter("idf_interface")));
		Date now = new Date();
		jiekou.setF_updatetime(now);
		service.deleteInterface(jiekou);
		List<ForteInterface> interfacelist = service.showInterface(f_source);
		request.setAttribute("f_source", f_source);
		request.setAttribute("interfaceList", interfacelist);
		return "interfaceList";
	}
	
	@RequestMapping(value="toUpdateInterfaceJsp")
	public String toUpdateInterfaceJsp(HttpServletRequest request){
		ForteInterface jiekou = service.getInterfaceDetailById(Integer.parseInt(request.getParameter("idf_interface")));
		request.setAttribute("jiekou", jiekou);
		return "updateInterface";
	}
	
	@RequestMapping(value="updateInterface")
	public String updateInterface(HttpServletRequest request){
		ForteInterface jiekou = new ForteInterface();
		jiekou.setIdf_interface(Integer.parseInt(request.getParameter("idf_interface")));
		jiekou.setF_name(request.getParameter("f_name"));
		jiekou.setF_desc(request.getParameter("f_desc"));
		jiekou.setF_url(request.getParameter("f_url"));
		jiekou.setF_method(request.getParameter("f_method"));
		jiekou.setF_parameters(request.getParameter("f_parameters"));
		Date now = new Date();
		jiekou.setF_updatetime(now);
		service.updateInterface(jiekou);
		List<ForteInterface> interfacelist = service.showInterface(request.getParameter("f_source"));
		request.setAttribute("f_source", request.getParameter("f_source"));
		request.setAttribute("interfaceList", interfacelist);
		return "interfaceList";
	}
	
	@RequestMapping(value="toInterfaceDetailJsp")
	public String toInterfaceDetailJsp(HttpServletRequest request , @RequestParam String idf_interface){
		ForteInterface jiekou = service.getInterfaceDetailById(Integer.parseInt(idf_interface));
		request.setAttribute("jiekou", jiekou);
		return "interfaceDetail";
	}
	
	@RequestMapping(value="saveInterface")
	public String saveInterface(@Valid ForteInterface forteInterface,  
            BindingResult result,HttpServletRequest request) {
		if (result.hasErrors()) {
			request.setAttribute("f_source", request.getParameter("f_source"));
			return "saveInterface";
		}
		forteInterface.setF_name(request.getParameter("f_name"));
		forteInterface.setF_desc(request.getParameter("f_desc"));
		forteInterface.setF_url(request.getParameter("f_url"));
		forteInterface.setF_method(request.getParameter("f_method"));
		forteInterface.setF_parameters(request.getParameter("f_parameters"));
		String f_source = request.getParameter("f_source");
		forteInterface.setF_source(f_source);
		Date now = new Date();
		forteInterface.setF_createtime(now);
		service.saveInterface(forteInterface);
		List<ForteInterface> interfacelist = service.showInterface(f_source);
		request.setAttribute("f_source", f_source);
		request.setAttribute("interfaceList", interfacelist);
		return "interfaceList";
	}
	
	@RequestMapping(value="showInterface")
	public String showInterface(HttpServletRequest request){
		List<ForteInterface> interfacelist = service.showInterface(request.getParameter("f_source"));
		request.setAttribute("interfaceList", interfacelist);
		return "interfaceList";
	}
	
	@RequestMapping(value="toRunInterfaceJsp")
	public String toRunInterfaceJsp(HttpServletRequest request){
		int idf_interface = Integer.parseInt(request.getParameter("idf_interface"));
		ForteInterface jiekou = service.getInterfaceDetailById(idf_interface);
		request.setAttribute("jiekou", jiekou);
		return "runInterface";
	}
	
	@RequestMapping(value="executeInterfaceResult")
	public String executeInterface(HttpServletRequest request){
		int idf_interface = Integer.parseInt(request.getParameter("idf_interface"));
		String f_name = request.getParameter("f_name");
		String f_url = request.getParameter("f_url");
		String f_method = request.getParameter("f_method");
		String f_desc = request.getParameter("f_desc");
		String[] keys = request.getParameter("f_parameters").split("\\|");
		String[] values = request.getParameterValues("paramList");
		if (keys.length == 1 && ("".equals(keys[0]) || keys[0] == null)) {
			keys[0] = "";
		}
		if (values.length == 1 && ("".equals(values[0]) || values[0] == null)) {
			values[0] = "";
		}

		String f_patch = "patch" + new Date().getTime();
		service.executeInterface(idf_interface,f_name,f_url,f_method,f_desc,keys,values,f_patch);
		List<ForteRunner> resultPatch = service.getResultListByPatch(f_patch);
		ForteInterface jiekou = service.getInterfaceDetailById(idf_interface);
		request.setAttribute("jiekou", jiekou);
		request.setAttribute("resultPatch", resultPatch);
		return "executeInterfaceResult";
	}
	
	@RequestMapping(value="toBatchJsp")
	public String toBatchJsp(HttpServletRequest request){
		java.sql.Connection conn = null;
		String sql;
		List<HRBatch> batchList = new ArrayList<HRBatch>();
		String url = "jdbc:mysql://172.95.65.18:3306/hr_manage?"
				+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("成功加载MySQL驱动程序");
			conn = DriverManager.getConnection(url);
			java.sql.Statement st = conn.createStatement();
			sql = "select id,batch_date,PROCEDURE_NAME,EXECUTE_BEGIN_TIME,EXECUTE_END_TIME,EXECUTE_STATE from rpt_record_history order by id";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				HRBatch batch = new HRBatch();
				batch.setId(rs.getInt(1));
				batch.setBatch_date(rs.getString(2));
				batch.setProcedure_name(rs.getString(3));
				batch.setExecute_begin_date(rs.getTimestamp(4));
				batch.setExecute_end_date(rs.getTimestamp(5));
				batch.setExecute_state(rs.getString(6));
				batchList.add(batch);
			}
		} catch (SQLException e) {
			System.out.println("MySQL操作错误");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.setAttribute("batchList", batchList);
		return "batchList";
	}

}
