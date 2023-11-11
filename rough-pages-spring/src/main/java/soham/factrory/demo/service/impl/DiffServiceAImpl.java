package soham.factrory.demo.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import soham.factrory.demo.annotation.ServiceRegistry;
import soham.factrory.demo.service.DiffService;
@Service
@ServiceRegistry(baseClass = DiffService.class,boundIdentifiers = "p")
@Slf4j
public class DiffServiceAImpl implements DiffService {

	@Override
	public void somethingElse() {
		log.info(getClass().getName());

	}

}
