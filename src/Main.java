import java.util.Scanner;

//interface MainInterface - описание методов
//class ParameterAction - реализация методов

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String strFromConsole, action;
        String[] existingActions = {"week","fact"};
        ParameterAction pa = new ParameterAction() {};
        while (!(strFromConsole=in.nextLine()).equals("")) {
            String parameter = pa.getParameter(strFromConsole);
            if(pa.checkParameter(parameter, "-T")) {
                action = pa.defineAndCheckParameterAction(strFromConsole, existingActions);
                pa.getParameterAction(strFromConsole, action);
            }
            else {
                System.out.println("Parameter doesn't match. Ignoring.");
            }
        }
    }
}
