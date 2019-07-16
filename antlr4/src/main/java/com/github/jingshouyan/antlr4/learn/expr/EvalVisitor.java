package com.github.jingshouyan.antlr4.learn.expr;

import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Long> implements LabeledExprVisitor<Long> {
    Map<String, Long> MEMORY = new HashMap<>();

    @Override
    public Long visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Long value = visit(ctx.expr());
        System.out.println(value);
        return value;
    }

    @Override
    public Long visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Long value = visit(ctx.expr());
        MEMORY.put(id, value);
        return value;
    }

    @Override
    public Long visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
    @Override
    public Long visitInt(LabeledExprParser.IntContext ctx) {
        return Long.valueOf(ctx.INT().getText());
    }
    @Override
    public Long visitId(LabeledExprParser.IdContext ctx) {
        return MEMORY.getOrDefault(ctx.ID().getText(), 0L);
    }
    @Override
    public Long visitAddSub(LabeledExprParser.AddSubContext ctx) {
        Long left = visit(ctx.expr(0));
        Long right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.ADD) {
            return left + right;
        }
        return left - right;
    }
    @Override
    public Long visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        Long left = visit(ctx.expr(0));
        Long right = visit(ctx.expr(1));
        if (ctx.op.getType() == LabeledExprParser.MUL) {
            return left * right;
        }
        return left / right;
    }
}
