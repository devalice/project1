package kr.co.abandog.scheduled;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.co.abandog.controller.AbandogRestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class SceduledTasks {

	private final AbandogRestController abandogController;
	
	//매일 10시마다 실행
	@Scheduled(cron = "0 0 10 * * *")
	public void run() {
		log.info("openAPI 병합 작업 수행: "+ new Date());
		abandogController.callopenAPIWithJSON();
		abandogController.callImgopenAPIWithJSON();
	}
}
