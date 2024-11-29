import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import sumdu.edu.ua.controller.StudentController;
import sumdu.edu.ua.model.Student;
import sumdu.edu.ua.repo.StudentRepo;
import org.springframework.context.ApplicationContext;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private Model model;

    @Mock
    private ApplicationContext mockFactory; // Мок контексту Spring

    @Test
    void testAddStudent() {
        // Arrange
        when(request.getParameter("name")).thenReturn("John");
        when(request.getParameter("surname")).thenReturn("Doe");
        when(request.getParameter("age")).thenReturn("20");
        when(request.getParameter("email")).thenReturn("john@example.com");
        when(request.getParameter("group")).thenReturn("CS101");
        when(request.getParameter("faculty")).thenReturn("Engineering");
        when(request.getParameter("_csrf")).thenReturn("mockedCsrfToken");

        Student studentMock = new Student();
        when(mockFactory.getBean("Student")).thenReturn(studentMock); // Мок повернення об'єкта студента

        when(studentRepo.save(any(Student.class))).thenReturn(studentMock);

        // Act
        ModelAndView result = studentController.addStudent(request, model);

        // Assert
        verify(studentRepo, times(1)).save(any(Student.class));
        assertEquals("student", result.getViewName());
    }
}

