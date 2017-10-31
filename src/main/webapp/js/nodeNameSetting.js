/**
 * Created by a7289 on 2016/12/16 0016.
 */
/**
 * Created by a7289 on 2016/11/17 0017.
 */

$(document).ready(function() {

    var type = getUrlParam('type');
    if(type == 0 || type == '0'){alert('上传文件、更新基站名称失败，请检查上传文件的格式与内容');}
    if(type == 1 || type == '1'){alert('上传文件、更新基站名称成功');}
    $('.upload').on('click', function(event) {
        event.preventDefault();
        /* Act on the event */
        var nodenames = $('.nodenames').val();
        var arr  = nodenames.split('.');
        var attr = arr[arr.length - 1];
        if(attr != 'txt')
        {
            alert('请选择文本类型(.txt)的文件');
            return;
        }
        $('#uploadForm').submit();
    })

});

