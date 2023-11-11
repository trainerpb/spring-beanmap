package soham.factrory.demo.publications;

import soham.factrory.demo.annotation.ServiceRegistry;
import soham.factrory.demo.service.SampleService;

@ServiceRegistry(baseClass = SampleService.class,boundIdentifiers = {"FTE"})
public class FTEWageCalculatorWageServiceImpl implements WageCalcuatorService {

	@Override
	public WageDTO calculateWage(Employee e) {
		// TODO Auto-generated method stub
		return null;
	}

}
