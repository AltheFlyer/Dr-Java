import java.util.Scanner;

/**
 * Quiz.java
 * @version 1.0
 * @author Allen Liu
 * @since Feb 21, 2019
 * A simple quiz game with 10 multiple choice problems about computer science related careers
 */

public class Quiz {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        //Array for all quiz values
        /*
         * Items in each array are listed based on their position:
         * The first string in the hint array and answer array are
         * for the first string in the question array
         * 
         * The jobs included are:
         * Software Developer
         * Database Administrator
         * Computer Hardware Engineer
         * Computer Systems Analyst
         * Computer Network Architect
         * Web Developer
         * Information Security Analyst
         * IT Support
         * Computer and Information Systems Managers
         * Game Developer
         */
        String[] questions = {
            "This job involves working with the design, production, and maintenance of software systems. Some key " +
            "parts of the job include debugging, testing, and installation of software for companies or users." +
            "The average salary is $90,000 CAD/year, and the job requires a bachelor’s degree in computer science or " +
            "software engineering but may also requires knowledge in calculus and physics.",
            
            "This job involves working with large sets of data using specialized software systems. Those with this "+
            "job will work with data migration, security, and recovery. The average salary is $70,000 CAD/year and " +
            "the job requires at least a bachelor’s degree in computer science, although entry-level jobs may " + 
            "require lesser degrees.",
            
            "This job involves the creation of physical computer systems and peripherals. While there is a special " +
            "engineering degree associated with it, a degree in electrical engineering may also be accepted. The " + 
            "average salary is $73,000 CAD/year.",
            
            "This job focuses on integrating computer systems for organizations. Research and testing  is used to " +
            "optimize computer systems. The median salary is $65,000 CAD/year, and the job requires a bachelor's " + 
            "degree in either computer systems analysis, computer science, computer information systems, management " +
            "information systems, or business intelligence.",
            
            "This job designs and builds data communication networks, including local and wide scale networks. The " +
            "median salary is $92,000 CAD and the job requires a bachelor’s in computer science, information " +
            "systems, or a similar field.",
            
            "This job involves creating content for the World Wide Web, mainly involving HTML, CSS, and Javascript. " +
            "The average salary in Ontario is around $55,000 CAD/year and the job typically requires a bachelor’s " +
            "degree that is related to computer science.",
            
            "This job focuses on improving the security of computer networks to detect and protect an organization " +
            "from cyber attacks. The average salary is $69,000 CAD and the job requires a bachelor’s degree in cyber " +
            "security or a related field.",
           
            "This job helps to monitor and maintain the computer systems of an organisation, which may involve " +
            "dealing with technical issues and troubleshooting. The average salary is $49,000 CAD and the job may or " +
            "may not require a bachelor’s in computer science, or a lesser degree.",
            
            "This job involves administering and implementing computer related technologies and systems. They tend " + 
            "to oversee the information systems within an organization. The average salary is $102,000 CAD/year, and " +
            "the job requires a bachelor’s degree in a computer science related field, but a Master’s degree is " +
            "recommended.",
            
            "This job works closely with other departments (mostly involving design and assets) to create games. The " +
            "average salary is $57,000 CAD and the job prefers a bachelor’s degree in software engineering, although " +
            "computer science, software engineering, mathematics, and computer information systems majors are also " +
            "common."
        };
        
        //The choices for answers to each question
        String[] choices = {
            "a)Software Developer\nb)Web Developer\nc)Computer Systems Analyst\nd)Computer Network Architect\n",
            "a)Computer Hardware Engineer\nb)Database Administrator\nc)Computer and Information Systems Managers\nd)Information Security Analyst\n",
            "a)Web Developer\nb)Information Security Analyst\nc)Computer Hardware Engineer\nd)IT Support\n",
            "a)Database Administrator\nb)Software Developer\nc)IT Support\nd)Computer Systems Analyst\n",
            "a)Computer and Information Systems Managers\nb)Computer Network Architect\nc)Game Developer\nd)Database Administrator\n",
            "a)Game Developer\nb)Computer Hardware Engineer\nc)Information Security Analyst\nd)Web Developer\n",
            "a)Information Security Analyst\nb)Computer Systems Analyst\nc)Computer Network Architect\nd)Computer and Information Systems Managers\n",
            "a)IT Support\nb)Game Developer\nc)Software Developer\nd)Computer Hardware Engineer\n",
            "a)Computer Network Architect\nb)Computer and Information Systems Managers\nc)Web Developer\nd)Software Developer\n",
            "a)Computer Systems Analyst\nb)IT Support\nc)Database Administrator\nd)Game Developer\n"
        };
 
        //A multiple choice answer key
        String answers = "abcdbdaabd";
        
        //A string of valid/acceptable answers
        String possibleAnswers = "abcd";
            
        //The player score
        int score;
        //The player's name
        String name;
        //Tutorial text, can be re-printed as needed
        String tutorial = "This is a quiz game about Computer Science related jobs.\n" +
            "There are 10 questions in total, and you can choose from 4 different answers to each.\n" +
            "To choose an answer, type its associated letter: (a, b, c, or d).\n";
        //Controls the current question
        int questionCounter;
        
        System.out.println(tutorial);
        
        //Primary game loop, allows for replaying
        do {
            questionCounter = 0;
            score = 0;
            //Pre-quiz prompt + tutorial
            System.out.println("Enter your name >>>");
            name = input.nextLine();
        
            /* The while loop is used to make control flow smoother
             * The question only changes when an answer is entered
             * Non-question altering commands won't update the question
             * Neither will invalid responses
             */
            while (questionCounter < questions.length) {
                System.out.printf("Question %d:\n", questionCounter + 1);
                System.out.println(questions[questionCounter]);
                System.out.println(choices[questionCounter]);
                
                //Handles multiple commands, force lowercase
                String command = input.nextLine().toLowerCase();
                
                if (command.equals("help")) {
                    //Re-prints the tutorial, ++userFriendliness
                    System.out.println(tutorial);
                    //Check if the user input is a valid selection character
                } else if (command.length() > 0 && possibleAnswers.indexOf(command.charAt(0)) > -1) {
                    //Check if the input letter is the same as the answer defined in the answers array
                    if (command.equals(answers.substring(questionCounter, questionCounter + 1))) {
                        System.out.println("You got the right answer!");
                        score++;
                    } else {
                        System.out.println("You got the wrong answer. :(");
                        System.out.println("The correct answer was: " + answers.charAt(questionCounter));
                    }
                    questionCounter++;
                } else {
                    //Unknown/questionable input handler
                    System.out.println("Unrecognized command or invalid answer. Type 'help' (no quotes) to see valid " +
                                       "commands, or make sure you enter only the letters a, b, c, and d as answers.");
                }
            }
            System.out.printf("%s, you got a score of %d/%d. Play again(Y/N)\n", name, score, questions.length);
        } while (input.nextLine().toUpperCase().equals("Y"));
        
        //Close the scanner
        input.close();
    }
}