package soham.factrory.demo.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import soham.factrory.demo.annotation.ServiceRegistry;
import soham.factrory.demo.service.DiffService;
@Service
@ServiceRegistry(baseClass = DiffService.class,boundIdentifiers = "q")
@Slf4j
public class DiffServiceBImpl implements DiffService {

	@Override
	public void somethingElse() {
		log.info(getClass().getName());

	}

}
