package com.wky.demo.spel;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import java.util.HashMap;
import java.util.Map;

/**
 * SpEL支持加(+)、减(-)、乘(*)、除(/)、求余（%）、幂（^）运算。
 * 执行结果：
 * 3
 * applyId:2
 * 1
 * 默认的上下文是Spring的容器：ApplicationContext
 * @author wangkunyang
 * @date 2022/12/16 11:42
 */
public class SpElDemo {

    public static void main(String[] args) {
        // 创建表达式解析器
        ExpressionParser parser = new SpelExpressionParser();

//        String expression = "1 + 2";
//        String value1 = parser.parseExpression(expression).getValue(String.class);
//        System.out.println(value1);

        // 使用TemplateParserContext, 支持根据模板解析, 只会对#{}中的内容进行解析
        String template = "applyId:#{[applyId]}, nodeId:#{[nodeId]}";    // applyId:#{[applyId]}  applyId:#{['applyId']}  单引号可以省略
        // 自定义上下文(root), 可以是map/list/...
        Map<String, Object> root = new HashMap<>(8);
        root.put("applyId", 2);
//        List<Integer> root = new ArrayList<>();   // applyId:#{[0]}
//        root.add(2);
        // 执行表达式的前缀和后缀，即#{}
        TemplateParserContext templateParserContext = new TemplateParserContext();
        /**
         * Expression[] expressions = parseExpressions(expressionString, context);
         * expressions.add(doParseExpression(expr, context));
         * applyId:
         */
        String value2 = parser.parseExpression(template, templateParserContext).getValue(root, String.class);
        System.out.println(value2);

        // Spring提供的上下文  评估上下文
//        EvaluationContext evaluationContext = new StandardEvaluationContext();
//        evaluationContext.setVariable("id", 1);
//        String value3 = parser.parseExpression("#id").getValue(evaluationContext, String.class);
//        System.out.println(value3);

    }




}
