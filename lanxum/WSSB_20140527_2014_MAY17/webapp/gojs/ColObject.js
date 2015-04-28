function ColObject(name, type, action, showlen, maxlength, align, value, editable, className, style, td){
	this.name = name;
	this.type = type;
	this.action = action;
	this.showlen = showlen;
	this.maxlength = maxlength;
	this.align = align;
	this.value = value;
	this.editable = editable;
	this.className = className;
	this.style = style;
	this.td = td;
}

ColObject.prototype.html = function(){
	var html = "";
	switch(this.type){
		case 0:
			html = this.textField();
			break;
		case 1:
			html = this.hidden();
			break;
		case 2:
			html = this.date();
			break;
		case 3:
			html = this.checkBox();
			break;
		case 4:
			html = this.text();
			break;
		default:
			break;
	}
	return html;
}

ColObject.prototype.text = function(){
	return this.value;
}

ColObject.prototype.textField = function(){
	var html = "<input type='textfield' name='"+this.name+"' ";
	if(this.value!=null&&this.value!="") html += " value='"+this.value+"' ";
	if(this.showlen!=null) html += " size="+this.showlen;
	if(this.maxlength!=null) html += " maxlength="+this.maxlength;
	if(!this.editable) html += " readonly ";
	if(this.align!=null){
		if(this.align==0) html += " align='left' ";
		else if(this.align==1) html += " align='middle' ";
		else if(this.align==2) html += " align='right' ";
	}
	if(this.style!=null&&this.style!="") html += " style='"+this.style+"'";
	if(this.className!=null&&this.className!="") html += " class='"+this.className+"'";
	if(this.action!=null&&this.action!="") html += this.action;
	html += ">";
	return html;
}

ColObject.prototype.hidden = function(){
	var html = "<input type='hidden' name='"+this.name+"' ";
	if(this.value!=null&&this.value!="") html += " value='"+this.value+"' ";
	html += ">";
	return html;
}

ColObject.prototype.date = function(){
	var html = "<input type='textfield' name='"+this.name+"' ";
	if(this.value!=null&&this.value!="") html += " value='"+this.value+"' ";
	if(this.showlen!=null) html += " length="+this.showlen;
	if(this.maxlength!=null) html += " maxlength="+this.maxlength;
	if(!this.editable) html += " readonly ";
	if(this.align!=null){
		if(this.align==0) html += " align='left' ";
		else if(this.align==1) html += " align='middle' ";
		else if(this.align==2) html += " align='right' ";
	}
	if(this.style!=null&&this.style!="") html += " style='"+this.style+"'";
	if(this.className!=null&&this.className!="") html += " class='"+this.className+"'";
	if(this.action!=null&&this.action!="") html += this.action;
	html += ">ÈÕÆÚ";
	return html;
}

ColObject.prototype.checkBox = function(){
	var html = "<checkbox name='"+this.name+"' ";
	if(this.value!=null&&this.value!="") html += " value='"+this.value+"' ";
	if(!this.editable) html += " disabled ";
	if(this.style!=null&&this.style!="") html += " style='"+this.style+"'";
	if(this.className!=null&&this.className!="") html += " class='"+this.className+"'";
	if(this.action!=null&&this.action!="") html += this.action;
	html += ">";
	return html;
}

function TDObject(id, className, colspan){
	this.id = id;
	this.className = className;
	this.colspan = colspan;
}

function TRObject(id, className){
	this.id = id;
	this.className = className;
}

TRObject.prototype.html = function(table, cols, trArr){
	var trNode = document.all(table).insertRow(trArr.length+1);
	if(this.className!=null&&this.className!="") trNode.className = this.className;
	var html = "";
	for(i=0;i<cols.length;i++){
		if(cols[i].type==1){
			html += cols[i].html();
			continue;
		}
		var tdNode = trNode.insertCell();
		if(cols[i].td.id!=null&&cols[i].td.id!="") tdNode.id = cols[i].td.id;
		if(cols[i].td.className!=null&&cols[i].td.className!="") tdNode.className = cols[i].td.className;
		if(cols[i].td.colspan!=null) tdNode.colspan = cols[i].td.colspan;
		//alert(cols[i].html() + html);
		tdNode.innerHTML = cols[i].html() + html;
		html = "";
		trNode.appendChild(tdNode);
	}
	trArr[trArr.length] = trNode;
}

