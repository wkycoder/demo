package com.wky.demo.spel;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import java.util.HashMap;
import java.util.Map;

/**
 * SpEL支持加(+)、减(-)、乘(*)、除(/)、求余（%）、幂（^）运算。
 * 执行结果：
 * 3
 * applyId:2
 * 1
 * @author wangkunyang
 * @date 2022/12/16 11:42
 */
public class SpElDemo {

    public static void main(String[] args) {
        // 创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();

        String expression = "1 + 2";
        String value1 = parser.parseExpression(expression).getValue(String.class);
        System.out.println(value1);

        // 使用TemplateParserContext, 只会对#{}中的内容进行解析
        String template = "applyId:#{[applyId]}";
        // 自定义上下文
        Map<String, Object> context = new HashMap<>(8);
        context.put("applyId", 2);
        // 执行表达式的前缀和后缀，即#{}
        TemplateParserContext templateParserContext = new TemplateParserContext();
        String value2 = parser.parseExpression(template, templateParserContext).getValue(context, String.class);
        System.out.println(value2);

        // Spring提供的上下文
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("id", 1);
        String value3 = parser.parseExpression("#id").getValue(evaluationContext, String.class);
        System.out.println(value3);

    }




}
