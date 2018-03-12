package com.wtt.stack.practice;

/**
 * Created by wutaotao
 * 2018/3/12 21:59
 */

import edu.princeton.cs.algs4.StdIn;

/**
 * ClassName    : E10303 <br>
 * Function     : TODO ADD FUNCTION. <br>
 * date         : Sep 28, 2016 2:25:18 PM <br>
 */
public class E10303 {
    public static boolean isValid(int[] seq) {
        Stack<Integer> stack = new Stack<Integer>();
        int currentNum = 9;
        int index = 9;
        while (currentNum >= 0) {
            if (index >= 0 && seq[index] == currentNum) {
                index--;
                currentNum--;
            } else if (!stack.isEmpty() && stack.top() == currentNum) {
                stack.pop();
                currentNum--;
            } else {
                if (index < 0)
                    break;
                stack.push(seq[index]);
                index--;
            }
            if (stack.size() == 0) System.out.print("null");
            for (Integer i : stack) {
                System.out.print(i + ",");
            }
            System.out.println();
        }
//        int currentNum = 0;
//        int index = 0;
//
//        while(currentNum <= 9) {
//
//            if (index <= 9 && seq[index] == currentNum) {
//                index++;
//                currentNum++;
//            } else if(!stack.isEmpty() && stack.top() == currentNum) {
//                stack.pop();
//                currentNum++;
//            } else {
//                if (index > 9) break;
//                stack.push(seq[index]);
//                index++;
//            }
//            if (stack.size() == 0) System.out.print("null");
//            for (Integer i : stack) {
//                System.out.print(i + ",");
//            }
//            System.out.println();
//        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {

        String line = "2 5 6 7 4 8 9 3 1 0";
        String[] values = line.split("\\s+");
        int[] nums = new int[10];

        for (int i = 0; i < values.length; i++) {
            nums[i] = Integer.parseInt(values[i]);
        }

        if (isValid(nums)) {
            System.out.println("OK");
        } else {
            System.out.println("No");
        }
    }

}

