	//�������ص�javascript ����
	var arySelect_zlqx = [
		<bean:size id="size_zlqx" name="zlqx_list"/>
		<%
			Integer list_zlqx = (Integer) pageContext.getAttribute("size_zlqx");
			int counter_zlqx = 1; 
		%>
		<logic:iterate id="item" name="zlqx_list">
			[
			"<bean:write name="item" property="value"/>",
			"<bean:write name="item" property="label"/>"
			]
			<%
				if ( list_zlqx != null )
					if ( list_zlqx.intValue() > counter_zlqx++ ) out.println("\t,\n");
			%>
			
		</logic:iterate>
	];
	
	//���õ�ǰ��¼�û�����������
	for ( var i=0; i < arySelect_zlqx.length; i++ ) {
		var ar = arySelect_zlqx[i];
		if ( ar[0] == zlqx ) {
			zlqx_name = ar[1];
			break;
		}
	}
	
	
	//����һ���������ص�option
	var option_zlqx = ''
	<logic:iterate id="item_zlqx" name="zlqx_list">
	+ '<option value="<bean:write name="item_zlqx" property="value"/>"><bean:write name="item_zlqx" property="label"/></option>'
	</logic:iterate>
	;