function displayLoginPage() {
	$("body").load("html/login.html");
}

function connection(form) {
	var login = form.username.value;
	var password = form.password.value;
	if (checkFormConnection(login, password)) {
		sendRequest(login, password);
		return true;
	}
	return false;
}

function checkFormConnection(login, password) {
	if (login.length == 0) {
		error_ft("Enter a username");
		return false;
	}
	if (login.length > 42) {
		error_ft("Username too long");
		return false;
	}
	if (password.length == 0) {
		error_ft("Enter a password");
		return false;
	}
	if (password.length > 128) {
		error_ft("Password too long");
		return false;
	}
	return true;
}

function sendRequest(login, password) {
	$.ajax({type: "Post",
		    url: url_site + "src/services/User",
		    data: "username=" + login + "&password=" + password,
		    dataType: "json",
		    success: function(res) {
		    	responseConnection(res);
		    },
		    error: function(xhr, status, err) {
		    	error_ft(status + ": " + err);
		    }
		});
}

function responseConnection(res) {
	if (res.errorcode == undefined) {
		env.key = res.key;
		env.id = res.id;
		env.username = res.username;
		dispHomePage();
	} else {
        console.log(res.errorMessage + ", ERROR_CODE: " + res.errorCode);
		error_ft(res.errorMessage);
	}
}