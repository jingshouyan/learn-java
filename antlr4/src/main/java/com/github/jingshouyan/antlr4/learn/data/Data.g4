grammar Data;

file: group+;

group: INT sequeue[$INT.int];

sequeue[int n]
locals [int i = 0;]
    : ( {$i < $n}? INT {$i++;} )*
    ;
INT: [0-9]+;
WS: [ \t\n\r]+ -> skip;