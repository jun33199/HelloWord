
    /**
表达式
     */  
function MathString(express,sbbName) {
	this.sbbName = sbbName;
        this.expression = express;
        
        this.bolanList = new Array();
        this.opers = new Stack();
        this.pos = 0;
        
        this.expressToBolan();
}

    /***
     * 将表达式转换为波兰表示法
     */
MathString.prototype.expressToBolan = function (){

        var bolan;
        while((bolan= this.seekNextBolan()) != null){
            
            
            var len = this.bolanList.length;
            if(bolan.flag == 0){
                this.bolanList[len] = bolan;
            }else if(bolan.flag == 1){
                this.bolanList[len] = bolan;
            }else if(bolan.flag == 2){
                this.addToBolanList(bolan);
            }else if(bolan.flag == 3){
              this.addToBolanList(bolan);
            }else if(bolan.flag == 4){
              this.addToBolanList(bolan);
            }
        }

        while(this.opers.length() > 0){
            var len = this.bolanList.length;
            this.bolanList[len] = this.opers.pop();
        }
        
        
//        for(var i=0;i<this.bolanList.length;i++){alert(this.bolanList[i].operator);}

        return true;
    }
    
    //查找下一个Bolan对象
MathString.prototype.seekNextBolan = function (){

        var len = this.expression.length;
        var c ;
        while( this.pos < len){
            c = this.expression.charAt(this.pos);
            if(c ==' '){
                //空格
                this.pos ++;
                 continue;
            }else if(c >= '0' && c <='9'){
                 //数字
                var b ="";
                while( this.pos< len){
                    c = this.expression.charAt(this.pos);
                    if((c >= '0' && c <='9') || c == '.'){
                        b = b+""+c;
                        this.pos++;
                        continue;
                    }
                    break;
                }
                var bolan = new Bolan(0,b.toString());
                return bolan;
            }else if(isOperator(c)){
                var bolan = new Bolan(2,c+"");
                this.pos ++;
                return bolan;
            }else if((c>='a'&& c<='z') || (c>='A'&& c<='Z') || (c == '_') ){
                //变量
                var b = ""+c;
                this.pos++;

                while( this.pos< len){
                    c = this.expression.charAt(this.pos);
                    if((c>='a'&& c<='z') || (c>='A'&& c<='Z') || (c>='0' && c<='9')||(c =='_'))
                    {
//                    if(c!=' ' && !isOperator(c) && !isOtherOperator(c)){
                        b = b+""+c;
                        this.pos++;
                        continue;
                    }
                    break;
                }

                var bolan = new Bolan(1,b.toString());
                return bolan;
            }else{
              //比较运算符或‘=’运算符
              var b = ""+c;
              this.pos++;
              
               c = this.expression.charAt(this.pos);
               if(c == '='){
                   b = b+""+c;
                   this.pos++;
                }

              var express = b.toString();
              if(express == "="){
                return new Bolan(4,express);
              }else if(isOtherOperator(express)){
                return new Bolan(3,express);
              }
              continue;
            }
        }
        return null;
    }
/**
 * 是否是算数表达式
 */
    function isOperator(c){
        return (c=='+') ||(c=='-') ||(c=='*') ||(c=='/')||(c=='(')||(c==')');
    }

/**
 *是否是比较运算符或赋值运算符
 */
    function isOtherOperator(oper){
      var ret = 
          (oper == ">") ||
          (oper == "<") ||
          (oper == "==") ||
          (oper == ">=") ||
          (oper == "<=") ||
          (oper == "!=") ||
          (oper == "=");
          
          
          return ret;
    }






MathString.prototype.addToBolanList = function (bolan){
                if(this.opers.length() == 0){
                    this.opers.push(bolan);
                }else{
                    var oper = bolan.operator;
                    var stackCurOper = this.opers.pop();
                    var preOper = stackCurOper.operator;
                    this.opers.push(stackCurOper);
                    
                    var l = this.bolanList.length;
                    if(oper=="+"  || oper == "-"){
                        if(preOper == "+" || preOper == "-" || preOper == "*" || preOper == "/"){
                            this.bolanList[l] = this.opers.pop();
                            this.addToBolanList(bolan);
                        }else{
                            this.opers.push(bolan);
                        }
                    }else if(oper == "*" || oper == "/"){
                        if(preOper == "*" || preOper == "/"){
                            this.bolanList[l] = this.opers.pop();
                            this.addToBolanList(bolan);
                        }else{
                            this.opers.push(bolan);
                        }
                    }else if(oper == "("){
                        this.opers.push(bolan);
                    }else if(oper == ")"){
                        var  b = this.opers.pop();
                        while(b.operator != "(")
                        {
                            l = this.bolanList.length;
                            this.bolanList[l] = b;
                            b = this.opers.pop();
                        }
                    }else if(isOtherOperator(oper)){
                      while(this.opers.length() > 0){
                        var len = this.bolanList.length;
                        this.bolanList[len] = this.opers.pop();
                      }
                      this.opers.push(bolan);
                    }
                }

}




//计算表达式
MathString.prototype.compute = function (){

        var comp = new Stack();

        for(var i=0;i<this.bolanList.length;i++){
            var item = this.bolanList[i];
            if(item.flag == 0){
                //如果是数字，放入堆栈中
                comp.push(item);
            }else if(item.flag == 1){
                //如果是变量，放入堆栈中
                item.value = getValue(item.operator);
                
                comp.push(item);
            }else if(item.flag == 2){
                //如果是运算符（+-*/）

                var v2 = parseFloat((comp.pop()).value);
                var v1 = parseFloat((comp.pop()).value);
                var v = 0;
                if(item.operator == "+" ){
                    v = v1+v2;
                }else if(item.operator == "-"){
                    v = v1-v2;
                }else if(item.operator == "*"){
                    v = v1*v2;
                }else if(item.operator == "/"){
//                  if(v2 == 0){
//                    return null;
//                  }
//                  v = v1/v2;
                  if(v2 == 0){
                    v = 0;
                  }else{
                    v = v1/v2;
                  }
                }

                //将返回结果构建一个类型为数字的Bolan对象
                var b = new Bolan(0,""+v);
                //放入堆栈中
                comp.push(b);
            }else if(item.flag == 3){
                //如果是比较运算符（> >= < <= == !=）
                var v2 = parseFloat((comp.pop()).value);
                var v1 = parseFloat((comp.pop()).value);
              var res =false;
              if(item.operator == (">")){
                  res  = v1>v2;
              }else if(item.operator == (">=")){
                res  = v1>=v2;
              }else if(item.operator == ("<")){
                res  = v1<v2;
              }else if(item.operator == ("<=")){
                res  = v1<=v2;
              }else if(item.operator == ("==")){
                res  = v1==v2;
              }else if(item.operator == ("!=")){
                res  = v1!=v2;
              }

              //将结果bolean值放入Bolan对象中，返回
              item.bolValue = res;
              return item;

            }else if(item.flag == 4){
                //如果是赋值=
                var v2 = parseFloat((comp.pop()).value);
              var b = comp.pop();
              b.value = v2;

              return b;
            }
        }

        return comp.pop();
    }


MathString.prototype.isInExpress = function(hc){
        var len = this.bolanList.length;
        var index = 0;
        if(this.bolanList[len - 1].operator == '='){
            index = 1;
        }
        while(index < len)
        {
            if(this.bolanList[index].flag!=0 && this.bolanList[index].operator.toString() == hc.toString()){
                return true;
            }
            
            index++;
        }
        
        return false;
}

