/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meterUI;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author pandu
 */
public class Stack {
 
    private int top;
    private List<String> stackArr;

    public Stack() {
        top = -1;
        stackArr = new ArrayList<String>();
    }

    public void push(String val) {
        top++;
        stackArr.add(val);

    }

    public String pop() {
        String result = stackArr.remove(top);
        top--;
        return result;
    }

    public int peek() {
        return top + 1;
    }
}
