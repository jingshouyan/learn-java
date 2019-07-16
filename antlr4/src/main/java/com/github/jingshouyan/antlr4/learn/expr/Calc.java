package com.github.jingshouyan.antlr4.learn.expr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;

public class Calc {

    public static void main(String[] args) throws Exception {
        InputStream is = Calc.class.getResourceAsStream("/t.expr");
        CharStream input = CharStreams.fromStream(is);
        LabeledExprLexer lexer = new LabeledExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LabeledExprParser parser = new LabeledExprParser(tokens);
        ParseTree tree = parser.prog();
//        System.out.println(tree.toStringTree(parser));
        EvalVisitor visitor = new EvalVisitor();
        visitor.visit(tree);
    }
}
