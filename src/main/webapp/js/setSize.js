/**
 * Created by a7289 on 2016/12/22 0022.
 */
$(function() {
    var whole=document.documentElement.clientHeight;
    $("#outer").height(whole);
    var middle=whole-38-60;
    $("#middle").height(middle);
    $("#map").height(middle);
    $("#right_img").height(middle);
    $("#left_img").height(middle);
    $("#left_div").height((middle-34));
    $("#left_tac").height((middle-70));
    $("#left_node").height((middle-70));
    $("#right").height(middle-110);
    $("#setting_middle").height(whole-60);
    $("#setting_content").height(whole-60);
    $("#left_setting_img").height(whole-60);
})