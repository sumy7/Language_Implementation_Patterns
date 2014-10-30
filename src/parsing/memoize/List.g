stat : list EOF
     | list '=' list
     ;
list : '[' elements ']' ; 
elements : element (',' element)* ;  
element : NAME '=' NAME | NAME | list ; 