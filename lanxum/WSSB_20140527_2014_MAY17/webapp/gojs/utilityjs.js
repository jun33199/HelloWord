/*********������ר��js********/
function InitialPage(){
    for(i=0;i<info_array_2.length;i++){
        var otr=Table_Master.insertRow();
        rowlength=Table_Master.rows.length-1;
        //��һ�� ���
        var xh_cell=otr.insertCell();
        xh_cell.innerText=rowlength;
        xh_cell.onclick=selectRow;
        xh_cell.className="2-td2-left";

        var xh_cell=otr.insertCell();
        xh_cell.innerText=info_array_2[i][5];
        xh_cell.onclick=selectRow;
        xh_cell.className="2-td2-left";

        var xh_cell=otr.insertCell();
        for (var j=0;j<clsydj_form.cllx.options.length;j++) {
            if (clsydj_form.cllx.options[j].value == info_array_2[i][6]) {
                xh_cell.innerText = clsydj_form.cllx.options[j].innerText==""?"  ":clsydj_form.cllx.options[j].innerText;
                break;
            }
            else xh_cell.innerText = "  ";
        }
        xh_cell.onclick=selectRow;
        xh_cell.className="2-td2-left";

        var xh_cell=otr.insertCell();
        for (var j=0;j<clsydj_form.hpys.options.length;j++) {
            if (clsydj_form.hpys.options[j].value == info_array_2[i][7]) {
                xh_cell.innerText = clsydj_form.hpys.options[j].innerText==""?"  ":clsydj_form.hpys.options[j].innerText;
                break;
            }
            else xh_cell.innerText = "  ";
        }
        xh_cell.onclick=selectRow;
        xh_cell.className="2-td2-left";

        var xh_cell=otr.insertCell();
        xh_cell.innerText=info_array_2[i][8];
        xh_cell.onclick=selectRow;
        xh_cell.className="2-td2-left";

        var xh_cell=otr.insertCell();
        xh_cell.innerText=info_array_2[i][9];
        xh_cell.onclick=selectRow;
        xh_cell.className="2-td2-left";

        var xh_cell=otr.insertCell();
        xh_cell.innerText=info_array_2[i][10];
        xh_cell.onclick=selectRow;
        xh_cell.className="2-td2-center";
        xh_cell.style.textAlign="center";
    }
}

//move methods
function firstRow(){
    if (currentRow == 1) {
            alert('�Ѿ��ǵ�һ����');
    }
    if(Table_Master.rows.length > 1) {
        selectRow(1);
    }
}
function lastRow(){
    if (currentRow == Table_Master.rows.length -1 && Table_Master.rows.length > 1) {
            alert('�Ѿ������һ����');
    }
    if (Table_Master.rows.length > 1){
        selectRow(Table_Master.rows.length-1);
    }
}
function previousRow(){
    if (currentRow == 0) {
        if (Table_Master.rows.length == 1) {
            alert('û�м�¼���޷�ѡ��');
        }
        else {
            alert('��ѡ��ĳһ����¼��');
        }
    }
    else if (currentRow == 1) {
        alert('�Ѿ��ǵ�һ����');
    }
    else {
        prow = currentRow - 1;
        selectRow(prow);
    }
}
function nextRow(){
    if (currentRow == 0) {
        if (Table_Master.rows.length == 1) {
            alert('û�м�¼���޷�ѡ��');
        }
        else {
            alert('��ѡ��ĳһ����¼��');
        }
    }
    else if (currentRow == Table_Master.rows.length -1) {
        alert('�Ѿ������һ����');
    }
    else {
        prow = currentRow + 1;
        selectRow(prow);
    }
}

//clear elements.
function clearFormElement(){
    clsydj_form.czmc.value="";
    clsydj_form.hphm.value="";
    clsydj_form.hpys.options.selectedIndex=0;
    clsydj_form.czzjlx.options.selectedIndex=0;
    clsydj_form.cjh.value="";
    clsydj_form.czzjhm.value="";
    clsydj_form.czdz.value="";
    clsydj_form.gjdm.options.selectedIndex=0;
    clsydj_form.cpxh.value="";
    clsydj_form.fdjh.value="";
    clsydj_form.djrq_n.value="";
    clsydj_form.djrq_y.value="";
    clsydj_form.fxszrq_n.value="";
    clsydj_form.fxszrq_y.value="";
    clsydj_form.fxszrq_r.value="";
    clsydj_form.fsrq_n.value="";
    clsydj_form.fsrq_y.value="";
    clsydj_form.fsrq_r.value="";
    clsydj_form.bfrq_n.value="";
    clsydj_form.bfrq_y.value="";
    clsydj_form.bfrq_r.value="";
    clsydj_form.tsrq_n.value="";
    clsydj_form.tsrq_y.value="";
    clsydj_form.tsrq_r.value="";
    clsydj_form.hdzk.value="";
    clsydj_form.jssqpgc.value="";
    clsydj_form.zzl.value="";
    clsydj_form.hdzzl.value="";

    clsydj_form.cllx.options.selectedIndex=0;
    zsmSource();
    clsydj_form.zsm.options.selectedIndex=0;
    clsydj_form.fsm.options.selectedIndex=0;
    clsydj_form.nynse.value="";
    clsydj_form.sfms.checked=false;
}

