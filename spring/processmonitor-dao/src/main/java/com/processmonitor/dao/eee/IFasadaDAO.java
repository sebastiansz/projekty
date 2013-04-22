
package com.processmonitor.dao.eee;

import org.springframework.stereotype.Component;

public interface IFasadaDAO {

	IExecutorDAO getExecutor();

	void setExecutor(IExecutorDAO executor);

}
