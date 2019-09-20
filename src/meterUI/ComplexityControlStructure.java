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
public class ComplexityControlStructure {
    
    
        
    CommonAsserts cmassertsobj = new CommonAsserts();

    
    public String[] printCtc(String code) {
		// ArrayList<String> code_array = new ArrayList<>();
		Scanner scanner = new Scanner(code);

		String text = code;

		int arraySize = cmassertsobj.countLines(code);

		String CtcKeys[] = new String[arraySize];
		int Ctc[] = new int[arraySize];
		String outputCtc[] = new String[arraySize];

		for (int i = 0; i < arraySize; i++) {
			Ctc[i] = 0;
			CtcKeys[i] = " ";
		}

		String[] lines = text.split("\\r?\\n");
		int i = 0;
		
		for (String line : lines) {

			if (line.contains("&")) {				
				if (line.contains("&&")) {
					CtcKeys[i] = CtcKeys[i] + "&& ";
					Ctc[i] = Ctc[i] + 1;
				} else {
					CtcKeys[i] = CtcKeys[i] + "& ";
					Ctc[i] = Ctc[i] + 1;
				}
			}
			
			if (line.contains("|")) {				
				if (line.contains("||")) {
					CtcKeys[i] = CtcKeys[i] + "|| ";
					Ctc[i] = Ctc[i] + 1;
				} else {
					CtcKeys[i] = CtcKeys[i] + "| ";
					Ctc[i] = Ctc[i] + 1;
				}
			}
			
			if (line.contains("if ")) {
				CtcKeys[i] = CtcKeys[i] + "if ";
				Ctc[i] = Ctc[i] + 1;
			}
			if (line.contains("for ")) {
				CtcKeys[i] = CtcKeys[i] + "for ";
				Ctc[i] = Ctc[i] + 2;
			}
			if (line.contains("while ")) {
				CtcKeys[i] = CtcKeys[i] + "while ";
				Ctc[i] = Ctc[i] + 1;
			}
			if (line.contains("do ")) {
				CtcKeys[i] = CtcKeys[i] + "do ";
				Ctc[i] = Ctc[i] + 2;
			}
			if (line.contains("switch ")) {
				CtcKeys[i] = CtcKeys[i] + "switch ";
				Ctc[i] = Ctc[i] + 1;
			}
                        if (line.contains("catch ")) {
				CtcKeys[i] = CtcKeys[i] + "switch ";
				Ctc[i] = Ctc[i] + 1;
			}
			if (line.contains("case ")) {
				CtcKeys[i] = CtcKeys[i] + "case ";
				Ctc[i] = Ctc[i] + 1;
			}
	
			outputCtc[i] = String.valueOf(Ctc[i]);

			i = i + 1;
		}

		return outputCtc;
	}
    
    public void calculateCnc(String code){
        Scanner scanner = new Scanner(code);
                
        String text = code;        
        
        int arraySize = cmassertsobj.countLines(code);  
        
        String CncKeys[] = new String[arraySize];
        int Cnc[] = new int[arraySize];

        for(int i = 0; i<arraySize; i++){
                    Cnc[i] = 0;
                    CncKeys[i] = " ";
        }
                

        String[] lines = text.split("\\r?\\n");
        int i=0;
        for(String line : lines){
            
            
            if(line.contains("if")){
                        
                String[] ifs = text.split("else");
                System.out.println(ifs);

            }
            
            System.out.print(CncKeys[i] + "      ");
            System.out.println(Cnc[i]);

            i = i + 1;
        }   

       
    }
}
