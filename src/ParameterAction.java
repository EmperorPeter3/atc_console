import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

//Класс ParameterAction объявлен не абстрактным, т.к. он реализовал все
// унаследованные от интерфейса MainInterface методы.

public class ParameterAction implements MainInterface{

    @Override
    /**
     * Get an input parameter
     *
     * @param str a String from console.
     * @return an input parameter.
     */
     public  String getParameter (String str){
        String result;
        if (str.length() < 2)
            result = "Parametr doesn't exist";
        else
            result = str.substring(0,2);
        return result;
    }

    @Override
    /**
     * Check an input parameter
     *
     * @param parameter an input parameter.
     * @param needParameter a parameter which we need.
     * @return a true if parameters equals or false if not.
     */
    public boolean checkParameter (String parameter, String needParameter){
        if (parameter.equals(needParameter))
            return true;
        else
            return false;
    }

    @Override
    /**
     * Define an action of parameter, then check it.
     *
     * @param str an input string from console.
     * @param existingActions an array with existing actions of parameter.
     * @return a string-action of parameter or message about miss.
     */
    public String defineAndCheckParameterAction (String str, String[] existingActions){
        String result="",temp;
        boolean flag=false;
        if (str.contains("=") && (str.length() > str.indexOf("=")+1)){
            temp = str.substring(2, str.indexOf("="));
            for (int i = 0; i<existingActions.length; i++){
                if (temp.equals(existingActions[i])) {
                    result = temp;
                    flag = true;
                    break;
                }
            }
            if (!flag){
                System.out.println("Parameter's action doesn't match. Ignoring.\n 42! = " + getFactN(42));
            }
        } else {
            System.out.println("Incorrect format. (correct: -T<name>=<value>) Ignoring.");
        }
        return result;
    }

    @Override
    /**
     * Make an action that checked
     *
     * @param str an input string from console.
     * @param parameterAction a string-action which define in defineAndCheckParameterAction
     * @return an action of parameter.
     */
    public Object getParameterAction (String str, String parameterAction){
        Object result = null;
        switch (parameterAction){
            case "week":
                if (checkWeek(str))
                    getWeekStartEnd(str);
                break;
            case "fact":
                checkAndGetFact(str);
                break;
            //default: result = "Existing action";
        }
        return result;
    }

    @Override
    /**
     * Check that date for 'week' is correctly
     *
     * @param str an input string from console.
     * @return true/false.
     */
    public boolean checkWeek (String str){
        String stringDate = str.substring(str.indexOf("=") + 1, str.length());
        if (stringDate.length() > 8)
            return false;
        else {
            try {
                int day = Integer.parseInt(stringDate.substring(0, 2));
                int month = Integer.parseInt(stringDate.substring(2, 4));
                int year = Integer.parseInt(stringDate.substring(4, 8));

                if (day < 1 || day > 31 || month < 1 ||  month > 12 || year < 1970 || year > 2025 ||
                        (month == 2 && day > 28) ||
                        ((month ==4 || month ==6 || month ==9 || month ==11) && day > 30 ))
                {
                    System.out.println("error: -Tweek="+stringDate+" - an incorrect date");
                    return false;
                }
                else
                    return true;
            } catch (Exception e) {
                System.out.println("error: -Tweek="+stringDate+" - an incorrect date");
                return false;
            }
        }
    }

    @Override
    /**
     * Get the dates of beginning and ending of week
     *
     * @param str an input string from console.
     * return 2 strings to console: start and end dates of week.
     */
    public void getWeekStartEnd (String str){
        Date date = new Date(), weekStart,weekEnd;
        String stringDate = str.substring(str.indexOf("=") + 1, str.length());
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        try {
            date = sdf.parse(stringDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK) - 6); //week beginning
        weekStart = calendar.getTime();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMaximum(Calendar.DAY_OF_WEEK) + 1); //week ending
        weekEnd = calendar.getTime();

        System.out.println("Week begins at: " + weekStart);
        System.out.println("Week ends at: " + weekEnd);

        hurrayCheck(date);
    }

    @Override
    /**
     * Check that week includes Friday 13th
     *
     * @param date an input date of day in week, which checking.
     * return a string in console with message "Hurray!"
     */
    public void hurrayCheck (Date date){
        Calendar hurray = Calendar.getInstance();
        hurray.setTime(date);
        hurray.set(Calendar.DAY_OF_WEEK, hurray.getActualMinimum(Calendar.DAY_OF_WEEK) - 2);
        if (hurray.get(Calendar.DAY_OF_MONTH) == 13)
            System.out.println("Hurray!");
    }

    @Override
    /**
     * Check that value to factorialize is correct
     * then get factorial.
     *
     * @param str an input string from console.
     * return string to console: value of factorial with wasted time or error-message.
     */
    public void checkAndGetFact (String str){
        String stringFactValue = str.substring(str.indexOf("=") + 1, str.length());
        FactorialUtil fu = new FactorialUtil();


        try{
            int intFactValue = Integer.parseInt(stringFactValue);
            if (intFactValue > 1000) {
                System.out.println("Too big value for factorial, but i try...");
                long timeout= System.currentTimeMillis();
                System.out.println(fu.doFactorial(intFactValue)+" "+ Objects.toString(System.currentTimeMillis() - timeout, null)+"мс");
            }
            else {
                long timeout = System.currentTimeMillis();
                System.out.println(fu.doFactorial(intFactValue)+" "+ Objects.toString(System.currentTimeMillis() - timeout, null)+"мс");
            }
        } catch (Exception e) {
            System.out.println("error: -Tfact="+stringFactValue+" - an incorrect value for the factorial");
        }
    }

    @Override
    /**
     * Get the factorial (need to 42!)
     *
     * @param n an integer value.
     * @return value of factorial(n)
     */
    public BigInteger getFactN (int n){
        FactorialUtil fu = new FactorialUtil();
        return fu.doFactorial(n);
    }

}
