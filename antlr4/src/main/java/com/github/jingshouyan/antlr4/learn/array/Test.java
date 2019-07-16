package com.github.jingshouyan.antlr4.learn.array;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Test {

    public static void main(String[] args) throws Exception {
//        String in = "{1,2,3,4,{45,44,65,77,33},12}";
//        CodePointBuffer buffer = CodePointBuffer.withBytes(ByteBuffer.wrap(in.getBytes()));
//        CharStream input = CodePointCharStream.fromBuffer(buffer);
        CharStream input = new ANTLRInputStream(System.in);
        ArrayInitLexer lexer = new ArrayInitLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ArrayInitParser parser = new ArrayInitParser(tokens);
        ParseTree tree = parser.init();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new Short2UnicodeString(), tree);
        System.out.println(tree.toStringTree(parser));
    }
}
