package application;

import java.util.List;
import java.util.Scanner;

public class Main {

    static QuestionService questionService = new QuestionImplementation(); // upcasting
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("------------------------------------------------");
        System.out.println("1. ADD QUESTION");
        System.out.println("2. REMOVE QUESTION");
        System.out.println("3. UPDATE QUESTION, OPTIONS AND ANSWER");
        System.out.println("4. DISPLAY ALL QUESTION");
        System.out.println("5. TAKE TEST");
        System.out.println("6. EXIT");

        int ch = sc.nextInt();

        if(ch<5)
        {
            System.out.println("ENTER YOUR PASSWORD");
            String pass = sc.next();
            String originalPass = questionService.password();
            if(!pass.equals(originalPass))
                System.exit(0);

        }

        System.out.println("------------------------------------------------");
        switch (ch)
        {
            case 1:
                addQuestion();
                break;
            case 2:
                 removeQuestion();
                break;
            case 3:
                 updateQuestion();
                break;
            case 4:
                 displayAllQuestion();
                break;
            case 5:
                 takeTest();
                break;
            case 6:
                System.out.println("APPLICATION CLOSED");
                System.exit(0);
                break;

            default:
                System.out.println("INVALID OUTPUT");
        }
        main(args);
    }

    private static void takeTest() {

        List<Question> questionList = questionService.displayAllQuestion();
        int marks =0;

        System.out.println("READY FOR TEST");
        System.out.println("NOTE: CHOOSE OPTION IN THE FORM OF NUMBER (E.X 1,2,3)");
        String ans = sc.nextLine();

        for(Question q:questionList)
        {
            System.out.println("Q"+ q.getQuestionId()+ " ."+q.getQuestion());
            System.out.println("1. "+ q.getOption1());
            System.out.println("2. "+q.getOption2());
            System.out.println("3. "+q.getOption3());
            System.out.println("ENTER YOUR ANS");
            ans = sc.nextLine();

            String acuAns = q.getAnswer();
            if(ans.equals(acuAns))
                marks+=5;
            else
                marks-=2;
        }

        System.out.println("\n\n ------------------------------------------------");
        System.out.println("YOUR TOTAL SCORE ARE: "+marks);
        System.out.println("---------------------------------------------------------");
        System.exit(0);
    }

    private static void displayAllQuestion() {
        System.out.println("-------------------------------------------------------------");
         List<Question> questionAll = questionService.displayAllQuestion();

         for(Question q: questionAll)
         {
             // System.out.println(q.getQuestionId() +"\t " + q.getQuestion() +"\t" + q.getOption1()+"\t"+ q.getOption2()+"\t"+q.getOption3() +"\t"+q.getAnswer());

             System.out.println("Q"+ q.getQuestionId()+ " ."+q.getQuestion());
             System.out.println("1. "+ q.getOption1());
             System.out.println("2. "+q.getOption2());
             System.out.println("3. "+q.getOption3());
             System.out.println("------->" +q.getAnswer());
             System.out.println("\n---------------------------------------\n");
         }
        System.out.println("\n\n");
    }


    public static void updateQuestion(){
        System.out.println("--------------------------------------------------------------");
        System.out.println("1. MODIFY QUESTION ");
        System.out.println("2. MODIFY OPTIONS ");
        System.out.println("3. MODIFY ANSWER");
        System.out.println("4. BACK TO MAIN SECTION ");
        int ch = sc.nextInt();
        System.out.println("--------------------------------------------------------------");
        switch (ch){
            case 1:
                 modifyQuestion() ;
                break;
            case 2:
                 modifyOptions();
                break;
            case 3:
                modifyAnswer();
                break;
            case 4 :
                return;  // IT WILL RETURN TO THE MAIN METHOD
            default:
                System.out.println("INVALID OPTION");
        }

        updateQuestion();

    }

    private static void modifyAnswer() {
        System.out.println("---------------------------------");
        System.out.println("ENTER QUESTION ID");
        int questionId = sc.nextInt();
        System.out.println("ENTER ANSWER");
        String answer = sc.nextLine();
        answer = sc.nextLine();

        int n = questionService.modifyAnswer(questionId,answer);
        System.out.println(n+" ANSWER MODIFIED ");
        System.out.println("\n\n");
    }

    private static void modifyOptions() {

        System.out.println("-------------------------------------------------------");
        System.out.println("ENTER QUESTION ID");
        int questionId = sc.nextInt();
        System.out.println("ENTER OPTION 1");
        String option1 = sc.nextLine();
        option1 = sc.nextLine();
        System.out.println("ENTER OPTION 2");
        String option2 = sc.nextLine();
        System.out.println("ENTER OPTION 3");
        String option3 = sc.nextLine();

        int n = questionService.modifyOptions(questionId,option1,option2,option3);

        System.out.println(" OPTIONS MODIFIED ");
        System.out.println("\n\n");
    }

    private static void modifyQuestion() {
        System.out.println("ENTER QUESTION ID");
        int questionId = sc.nextInt();
        System.out.println("ENTER QUESTION");
        String question = sc.nextLine();
        question = sc.nextLine();

        int n = questionService.modifyQuestion(questionId,question);
        System.out.println(n+" QUESTION MODIFIED");
        System.out.println("\n\n");
    }

    public static void removeQuestion() {
        System.out.println("ENTER QUESTION ID TO REMOVE");
        int questionId = sc.nextInt();

        int n = questionService.removeQuestion(questionId);
        System.out.println(n+" RECORD DELETED");
        System.out.println("\n\n");

    }

    public static void addQuestion() {
        System.out.println("ENTER QUESTION:");
        String question=sc.nextLine();
        question = sc.nextLine();
        System.out.println("ENTER OPTION1:");
        String option1=sc.nextLine();
        System.out.println("ENTER OPTION2:");
        String option2=sc.nextLine();
        System.out.println("ENTER OPTION3:");
        String option3=sc.nextLine();
        System.out.println("ENTER ANSWER");
        String answer = sc.nextLine();

        Question que= new Question(question,option1,option2,option3,answer);

        int n = questionService.addQuestion(que);

        System.out.println(n +"QUESTION INSERTED");
        System.out.println("\n\n");
    }




}
