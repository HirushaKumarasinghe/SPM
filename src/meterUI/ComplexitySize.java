/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meterUI;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hirusha
 */
public class ComplexitySize {
    
    CommonAsserts cmassertsobj = new CommonAsserts();
    
    private static Pattern method = Pattern.compile("(public|protected|private|static|\\s) +[\\w\\<\\>\\[\\]]+\\s+(\\w+) *\\([^\\)]*\\) *(\\{?|[^;])");
    private static Pattern variable = Pattern.compile("\"[^\"]*\"|((?=_[a-z_0-9]|[a-z])([a-z_0-9]|[a-z0-9\\[\\]])+(?=\\s*=))");
    private static Pattern param = Pattern.compile("\\((.*?)\\)");
    private static Pattern newPattern = Pattern.compile("(new)+\\s\\w+");
    private static Pattern methodCalls = Pattern.compile("([^\\W:.,()\\s]+)\\s*\\(|::([^W:.,()\\s]+)");
    
    public int[] printCr(String code,String type){
        
        String text = code;        
        int arraySize = cmassertsobj.countLines(code);  
        String CsKeys[] = new String[arraySize];
        int Cs[] = new int[arraySize];
        String outputCs[] = new String[arraySize];

        for(int i = 0; i<arraySize; i++){
                    Cs[i] = 0;
                    CsKeys[i] = " ";
        }

        String[] lines = text.split("\\r?\\n");        
        int i=0;
        for(String line : lines){
            
            if(line.contains("&")){
                if(type.equals("java")){
                    if(line.contains("&&")){
                    CsKeys[i] = CsKeys[i] + "&& ";
                    Cs[i] = Cs[i] + 1;
                    }
                    else{
                    CsKeys[i] = CsKeys[i] + "& ";
                    Cs[i] = Cs[i] + 2;
                    }
                }
                else if(type.equals("cpp")){
                    
                }
                else{
                    
                }     
            }
            if(line.contains("public")){
                CsKeys[i] = CsKeys[i] + "public ";
                Cs[i] = Cs[i] + 2;
            }
            if(line.contains("new")){
                CsKeys[i] = CsKeys[i] + "new ";
                Cs[i] = Cs[i] + 2;
            }
            if(line.contains("delete")){
                CsKeys[i] = CsKeys[i] + "delete ";
                Cs[i] = Cs[i] + 2;
            }
            if(line.contains("throw")){
                CsKeys[i] = CsKeys[i] + "throw ";
                Cs[i] = Cs[i] + 2;
            }
            if(line.contains("throws")){
                CsKeys[i] = CsKeys[i] + "throws ";
                Cs[i] = Cs[i] + 2;
            }
            if(line.contains("void")){
                CsKeys[i] = CsKeys[i] + "void ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("double")){
                CsKeys[i] = CsKeys[i] + "double ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("int")){
                CsKeys[i] = CsKeys[i] + "int ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("float")){
                CsKeys[i] = CsKeys[i] + "float ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("string")){
                CsKeys[i] = CsKeys[i] + "string ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("printf")){
                CsKeys[i] = CsKeys[i] + "printf ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("println")){
                CsKeys[i] = CsKeys[i] + "println ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("print")){
                CsKeys[i] = CsKeys[i] + "print ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("cout")){
                CsKeys[i] = CsKeys[i] + "cout ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("cin")){
                CsKeys[i] = CsKeys[i] + "cin ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("if")){
                CsKeys[i] = CsKeys[i] + "if ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("for")){
                CsKeys[i] = CsKeys[i] + "for ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("while")){
                CsKeys[i] = CsKeys[i] + "while ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("do ")){
                CsKeys[i] = CsKeys[i] + "do ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("switch")){
                CsKeys[i] = CsKeys[i] + "switch ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("case")){
                CsKeys[i] = CsKeys[i] + "case ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("endl’")){
                CsKeys[i] = CsKeys[i] + "endl ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("'")){
                CsKeys[i] = CsKeys[i] + "' ";
                Cs[i] = Cs[i] + 1;
            }
            if(line.contains("+")){
                if(line.contains("++")){
                CsKeys[i] = CsKeys[i] + "++ ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("+=")){
                CsKeys[i] = CsKeys[i] + "+= ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "+ ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("-")){
                if(line.contains("--")){
                CsKeys[i] = CsKeys[i] + "-- ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("->")){
                CsKeys[i] = CsKeys[i] + "-> ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("-=")){
                CsKeys[i] = CsKeys[i] + "-= ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "- ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("=")){
                if(line.contains("==")){
                CsKeys[i] = CsKeys[i] + "== ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "= ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("!")){
                if(line.contains("!=")){
                CsKeys[i] = CsKeys[i] + "!= ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "! ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains(">")){
                if(line.contains(">=")){
                CsKeys[i] = CsKeys[i] + ">= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains(">>=")){
                CsKeys[i] = CsKeys[i] + ">>= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains(">>>=")){
                CsKeys[i] = CsKeys[i] + ">>>= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains(">>>")){
                CsKeys[i] = CsKeys[i] + ">>> ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains(">>")){
                CsKeys[i] = CsKeys[i] + ">> ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "> ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("<")){
                if(line.contains("<=")){
                CsKeys[i] = CsKeys[i] + "<= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("<<=")){
                CsKeys[i] = CsKeys[i] + "<<= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("<<<=")){
                CsKeys[i] = CsKeys[i] + "<<<= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("<<<")){
                CsKeys[i] = CsKeys[i] + "<<< ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("<<")){
                CsKeys[i] = CsKeys[i] + "<< ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "< ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("*")){
                if(line.contains("*=")){
                CsKeys[i] = CsKeys[i] + "*= ";
                Cs[i] = Cs[i] + 1;
                }
                //code for poiner
                //else if(line.contains("**")){
                //CsKeys[i] = CsKeys[i] + "** ";
                //Cs[i] = Cs[i] + 1;
                //}
                else{
                CsKeys[i] = CsKeys[i] + "* ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("/")){
                if(line.contains("/=")){
                CsKeys[i] = CsKeys[i] + "/= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("//")){
                CsKeys[i] = CsKeys[i] + "";
                Cs[i] = Cs[i] + 0;
                }
                else{
                CsKeys[i] = CsKeys[i] + "/ ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("|")){
                if(line.contains("|=")){
                CsKeys[i] = CsKeys[i] + "|= ";
                Cs[i] = Cs[i] + 1;
                }
                else if(line.contains("||")){
                CsKeys[i] = CsKeys[i] + "|| ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "| ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("%")){
                if(line.contains("%=")){
                CsKeys[i] = CsKeys[i] + "%= ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "% ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains(":")){
                if(line.contains("::")){
                CsKeys[i] = CsKeys[i] + ":: ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + ": ";
                Cs[i] = Cs[i] + 1;
                }
            }
            if(line.contains("^")){
                if(line.contains("^=")){
                CsKeys[i] = CsKeys[i] + "^= ";
                Cs[i] = Cs[i] + 1;
                }
                else{
                CsKeys[i] = CsKeys[i] + "^ ";
                Cs[i] = Cs[i] + 1;
                }
            }
            
            //=================================================
            Matcher m = method.matcher(line);
                while (m.find()) {
                    String name = m.group(2);
                    if (line.contains(m.group(2))) {
                        System.out.println(m.group(2));
                    Cs[i] = Cs[i] + 1;
                    }
                }
            //=================================================
            i = i + 1;
        }   
        return Cs;
    }
    
    
    //private static List<String> keywordsOne = Arrays.asList(PropertyReader.getInstance().getProperty("cs.one").split(","));


    public static List<String> getMethodAndVariables(String code) {
        List<String> list = new ArrayList<>();
        //try (LineNumberReader lnr = new LineNumberReader(new FileReader(file))) {
        String[] lines = code.split("\\r?\\n"); 
        int i= 1;

        for(String line : lines){
                                    
            list.add(String.valueOf(i));


                // method finder
                Matcher m = method.matcher(line);
                while (m.find()) {
                    String name = m.group(2);
                    if (!list.contains(name)) {
                        list.add(name);
                    }
                }

                // method call finder
                Matcher mc = methodCalls.matcher(line);
                while (mc.find()) {
                    String name = mc.group(0).trim().replaceAll("[\\s\\(]", "");
                    if (!list.contains(name)) {
                        list.add(name);
                    }
                }

                // parameter finder example(String a)
                Matcher p = param.matcher(line);
                while (p.find()) {
                    String params = p.group(1);
                    List<String> paramList = Arrays.asList(params.split(","));
                    paramList.forEach(param -> {
                        List<String> single = Arrays.asList(param.trim().split(" "));
                        if (single.size() == 2) {
                            String param1 = single.get(0).replaceAll("[\\[\\]]", "");
                            if (!list.contains(param1)) {
                                list.add(param1);
                            }
                            String param2 = single.get(1).replaceAll("[\\[\\]]", "");
                            if (!list.contains(param2)) {
                                list.add(param2);
                            }
                        }
                    });
                }

                // Object creations finder
                // ex: new FileReader()
                Matcher np = newPattern.matcher(line);
                while (np.find()) {
                    String params = np.group(0);
                    List<String> single = Arrays.asList(params.split(" "));
                    if (single.size() == 2) {
                        String paramName = single.get(1);
                        if (!list.contains(paramName)) {
                            list.add(paramName);
                        }
                    }
                }

                //variable finder
                Matcher v = variable.matcher(line);
                while (v.find()) {
                    String name = v.group(1);
                    if (name != null && !list.contains(name)) {
                        list.add(name.replace("[", "").replace("]", ""));
                    }
                }

//                for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
//                    String value = iterator.next();
//                    if (keywordsOne.contains(value.toLowerCase())) {
//                        iterator.remove();
//                    }
//                }

//        } catch (IOException e) {
//            System.out.println("Input error");        }
    
        i++;
        }
        return list;

    }
}
