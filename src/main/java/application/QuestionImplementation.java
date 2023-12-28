package application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class QuestionImplementation implements QuestionService{

    static Connection conn;

    static
    {
        String url = "jdbc:mysql://localhost:3306/questiondb";
        try {
            conn = DriverManager.getConnection(url,"root","tiger");


        } catch (SQLException e) {
            System.err.println("CONNECTION UNSUCESSFUL");
            System.exit(1);
        }
    }


    @Override
    public int addQuestion(Question newQuestion) {

        String insertQuery = "INSERT INTO QUESTION_INFO(QUESTION , OPTION1 , OPTION2 , OPTION3  , ANSWER)" +
                "VALUES (? , ? , ? , ? , ?) ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            pstmt.setString(1 , newQuestion.getQuestion());
            pstmt.setString(2 , newQuestion.getOption1());
            pstmt.setString(3 , newQuestion.getOption2());
            pstmt.setString(4 , newQuestion.getOption3());
            pstmt.setString(5 , newQuestion.getAnswer());

            return pstmt.executeUpdate() ;
        } catch (SQLException e) {
            System.err.println("INVALID QUESTION DATA !!");
        }
        return 0 ;
    }

    @Override
    public int removeQuestion(int questionId) {
        String deleteQuery = "DELETE FROM QUESTION_INFO WHERE QUESTION_ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(deleteQuery);
            pstmt.setInt(1 , questionId);
            return  pstmt.executeUpdate() ;
        } catch (SQLException e) {
            System.err.println("INVALID QUESTION ID ");
        }
        return 0;

    }

    @Override
    public int modifyQuestion(int questionId, String question) {
        String updateQuestion = "UPDATE QUESTION_INFO SET QUESTION = ? WHERE QUESTION_ID = ? ";

        try {
            PreparedStatement pstmt = conn.prepareStatement(updateQuestion);
            pstmt.setString(1,question);
            pstmt.setInt(2,questionId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("CANNOT MODIFY QUESTION");
        }
        return 0;
    }

    @Override
    public int modifyOptions(int questionId,String option1, String option2, String option3) {

        String updateOption = "UPDATE QUESTION_INFO SET OPTION1 = ? , OPTION2 = ? , OPTION3 = ? WHERE QUESTION_ID = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(updateOption);
            pstmt.setString(1,option1);
            pstmt.setString(2,option2);
            pstmt.setString(3,option3);
            pstmt.setInt(4,questionId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("CANNOT MODIFY OPTIONS");
        }
        return 0;
    }

    @Override
    public int modifyAnswer(int questionId, String answer) {
        String updateAnswer = "UPDATE QUESTION_INFO SET ANSWER = ? WHERE QUESTION_ID = ?";

        try {
            PreparedStatement pstmt  = conn.prepareStatement(updateAnswer);
            pstmt.setString(1,answer);
            pstmt.setInt(2,questionId);

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("CANNOT MODIFY ANSWER");
        }
        return 0;
    }

    @Override
    public List<Question> displayAllQuestion() {
        String displayAll = "SELECT * FROM QUESTION_INFO";
        List<Question> questionAll = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(displayAll);

            while (rs.next())
            {
               // Question q = new Question();

                String question  =rs.getString(2);
                String option1 = rs.getString(3);
                String option2 = rs.getString(4);
                String option3 = rs.getString(5);
                String answer = rs.getString(6);

                Question questionQ = new Question(question,option1,option2,option3,answer);
                questionQ.setQuestionId(rs.getInt(1));
                questionAll.add(questionQ);
            }

        } catch (SQLException e) {
            System.err.println("CANNOT DISPLAY QUESTION");
        }
        return questionAll;
    }

    @Override
    public String password() {

        String pass= "SELECT * FROM PASS_INFO";
        String p = "";
        try {
            Statement stmt = conn.createStatement();
           ResultSet rs= stmt.executeQuery(pass);

           while (rs.next())
           {
               p = rs.getString(1);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
      return p;
    }
}
