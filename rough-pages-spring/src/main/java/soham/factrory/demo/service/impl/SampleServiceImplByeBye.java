package soham.factrory.demo.service.impl;

import org.springframework.stereotype.Service;

import soham.factrory.demo.annotation.ServiceRegistry;
import soham.factrory.demo.service.SampleService;

@Service
@ServiceRegistry(baseClass = SampleService.class,boundIdentifiers = "x")
public class SampleServiceImplByeBye implements SampleService {

	@Override
	public String serve() {
		// TODO Auto-generated method stub
		return "bye ";
	}

}