function selectRow(index){
    var tmpCurrentRow = currentRow; 	//�õ���ʼ״̬��currentRow�����Ϊ0ֱ����ʾ����������Ա��沢��ʾ��һ����
    if (tmpCurrentRow != 0) {
    if (checkValid()) {  //��������ԣ����档��currentRowû�ı�֮ǰ��
        modifyData();

    if(index==""||index==null){
        obj=window.event.srcElement;
        currentRow=obj.parentElement.cells[0].innerText*1;
    }
    else{
        currentRow=index;  //�õ����ڵ�currentRow
    }
    

    for(i=0;i<Table_Master.rows[currentRow].cells.length;i++) //����ѡ��ʱ�ı�����ɫ
        Table_Master.rows[currentRow].cells[i].style.backgroundColor="aabbcc";
    clsydj_form.hphm.value=info_array_2[currentRow-1][5];
    clsydj_form.cjh.value=info_array_2[currentRow-1][8];
    for (var j=0;j<clsydj_form.hpys.options.length;j++) {
        if (clsydj_form.hpys.options[j].value == info_array_2[currentRow-1][7]) {
            clsydj_form.hpys.selectedIndex = j;
            break;
        }
        else
            clsydj_form.hpys.selectedIndex = 0;
    }
    clsydj_form.czmc.value=info_array_2[currentRow-1][0];
    for (var j=0;j<clsydj_form.czzjlx.options.length;j++) {
        if (clsydj_form.czzjlx.options[j].value == info_array_2[currentRow-1][1]) {
            clsydj_form.czzjlx.selectedIndex = j;
            break;
        }
        else
            clsydj_form.czzjlx.selectedIndex = 0;
    }
    clsydj_form.czzjhm.value=info_array_2[currentRow-1][2];
    clsydj_form.czdz.value=info_array_2[currentRow-1][4];
    for (var j=0;j<clsydj_form.gjdm.options.length;j++) {
        if (clsydj_form.gjdm.options[j].value == info_array_2[currentRow-1][3]) {
            clsydj_form.gjdm.selectedIndex = j;
            break;
        }
        else
            clsydj_form.gjdm.selectedIndex = 0;
    }
    clsydj_form.cpxh.value=info_array_2[currentRow-1][9];
    clsydj_form.fdjh.value=info_array_2[currentRow-1][10];
    
    for (var j=0;j<clsydj_form.cllx.options.length;j++) {
        if (clsydj_form.cllx.options[j].value == info_array_2[currentRow-1][6]) {
            clsydj_form.cllx.selectedIndex = j;
            break;
        }
        else
            clsydj_form.cllx.selectedIndex = 0;
    }


    var djrqArr = info_array_2[currentRow-1][11].split("-");
    var fxszrqArr = info_array_2[currentRow-1][12].split("-");
    var tsrqArr  = info_array_2[currentRow-1][13].split("-");
    var fsrqArr = info_array_2[currentRow-1][14].split("-");
    var bfrqArr = info_array_2[currentRow-1][15].split("-");
    for (var i=0;i<djrqArr.length;i++) {
        if (i==0)
            clsydj_form.djrq_n.value = djrqArr[i];
        if (i==1)
            clsydj_form.djrq_y.value = djrqArr[i];
    }
    for (var i=0;i<fxszrqArr.length;i++) {
        if (i==0)
            clsydj_form.fxszrq_n.value = fxszrqArr[i];
        if (i==1)
            clsydj_form.fxszrq_y.value = fxszrqArr[i];
        if (i==2)
            clsydj_form.fxszrq_r.value = fxszrqArr[i];
    }
    for (var i=0;i<tsrqArr.length;i++) {
        if (i==0)
            clsydj_form.tsrq_n.value = tsrqArr[i];
        if (i==1)
            clsydj_form.tsrq_y.value = tsrqArr[i];
        if (i==2)
            clsydj_form.tsrq_r.value = tsrqArr[i];
    }
    for (var i=0;i<fsrqArr.length;i++) {
        if (i==0)
            clsydj_form.fsrq_n.value = fsrqArr[i];
        if (i==1)
            clsydj_form.fsrq_y.value = fsrqArr[i];
        if (i==2)
            clsydj_form.fsrq_r.value = fsrqArr[i];
    }
    for (var i=0;i<bfrqArr.length;i++) {
        if (i==0)
            clsydj_form.bfrq_n.value = bfrqArr[i];
        if (i==1)
            clsydj_form.bfrq_y.value = bfrqArr[i];
        if (i==2)
            clsydj_form.bfrq_r.value = bfrqArr[i];
    }

    clsydj_form.hdzk.value=info_array_2[currentRow-1][16];
    clsydj_form.jssqpgc.value=info_array_2[currentRow-1][17];
    clsydj_form.zzl.value=info_array_2[currentRow-1][18];
    clsydj_form.hdzzl.value=info_array_2[currentRow-1][19];
    
    //����˰Ŀ
    for (var j=0;j<clsydj_form.zsm.options.length;j++) {
        if (clsydj_form.zsm.options[j].value == info_array_2[currentRow-1][20]) {
            clsydj_form.zsm.selectedIndex = j;
            break;
        }
        else
            clsydj_form.zsm.selectedIndex = 0;
    }
    for (var j=0;j<clsydj_form.fsm.options.length;j++) {
        if (clsydj_form.fsm.options[j].value == info_array_2[currentRow-1][21]) {
            clsydj_form.fsm.selectedIndex = j;
            break;
        }
        else
            clsydj_form.fsm.selectedIndex = 0;
    }    
    clsydj_form.nynse.value=info_array_2[currentRow-1][22];
    //�������ڼ����仯
    
    if (info_array_2[currentRow-1][23] == "1") clsydj_form.sfms.checked = true;
    else clsydj_form.sfms.checked = false;

    //ȥ������֮ǰ��ѡ�м�¼����ɫ
    unselectRow();
    }
    }
    
    //-------------------------------------
    else { //ֱ����ʾ
    if(index==""||index==null){
        obj=window.event.srcElement;
        currentRow=obj.parentElement.cells[0].innerText*1;
    }
    else{
        currentRow=index;
    }

    for(i=0;i<Table_Master.rows[currentRow].cells.length;i++) //����ѡ��ʱ�ı�����ɫ
        Table_Master.rows[currentRow].cells[i].style.backgroundColor="aabbcc";

    clsydj_form.hphm.value=info_array_2[currentRow-1][5];
    clsydj_form.cjh.value=info_array_2[currentRow-1][8];
    for (var j=0;j<clsydj_form.hpys.options.length;j++) {
        if (clsydj_form.hpys.options[j].value == info_array_2[currentRow-1][7]) {
            clsydj_form.hpys.selectedIndex = j;
            break;
        }
        else
            clsydj_form.hpys.selectedIndex = 0;
    }
    clsydj_form.czmc.value=info_array_2[currentRow-1][0];
    for (var j=0;j<clsydj_form.czzjlx.options.length;j++) {
        if (clsydj_form.czzjlx.options[j].value == info_array_2[currentRow-1][1]) {
            clsydj_form.czzjlx.selectedIndex = j;
            break;
        }
        else
            clsydj_form.czzjlx.selectedIndex = 0;
    }
    clsydj_form.czzjhm.value=info_array_2[currentRow-1][2];
    clsydj_form.czdz.value=info_array_2[currentRow-1][4];
    for (var j=0;j<clsydj_form.gjdm.options.length;j++) {
        if (clsydj_form.gjdm.options[j].value == info_array_2[currentRow-1][3]) {
            clsydj_form.gjdm.selectedIndex = j;
            break;
        }
        else
            clsydj_form.gjdm.selectedIndex = 0;
    }
    clsydj_form.cpxh.value=info_array_2[currentRow-1][9];
    clsydj_form.fdjh.value=info_array_2[currentRow-1][10];
    
    for (var j=0;j<clsydj_form.cllx.options.length;j++) {
        if (clsydj_form.cllx.options[j].value == info_array_2[currentRow-1][6]) {
            clsydj_form.cllx.selectedIndex = j;
            break;
        }
        else
            clsydj_form.cllx.selectedIndex = 0;
    }
    zsmSource(); //Initialize zsm.

    var djrqArr = info_array_2[currentRow-1][11].split("-");
    var fxszrqArr = info_array_2[currentRow-1][12].split("-");
    var tsrqArr  = info_array_2[currentRow-1][13].split("-");
    var fsrqArr = info_array_2[currentRow-1][14].split("-");
    var bfrqArr = info_array_2[currentRow-1][15].split("-");
    for (var i=0;i<djrqArr.length;i++) {
        if (i==0)
            clsydj_form.djrq_n.value = djrqArr[i];
        if (i==1)
            clsydj_form.djrq_y.value = djrqArr[i];
    }
    for (var i=0;i<fxszrqArr.length;i++) {
        if (i==0)
            clsydj_form.fxszrq_n.value = fxszrqArr[i];
        if (i==1)
            clsydj_form.fxszrq_y.value = fxszrqArr[i];
        if (i==2)
            clsydj_form.fxszrq_r.value = fxszrqArr[i];
    }
    for (var i=0;i<tsrqArr.length;i++) {
        if (i==0)
            clsydj_form.tsrq_n.value = tsrqArr[i];
        if (i==1)
            clsydj_form.tsrq_y.value = tsrqArr[i];
        if (i==2)
            clsydj_form.tsrq_r.value = tsrqArr[i];
    }
    for (var i=0;i<fsrqArr.length;i++) {
        if (i==0)
            clsydj_form.fsrq_n.value = fsrqArr[i];
        if (i==1)
            clsydj_form.fsrq_y.value = fsrqArr[i];
        if (i==2)
            clsydj_form.fsrq_r.value = fsrqArr[i];
    }
    for (var i=0;i<bfrqArr.length;i++) {
        if (i==0)
            clsydj_form.bfrq_n.value = bfrqArr[i];
        if (i==1)
            clsydj_form.bfrq_y.value = bfrqArr[i];
        if (i==2)
            clsydj_form.bfrq_r.value = bfrqArr[i];
    }

    clsydj_form.hdzk.value=info_array_2[currentRow-1][16];
    clsydj_form.jssqpgc.value=info_array_2[currentRow-1][17];
    clsydj_form.zzl.value=info_array_2[currentRow-1][18];
    clsydj_form.hdzzl.value=info_array_2[currentRow-1][19];
    
    //����˰Ŀ
    for (var j=0;j<clsydj_form.zsm.options.length;j++) {
        if (clsydj_form.zsm.options[j].value == info_array_2[currentRow-1][20]) {
            clsydj_form.zsm.selectedIndex = j;
            break;
        }
        else
            clsydj_form.zsm.selectedIndex = 0;
    }
    for (var j=0;j<clsydj_form.fsm.options.length;j++) {
        if (clsydj_form.fsm.options[j].value == info_array_2[currentRow-1][21]) {
            clsydj_form.fsm.selectedIndex = j;
            break;
        }
        else
            clsydj_form.fsm.selectedIndex = 0;
    }    
    clsydj_form.nynse.value=info_array_2[currentRow-1][22];
    //�������ڼ����仯
    
    if (info_array_2[currentRow-1][23] == "1") clsydj_form.sfms.checked = true;
    else clsydj_form.sfms.checked = false;

    //ȥ������֮ǰ��ѡ�м�¼����ɫ
    unselectRow();	
    }
    zsmSource(); //Initialize zsm.
}

