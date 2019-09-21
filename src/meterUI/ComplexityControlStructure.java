/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meterUI;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import meterUI.Stack;
import java.util.regex.Pattern;
/**
 *
 * @author Panduka
 */
public class ComplexityControlStructure {
    
     // Pattern 
    private static Pattern forPat = Pattern.compile("(for\\s*\\()([a-zA-Z]*\\s*\\w*\\s*=?\\s*[a-zA-Z0-9]*;+\\s*)(\\w+\\s*[><=!][=]*\\s*[a-zA-Z0-9]+)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!][=]*\\s*[a-zA-Z0-9]+))*(;\\s*[a-zA-Z]+\\+\\+)(\\)\\s*\\{)");
    private static Pattern whilePat = Pattern.compile("(while\\s*\\()(\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\s*\\{)");
    private static Pattern doWhilePat = Pattern.compile("(do\\s*\\{)");
    private static Pattern doWhileBotPat = Pattern.compile("(\\}\\s*while\\s*\\()(\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\;)");
    private static Pattern ifPat = Pattern.compile("(\\w*\\s*if\\s*\\()(\\(*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*\\)*)((\\s*\\&\\&|\\s*\\|\\||\\s*\\&|\\s*\\|)(\\s*\\(*\\w+\\s*[><=!]*[=]*\\s*[a-zA-Z0-9]*\\)*))*(\\.\\w+\\(\\\"*\\w*\\\"*\\))*(\\)\\s*\\{)");
    private static Pattern elsePat = Pattern.compile("(\\}*\\s*else\\s*\\{)");
    private static Pattern textInsideDoubleQuoted = Pattern.compile("\"(.*?)\"");
    public static Stack stack;

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
    
    
    public String[] calculateCnc(String code){
         
        Scanner scanner = new Scanner(code);           
        String text = code;        
        
        int arraySize = cmassertsobj.countLines(code);  
        
        String CncKeys[] = new String[arraySize];
        int Cnc[] = new int[arraySize];
        String outputCnc[] = new String[arraySize];
        stack = new Stack();

        for(int i = 0; i<arraySize; i++){
                    Cnc[i] = 0;
                    CncKeys[i] = " ";
        }
                
  
        String[] lines = text.split("\\r?\\n");
        int i=0;
        for(String line : lines){
            
            line = line.trim(); 
            
            try {
                
            Matcher q = textInsideDoubleQuoted.matcher(line);
            while (q.find()) {
                line = line.replace(q.group(0), "");
            }
                                
            Matcher forMatcher = forPat.matcher(line);
            if (forMatcher.matches() && line.startsWith(forMatcher.group(1))) {
                List<String> forWords = Arrays.asList(line.replaceAll("[\\(\\+\\=\\)\\;\\>\\<\\!]", " ").split(" "));
                for (String forChar : forWords) {
                    if (forChar.equals("{")) {
                        stack.push("{");
                    }
                }
            }

            Matcher whileMatcher = whilePat.matcher(line);
            if (whileMatcher.matches() && line.startsWith(whileMatcher.group(1))) {         
                List<String> whileWords = Arrays.asList(line.replaceAll("[\\(\\.\\=\\)\\>\\<\\!\\\"]", " ").split(" "));
                for (String whileChar : whileWords) {                  
                    if (whileChar.equals("{")) {
                        stack.push("{");
                    }
                }
            }

            Matcher doWhileTopMatcher = doWhilePat.matcher(line);
            if (doWhileTopMatcher.matches() && line.startsWith(doWhileTopMatcher.group(1))) {             
                List<String> doWhileWords = Arrays.asList(line.split(" "));
                for (String doWhileChar : doWhileWords) {
                    if (doWhileChar.equals("{") || doWhileChar.contains("{")) {
                        stack.push("{");
                    }
                }
            }

            Matcher elseMatcher = elsePat.matcher(line);

            if (elseMatcher.matches() && line.startsWith(elseMatcher.group(1))) {
                List<String> elseWords = Arrays.asList(line.split(" "));
                for (String elseChar : elseWords) {
                    if (elseChar.equals("{") || elseChar.contains("{")) {
                        stack.push("{");
                    }
                }
            }
            
            Matcher ifMatcher = ifPat.matcher(line);
            if (ifMatcher.matches() && line.startsWith(ifMatcher.group(1))) {
                //ctc = ctc + 1;
                List<String> ifWords = Arrays.asList(line.replaceAll("[\\(\\.\\=\\)\\>\\<\\!\\\"]", " ").split(" "));
                for (String ifChar : ifWords) {
                    if (ifChar.equals("{")) {
                        stack.push("{");
                    }
                }
            }
   
            if (stack.peek() != 0 && (line.startsWith("}") || line.endsWith("}"))) {
                String val = stack.pop();
            }

             Cnc[i] = Cnc[i] + stack.peek();
             System.out.println(Cnc[i]);
                       
        } catch (Exception e) {
            e.printStackTrace();
        }
            
         outputCnc[i] = String.valueOf(Cnc[i]);
         i = i + 1;   
          
        }   
       return outputCnc;
    }
}
