package com.example.springadvanced;

import com.example.springadvanced.domain.user.application.UserService;
import com.example.springadvanced.domain.user.domain.User;
import com.example.springadvanced.domain.user.domain.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@SpringBootTest
class SpringAdvancedApplicationTests {

	@Test
	void contextLoads() {
		// Spring Boot 애플리케이션 컨텍스트 로드 테스트
	}

	//JUnit
	@Test
	public void testFindUserById() {
		// Given
		UserRepository userRepository = new TestUserRepository();
		UserService userService = new UserService(userRepository);
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		user.setName("하재훈");
		user.setEmail("haman@example.com");
		userRepository.save(user); // 테스트용 UserRepository에 사용자 추가

		// When
		User foundUser = userService.findUserById(userId);

		// Then
		Assertions.assertNotNull(foundUser);
		Assertions.assertEquals(userId, foundUser.getId());
		Assertions.assertEquals("하재훈", foundUser.getName());
		Assertions.assertEquals("haman@example.com", foundUser.getEmail());
	}

	// 테스트용 UserRepository 구현
	private static abstract class TestUserRepository implements UserRepository {
		private Map<Long, User> users = new HashMap<>();

		@Override
		public Optional<User> findById(Long id) {
			return Optional.ofNullable(users.get(id));
		}

		@Override
		public Object save(User user) {
			users.put(user.getId(), user);
			return null;
		}
	}

	//Mockito
	@Test
	public void testFindUserById2() {
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
