package com.jetbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class SystemTools {

    public String getInput(final String msg, final String regex, final String msgError){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        do{
            printTabledArray(new String[]{msg});
            try{
                input = br.readLine();
            }catch(IOException e){
                System.out.println(e);
            }
            if(!input.matches(regex)){
                printTabledArray(new String[]{msgError});
            }
        }while(!input.matches(regex));
        return input;
    }

    public void SystemPause(){
        printTabledArray(new String[]{"Press enter to continue."});
        getInput("","","");
    }

    public void printTabledArray(final String[] arr){
        //DEFINING WIDTH OF TABLE BASED ON THE BIGEST STRING ELEMENT IN THE ARRAY:
        final Integer width = 50;
        System.out.println("+"+strMultiply("-", width)+"+");
        for(final String sentence : arr){
            System.out.println("|"+
                    strMultiply(" ", (width-sentence.length())/2)+
                            sentence+
                            strMultiply(" ",(width-sentence.length())/2)
            +"|");
        }
        System.out.println("+"+strMultiply("-", width)+"+");
    }

    public void printTabledArray(final String str){
        printTabledArray(new String[]{str});
    }

    private Integer getSizeOfBiggestArrayElement(final String[] arr){
        Integer biggest = 0;
        for(String element : arr){
            if(element.length()> biggest){
                biggest = element.length();
            }
        }
        return biggest;
    }

    public String strMultiply(final String str, final Integer times){
        String text = "";
        for(int i = 0; i < times; i++){
            text+=str;
        }
        return text;
    }

    public String genRandomName(){
        String[] listOfNames = new String[]{
                "Raphus Miwe","Daleanix Zazorie","Korla Impira", "Himel Sevinjoh", "Adia Embex",
                "Liversei Laugath", "Antriel Hani", "Crona Meles", "Nilor Asthon", "Glon Umel",
                "Phor Skya", "Derulta Ancon", "Xolian Romon", "Romishion Abys", "Fawyr Huweloon"
        };
        return listOfNames[new Random().nextInt(listOfNames.length)];
    }

}
