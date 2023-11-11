package soham.factrory.demo.annotation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServiceRegistryLookupService implements SmartInitializingSingleton {

	private Map<Class<?>, Map<String, Object>> serviceImplMap;

	private ApplicationContext applicationContext;

	public ServiceRegistryLookupService(ApplicationContext applicationContext) {

		this.applicationContext = applicationContext;

		this.serviceImplMap = new HashMap<>();
		if (log.isDebugEnabled()) {
			log.debug("init " + applicationContext + " for " + this.getClass());
		}
	}
	
	@Override
	public void afterSingletonsInstantiated() {
		this.populateService();
		log.debug("Done populating the service map");

	}

	public void populateService() {
		Map<String, Object> lookupResult = applicationContext.getBeansWithAnnotation(ServiceRegistry.class);
		log.info("ServiceRegistry look up result: {}", lookupResult);
		lookupResult.entrySet().forEach(beanEntry -> {
			final String beanName = beanEntry.getKey();
			final Object theBean = beanEntry.getValue();

			ServiceRegistry annotationOnTheBean = applicationContext.findAnnotationOnBean(beanName,
					ServiceRegistry.class);
			Class<?> theBaseServiceClass = annotationOnTheBean.baseClass();
			log.debug("{} is a matching bean for {}", beanName, theBaseServiceClass);
			Map<String, Object> beanBindingMapForTheBaseServiceClass = serviceImplMap.containsKey(theBaseServiceClass)
					? serviceImplMap.get(theBaseServiceClass)
					: new HashMap<>();

			final String[] serviceBindings = annotationOnTheBean.boundIdentifiers();
			for (String binding : serviceBindings) {
				if (beanBindingMapForTheBaseServiceClass.containsKey(binding)) {
					throw new IllegalArgumentException(
							"Duplicate bean binding for " + binding + " on bean-" + beanName);
				}
				beanBindingMapForTheBaseServiceClass.put(binding, theBean);
			}
			serviceImplMap.put(theBaseServiceClass, beanBindingMapForTheBaseServiceClass);

		});

	}



	@SuppressWarnings("unchecked")
	public <T> T getServiceImpl(String identifier, Class<T> baseClass) {
		if (serviceImplMap.containsKey(baseClass)) {
			if (serviceImplMap.get(baseClass).containsKey(identifier)) {
				return (T) serviceImplMap.get(baseClass).get(identifier);
			} else {
				throw new UnsupportedOperationException(
						"No supported for identifier : " + identifier + " for base class " + baseClass);
			}

		} else {
			throw new UnsupportedOperationException("No supported base class " + baseClass);
		}
	}
}
