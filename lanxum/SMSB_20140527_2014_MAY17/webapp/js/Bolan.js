
/*
     ���ʽ��־
  0-���� ֵ-value
  1-���� ������-operator ֵ-value
  2-��������� operator ��+��-��*��/����������
  3-�Ƚϱ��ʽ  operator(>��>=.<.<= == .!=) �����bolValue(true false)
  4-��ֵ����� operator (=);
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


