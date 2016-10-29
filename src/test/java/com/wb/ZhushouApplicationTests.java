package com.wb;

import com.wb.entity.User;
import com.wb.repository.UserRepo;
import com.wb.service.ICollectSuitService;
import com.wb.service.IRegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZhushouApplicationTests {


	@Autowired
	private IRegisterService registerService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ICollectSuitService iCollectSuitService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void _测试注册用户(){
		registerService.register();
	}

	@Test
	public void _保存用户(){
		User user = new User();
		user.setUserId("12312ha1");
		user.setSession("12312");
		user.setExtra("哈哈as");
		user.setGmtCreate(new Timestamp(new Date().getTime()));
		user.setGmtModified(new Timestamp(new Date().getTime()));

		userRepo.save(user);
	}

	@Test
	public void _测试收集衣服(){
		//iCollectSuitService.collectSuit();
	}

}
