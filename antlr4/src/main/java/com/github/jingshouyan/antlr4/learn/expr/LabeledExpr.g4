grammar LabeledExpr;

import CommonLexerRules;

prog: stat+;
stat: expr NEWLINE          #printExpr
    | ID '=' expr           #assign
    | NEWLINE               #black
    ;
expr: expr op=('*'|'/') expr   #mulDiv
    | expr op=('+'|'-') expr   #addSub
    | INT                   #int
    | ID                    #id
    | '(' expr ')'          #parens
    ;

MUL: '*';
DIV: '/';
ADD: '+';
SUB: '-';