package kr.co.abandog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.co.abandog.controller.AbandogRestController;

@SpringBootTest
public class ControllerTest {

	@Autowired
	private AbandogRestController openAPIController;
	
	@Test
	public void test1() {
		openAPIController.callImgopenAPIWithJSON();
	}
}
