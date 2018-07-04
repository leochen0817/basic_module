package com.forte.auto.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.forte.auto.entity.ForteInterface;
import com.forte.auto.entity.ForteRunner;

@Repository
public interface IForteInterfaceDao {

	void saveInterface(ForteInterface jiekou);

	List<ForteInterface> showInterface(String interfaceSource);

	ForteInterface getInterfaceDetailById(int idf_interface);

	void saveRunScript(ForteRunner runner);

	ForteRunner getResultById(int idf_runner);

	void deleteInterface(ForteInterface jiekou);

	int updateInterface(ForteInterface jiekou);

	List<ForteRunner> getResultListByPatch(String f_patch);

}
