import java.math.BigInteger;
import java.util.Date;

public interface MainInterface {
    String getParameter(String str);
    boolean checkParameter (String parameter, String needParameter);
    String defineAndCheckParameterAction (String str, String[] existedActions);
    Object getParameterAction (String str, String parameterAction);
    boolean checkWeek (String str);
    void getWeekStartEnd (String str);
    void checkAndGetFact (String str);
    BigInteger getFactN (int n);
    void hurrayCheck (Date date);
}
