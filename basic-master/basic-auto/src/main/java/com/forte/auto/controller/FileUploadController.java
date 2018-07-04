package com.forte.auto.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.forte.auto.common.ExcelProcess;
import com.forte.auto.entity.ForteInterface;
import com.forte.auto.entity.ForteRunner;
import com.forte.auto.service.ForteInterfaceService;

@Controller
public class FileUploadController {
	@Resource
	ForteInterfaceService service;

	@RequestMapping(value = "upload111.html", method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response){

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("file");

		String name = multipartRequest.getParameter("name");
		System.out.println("name: " + name);
		String realFileName = file.getOriginalFilename();
		System.out.println("����ļ�����" + realFileName);
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "images/";
		File dirPath = new File(ctxPath);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		File uploadFile = new File(ctxPath + realFileName);
		try {
			FileCopyUtils.copy(file.getBytes(), uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("files", loadFiles(request));
		return new ModelAndView("success");
	}

	@RequestMapping(value = "upload.html", method = RequestMethod.POST)
	public String uploadAndRunPatch(HttpServletRequest request, HttpServletResponse response){
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile("file");
		String realFileName = file.getOriginalFilename();
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator;
		File dirPath = new File(ctxPath);
		if (!dirPath.exists()) {
			dirPath.mkdir();
		}
		File uploadFile = new File(ctxPath + realFileName);
		try {
			FileCopyUtils.copy(file.getBytes(), uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int idf_interface = Integer.parseInt(request.getParameter("idf_interface"));
		ForteInterface jiekou = service.getInterfaceDetailById(idf_interface);
		String f_method = jiekou.getF_method();
		String f_url = jiekou.getF_url();
		String fullPath = ctxPath + realFileName;
		String[] keys = jiekou.getF_parameters().split("\\|");
		
		if (keys.length == 1 && ("".equals(keys[0]) || keys[0] == null)) {
			keys[0] = "";
		}
		
		int length = keys.length;
		ExcelProcess excel = new ExcelProcess();
		int rowCount = excel.getRowCount(fullPath);
		String f_patch = "patch" + new Date().getTime();
		for(int i=1;i<=rowCount-1;i++){
			String[] values = excel.readRow(fullPath , i , length);
			String f_name = excel.readCellValue(fullPath , i , 0);
			String f_desc = excel.readCellValue(fullPath, i, 1);
			service.executeInterface(idf_interface,f_name,f_url,f_method,f_desc,keys,values,f_patch);
		}
		List resultPatch = service.getResultListByPatch(f_patch);
		request.setAttribute("jiekou", jiekou);
		request.setAttribute("resultPatch", resultPatch);
		return "executeInterfaceResult";
	}

	@RequestMapping(value = "/upload3", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile image, HttpServletRequest request) throws IOException {

		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "images\\";
		System.out.println("·����" + ctxPath);
		File file = new File(ctxPath + "/" + image.getOriginalFilename());
		// FileCopyUtils.copy(image.getBytes(),new
		// File(ctxPath+"/"+image.getOriginalFilename()));
		try {
			image.transferTo(file); // �����ϴ����ļ�
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		request.setAttribute("files", loadFiles(request));
		return "success";
	}

	@RequestMapping(value = "/upload4", method = RequestMethod.POST)
	public ModelAndView fileUpload(HttpServletRequest request, HttpServletResponse response, BindException errors)
			throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "images\\";

		File file = new File(ctxPath);
		if (!file.exists()) {
			file.mkdir();
		}
		String fileName = null;
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			// System.out.println("key: " + entity.getKey());
			MultipartFile mf = entity.getValue();
			fileName = mf.getOriginalFilename();
			File uploadFile = new File(ctxPath + fileName);
			FileCopyUtils.copy(mf.getBytes(), uploadFile);
		}
		request.setAttribute("files", loadFiles(request));
		return new ModelAndView("success");
	}

	public List<String> loadFiles(HttpServletRequest request) {
		List<String> files = new ArrayList<String>();
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "\\" + "images\\";
		File file = new File(ctxPath);
		if (file.exists()) {
			File[] fs = file.listFiles();
			String fname = null;
			for (File f : fs) {
				fname = f.getName();
				if (f.isFile()) {
					files.add(fname);
				}
			}
		}
		return files;
	}

	@RequestMapping(value="download.html")
	public ModelAndView download(HttpServletRequest request,HttpServletResponse response) {
		int idf_interface = Integer.parseInt(request.getParameter("idf_interface"));
		ForteInterface jiekou = service.getInterfaceDetailById(idf_interface);
		String f_name = jiekou.getF_name();
		String f_desc = jiekou.getF_desc();
		String f_parameters = jiekou.getF_parameters();
		if(f_parameters == null || "".equals(f_parameters)){
			f_parameters = "";
		}
		
		String ctxPath = request.getSession().getServletContext().getRealPath("/") + "temp" + File.separator;
		String fileName = "" + new Date().getTime();
		String downLoadPath = ctxPath + fileName + ".xls";
		ExcelProcess excel = new ExcelProcess();
		excel.createExcelModule(downLoadPath , f_parameters , f_name , f_desc);
		response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + "module.xls");
        try {
            InputStream inputStream = new FileInputStream(downLoadPath);
            OutputStream os = response.getOutputStream();
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }
            os.close();
            inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return null;
	}
}