function unselectRow(){
    for (j=1;j<Table_Master.rows.length;j++) {
        if (j == currentRow) continue;
        for(i=0;i<Table_Master.rows[j].cells.length;i++)
          Table_Master.rows[j].cells[i].style.backgroundColor="#ECF2F4";
    }
}

function addRow(){
    if (currentRow > 0){ //ȡ���û��������״̬
        currentRow = 0;
        unselectRow();
        clearFormElement();
    }
    else {
        if (checkValid()) {
                //��������
    		var czmc_value = clsydj_form.czmc.value;
        	var czzjlx_value = clsydj_form.czzjlx.value;
	        var czzjhm_value = clsydj_form.czzjhm.value;
        	var gjdm_value = clsydj_form.gjdm.value;
 	        var czdz_value = clsydj_form.czdz.value;
        	var hphm_value = clsydj_form.hphm.value;
	        var cllx_value = clsydj_form.cllx.value;
        	var hpys_value = clsydj_form.hpys.value;
	        var cjh_value = clsydj_form.cjh.value;
        	var cpxh_value = clsydj_form.cpxh.value;
	        var fdjh_value = clsydj_form.fdjh.value;
        	var djrq_value = clsydj_form.djrq_n.value + "-" + clsydj_form.djrq_y.value+"-01";
	        var fxszrq_value = clsydj_form.fxszrq_n.value + "-" + clsydj_form.fxszrq_y.value + "-" + clsydj_form.fxszrq_r.value;
        	var tsrq_value = clsydj_form.tsrq_n.value + "-" + clsydj_form.tsrq_y.value + "-" + clsydj_form.tsrq_r.value;
	        var fsrq_value = clsydj_form.fsrq_n.value + "-" + clsydj_form.fsrq_y.value + "-" + clsydj_form.fsrq_r.value;
        	var bsrq_value = clsydj_form.bfrq_n.value + "-" + clsydj_form.bfrq_y.value + "-" + clsydj_form.bfrq_r.value;
	        var hdzk_value = clsydj_form.hdzk.value;
        	var jssqpgc_value = clsydj_form.jssqpgc.value;
	        var zzl_value = clsydj_form.zzl.value;
        	var hdzzl_value = clsydj_form.hdzzl.value;
	        var zsm_value = clsydj_form.zsm.value;
        	var fsm_value = clsydj_form.fsm.value;
	        var nynse_value = clsydj_form.nynse.value;
        	var sfms_value = clsydj_form.sfms.value;

	        var otr=Table_Master.insertRow();        
        	rowlength=Table_Master.rows.length-1;
        	
	        var xh_cell=otr.insertCell();
	        xh_cell.innerText=rowlength;
	        xh_cell.onclick=selectRow;        
	        xh_cell.className="2-td2-left";

	        var xh_cell=otr.insertCell();
	        xh_cell.innerText=hphm_value==""?"  ":hphm_value;
	        xh_cell.onclick=selectRow;       
	        xh_cell.className="2-td2-left";

	        var xh_cell=otr.insertCell();
	        xh_cell.innerText=clsydj_form.cllx.options[clsydj_form.cllx.selectedIndex].innerText==""?"  ":clsydj_form.cllx.options[clsydj_form.cllx.selectedIndex].innerText;
        	xh_cell.onclick=selectRow;        
	        xh_cell.className="2-td2-left";

	        var xh_cell=otr.insertCell();
	        xh_cell.innerText=clsydj_form.hpys.options[clsydj_form.hpys.selectedIndex].innerText==""?"  ":clsydj_form.hpys.options[clsydj_form.hpys.selectedIndex].innerText;
        	xh_cell.onclick=selectRow;        
	        xh_cell.className="2-td2-left";

	        var xh_cell=otr.insertCell();
        	xh_cell.innerText=cjh_value==""?"  ":cjh_value;        
	        xh_cell.onclick=selectRow;
        	xh_cell.className="2-td2-left";

	        var xh_cell=otr.insertCell();
        	xh_cell.innerText=cpxh_value==""?"  ":cpxh_value;
	        xh_cell.onclick=selectRow;        
 	        xh_cell.className="2-td2-left";

	        var xh_cell=otr.insertCell();
        	xh_cell.innerText=fdjh_value==""?"  ":fdjh_value;
        	xh_cell.onclick=selectRow;        
	        xh_cell.className="2-td2-center";
        	xh_cell.style.textAlign="center";
	
                //���뵽ȫ�������� 
	        var info_array2 = [[czmc_value,czzjlx_value,czzjhm_value,gjdm_value,czdz_value,
                          	    hphm_value,cllx_value,hpys_value,cjh_value,cpxh_value,fdjh_value,
                                    djrq_value,fxszrq_value,tsrq_value,fsrq_value,bsrq_value,hdzk_value,
                                    jssqpgc_value,zzl_value,hdzzl_value,zsm_value,fsm_value,nynse_value,sfms_value
                                  ]];
	        info_array_2 = info_array_2.concat(info_array2);
        }  
    }
}

