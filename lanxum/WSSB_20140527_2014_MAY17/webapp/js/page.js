/***Begin***ҳ����ص�JavaScript�ű� ******************************/
//ҳ���ַ���ȥ�ո�
function killSpace(str)
{
	//ȥ�ո� 
	var reStr = str.replace(/[ ]/g,"");
	return reStr;
}

/**
 * �ж��Ƿ�ѡ���˲�ѯ�б��еĵ�ѡ��
 * radios ��Ҫ����ѡ���жϵĵ�ѡ����
 */
function isSelected(radios) 
{
    try 
	{
        // ��ѯ�б���ֻ����һ����¼
        if ( radios.length == null ) 
		{
            if ( radios.checked ) 
			{
                return true;
            }
        } 
		else 
		{
            // ��ѯ�б��д��ڶ�����¼
            for ( i = 0; i < radios.length; i++ ) 
			{
                if ( radios[i].checked ) 
				{
                    return true;
                }
            }
        }
        // û��ѡ���κε�ѡ����ѡ����
        return false;
    }
    catch(ex) 
	{
        // ��ѯ�б���û�н��
        return false;
    }
}


/**
 * ȡ���Ѿ�ѡ��ĵ�ѡ���Value
 * radios ��Ҫ����ѡ���жϵĵ�ѡ����
 */
function getSelectedValue(radios) 
{
    //var result = new Array();
	var value;
	//ֻ��һ����¼
    if ( radios.length == null ) 
	{
        // ���
		value = radios.value;
        //result[0] = document.forms[0].arrJbbh.value;
        // ���������
        //result[1] = document.forms[0].nsrjsjdms.value;
        
    } 
	else 
	{
        // �������е�ѡ�򣬲����Ƿ��б�ѡ��ĵ�ѡ��
        for ( i = 0; i < radios.length; i++ ) 
		{
            if ( radios[i].checked ) 
			{
				//���
				value = radios[i].value;
                // �ٱ����
                //result[0] = document.forms[0].arrJbbh[i].value;
                // ���������
                //result[1] = document.forms[0].nsrjsjdms[i].value;
                
                break;
            }
        }
    }
    return value;
}

/**
 * ����ʾƵ�м���´���
 * URLStr �´���·��
 * width ��
 * height ��
 * name �¿���������
 */
var popUpWin=0;
function popUpWindow(URLStr, width, height, name) {
    if ( popUpWin )
    {
        if(!popUpWin.closed) popUpWin.close();
    }
	//��ʾƵ��
	var windowWidth = window.screen.width;
	//��ʾƵ��
	var windowHeight = window.screen.height;
	//�����¿�������߾ࡢ�ϱ߾�
	var left = (windowWidth - width) / 2;
	var top = (windowHeight - height) / 2;
	var tmp = window.open(URLStr, name, 'toolbar=no,location=no,directories=no,status=yes,menub ar=no,scrollbars=yes,resizable=no,copyhistory=yes,width='+width+',height='+height+',left='+left+', top='+top+',screenX='+left+',screenY='+top+'', "_blank");
    return tmp;
}
/***End*****ҳ����ص�JavaScript�ű� *************************************/
