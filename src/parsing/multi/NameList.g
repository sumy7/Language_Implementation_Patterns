list : '[' elements ']' ;  // 匹配方括号内的列表
elements : element (',' element)* ;  // 匹配中间有逗号的列表
element : NAME '=' NAME // 匹配a=b这样的赋值语句 
        | NAME
        | list 
        ; 