function deleteRow(){
    if (currentRow ==0 ) {
        alert('��ѡ��һ����¼��');
        return;
    }
    if(currentRow!=0) Table_Master.deleteRow(currentRow);
    for(i=Table_Master.rows.length-1;i>0;i--){
        var obj=Table_Master.rows(i).cells(0);
        obj.innerText=i;
    }
    clearFormElement();

    if(currentRow == 1)
        info_array_2 = info_array_2.slice(1);
    else if (currentRow == info_array_2.length)
        info_array_2 = info_array_2.slice(0,currentRow-1);
    else {
        temp2 = info_array_2.slice(0,currentRow-1);
        temp1 = info_array_2.slice(currentRow);
        info_array_2 = temp2.concat(temp1);
    }
    currentRow=0;
}

function modifyData()
{
    if(currentRow>0)
    {
        var tempFlag;
        var djrq=clsydj_form.djrq_n.value+"-"+clsydj_form.djrq_y.value+"-01";
        var fxszrq=clsydj_form.fxszrq_n.value+"-"+clsydj_form.fxszrq_y.value+"-"+clsydj_form.fxszrq_r.value;
        var tsrq=clsydj_form.tsrq_n.value+"-"+clsydj_form.tsrq_y.value+"-"+clsydj_form.tsrq_r.value;
        var fsrq=clsydj_form.fsrq_n.value+"-"+clsydj_form.fsrq_y.value+"-"+clsydj_form.fsrq_r.value;
        var bsrq=clsydj_form.bfrq_n.value+"-"+clsydj_form.bfrq_y.value+"-"+clsydj_form.bfrq_r.value;
        if(clsydj_form.sfms.checked)   tempFlag="1";
        if(clsydj_form.sfms.checked==false)   tempFlag="0";

        var temp=new Array([clsydj_form.czmc.value,clsydj_form.czzjlx.value,clsydj_form.czzjhm.value,clsydj_form.gjdm.value,clsydj_form.czdz.value,
                           clsydj_form.hphm.value,clsydj_form.cllx.value,clsydj_form.hpys.value,clsydj_form.cjh.value,
                           clsydj_form.cpxh.value,clsydj_form.fdjh.value,
                           djrq,fxszrq,tsrq,fsrq,bsrq,
                           clsydj_form.hdzk.value,clsydj_form.jssqpgc.value,
                           clsydj_form.zzl.value,clsydj_form.hdzzl.value,clsydj_form.zsm.value,
                           clsydj_form.fsm.value,clsydj_form.nynse.value,tempFlag,
                           ]);
        if(currentRow==1)
        {
            info_array_2=info_array_2.slice(1);
            info_array_2=temp.concat(info_array_2);
        }
        else if(currentRow==info_array_2.length)
        {
            info_array_2=info_array_2.slice(0,currentRow-1);
            info_array_2=info_array_2.concat(temp);
        }
        else
        {
            temp2=info_array_2.slice(0,currentRow-1);
            temp1=info_array_2.slice(currentRow);
            info_array_2=temp2.concat(temp);
            info_array_2=info_array_2.concat(temp1);

        }
    }
}

