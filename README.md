Language_Implementation_Patterns
================================

编译语言实现模式例程

## parsing.lexer
词法分析器。将输入分解为Token，用于后续的语法分析处理。  

## parsing.recursive_descent
运用递归思想，根据之后遇到的Token来匹配相应的文法。适用于LL(1)文法。  
  
示例文法：  

    list : '[' elements ']' ;  // 匹配方括号内的列表  
    elements : element (',' element)* ;  // 匹配中间有逗号的列表  
    element : NAME | list ;  // 一个element要么是NAME，要么是嵌套的列表  

Test程序：匹配成功无显示，匹配失败抛出异常。  

## parsing.multi
为处理First集有交集的文法（非LL(1)文法），根据之后遇到的1~n个Token来匹配相应的文法。  
  
示例文法：  

    list : '[' elements ']' ;  // 匹配方括号内的列表  
    elements : element (',' element)* ;  // 匹配中间有逗号的列表  
    element : NAME '=' NAME // 匹配a=b这样的赋值语句   
            | NAME  
            | list   
            ;   
            
Test程序：匹配成功无显示，匹配失败抛出异常。  

## parsing.backtrack
类DFS方式。假定一个文法进行尝试，若尝试成功就依此匹配，尝试失败返回更换文法重新尝试；文法尝试完毕未成功则匹配失败，抛出异常。  

示例文法：  

    stat : list EOF | assign EOF ;
    assign : list '=' list ;
    list : '[' elements ']' ; 
    elements : element (',' element)* ;  
    element : NAME '=' NAME | NAME | list ; 
    
Test程序：匹配成功无显示，匹配失败抛出异常。  

## parsing.memoize
记忆化的回溯匹配。在匹配过程中记录成功或失败的位置，当下一次在相同位置用相同文法进行匹配时直接返回结果。  
  
示例文法：

    stat : list EOF
         | list '=' list
         ;
    list : '[' elements ']' ; 
    elements : element (',' element)* ;  
    element : NAME '=' NAME | NAME | list ; 

Test程序：显示匹配的大致过程，匹配失败抛出异常。