package com.ke.stack;

/**
 * 栈实现综合计算器
 * 文档：栈.md
 * 链接：http://note.youdao.com/noteshare?id=030857fdd755d931ec4bf6a3173b3952&sub=DC399AE217D44C96B3E3CD7A61B466F9
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-22 16:53
 **/
public class Calculator {
    public static void main(String[] args) {
        String expression = "700+2*6-4";

        // 创建两个栈，一个数栈，一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operatorStack = new ArrayStack2(10);
        // 用于扫描
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int operator = 0;
        int res = 0;
        // 将每次扫描得到的 char 保存到 ch
        char ch = ' ';
        // 用于拼接多位数
        String keepNum = "";
        // 循环扫描 expression
        while (true) {
            // 依次得到 expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断 ch 是什么，然后做相应的处理
            if (operatorStack.isOperator(ch)) {
                // 判断当前的符号栈是否为空
                if (!operatorStack.isEmpty()) {
                    /*
                     * 如果符号栈有操作符，就进行比较，如果当前的操作符的优先级小于或者等于栈中的操作符，就需要从数栈中 pop 出两个数，
                     * 再从符号栈中 pop 出一个符号，进行运算，将得到结果入数栈，然后将当前的操作符入符号栈
                     * */
                    if (operatorStack.priority(ch) <= operatorStack.priority(operatorStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = operatorStack.pop();
                        res = numStack.cal(num1, num2, operator);
                        // 把运算的结果入数栈
                        numStack.push(res);
                        // 当前操作符入符号栈
                        operatorStack.push(ch);
                    } else {
                        // 如果当前的操作符的优先级大于栈中的操作符，就直接入符号栈
                        operatorStack.push(ch);
                    }
                } else {
                    // 不为空直接入栈
                    operatorStack.push(ch);
                }
            } else {
                // Ascii 表中 1 指向 49
//                numStack.push(ch - 48);
                /*
                 * 1、当处理多位数时，不能发现时一个数就立即入栈，因为他可能是多位数
                 * 2、在处理数，需要向 expression 的表达式的 index 后再看一位，如果是数就继续扫描，否则入栈
                 * 3、因此我们需要定义一个变量字符串，用于拼接
                 * */
                keepNum += ch;

                // 如果 ch 已经是 expression 的最后一位，就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    // 判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    if (operatorStack.isOperator(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符，直接入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum = "";
                    }
                }

            }
            // 让 index + 1，并判断是否扫描到 expression 最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕，就顺序的从数栈和符号栈中 pop 出响应的数和符号，并运行
        while (true) {
            // 如果符号栈为空，则计算到最后的结果，数栈中只有一个数字
            if (operatorStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = operatorStack.pop();
            res = numStack.cal(num1, num2, operator);
            numStack.push(res);
        }
        // 将数栈的最后数 pop 就是结果
        System.out.printf("表达式 %s = %d", expression, numStack.pop());
    }
}

/**
 * 定义一个 ArrayStack 表示栈
 */
class ArrayStack2 {
    /**
     * 表示栈的大小
     */
    private int maxSize;

    /**
     * 数组模拟栈，数据就放在该数组
     */
    private int[] stack;
    /**
     * 表示栈顶，初始化为 -1
     */
    private int top = -1;

    /**
     * 构造器
     *
     * @param maxSize
     */
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 返回当前栈顶的值，不出栈
     *
     * @return
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈，将栈顶的数据返回
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈，需要从栈顶开始显示数据
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d] = %d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级，优先级是程序员来确定，用数字表示，越大则优先级越高
     *
     * @param operator
     * @return
     */
    public int priority(int operator) {
        if (operator == '*' || operator == '-') {
            return 1;
        } else if (operator == '+' || operator == '-') {
            return 0;
        }
        return -1;
    }

    /**
     * 判断是不是一个运算符
     *
     * @param val
     * @return
     */
    public boolean isOperator(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     *
     * @param num1
     * @param num2
     * @param operator
     * @return
     */
    public int cal(int num1, int num2, int operator) {
        int res = 0;
        switch (operator) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
