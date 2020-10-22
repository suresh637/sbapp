package com.sb.app;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.sb.app.persistance.EmployeeRepository;
import com.sb.app.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
class SbappApplicationTests {


	@Autowired
	private EmployeeServiceImpl empService;
	
	@MockBean
	private EmployeeRepository empRepository;
	
	@Test
	void contextLoads() {
	}
	
//	
//	@Test
//	public void getUsersTest() {
//		when(empRepository.findAll()).thenReturn(Stream
//				.of(new User(376, "Danile", 31, "USA"), new User(958, "Huy", 35, "UK")).collect(Collectors.toList()));
//		assertEquals(2, service.getUsers().size());
//	}
	
	
//	@Test
//	public void saveUserTest() {
//		User user = new User(999, "Pranya", 33, "Pune");
//		when(repository.save(user)).thenReturn(user);
//		assertEquals(user, service.addUser(user));
//	}

}
