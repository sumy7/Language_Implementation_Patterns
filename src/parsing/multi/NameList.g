list : '[' elements ']' ;  // ƥ�䷽�����ڵ��б�
elements : element (',' element)* ;  // ƥ���м��ж��ŵ��б�
element : NAME '=' NAME // ƥ��a=b�����ĸ�ֵ��� 
        | NAME
        | list 
        ; 