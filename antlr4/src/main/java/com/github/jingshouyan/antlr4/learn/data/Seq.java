package com.github.jingshouyan.antlr4.learn.data;

import com.github.jingshouyan.antlr4.learn.expr.Calc;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;

public class Seq {

    public static void main(String[] args) throws Exception{
        InputStream is = Calc.class.getResourceAsStream("/t.data");
        CharStream input = CharStreams.fromStream(is);
        DataLexer lexer = new DataLexer(input);
        CommonTokenStream token = new CommonTokenStream(lexer);
        DataParser parser = new DataParser(token);
        ParseTree tree = parser.file();
        System.out.println(tree.toStringTree(parser));
    }
}
