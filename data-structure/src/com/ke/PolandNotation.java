package com.ke;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 * 文档：逆波兰计算器.md
 * 链接：http://note.youdao.com/noteshare?id=50fcebbc0f908dbf010854975fa571ba&sub=C4622E0D35614652BA6EF0ED40FF5D0A
 *
 * @program: data-structure-algorithm
 * @author: ke
 * @create: 2021-09-23 15:47
 **/
public class PolandNotation {

    public static void main(String[] args) {

        /*
         * 完成将一个中缀表达式转成后缀表达式的功能
         * 说明：
         * 1、1+((2+3)*4)-5 => 转成 1 2 3 + 4 * + 5 -
         * 2、因为直接对 str 操作不方便，因此先将中缀表达式转为对应的 List
         * 3、将得到的中缀表达式对应的 List => 后缀表达式对应的 List
         *      即 [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] => [1, 2, 3, +, 4, *, +, 5, -]
         * */
        String expression = "1+((2+3)*4)-5";
        // [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List=" + suffixExpressionList);
        System.out.println("expression=" + calculate(suffixExpressionList));


        // 定义逆波兰表达式 (3+4)*5-6 => 3 4 + 5 * 6 - => 29
        String suffixExpression = "3 4 + 5 * 6 -";
        /*
         * 思路：
         * 1、先将 “3 4 + 5 * 6 - ”放到 ArrayList 中
         * 2、将 ArrayList 传递给一个方法，遍历配合栈完成计算
         * */
        List<String> list = getListString(suffixExpression);
        System.out.println("计算的结果是：" + calculate(list));
    }

    /**
     * 将得到的中缀表达式转换成对应的后缀表达式
     *
     * @param list
     * @return
     */
    public static List<String> parseSuffixExpressionList(List<String> list) {
        // 定义两个栈
        // 符号栈
        Stack<String> s1 = new Stack<>();
        // s2 栈在转换过程中，没有 pop 操作，直接使用 List
        List<String> s2 = new ArrayList<>();

        // 遍历
        for (String item : list) {
            // 如果是一个数，加入 s2
            if (item.matches("\\d+")) {
                s2.add(item);
            }
            // 左括号直接入栈
            else if (item.equals("(")) {
                s1.push(item);
            }
            // 如果是右括号，则依次弹出 s1 栈顶的预算符，并压入 s2，知道遇到左括号为止，此时将这一对括号丢弃
            else if (item.equals(")")) {
                // 依次弹出，知道遇到左括号
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                // 将左括号弹出，丢弃括号
                s1.pop();
            }
            // 如果是运算符
            else {
                // 当 item 的优先级小于等于 list 栈顶运算符，将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到4.1与 s1 中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                // 还需要将 item 入栈
                s1.push(item);
            }
        }

        // 将 s1 中剩余的运算符依次弹出并加入 s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 将中缀表达式转成对应的 List
     *
     * @param s
     * @return
     */
    public static List<String> toInfixExpressionList(String s) {
        List<String> list = new ArrayList<>();
        int i = 0;
        // 对多位数的拼接
        String str;
        // 每遍历到一个字符，就放入到 c
        char c;
        do {
            // 如果 c 是非数字，就需要加入到 ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                list.add("" + c);
                i++;
            }
            // 如果是一个数，需要考虑多位数的问题
            else {
                str = "";
                // '0'[48] => '9'[57]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;
                    i++;
                }
                list.add(str);
            }
        } while (i < s.length());
        return list;
    }

    /**
     * 将一个波兰表达式依次将数据和运算符放入到 ArrayList 中
     *
     * @param suffixExpression
     * @return
     */
    public static List<String> getListString(String suffixExpression) {
        // 将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    /**
     * 1、从左至右扫描，将3和4压入退栈；
     * 2、遇到 + 运算府，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
     * 3、将 5 入栈；
     * 4、接下来是 * 运算，因此弹出 5 和 7，计算出 7 * 5 = 35，将 35 入栈；
     * 5、将 6 入栈；
     * 6、最后是 - 运算符，计算出 35 - 6 的值，即29，由此得出最终结果。
     *
     * @param ls
     * @return
     */
    public static int calculate(List<String> ls) {
        // 创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<>();
        for (String item : ls) {
            // 使用正则表达式取出数，匹配多位数
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // pop 出两个数并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                stack.push(res + "");
            }
        }
        // 最后留在 stack 中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

/**
 * 返回运算符对应的优先级
 */
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result += ADD;
                break;
            case "-":
                result += SUB;
                break;
            case "*":
                result += MUL;
                break;
            case "/":
                result += DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
