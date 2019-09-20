/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meterUI;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hirusha
 */
public class CommonAsserts {
    
    public int countLines(String code){
        Matcher m = Pattern.compile("\r\n|\r|\n").matcher(code);
        int lines = 1;
        while (m.find())
        {
            lines ++;
        }
    return lines;
    }
    
    public String [] printLine(String code){
        Scanner scanner = new Scanner(code);
        int lineCount = countLines(code);
        String linesArray[] = new String[lineCount]; 
         for(int i = 0; i<lineCount; i++){
                    linesArray[i] = " ";
        }
        int i = 0;
        while (scanner.hasNextLine()) {
  
            linesArray[i] = scanner.nextLine();
            i++;
        }
    return linesArray;
    }
    
    public String[] printKeyWords(String code){        
        String text = code;        
        int arraySize = countLines(code);  
        String CsKeys[] = new String[arraySize];


        for(int i = 0; i<arraySize; i++){
                    CsKeys[i] = " ";
        }
                

        String[] lines = text.split("\\r?\\n");        
        int i=0;
        for(String line : lines){
            if(line.contains("&")){
                if(line.contains("&&")){
                CsKeys[i] = CsKeys[i] + "&& ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "& ";
                }
            }
            if(line.contains("public")){
                CsKeys[i] = CsKeys[i] + "public ";
            }
            if(line.contains("new")){
                CsKeys[i] = CsKeys[i] + "new ";
            }
            if(line.contains("delete")){
                CsKeys[i] = CsKeys[i] + "delete ";
            }
            if(line.contains("throw")){
                CsKeys[i] = CsKeys[i] + "throw ";
            }
            if(line.contains("throws")){
                CsKeys[i] = CsKeys[i] + "throws ";
            }
            if(line.contains("void")){
                CsKeys[i] = CsKeys[i] + "void ";
            }
            if(line.contains("double")){
                CsKeys[i] = CsKeys[i] + "double ";
            }
            if(line.contains("int")){
                CsKeys[i] = CsKeys[i] + "int ";
            }
            if(line.contains("float")){
                CsKeys[i] = CsKeys[i] + "float ";
            }
            if(line.contains("string")){
                CsKeys[i] = CsKeys[i] + "string ";
            }
            if(line.contains("printf")){
                CsKeys[i] = CsKeys[i] + "printf ";
            }
            if(line.contains("println")){
                CsKeys[i] = CsKeys[i] + "println ";
            }
            if(line.contains("print")){
                CsKeys[i] = CsKeys[i] + "print ";
            }
            if(line.contains("cout")){
                CsKeys[i] = CsKeys[i] + "cout ";
            }
            if(line.contains("cin")){
                CsKeys[i] = CsKeys[i] + "cin ";
            }
            if(line.contains("if’")){
                CsKeys[i] = CsKeys[i] + "if ";
            }
            if(line.contains("for")){
                CsKeys[i] = CsKeys[i] + "for ";
            }
            if(line.contains("while")){
                CsKeys[i] = CsKeys[i] + "while ";
            }
            if(line.contains("do")){
                CsKeys[i] = CsKeys[i] + "do ";
            }
            if(line.contains("switch")){
                CsKeys[i] = CsKeys[i] + "switch ";
            }
            if(line.contains("case")){
                CsKeys[i] = CsKeys[i] + "case ";
            }
            if(line.contains("endl’")){
                CsKeys[i] = CsKeys[i] + "endl ";
            }
            if(line.contains("'")){
                CsKeys[i] = CsKeys[i] + "' ";
            }
            if(line.contains("+")){
                if(line.contains("++")){
                CsKeys[i] = CsKeys[i] + "++ ";
                }
                else if(line.contains("+=")){
                CsKeys[i] = CsKeys[i] + "+= ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "+ ";
                }
            }
            if(line.contains("-")){
                if(line.contains("--")){
                CsKeys[i] = CsKeys[i] + "-- ";
                }
                else if(line.contains("->")){
                CsKeys[i] = CsKeys[i] + "-> ";
                }
                else if(line.contains("-=")){
                CsKeys[i] = CsKeys[i] + "-= ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "- ";
                }
            }
            if(line.contains("=")){
                if(line.contains("==")){
                CsKeys[i] = CsKeys[i] + "== ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "= ";
                }
            }
            if(line.contains("!")){
                if(line.contains("!=")){
                CsKeys[i] = CsKeys[i] + "!= ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "! ";
                }
            }
            if(line.contains(">")){
                if(line.contains(">=")){
                CsKeys[i] = CsKeys[i] + ">= ";
                }
                else if(line.contains(">>=")){
                CsKeys[i] = CsKeys[i] + ">>= ";
                }
                else if(line.contains(">>>=")){
                CsKeys[i] = CsKeys[i] + ">>>= ";
                }
                else if(line.contains(">>>")){
                CsKeys[i] = CsKeys[i] + ">>> ";
                }
                else if(line.contains(">>")){
                CsKeys[i] = CsKeys[i] + ">> ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "> ";
                }
            }
            if(line.contains("<")){
                if(line.contains("<=")){
                CsKeys[i] = CsKeys[i] + "<= ";
                }
                else if(line.contains("<<=")){
                CsKeys[i] = CsKeys[i] + "<<= ";
                }
                else if(line.contains("<<<=")){
                CsKeys[i] = CsKeys[i] + "<<<= ";
                }
                else if(line.contains("<<<")){
                CsKeys[i] = CsKeys[i] + "<<< ";
                }
                else if(line.contains("<<")){
                CsKeys[i] = CsKeys[i] + "<< ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "< ";
                }
            }
            if(line.contains("*")){
                if(line.contains("*=")){
                CsKeys[i] = CsKeys[i] + "*= ";
                }
                //code for poiner
                //else if(line.contains("**")){
                //CsKeys[i] = CsKeys[i] + "** ";
                //Cs[i] = Cs[i] + 1;
                //}
                else{
                CsKeys[i] = CsKeys[i] + "* ";
                }
            }
            if(line.contains("/")){
                if(line.contains("/=")){
                CsKeys[i] = CsKeys[i] + "/= ";
                }
                else if(line.contains("//")){
                CsKeys[i] = CsKeys[i] + "";
                }
                else{
                CsKeys[i] = CsKeys[i] + "/ ";
                }
            }
            if(line.contains("\\n")){
                CsKeys[i] = CsKeys[i] + "\\n ";
            }
            if(line.contains("\\t")){
                CsKeys[i] = CsKeys[i] + "\\t ";
            }
            if(line.contains("\\r")){
                CsKeys[i] = CsKeys[i] + "\\r ";
            }
            if(line.contains("|")){
                if(line.contains("|=")){
                CsKeys[i] = CsKeys[i] + "|= ";
                }
                else if(line.contains("||")){
                CsKeys[i] = CsKeys[i] + "|| ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "| ";
                }
            }
            if(line.contains("%")){
                if(line.contains("%=")){
                CsKeys[i] = CsKeys[i] + "%= ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "% ";
                }
            }
            if(line.contains(":")){
                if(line.contains("::")){
                CsKeys[i] = CsKeys[i] + ":: ";
                }
                else{
                CsKeys[i] = CsKeys[i] + ": ";
                }
            }
            if(line.contains("^")){
                if(line.contains("^=")){
                CsKeys[i] = CsKeys[i] + "^= ";
                }
                else{
                CsKeys[i] = CsKeys[i] + "^ ";
                }
            }
            i = i + 1;
        }   
        return CsKeys;
    }
}
