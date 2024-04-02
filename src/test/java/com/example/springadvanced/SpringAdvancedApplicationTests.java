package com.example.springadvanced;

import com.example.springadvanced.domain.user.application.UserService;
import com.example.springadvanced.domain.user.domain.User;
import com.example.springadvanced.domain.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class SpringAdvancedApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testFindUserById() {
		// Given
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		UserService userService = new UserService(userRepository);
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		user.setName("하재훈");
		user.setEmail("haman@example.com");
		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		// When
		User foundUser = userService.findUserById(userId);

		// Then
		Assertions.assertNotNull(foundUser);
		Assertions.assertEquals(userId, foundUser.getId());
		Assertions.assertEquals("하재훈", foundUser.getName());
		Assertions.assertEquals("haman@example.com", foundUser.getEmail());
	}



}
