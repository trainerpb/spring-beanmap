package soham.factrory.demo.publications;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class WageCalculatorServiceFactory {

	private final ContractorWageCalculatorWageServiceImpl contractorWageCalculatorWageServiceImpl;
	private final FTEWageCalculatorWageServiceImpl fteWageCalculatorWageServiceImpl;
	private final TraineeWageCalculatorWageServiceImpl traineeWageCalculatorWageServiceImpl;

	public WageCalcuatorService getWageCaculatorService(EmpType empType) {
		switch (empType) {
		case CONTRACTOR:
			return contractorWageCalculatorWageServiceImpl;

		case FTE:
			return fteWageCalculatorWageServiceImpl;
		case TRAINEE:
			return traineeWageCalculatorWageServiceImpl;
		default:
			throw new IllegalArgumentException("Invalid emp type: " + empType);
		}
	}
}
