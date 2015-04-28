var constTrue = 1;
var constFalse = 0;

var constLoadStatus = 0; //load from database,did not be modified;
var constNewStatus = 1;	//new record,not saved in database
var constUpdateStatus = 2; //load from database,modified by user;
var constDeleteStatus = 3; //load from database,deleted by user;
var constObsoleteStatus = 4;//new record,deleted by user;
var constCanEditStatus = 5;//load from database,can be modified, if modified, change it to UpdataStatus;

var constCTDefault = 0;
var constCTInput = 1;
var constCTSelect = 2;
var constCTRadioButton = 3;
var constCTCheckBox = 4;
var constCTSequence = 5;
var constCTHideTD = 6;

var constRowSeparator = "^$$";
var constColSeparator = "$^^";
var constLabelSeparator = "!^";


var _view_style_table = 1;
var _view_style_card = 2;

function _getConstStatusText(status) {
	switch (status) {
		case constLoadStatus:
			return "未修改";
		case constNewStatus:
			return "新增";
		case constUpdateStatus:
			return "更新";
		case constDeleteStatus:
			return "删除";
		case constObsoleteStatus:
			return "新增后删除";
		default :
			return "未知状态";
	}
}

