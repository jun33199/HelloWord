var stackList;

function Stack(){
    this.stackList = new Array();
    this.index = -1;
}
Stack.prototype.push = function(obj){
   this.index ++;
   this.stackList[this.index] = obj;
}

Stack.prototype.pop = function(){
    if(this.index < 0){
         alert("堆栈数组长度小于0");
         return null;
     }
        
   var obj = this.stackList[this.index];
   this.index--;
   return obj;
}


Stack.prototype.length = function(){
    return this.index +1;
}

