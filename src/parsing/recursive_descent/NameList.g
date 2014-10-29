list : '[' elements ']' ;  // 匹配方括号内的列表
elements : element (',' element)* ;  // 匹配中间有逗号的列表
element : NAME | list ;  // 一个element要么是NAME，要么是嵌套的列表