//���ݳ����������ж���˰Ŀ
function zsmSource() {
                clsydj_form.zsm.length=0;  //���select��options
                zsmjsjsArray.length=0;
                var j=0;
                var k=0;
                var tempStr=clsydj_form.cllx.options[clsydj_form.cllx.selectedIndex].value;
                for(var i=zsmdmArray.length-1;i>=0;i--)
                {
                    var temp=zsmdmArray[i][0].toString();
                    if(clsydj_form.hpys.value=="3")
                    {
                        if((temp.substring(0,2)=="88"||temp.substring(0,2)=="11") && tempStr==temp.substring(temp.length-3))
                        {
                          
                           var op=new Option(zsmdmArray[i][1].toString(),zsmdmArray[i][0].toString(),false,false);
                           clsydj_form.zsm.options[clsydj_form.zsm.options.length]=op;
                           zsmjsjsArray[j]=zsmdmArray[i][2];
                           j++;
                          
                        }
                    }
                    else
                    {
                        if(temp.substring(0,2)=="11" && tempStr==temp.substring(temp.length-3))
                        {
                           var op=new Option(zsmdmArray[i][1].toString(),zsmdmArray[i][0].toString(),false,false);
                           clsydj_form.zsm.options[clsydj_form.zsm.options.length]=op;
                           zsmjsjsArray[j]=zsmdmArray[i][2];
                           j++;
                        }
                    }
                       
                }
                clsydj_form.zsm.selectedIndex = 0;
                
                clsydj_form.fsm.length=0;
                if(clsydj_form.hpys.value=="3")
                {
                	if(clsydj_form.cllx.value=="321")
                	{
                    	for(i=zsmdmArray.length-1;i>=0;i--)
                    	{
                    		temp=zsmdmArray[i][0].toString();
                    		if((temp.substring(0,2)=="88"||temp.substring(0,2)=="11") && temp.substring(temp.length-3)=="421")
                    		{
                    			var op=new Option(zsmdmArray[i][1].toString(),zsmdmArray[i][0].toString(),false,false);
                        		clsydj_form.fsm.options[clsydj_form.fsm.options.length]=op;
                        		fsmjsjsArray[k]=zsmdmArray[i][2];
                        		k++;
                        	}	
                        }
 
                	}
                	
                	if(clsydj_form.cllx.value=="144")
                	{

                    	for(i=zsmdmArray.length-1;i>=0;i--)
                    	{
                    		temp=zsmdmArray[i][0].toString();
                    		if((temp.substring(0,2)=="88"||temp.substring(0,2)=="11") && temp.substring(temp.length-3)=="441")
                    		{

                    			var op=new Option(zsmdmArray[i][1].toString(),zsmdmArray[i][0].toString(),false,false);
                        		clsydj_form.fsm.options[clsydj_form.fsm.options.length]=op;
                        	}
                        	fsmjsjsArray[k]=zsmdmArray[i][2];
                        	k++;	
                        }
                	}
                	
                }	
                else
                {
                    if(clsydj_form.cllx.value=="321")
                	{
                    	for(i=zsmdmArray.length-1;i>=0;i--)
                    	{
                    		temp=zsmdmArray[i][0].toString();
                    		if(temp.substring(0,2)=="11" && temp.substring(temp.length-3)=="421")
                    		{

                    			var op=new Option(zsmdmArray[i][1].toString(),zsmdmArray[i][0].toString(),false,false);
                        		clsydj_form.fsm.options[clsydj_form.fsm.options.length]=op;
                        		fsmjsjsArray[k]=zsmdmArray[i][2];
                        		k++;
                        	}	
                        }
                	}
                	
                	if(clsydj_form.cllx.value=="144")
                	{
                    	for(i=zsmdmArray.length-1;i>=0;i--)
                    	{
                    		temp=zsmdmArray[i][0].toString();
                    		if(temp.substring(0,2)=="11" && temp.substring(temp.length-3)=="441")
                    		{
                    			var op=new Option(zsmdmArray[i][1].toString(),zsmdmArray[i][0].toString(),false,false);
                        		clsydj_form.fsm.options[clsydj_form.fsm.options.length]=op;
                        		fsmjsjsArray[k]=zsmdmArray[i][2];
                        		k++;
                        	}	
                        }
                	}
                }
                clsydj_form.fsm.selectedIndex = 0;
                
                //��������˰��
                if(clsydj_form.hpys.value=="3")
                {
                    var temp=0;
                    if(clsydj_form.cllx.value=="321")
                    {
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i]);
                       }
                       for(i=fsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(fsmjsjsArray[i])*parseInt(clsydj_form.hdzzl.value);
                       }
                       
                    }
                    else if(clsydj_form.cllx.value=="144")
                    {
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i])*20;
                       }
                       for(i=fsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(fsmjsjsArray[i])*(parseInt(clsydj_form.hdzzl.value)-20);
                       }
                    }
                    else if(clsydj_form.cllx.value=="151" || clsydj_form.cllx.value=="161")
                    {
                       
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i])*parseInt(clsydj_form.hdzzl.value);
                           
                       }
                       
                    }
                    else
                    {
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i]);
                       }
                       
                    }
                    clsydj_form.nynse.value=temp.toString();
                }
                else
                {
                    var temp=0;
                    if(clsydj_form.cllx.value=="321")
                    {
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i]);
                       }
                       for(i=fsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(fsmjsjsArray[i])*parseInt(clsydj_form.hdzzl.value);
                       }
                       
                    }
                    else if(clsydj_form.cllx.value=="144")
                    {
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i])*20;
                       }
                       for(i=fsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(fsmjsjsArray[i])*(parseInt(clsydj_form.hdzzl.value)-20);
                       }
                    }
                    else if(clsydj_form.cllx.value=="151" || clsydj_form.cllx.value=="161")
                    {
                       
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i])*parseInt(clsydj_form.hdzzl.value);
                           
                       }
                       
                    }
                    else
                    {
                       for(i=zsmjsjsArray.length-1;i>=0;i--)
                       {
                           temp=temp+parseInt(zsmjsjsArray[i]);
                       }
                       
                    }
                    clsydj_form.nynse.value=temp.toString();
                }
               
        }
