
/*
     表达式标志
  0-数字 值-value
  1-变量 变量名-operator 值-value
  2-算术运算符 operator （+、-、*、/、（、））
  3-比较表达式  operator(>、>=.<.<= == .!=) 结果：bolValue(true false)
  4-赋值运算符 operator (=);
  */

function Bolan(flag,oper){
        this.value =0;
        this.bolValue = false;
    
    
        this.index = -1;
        
        this.flag = flag;
        if(flag == 0){
          this.value = parseFloat(oper);
        }else if(flag == 1){
          this.operator = oper;
        }else if(flag == 2){
          this.operator = oper;
        }else if(flag == 3){
          this.operator = oper;
        }else if(flag == 4){
          this.operator = oper;
        }
        
}


