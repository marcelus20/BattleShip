package com.jetbrains.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * This class is a collection of useful tools for building the game. This class will have methods for getting user input,
 * game pausing and other tools.
 */

public class SystemTools {

    /**
     * Method for coolecting whatever user types on keyboard.
     * @param msg - a printing message to tell what user should do
     * @param regex - a robust rule for user typing what is being asked
     * @param msgError - an printing error msg in case user does not type what he/she has been asked
     * @return return a String
     */
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

    /**
     * method for pausing game
     */
    public void SystemPause(){
        getInput("Press enter to continue.","","");
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

    /**
     * METHOD FOR GIVING RANDOM NAME FOR BOTS.
     * it has a list of names, and it will return one of these names. It will be chosen randomly.
     * @return
     */
    public String genRandomName(){
        String[] listOfNames = new String[]{
                "Raphus Miwe","Daleanix Zazorie","Korla Impira", "Himel Sevinjoh", "Adia Embex",
                "Liversei Laugath", "Antriel Hani", "Crona Meles", "Nilor Asthon", "Glon Umel",
                "Phor Skya", "Derulta Ancon", "Xolian Romon", "Romishion Abys", "Fawyr Huweloon",
                "Lewintrele Mori", "Haltal Deardyron", "Shis Adruor", "Edgemidana Shyruzio",
                "Shaf Araza", "Karull Elindfin", "Alon Piriel", "Thar Yenlath", "Magi Beciant",
                "Leoniantz Anater", "Fariene Alfaiano", "Nogna Gealynny", "Aegar Thunarions",
                "Nadhil Phihan", "Adra Nerca", "Kivin Manastyn", "Thena Fenim", "Teanak Suron"
        };
        return listOfNames[new Random().nextInt(listOfNames.length)];
    }

}