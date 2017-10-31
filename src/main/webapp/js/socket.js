/**
 * Created by a7289 on 2016/12/12 0012.
 */
/**
 * Created by a7289 on 2016/11/17 0017.
 */

var websocket = null;
//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    //必须为绝对路径
    websocket = new WebSocket("ws://"+window.location.host+"/SRAN/lte/websocket");
}
else{
    alert('Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function(){
};

//连接成功建立的回调方法
websocket.onopen = function(event){
}

//接收到消息的回调方法
websocket.onmessage = function(){
    websocket.close();
    window.location.reload();
}

//连接关闭的回调方法
websocket.onclose = function(){
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}