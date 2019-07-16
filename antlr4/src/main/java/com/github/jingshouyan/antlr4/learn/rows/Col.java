package com.github.jingshouyan.antlr4.learn.rows;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.InputStream;

public class Col {

    public static void main(String[] args) throws Exception{
        int col = 1;
        InputStream is = Col.class.getResourceAsStream("/t.rows");
        CharStream input = CharStreams.fromStream(is);
        RowsLexer lexer = new RowsLexer(input);
        CommonTokenStream token = new CommonTokenStream(lexer);
        RowsParser parser = new RowsParser(token,col);
        parser.setCol(3);
        parser.setBuildParseTree(false);
        RowsParser.FileContext file = parser.file();
        System.out.println();
    }
}
