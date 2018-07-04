package com.forte.auto.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.Consts;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.forte.auto.dao.IForteInterfaceDao;
import com.forte.auto.entity.ForteInterface;
import com.forte.auto.entity.ForteRunner;

@Service
public class ForteInterfaceService implements IForteInterfaceDao {

	@Resource
	IForteInterfaceDao dao;
	

	public void saveInterface(ForteInterface jiekou) {
		dao.saveInterface(jiekou);
	}

	public List<ForteInterface> showInterface(String f_source) {
		List<ForteInterface> interfaceList = dao.showInterface(f_source);
		return interfaceList;
	}

	public ForteInterface getInterfaceDetailById(int idf_interface) {
		ForteInterface jiekou = dao.getInterfaceDetailById(idf_interface);
		return jiekou;
	}

	public void executeInterface(int idf_interface, String f_name, String f_url, String f_method, String f_desc,
			String[] keys, String[] values, String f_patch) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = null;
		String str = "";
		List f_parameters = new ArrayList();
		if (keys.length == 1 && "".equals(keys[0])) {
			httppost = new HttpPost(f_url);
			str = "无参数";
		} else {
			if(keys.length == 1){
				f_parameters.add(new BasicNameValuePair(keys[0], values[0]));
				StringBuffer sb = new StringBuffer();
				str = sb.append(new BasicNameValuePair(keys[0], values[0]).toString()).toString();
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(f_parameters, Consts.UTF_8);
				httppost = new HttpPost(f_url);
				httppost.setEntity(entity);
			}else{
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < keys.length; i++) {
					f_parameters.add(new BasicNameValuePair(keys[i], values[i]));
					sb.append(new BasicNameValuePair(keys[i], values[i]).toString()).append("|");
				}
				str = sb.toString().substring(0,sb.length()-1);
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(f_parameters, Consts.UTF_8);
				httppost = new HttpPost(f_url);
				httppost.setEntity(entity);
			}
		}
		
		try {
			CloseableHttpResponse response = httpclient.execute(httppost);
			ForteRunner runner = new ForteRunner();
			runner.setF_interface_id(idf_interface);
			runner.setF_parameters(str);
			String resp = EntityUtils.toString(response.getEntity());
			if(resp.length() >= 500)
				resp = resp.substring(0,500);
			runner.setF_response(resp);
			Date now = new Date();
			runner.setF_createtime(now);
			runner.setF_patch(f_patch);
			saveRunScript(runner);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveRunScript(ForteRunner runner) {
		dao.saveRunScript(runner);
	}

	public ForteRunner getResultById(int idf_running) {
		return dao.getResultById(idf_running);
	}

	public void deleteInterface(ForteInterface jiekou) {
		dao.deleteInterface(jiekou);
	}

	public int updateInterface(ForteInterface jiekou) {
		return dao.updateInterface(jiekou);
	}

	public List<ForteRunner> getResultListByPatch(String f_patch) {
		List<ForteRunner> resultPatch = new ArrayList<ForteRunner>();
		resultPatch = dao.getResultListByPatch(f_patch);
		return resultPatch;
	}

}
