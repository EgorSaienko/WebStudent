import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import sumdu.edu.ua.controller.UserController;
import sumdu.edu.ua.model.User;
import sumdu.edu.ua.security.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private Model model;

    @Test
    void testRegisterUser() {
        // Arrange
        User user = new User();
        user.setUsername("admin");
        user.setPassword("password");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        String result = userController.registerUser(user, model);

        // Assert
        assertEquals("registration", result);
        verify(userRepository, times(1)).save(user);
        verify(model, times(1)).addAttribute(eq("message"), contains("succesfully saved"));
    }
}
