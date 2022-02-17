package edu.kh.jvj.common.classupdateschedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.jvj.onedayclass.model.service.OnedayClassService;

@Component
public class ClassUpdate {
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private OnedayClassService classService;
	
	@Scheduled(cron = "0 0 * * * *")//모든 년월일시분, 요일 0 초 마다: 매분 0초마다 라는 뜻(매분마다) 
	public void imageDelete() {
		int result = classService.doUpdateOverdueClass();
		log.info("날짜가 지난 클래스의 상태코드를 3으로 변경합니다. 변경된 행의 개수 : {}",result);
	}
}
