
package ailatrieuphu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Questions implements Serializable {
    private String title;
    List<String> options = new ArrayList<>();
    int result;

    public Questions() {
    }

    public Questions(String title, int result) {
        this.title = title;
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
    
    public void input() {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Enter questions");
        title = scan.nextLine();
        
        System.out.println("Enter options: ");
        int index = 1;
        for(;;) {
            System.out.printf("Options %d: \n", index++);
            String option = scan.nextLine();
            options.add(option);
            
            System.out.println("Continue? Y/N");
            option = scan.nextLine();
            
            if(option.equalsIgnoreCase("N")) {
                break;
            }
        }
        System.out.println("Enter correct answer");
        result = Integer.parseInt(scan.nextLine());
    }
    
    public void showQuestions() {
        System.out.println("Question: "+ title);
        int index = 1;
        for(String option : options) {
            System.out.println((index++) + ". " + option);
        }
        System.out.println("Answer");
    }
    
    public boolean checkResult(int result) {
        return this.result == result;
    }
}
