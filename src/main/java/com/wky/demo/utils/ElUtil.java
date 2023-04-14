package com.wky.demo.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Objects;

/**
 * @author wangkunyang
 * @date 2023/02/22 14:19
 */
public class ElUtil {

    private static final ExpressionParser PARSER = new SpelExpressionParser();

    /**
     * SpEL表达式计算
     *
     * @param context  上下文
     * @param template 模板
     * @param clazz    类型
     * @param <T>
     * @return
     */
    public static <T> T getValue(EvaluationContext context, String template, Class<T> clazz) {
        Expression expression = PARSER.parseExpression(template);
        if (Objects.isNull(context)) {
            return expression.getValue(clazz);
        }
        return expression.getValue(context, clazz);
    }

    /**
     * 获取上下文对象
     * @param keys
     * @param values
     * @return
     */
    public static EvaluationContext getContext(List<String> keys, List<Object> values) {
        EvaluationContext context = new StandardEvaluationContext();
        if (CollectionUtils.isNotEmpty(keys)) {
            for (int i = 0; i < keys.size(); i++) {
                if (i < values.size()) {
                    context.setVariable(keys.get(i), values.get(i));
                }
            }
        }
        return context;
    }


}
