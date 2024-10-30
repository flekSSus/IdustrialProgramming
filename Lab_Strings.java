import java.util.Comparator;
import java.util.Collections;
import java.util.Random;
import java.util.regex.*;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.io.*;
import java.lang.StringBuilder;

class Lab_Strings
{
    public static void main(String []args)
    {
        String str1=" ";
        String str2=" ";
        String str1_m=" ";
        String longestStr=" ";
        char symbolToFind=' ';
        ArrayList<String> arr_tokens= new ArrayList<String>();
        ArrayList<String> arr_num =new ArrayList<String>();
        ArrayList<String> arr_time= new ArrayList<String>();
        Random randInt= new Random();
        
        try(BufferedReader bufR= new BufferedReader(new FileReader("input.txt")))
        {
            str1=bufR.readLine();
            str2=bufR.readLine();
            symbolToFind=(char)bufR.read();
        }
        catch(IOException e)
        {
            System.out.println(e);
        }

        StringBuilder strB1_m=new StringBuilder();

        StringTokenizer strToker= new StringTokenizer(str1,str2);

        while(strToker.hasMoreTokens())
        {
            String tmp=strToker.nextToken();
            arr_tokens.add(tmp);
            if(IsNumber(tmp))
                arr_num.add(tmp);
            else if(IsTime(tmp))
                arr_time.add(tmp);
        }

        if(!arr_time.isEmpty())
        {
            int first_time=arr_tokens.indexOf(arr_time.get(0));
            for(int i=0;i<arr_tokens.size();++i)
            {
                strB1_m.append(arr_tokens.get(i));
                if(i==first_time)
                    strB1_m.append(randInt.nextInt());
                strB1_m.append(' ');
            }
        }
        else
        {
            for(String str : arr_tokens)
            {
                strB1_m.append(str);
                strB1_m.append(' ');
            }
            strB1_m.insert(0,randInt.nextInt());
        }

        longestStr=LongestString(str1, symbolToFind);
        StringBuilder strB2_m=new StringBuilder(str1);
        if(longestStr!=" ")
        {
            strB2_m.replace(0,longestStr.length(),"");
        }


        

        try(BufferedWriter bufW= new BufferedWriter(new FileWriter("output.txt")))
        {
            bufW.write("Input string1: "+str1+'\n');
            bufW.write("Input string2: "+str2);
            bufW.write('\n');
            bufW.write("Only a numerical lexemes: ");
            for(String str: arr_num)
            {
                bufW.write(str);
                bufW.write(' ');
            }
            bufW.write('\n');
            bufW.write("Only a time lexemes: ");
            for(String str:arr_time)
            {
                bufW.write(str);
                bufW.write(' ');
            }
            bufW.write('\n');
            bufW.write("Modified string: \n");
            bufW.append(strB1_m);
            bufW.write('\n');
            bufW.write("Longest substr with ending symbol "+symbolToFind+": ");
            bufW.write(longestStr);
            bufW.write('\n');
            bufW.write("Modified string: \n");
            bufW.append(strB2_m);

            Collections.sort(arr_tokens, (o1, o2) -> o1.compareTo(o2));
/*            Arrays.sort(arr_tokens,new Comparator<String>()
            {
                public int compare(String obj1,String obj2)
                {
                    return obj1.compareTo(obj2);
                }
            });
*/
            bufW.write("\nSorted lexemes: ");
            for(String str: arr_tokens)
                bufW.write(str+'\n');

        }
        catch(IOException e)
        {
            System.out.println(e);
        }

    }
    private static boolean IsNumber(String str)
    {
        String reg="-?\\d+\\.?\\d+";
        return Pattern.matches(reg,str);
    }

    private static boolean IsTime(String str)
    {
        String reg="[0-2][0-9]-[0-9][0-9]";
        return Pattern.matches(reg,str);
    }

    private static String LongestString(String str, char symbol)
    {
        int index= str.lastIndexOf(symbol);
        if(index!=-1)
            return str.substring(0,index+1);
        return " ";
    }

    
}

