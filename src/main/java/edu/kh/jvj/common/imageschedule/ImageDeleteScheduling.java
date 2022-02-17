package edu.kh.jvj.common.imageschedule;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.jvj.admin.model.service.AdminService;

@Component
public class ImageDeleteScheduling {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	private ServletContext servletContext; //서블릿 컨테이너
	
	@Autowired
	private AdminService boardService;
	
	@Scheduled(cron = "0 * * * * *")//모든 년월일시분, 요일 0 초 마다: 매분 0초마다 라는 뜻(매분마다) 
	public void imageDelete() {
		
		String serverPath = servletContext.getRealPath("/resources/images/thumbimgs");
		File[] imgArr = new File(serverPath).listFiles();
		List<File> serverImgList = Arrays.asList(imgArr);
		List<String> dbImgList = boardService.selectImgList();
		//serverImgList : 서버에 저장된 파일 목록
		//dbImgList : DB에 저장된 파일명 목록
		
		//서버 DB 모두 비어있지 않은 경우
		if(!serverImgList.isEmpty() &&!dbImgList.isEmpty() ) {
			for(File img : serverImgList) {
				
				String serverImgName = 
						img.toString().substring(img.toString().lastIndexOf("\\")+1);
					
						//img.toString : 경로 + 파일명
						//.substring -> 문자열 시작부터 지정된 index 이전까지 문자열을 모두 삭제
				if(dbImgList.indexOf(serverImgName) == -1) {
					//DB파일명 목록에 서버 파일명과 같은 이름이 없다면
					log.info("매 분 상품이미지 Delete 스케쥴러가 작동됩니다." );
					log.info("{} 를 삭제합니다" , serverImgName);
					img.delete();
				}
				
			}
		}
		
	}
	
}
