package edu.kh.jvj.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.jvj.store.model.service.StoreService;

@Component
public class StoreSchedule {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private StoreService service;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void expiredSale() {
		int result = service.expiredSale();
		logger.info("만료된 세일상품  "+ result +"개 세일컬럼 삭제");
	}
	
}
