package soham.factrory.demo.service.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import soham.factrory.demo.annotation.ServiceRegistryLookupService;
import soham.factrory.demo.service.SampleService;
import soham.factrory.demo.service.impl.SampleServiceImplByeBye;
import soham.factrory.demo.service.impl.SampleServiceImplHelloWorld;

@Service
@RequiredArgsConstructor
public class SampleServiceFactory {
	private final SampleServiceImplHelloWorld sampleServiceImplHelloWorld;
	private final SampleServiceImplByeBye sampleServiceImplByeBye;
	private final ApplicationContext applicationContext;

//	public SampleService getService(String identifier) {
//		switch (identifier) {
//		case "x":
//
//			return sampleServiceImplHelloWorld;
//		case "y":
//			return sampleServiceImplByeBye;
//		default:
//			return null;
//		}
//
//	}
	
	@Bean
	public ServiceRegistryLookupService lookupService() {
		return new ServiceRegistryLookupService(applicationContext);
	}
}
