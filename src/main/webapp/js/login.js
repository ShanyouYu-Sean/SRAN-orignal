function user(){
	$("#userName").removeClass("username");
}
function pwd(){
	$("#userPwd").removeClass("userpwd");
}
function users(){
	if($("#userName").val()==''||$("#userName").val()==null){
		$("#userName").addClass("username");
	}
}
function pwds(){
	if($("#userPwd").val()==''||$("#userPwd").val()==null){
		$("#userPwd").addClass("userpwd");
	}
}
$(document).ready(function() {

    /*默认添加初始账号密码*/
    // $("#userName").removeClass("username");
    // $("#userPwd").removeClass("userpwd");
    // $('#userName').val('admin');
    // $('#userPwd').val('888888');


    $('#login').bind("click", function() {
        var input_username = $('#userName').val();
        var input_password = $('#userPwd').val();
        if (input_username == '' || input_password == ''|| input_username.trim()==''||input_password.trim()=='') {
            alert('用户名或密码不能为空!');
        } else {
            var data = {
                "loginName" : input_username,
                "password" : input_password
            };
            $.ajax({
                contentType : 'application/json;charset=utf-8',
                type: "post",
                url: "../lte/loginCheck",
                data: JSON.stringify(data),
                dataType: 'json',
                success: function(response) {
                    if (response.status) {
//									setCookie('user_name',response.data.user_name,1);
//									setCookie('user_id',response.data.user_id,1);
                        setCookie('ITEID',JSON.stringify(response.data),1);
                        window.location = response.url;
                    } else {
                        alert(response.msg);
                    }
                },
                error: function(e) {
                    console.log(e.responseText);
                }
            });
        }
    });
});