//��˰Ŀ�仯��Ӧ��˰��Ҳ�仯
function nynseChange() {
   
}

function checkValid() { //check validation
    if (getStringLength(clsydj_form.hphm.value) > 10) { //��飺���ƺ���
        alert('���ƺ��������������������Ϊ10��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        clsydj_form.hphm.focus();
        return false;	
    }
    else if (getStringLength(clsydj_form.cjh.value) > 30) { //��飺���ܺ�
        alert('���ܺ������������������Ϊ30��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        clsydj_form.cjh.focus();
        return false;	
    }
    else if (clsydj_form.czzjlx.value == "") {  //��飺����֤������
    	alert('֤������һ��Ҫѡ��');
    	clsydj_form.czzjlx.focus();
    	return false;    	
    }
    else if (clsydj_form.czzjhm.value == "") {  //��飺����֤������_�ǿ���
    	alert('֤������һ��Ҫ��д��');
    	clsydj_form.czzjhm.focus();
    	return false;    	
    }
    else if (getStringLength(clsydj_form.czzjhm.value) > 18) {  //��飺����֤������_����
    	alert('֤�����������������������Ϊ18��\n���ֳ���Ϊ2����ĸ������Ϊ1');
    	clsydj_form.czzjhm.focus();
    	return false;    	
    }
    else if (clsydj_form.czzjlx.options[clsydj_form.czzjlx.selectedIndex].innerText=="���֤" && checkIdentityCard(clsydj_form.czzjhm.value) != 0) {  //��飺����֤������_������
    	alert('���֤���벻��ȷ��');
    	clsydj_form.czzjhm.focus();
    	return false;    	
    }
    else if (clsydj_form.gjdm.value == "") {  //��飺����
    	alert('����һ��Ҫѡ��');
    	clsydj_form.gjdm.focus();
    	return false;
    }
    else if (getStringLength(clsydj_form.cpxh.value) > 20) { //��飺�����ͺ�
        alert('�����ͺ������������������Ϊ20��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        clsydj_form.cpxh.focus();
        return false;	
    }
    else if (getStringLength(clsydj_form.fdjh.value) > 30) { //��飺��������
        alert('�������������������������Ϊ30��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        clsydj_form.fdjh.focus();
        return false;	
    }
    //������ںϸ���
    else if (clsydj_form.fsrq_n.value != "" || clsydj_form.fsrq_y.value != "" || clsydj_form.fsrq_r.value != "") { //��飺��ʻʱ��
        var fsrq = clsydj_form.fsrq_n.value + "-" + clsydj_form.fsrq_y.value + "-" + clsydj_form.fsrq_r.value;
        if (!isDate(fsrq)) {
            alert('��ʻʱ���ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    else if (clsydj_form.bfrq_n.value != "" || clsydj_form.bfrq_y.value != "" || clsydj_form.bfrq_r.value != "") { //��飺����ʱ��
        var bfrq = clsydj_form.bfrq_n.value + "-" + clsydj_form.bfrq_y.value + "-" + clsydj_form.bfrq_r.value;
        if (!isDate(bfrq)) {
            alert('����ʱ���ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    else if (clsydj_form.tsrq_n.value != "" || clsydj_form.tsrq_y.value != "" || clsydj_form.tsrq_r.value != "") { //��飺ͣʻʱ��
        var tsrq = clsydj_form.tsrq_n.value + "-" + clsydj_form.tsrq_y.value + "-" + clsydj_form.tsrq_r.value;
        if (!isDate(tsrq)) {
            alert('ͣʻʱ���ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    else if (clsydj_form.djrq_n.value != "" || clsydj_form.djrq_y.value != "") { //��飺�Ǽ�����
        var djrq = clsydj_form.djrq_n.value + "-" + clsydj_form.djrq_y.value + "-01";
        if (!isDate(djrq)) {
            alert('�Ǽ����ڸ�ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    else if (clsydj_form.fxszrq_n.value != "" || clsydj_form.fxszrq_y.value != "" || clsydj_form.fxszrq_r.value != "") { //��飺����ʻ֤����
        var fxszrq = clsydj_form.fxszrq_n.value + "-" + clsydj_form.fxszrq_y.value + "-" + clsydj_form.fxszrq_r.value;
        if (!isDate(fxszrq)) {
            alert('����ʻ֤���ڸ�ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    else if (clsydj_form.hdzk.value != "") { //��飺�˶��ؿ�
        if (!isAllCharValid(clsydj_form.hdzk.value,"0123456789") || clsydj_form.hdzk.value.charAt(0)=="0") {
            alert('�˶��ؿ�Ҫ������������');
            clsydj_form.hdzk.select();
            return false; 
        }
    }
    else if (clsydj_form.jssqpgc.value != "") { //��飺��ʻ��ǰ�Ź���
        if (!isAllCharValid(clsydj_form.jssqpgc.value,"0123456789") || clsydj_form.jssqpgc.value.charAt(0)=="0") {
            alert('��ʻ��ǰ�Ź���Ҫ������������');
            clsydj_form.jssqpgc.select();
            return false; 
        }
    }
    else if (clsydj_form.zzl.value != "") { //��飺������
        if (!isDigitString(clsydj_form.zzl.value,6,1) || clsydj_form.zzl.value.charAt(0)=="0") {
	        alert('������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	clsydj_form.zzl.select();
            	return false; 
        }
    }
    else if (clsydj_form.zzl.value != "") { //��飺������
            if (eval(clsydj_form.zzl.value) < 1000) {
	        alert('������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	clsydj_form.zzl.select();
            	return false; 
            }
    }
    else if (clsydj_form.hdzzl.value != "") { //��飺�˶�������
        if (!isDigitString(clsydj_form.hdzzl.value,6,1) || clsydj_form.hdzzl.value.charAt(0)=="0") {
            	alert('�˶�������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	clsydj_form.hdzzl.select();
                return false; 
        }

    }
    else if (clsydj_form.hdzzl.value != "") { //��飺�˶�������
            if (eval(clsydj_form.hdzzl.value) < 1000) {
            	alert('�˶�������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	clsydj_form.hdzzl.select();
                return false; 
            }
    }
    else if (clsydj_form.nynse.value != "") { //��飺��Ӧ��˰��
        if (!isDigitString(clsydj_form.nynse.value,8,2) || clsydj_form.nynse.value.charAt(0)=="0") {
            	alert('��Ӧ��˰��Ҫ��������\n�ɾ�ȷ��С�������λ����');
            	clsydj_form.nynse.select();
                return false; 
        }

    }
        
    return true;    
}
/*********������ר��js********/
