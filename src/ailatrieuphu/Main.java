package ailatrieuphu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        int choose;

        do {
            showMenu();
            choose = Integer.parseInt(scan.nextLine());

            switch (choose) {
                case 1:
                    inputQuestions();
                    break;
                case 2:
                    startGame();
                    break;
                case 3:
                    System.out.println("Exit!");
                    break;
                default:
                    System.out.println("Please choose 1 - 3");
                    break;
            }
        } while (choose != 3);
    }

    static void showMenu() {
        System.out.println("1. Enter questions");
        System.out.println("2. Start game");
        System.out.println("3. Exit");
        System.out.println("Choose");
    }

    private static void inputQuestions() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        
        try {
            fos = new FileOutputStream("question.inp", true);
            oos = new ObjectOutputStream(fos);
            
            for(;;) {
                Questions question = new Questions();
                question.input();
                
                oos.writeObject(question);
                
                System.out.println("Continue Y/N:");
                String option = scan.nextLine();
                if(option.equalsIgnoreCase("N")) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private static void startGame() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        
        List<Questions> questions = new ArrayList<>();
        
        try {
            fis = new FileInputStream("question.inp");
            ois = new ObjectInputStream(fis);
            
            for(;;) {
                Object obj = null;
                try {
                    obj = ois.readObject();
                } catch(Exception e) {
                    obj = null;
                }
                if(obj == null) {
                    break;
                }
                questions.add((Questions) obj);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(ois != null) {
                try {
                    ois.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //du lieu cau hoi
        int correct = 0;
        int total = questions.size();
        
        //sinh ngau nhien 15 cau hoi.
        if(questions.size() > 15) {
            List<Questions> testList = new ArrayList<>();
            Random random = new Random();
            
            for (int i = 0; i < 15; i++) {
                int index = random.nextInt(questions.size());
                testList.add(questions.remove(index));
            }
            
            questions = testList;
        }
        
        for (Questions question : questions) {
            question.showQuestions();
            int result = Integer.parseInt(scan.nextLine());
            if(question.checkResult(result)) {
                correct++;
            }
        }
        
        System.out.format("\nKet qua test : %d/%d\n", correct, total);
    }
}
