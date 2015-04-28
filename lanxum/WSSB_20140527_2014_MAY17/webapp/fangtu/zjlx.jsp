	//证件类型的javascript 数组
	var arySelect_zlqx = [
		<bean:size id="size_zjlx" name="zjlx_list"/>
		<%
			Integer list_zjlx = (Integer) pageContext.getAttribute("size_zjlx");
			int counter_zjlx = 1; 
		%>
		<logic:iterate id="item" name="zjlx_list">
			[
			"<bean:write name="item" property="value"/>",
			"<bean:write name="item" property="label"/>"
			]
			<%
				if ( list_zjlx != null )
					if ( list_zjlx.intValue() > counter_zjlx++ ) out.println("\t,\n");
			%>
			
		</logic:iterate>
	];
	
	
	//构造一个证件类型的option
	var option_zjlx = ""
	<logic:iterate id="item" name="zjlx_list">
	+ '<option value="<bean:write name="item" property="value"/>"><bean:write name="item" property="label"/></option>'
	</logic:iterate>
	;