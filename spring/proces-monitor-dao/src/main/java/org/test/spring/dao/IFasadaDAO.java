
package org.test.spring.dao;

import org.springframework.stereotype.Component;

public interface IFasadaDAO {

	IExecutorDAO getExecutor();

	void setExecutor(IExecutorDAO executor);

}
