package databasepackage;

public class CheckingForAFull 
{
    public static boolean isDate(String str)
    {
        if(str.length() != 10)
        {
            return false;
        }
        if((str.charAt(4) != '-') || (str.charAt(7) != '-'))
        {
            return false;
        }
        return true;
    }
}
