package mydiaryweb.module.mdqa.util;


import java.util.List;

public class StringUtil {

    public static String listToString(List<String> wordsList){
        String listString = "";

        for (String str : wordsList) {
            listString += str + " ";
        }

        return listString;
    }
}
