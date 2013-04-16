package test;

import org.springframework.stereotype.Component;

@Component
public class ExternalExecutorDAO implements IExternalExecutorDAO {

	public ExternalExecutorDAO() {
		System.out.println(getClass().getSimpleName() + " CTOR()");

	}
}
