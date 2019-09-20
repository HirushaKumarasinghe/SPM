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
 * @author Panduka
 */
public class ComplexityControlStructure {
    
     // Pattern 
        private static Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
        
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
                int ct = 0;
                int iswitch = 1;
		
		for (String line : lines) {

                        Matcher q = textInsideDoubleQuoted.matcher(line);
                        while (q.find()) {
                        line = line.replace(q.group(0), "");
                        }
                        
                        if (line.startsWith("System.out.println") || line.startsWith("(\\s+)\\(.*)") || line.startsWith("(\\s+)//(.*)") 
                           || (line.startsWith("(\\s+)/*(.*)")) || line.startsWith("(\\s+)*/(.*)")|| line.endsWith("(\\s+)*/(.*)") 
                                || line.startsWith("cout") || line.startsWith("(/*")|| line.endsWith("(*/") || line.startsWith("//") ) {
                            Ctc[i] = 0;                           
                        }
                        if(line.contains("//")){
                            line = line.replace(line.substring(line.indexOf("//")), "");
                        }
                        else{
			if (line.contains("if")) {
				CtcKeys[i] = CtcKeys[i] + "if ";
				Ctc[i] = Ctc[i] + 1; 
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
			}                         
			if (line.contains("for")) {
				CtcKeys[i] = CtcKeys[i] + "for ";
				Ctc[i] = Ctc[i] + 2;
                                if (line.contains("&")) {				
                                    if (line.contains("&&")) {
					CtcKeys[i] = CtcKeys[i] + "&& ";
					Ctc[i] = Ctc[i] + 2;
                                    } else {
					CtcKeys[i] = CtcKeys[i] + "& ";
					Ctc[i] = Ctc[i] + 2;
                                    }
                                }		
                                if (line.contains("|")) {				
                                    if (line.contains("||")) {
					CtcKeys[i] = CtcKeys[i] + "|| ";
					Ctc[i] = Ctc[i] + 2;
                                    } else {
					CtcKeys[i] = CtcKeys[i] + "| ";
					Ctc[i] = Ctc[i] + 2;
                                    }
                                }
                                
			}
			if (line.contains("while")) {
				CtcKeys[i] = CtcKeys[i] + "while ";
				Ctc[i] = Ctc[i] + 1;
                                if (line.contains("&")) {				
                                    if (line.contains("&&")) {
					CtcKeys[i] = CtcKeys[i] + "&& ";
					Ctc[i] = Ctc[i] + 2;
                                    } else {
					CtcKeys[i] = CtcKeys[i] + "& ";
					Ctc[i] = Ctc[i] + 2;
                                    }
                                }		
                                if (line.contains("|")) {				
                                    if (line.contains("||")) {
					CtcKeys[i] = CtcKeys[i] + "|| ";
					Ctc[i] = Ctc[i] + 2;
                                    } else {
					CtcKeys[i] = CtcKeys[i] + "| ";
					Ctc[i] = Ctc[i] + 2;
                                    }
                                }
                                
			}
			if (line.contains("do")) {
				CtcKeys[i] = CtcKeys[i] + "do ";
				Ctc[i] = Ctc[i] + 2;
                                if (line.contains("&")) {				
                                    if (line.contains("&&")) {
					CtcKeys[i] = CtcKeys[i] + "&& ";
					Ctc[i] = Ctc[i] + 2;
                                    } else {
					CtcKeys[i] = CtcKeys[i] + "& ";
					Ctc[i] = Ctc[i] + 2;
                                    }
                                }		
                                if (line.contains("|")) {				
                                    if (line.contains("||")) {
					CtcKeys[i] = CtcKeys[i] + "|| ";
					Ctc[i] = Ctc[i] + 2;
                                    } else {
					CtcKeys[i] = CtcKeys[i] + "| ";
					Ctc[i] = Ctc[i] + 2;
                                    }
                                }
                                
			}
			if (line.contains("switch")) {
				//CtcKeys[i] = CtcKeys[i] + "switch ";
				//Ctc[i] = Ctc[i] + 1;
                                iswitch = iswitch+ i;
                                getSwitchCaseCount(code,iswitch);
			}
                        if (line.contains("catch")) {
				CtcKeys[i] = CtcKeys[i] + "catch ";
				Ctc[i] = Ctc[i] + 1;
			}
                        }
			outputCtc[i] = String.valueOf(Ctc[i]);

			i = i + 1;
		}

		return outputCtc;
	}
    
    int maxCases;
    public int getSwitchCaseCount(String code , int svalue) {

        int current_maxCases = 0;
        int current_svalue = svalue;
        int current_loop = 0;
        Scanner fileInput = new Scanner(code);

        // Read through file and find words
        while (fileInput.hasNextLine()) {
            
            String scannedline = fileInput.nextLine();
            if (current_loop > current_svalue) {
                if (scannedline.contains("switch") || scannedline.contains("default")) {
                   return maxCases;                    
                }
                if (scannedline.matches("(\\s+)case(.*)")) {
                    current_maxCases++;
                    // System.out.println(scannedline);
                    if (current_maxCases > maxCases) { 
                        maxCases = current_maxCases;
                        //CtcKeys[i] = CtcKeys[i] + "switch ";
		        //Ctc[i] = Ctc[i] + 1;
                    }
                }             
            }
            current_loop += 1;
        }
        return maxCases;
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
