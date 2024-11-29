import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.ModelAndView;
import sumdu.edu.ua.controller.ContentController;
import sumdu.edu.ua.model.Content;
import sumdu.edu.ua.model.Student;
import sumdu.edu.ua.repo.ContentRepo;
import sumdu.edu.ua.repo.StudentRepo;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContentControllerTest {

    @InjectMocks
    private ContentController contentController;

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private ContentRepo contentRepo;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Test
    void testFormContent() throws Exception {
        // Arrange
        int studentId = 1;
        Student student = new Student(studentId, "John", "Doe", 20, "john@example.com", "CS101", "Engineering");
        List<Content> contentList = List.of(new Content(1, studentId, "Math", "A", 95));
        when(studentRepo.getOne(studentId)).thenReturn(student);
        when(contentRepo.getScoresByStId(studentId)).thenReturn(contentList);

        // Act
        ModelAndView result = contentController.formContent(String.valueOf(studentId), request, response);

        // Assert
        assertEquals("score", result.getViewName());
        assertEquals(student, result.getModel().get("user"));
        assertEquals(contentList, result.getModel().get("scores"));
    }

    @Test
    void testUpdateScore() {
        // Arrange
        int scoreId = 1;
        int scoreNum = 95;
        String scoreLetter = "A";

        doNothing().when(contentRepo).updateScore(scoreId, scoreNum, scoreLetter);

        // Act
        String result = contentController.updateScore(scoreId, scoreNum, scoreLetter);

        // Assert
        assertEquals("redirect:/UserContent?id2=" + scoreId, result);
        verify(contentRepo, times(1)).updateScore(scoreId, scoreNum, scoreLetter);
    }

    @Test
    void testDeleteScore() {
        // Arrange
        int scoreId = 1;

        doNothing().when(contentRepo).deleteScore(scoreId);

        // Act
        String result = contentController.deleteScore(scoreId);

        // Assert
        assertEquals("redirect:/UserContent?id2=" + scoreId, result);
        verify(contentRepo, times(1)).deleteScore(scoreId);
    }
}
