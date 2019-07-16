package com.github.jingshouyan.antlr4.learn.json;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

/**
 * @author jingshouyan
 * #date 2019/6/17 17:10
 */

public class JsonRewriteListener extends JSONBaseListener {

    private TokenStreamRewriter rewriter;

    public JsonRewriteListener(TokenStream tokens) {
        rewriter = new TokenStreamRewriter(tokens);
    }

    @Override
    public void enterPair(JSONParser.PairContext ctx) {
        super.enterPair(ctx);
    }
}
