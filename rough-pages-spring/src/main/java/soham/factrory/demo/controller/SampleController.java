package soham.factrory.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import soham.factrory.demo.annotation.ServiceRegistryLookupService;
import soham.factrory.demo.publications.FTEWageCalculatorWageServiceImpl;
import soham.factrory.demo.publications.WageCalcuatorService;
import soham.factrory.demo.service.DiffService;
import soham.factrory.demo.service.SampleService;
import soham.factrory.demo.service.factory.SampleServiceFactory;

@RestController
@RequiredArgsConstructor
public class SampleController {

	private final SampleServiceFactory factory;
	private final ServiceRegistryLookupService lookupService;
	
	@GetMapping("/x")
	public String handleGet(@RequestParam("x")String x) {
		SampleService sampleService=lookupService.getServiceImpl(x, SampleService.class);
		String empType="";
		WageCalcuatorService wageCalculatorWageServiceImpl=lookupService.getServiceImpl(empType, WageCalcuatorService.class);
		if(null!=x) {
			return sampleService.serve();
		}
		return null;
	}
	
	@GetMapping("/test")
	public String handleGet2(@RequestParam("x")String x) {
		DiffService diffService=lookupService.getServiceImpl(x, DiffService.class);;
		diffService.somethingElse();
		return "";
	}
}
