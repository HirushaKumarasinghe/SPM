/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meterUI;

import java.util.Scanner;

/**
 *
 * @author Hirusha
 */
public class ComplexityInheritance {
        
    CommonAsserts cmassertsobj = new CommonAsserts();

    
     public String[] printCi(String code){
        Scanner scanner = new Scanner(code);
                
        String text = code;        
        
        int arraySize = cmassertsobj.countLines(code);  
        
        String CsKeys[] = new String[arraySize];
        int Ci[] = new int[arraySize];
        String outputCs[] = new String[arraySize];


        for(int i = 0; i<arraySize; i++){
                    Ci[i] = 1;
        }
                

        String[] lines = text.split("\\r?\\n");        
        int i=0;
        int defaultVal = 2;
        for(String line : lines){
            
            if(line.equals("}")){
                
                Ci[i] = 0;
            }
            else if(code.contains("class ")){
                Ci[i] = defaultVal;
                if(code.contains("extends ")){
                Ci[i] = defaultVal +1;
                }
            }
            outputCs[i] = String.valueOf(Ci[i]);
            i = i + 1;
        }   

        return outputCs;
       
    } 
}
