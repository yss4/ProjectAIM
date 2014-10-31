String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/gi, "");
}
Array.prototype.remove = function(index) {
	var newArray;
	switch(index) {
		case 0:
			this.shift();
			newArray = this;
			break;
		case this.length-1:
			this.pop();
			newArray = this;
			break;
		default:
			var tail = this.slice(index+1);
			var head = this.slice(0, index);
			newArray = head.concat(tail);
			break;
	}
	return newArray;
}
Array.prototype.add = function(data) {
	this[this.length] = data;
}

function revisingCheckbox(selectedIdList, idString, elementList) {
	var tempList = idString.split(",");
	for(var i = 0; i < tempList.length-1; ++i) {
		selectedIdList[i] = tempList[i].trim();
	}

	for(var i = 0; i < elementList.length; ++i) {
		var item = elementList[i];
		item.checked = false;
	}
	console.log(selectedIdList);
	for(var i = 0; i < selectedIdList.length; ++i) {
		document.getElementById(selectedIdList[i]).checked = true;
	}

}

function updateIdList(selectedId, idList) {
	var insertFlag = true;
	for(var i = 0; i < idList.length; ++i) {
		if(idList[i] === selectedId) {
			insertFlag = false;
			idList = idList.remove(i);
			break;
		}
	}

	if(insertFlag) {
		idList.add(selectedId);
	}

	return idList;
}
var JMap = function (obj) {
    
	var mapData = (obj != null) ? cloneObject(obj) : new Object();
	
	this.put = function(key, value) {
		mapData[key] = value;
	}
	
	this.get = function(key) {
		return mapData[key];
	}
	
	this.remove = function(key) {
		for (var tKey in mapData) {
			if (tKey == key) {
				delete mapData[tKey];
				break;
			}
		}
	}
	
	this.keys = function() {
		var keys = [];
		for (var key in mapData) {
			keys.push(key);
		}
		return keys;
	}
	
	this.values = function() {
		var values = [];
		for (var key in mapData) {
			values.push(mapData[key]);
		}
		return values;
	}
	
	this.containsKey = function(key) {
		for (var tKey in mapData) {
			if (tKey == key) {
				return true;
			}
		}
		return false;
	}
	
	this.isEmpty = function() {
		return (this.size() == 0);
	}
	
	this.clear = function() {
		for (var key in mapData) {
			delete mapData[key];
		}
	}
	
	this.size = function() {
		var size = 0;
		for (var key in mapData) {
			size++;
		}
		return size;
	}
	
	this.getObject = function() {
		return cloneObject(mapData);
	}
	
	var cloneObject = function(obj) {
		var cloneObj = {};
		for (var attrName in obj) {
			cloneObj[attrName] = obj[attrName];
		}
		return cloneObj;
	}
}