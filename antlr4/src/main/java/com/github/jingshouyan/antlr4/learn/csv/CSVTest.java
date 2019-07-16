package com.github.jingshouyan.antlr4.learn.csv;

import com.github.jingshouyan.antlr4.learn.expr.Calc;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.InputStream;

public class CSVTest {

    public static void main(String[] args) throws Exception{
        InputStream is = Calc.class.getResourceAsStream("/t.csv");
        CharStream input = CharStreams.fromStream(is);
        CSVLexer lexer = new CSVLexer(input);
        CommonTokenStream token = new CommonTokenStream(lexer);
        CSVParser parser = new CSVParser(token);
        ParseTree tree = parser.file();
        System.out.println(tree.toStringTree(parser));
    }
}
