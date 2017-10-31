/**
 * Created by a7289 on 2016/12/16 0016.
 */
check_login();
/*显示用户名称*/
show_username();
$(function() {
    $(".link_setting_left").click(function(){
        $(".click").removeClass("click");
        $(this).addClass("click");
        var src = $(this).data('src');
        $('#iframe').attr('src',src);
    });


    $(".link_setting_left").hover(function(){
        $(this).addClass("hover");},
        function () {
        $(this).removeClass("hover");
    });


});