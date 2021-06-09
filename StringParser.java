public class StringParser
{
    private StringParser()
    {}
    
    public static String formatString(String input)
    {
        input = input.toLowerCase();
        input = input.replaceAll(" the ", " "); 
        input = input.replaceAll(" a ", " "); 
        input = input.replaceAll(" an ", " "); 
        input = input.replaceAll(" this ", " "); 
        input = input.replaceAll(" that ", " "); 
        input = input.replaceAll(" to ", " ");
        input = input.trim();
        while (input.indexOf("  ") >= 0) 
            input = input.replaceAll("  ", " "); 

        return input;
    }
    
    public static String getCommand(String input)
    {
        if(input.indexOf(" ") > 0)
            return input.substring(0,input.indexOf(" "));
        else     
            return input;
    }    
    
    public static String getPhrase(String input)
    {
        if(input.indexOf(" ") > 0)
        {
            input = input.substring(input.indexOf(" ") + 1);
            return input;
        }
        else
            return "";
    }    
}        