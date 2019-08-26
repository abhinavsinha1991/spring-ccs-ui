/**
 * @author Tamil
 */

var ERR01 = "ConvertFrom and ConvertTo should not be same.";
var ERR02 = "Amount should be numeric.";
var ERR03 = "Amount should be greater than zero.";
var ERR04 = "Service error occurs. please contact administrator.";
var ERR05 = "Please select ConvertFrom currency.";
var ERR06 = "Please select ConvertTo currency.";

function getConvertion() {
	$("#spnError").text("");
	$("#spnResult").text("");

	if (validateInput()) {
		var url = "/convert";
		url += "?convertFrom=" + $("#ConvertFrom").val();
		url += "&convertTo=" + $("#ConvertTo").val();
		url += "&amount=" + $("#txtAmount").val().trim();

		// Ajax call
		var convertedAmt = getRequest(url);
		if (-1 != convertedAmt) {
			var dispData = $("#txtAmount").val().trim() + " "
					+ $("#ConvertFrom").val() + " = " + convertedAmt + " "
					+ $("#ConvertTo").val();
			$("#spnResult").text(dispData);
		} else {
			$("#spnError").text(ERR04);
		}
	}
}

function validateInput() {
	var bRet = true;
	var error = "";

	if (null == $("#ConvertFrom").val()) {
		error = ERR05;
		bRet = false;
	}

	if (bRet) {
		if (null == $("#ConvertTo").val()) {
			error = ERR06;
			bRet = false;
		}
	}

	if (bRet) {
		if ($("#ConvertFrom").val() == $("#ConvertTo").val()) {
			error = ERR01;
			bRet = false;
		}
	}

	if (bRet) {
		if (null == $("#txtAmount").val() || "" == $("#txtAmount").val().trim()) {
			error = ERR03;
			bRet = false;
		} else if ($("#txtAmount").val().trim() <= 0) {
			error = ERR02;
			bRet = false;
		} else if (!isNumeric($("#txtAmount").val().trim())) {
			error = ERR02;
			bRet = false;
		}
	}

	if (!bRet) {
		$("#spnError").text(error);
	}
	return bRet;
}

/**
 * Ajax request to server
 * 
 * @param serviceUrl
 * @returns {String}
 */
function getRequest(serviceUrl) {
	var responseJson = "";
	AJAX_ERROR = 0;
	$.ajax({
		headers : {
			'Accept' : 'application/json',
			'Content-Type' : 'application/json'
		},
		type : "GET",
		url : serviceUrl,
		async : false,
		cache : false,
		dataType : 'json',
		success : function(data) {
			// alert(data);
			responseJson = data;
		},
		error : function(xhr, status, error) {
			$("#spnError").text(ERR04);
		}
	});
	// alert(responseJson);
	return responseJson;
}

/**
 * 
 * @param argValue
 * @returns
 */
function isNumeric(argValue) {
	if (argValue.match(/[^0-9|]/g)) {
		return false;
	} else {
		return true;
	}
}