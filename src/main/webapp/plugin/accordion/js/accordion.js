/**
 * Created by a7289 on 2016/11/30 0030.
 */
$(function() {
    var x ="";
    $(".link").click(function(){
        var id = $(this).attr("id");
        var status = false;
        var $el = $('.link').parent();
        var $other = $(this).parent().siblings();
        $this = $(this),
            $next = $this.next();
        $next.slideToggle();
        $this.parent().toggleClass('open');
        if (x == id){
            $this.find(".details").text("");
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
            x = "";
        }else {
            x = id;
            $this.find(".details").text("详细");
            $other.find(".details").text("");
            $el.find('.submenu').not($next).slideUp().parent().removeClass('open');

        }
    });



});

// $(function() {
//     var Accordion = function(el, multiple) {
//         this.el = el || {};
//         this.multiple = multiple || false;
//
//         // Variables privadas
//         var links = this.el.find('.link');
//         // Evento
//         links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown)
//     }
//
//     Accordion.prototype.dropdown = function(e) {
//         var $el = e.data.el;
//         $this = $(this),
//             $next = $this.next();
//
//         $next.slideToggle();
//         $this.parent().toggleClass('open');
//
//         if (!e.data.multiple) {
//             $(this).append("<div id='details' style='display: inline-block;'>详情</div>" );
//             $el.find('.submenu').not($next).slideUp().parent().removeClass('open');
//         };
//     }
//
//     var accordion = new Accordion($('#accordion'), false);
// });
