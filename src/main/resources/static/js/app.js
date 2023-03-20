$(function() {


    /* Fixed Header */
    $(function() {
        let header = $('.header');

        $(window).scroll(function() {
            if($(this).scrollTop() > 1) {
                header.addClass('fixed');
            } else {
                header.removeClass('fixed');
            }
        });
    });
});