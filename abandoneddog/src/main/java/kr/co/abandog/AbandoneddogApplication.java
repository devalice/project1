package kr.co.abandog;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import kr.co.abandog.controller.AbandogController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@EnableJpaAuditing
@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
@Log4j2
public class AbandoneddogApplication {

	private final AbandogController openAPIController;
	
	public static void main(String[] args) {
		SpringApplication.run(AbandoneddogApplication.class, args);
	}
	
	//매일 09시마다 실행
	@Scheduled(cron = "0 0 09 * * *")
	public void run() {
		log.info("openAPI 병합 작업 수행: "+ new Date());
		openAPIController.callopenAPIWithJSON();
		openAPIController.callImgopenAPIWithJSON();
	}

}
