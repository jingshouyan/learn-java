grammar Rows;

@parser::members {
    private int col;

    public void setCol(int col) {
        this.col = col;
    }
    public RowsParser(TokenStream input,int col) {
        this(input);
        this.col = col;
    }
}

file: (row NL)+;
row
locals [int i = 0]
    : ( STUFF
        {
            $i++;
            if( $i == col) {
                System.out.println($STUFF.text);
            }
        }
    )+
    ;
TAB: '\t' -> skip;
NL: '\r'?'\n';
STUFF: ~[\t\r\n]+;