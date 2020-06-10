package org.example.stack;

/**
 * 计算器
 */
public class Calculator {
    public static void main(String[] args) {
        ArrayStack numStack = new ArrayStack(10);
        ArrayStack operStack = new ArrayStack(10);

        String expression = "11+20*(1+99*5-1-2)/9+10";

        int index = 0;
        char ch = ' ';
        int num1 = 0;
        int num2 = 0;
        int result = 0;
        int oper = 0;
        String keepNum = "";

        while (true){
            ch = expression.substring(index, index + 1).charAt(0);
            if (ToolUtil.isOper(ch)){// 是运算符
                if (operStack.isEmpty() || ch == '('){// 操作栈为空或者左括号，直接压入
                    operStack.push(ch);
                }else { // 不为空 进一步判断  右括号的级别最低，这样可以计算括号内的数据
                    if (ToolUtil.priority(ch) <= ToolUtil.priority(operStack.peek())){
                        // 当前操作符小于栈中的操作符优先级
                        if (ch == ')'){
                            while ((oper = operStack.pop()) != '('){
                                num1 = numStack.pop();
                                num2 = numStack.pop();
                                result = ToolUtil.cal(num1,num2,oper);
                                numStack.push(result);
                            }
                        }else {
                            oper = operStack.pop();
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            result = ToolUtil.cal(num1,num2,oper);
                            numStack.push(result);
                            operStack.push(ch);
                        }
                    }else {// 大于栈中
                        operStack.push(ch);
                    }
                }
            }else {// 是数据
//                numStack.push(ch - 48); //char转int
                // 上面的只能处理一位数的
                // 处理多位数
                keepNum += ch;
                if (index == expression.length() - 1){// 最后一位，则直接压栈
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    // 判断表达式的下一个字符是否是操作符，如不是说明是多位数
                    if (ToolUtil.isOper(expression.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }
            }

            if (++index >= expression.length()){
                break;
            }
        }

        // 最后从操作栈取一个，数值栈去两个进行计算，将结果压入数值栈，知道操作栈为空
        while (!operStack.isEmpty()){
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            result = ToolUtil.cal(num1,num2,oper);
            numStack.push(result);
        }

        System.out.printf("表达式%s = %d",expression,result);
    }
}

class ToolUtil{
    
    public static boolean isOper(char op){
        return op == '+' || op == '-' || op == '*' || op == '/' || op == '(' || op == ')';
    }

    public static int priority(int op){
        int ret = switch (op){
            case '*' -> 2;
            case '/' -> 2;
            case '+' -> 1;
            case '-' -> 1;
            case ')' -> 0;
            default -> -1;
        };
        return ret;
    }

    public static int cal(int num1,int num2,int op){
        int ret = switch (op){
            case '*' -> num1 * num2;
            case '/' -> num2 / num1;
            case '+' -> num1 + num2;
            case '-' -> num2 - num1;
            default -> -1;
        };
        return ret;
    }
}
