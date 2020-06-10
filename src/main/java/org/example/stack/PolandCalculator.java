package org.example.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Pattern;

/**
 * 后缀表达式（逆波兰表达式）
 */
public class PolandCalculator {
    public static void main(String[] args) {
//        String expression = "1+((20+3)*4)-5";
        String expression = "12.8 + (2 - 3.55)*4+10/5.0";
        // 转换成List
        List<String> list = transfer2List(expression);
        System.out.println("转换成list：" + list);
        // 转换成逆波兰表达式
        List<String> suffix = parseSuffixExpreesion(list);
        System.out.println("后缀表达式：" + suffix);
        // 计算
        double calResult = calculate(suffix);

        System.out.printf("表达式%s的计算结果为%.6f",expression,calResult);
    }

    private static double calculate(List<String> suffix) {
        Stack<String> stack = new Stack<>();
        for (String s : suffix) {
//            if (s.matches("\\d+")){ // 数字   压栈
            if (isNumber(s)){
                stack.push(s);
            }else { // 运算符  pop出两个数，计算，将结果压入栈
                double num1 = Double.parseDouble(stack.pop());
                double num2 = Double.parseDouble(stack.pop());
                double ret = switch (s){
                    case "+" -> num2 + num1;
                    case "-" -> num2 - num1;
                    case "*" -> num2 * num1;
                    case "/" -> num2 / num1;
                    default -> throw new RuntimeException("操作符不正确");
                };
                stack.push(String.valueOf(ret));
            }
        }

        // 最后栈中仅存的一个数，就是计算结果
        return Double.parseDouble(stack.pop());
    }

    private static List<String> parseSuffixExpreesion(List<String> list) {
        Stack<String> s1 = new Stack();
        // 这里也可以用栈，因为s2栈不涉及到出栈操作，所以用数组方便，可以直接将s2数组返回，
        // 若用栈，最后返回需要将出栈结果逆序
        List<String> s2 = new ArrayList<>();

        for (String s : list) {
//            if (s.matches("\\d+")){// 数字,加入数组s2
            if (isNumber(s)){
                s2.add(s);
            }else if (s.equals("(")){// 左括号 压栈
                s1.push(s);
            }else if (s.equals(")")){// 右括号 则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                // 最后将左括号弹出
                s1.pop();
            }else {// 运算符 比较优先级，若比s1栈中优先级低，则将s1栈出栈加入s2
                while (s1.size() > 0 && Operation.getValue(s) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                // 最后将s压栈
                s1.push(s);
            }
        }
        // 表达式遍历结束，将s1栈中元素一次出栈放入到s2中
        while (s1.size() > 0){
            s2.add(s1.pop());
        }

        return s2;
    }

    private static List<String> transfer2List(String expression) {
        // 处理空格
        expression = replaceAllBlank(expression);
        List<String> ret = new ArrayList<>();
        String s = "";
        int i = 0;
        while (i < expression.length()){
            char ch = expression.charAt(i);
            if (ch < 48 || ch > 57){// 对照ASCII码表 0~9对应的十进制是48~57
                ret.add(""+ch);
                i++;
            }else {
                s = "";
                // 0~9 或者是小数点
                while (i < expression.length() && ((expression.charAt(i) >= 48 && expression.charAt(i) <= 57) || expression.charAt(i) == 46)){
                    s+=expression.charAt(i);
                    i++;
                }
                ret.add(s);
            }
        }

        return ret;
    }

    /**
     * 判断是不是数字 int double long float
     * @param s
     * @return
     */
    public static boolean isNumber(String s){
//        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");
        return pattern.matcher(s).matches();
    }

    /**
     * 判断是不是运算符
     * @param s
     * @return
     */
    public static boolean isSymbol(String s){
        return s.matches("\\+|-|\\*|/|\\(|\\)");
    }

    /**
     * 去除所有空白符
     * @param s
     * @return
     */
    public static String replaceAllBlank(String s ){
        // \\s+ 匹配任何空白字符，包括空格、制表符、换页符等等, 等价于[ \f\n\r\t\v]
        return s.replaceAll("\\s+","");
    }
}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String s){
        int ret = switch (s){
            case "+" -> ADD;
            case "-" -> SUB;
            case "*" -> MUL;
            case "/" -> DIV;
            default -> -1;
        };

        return ret;
    }
}
