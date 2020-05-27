package com.login.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.login.service.models.Local;
import com.login.service.repository.LocalRepository;
import com.login.service.services.LocalService;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
class LocalTest {
	
	@Autowired
	private LocalService localService;
	
	@MockBean
	private LocalRepository localRepository;
	
	@Test
	void saveLocalTest() {
		Local local = new Local("465465465465", "Test", "test@test.com", "Cra 60 #45-22", 313, "123", true, true);
		when(localRepository.save(local)).thenReturn(local);
		assertEquals("200", localService.register(local));
	}
}
