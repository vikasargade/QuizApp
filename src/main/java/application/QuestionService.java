package application;
// utilization interface
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public interface QuestionService {

     public int addQuestion( Question  newQuestion);
     public int removeQuestion(int questionId);

     int modifyQuestion(int questionId, String question);

     int modifyOptions(int questionId,String option1, String option2, String option3);

     int modifyAnswer(int questionId, String answer);

     List<Question> displayAllQuestion();


     String password();
}
