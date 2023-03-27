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

// Прокрутка страницы при клике на якорную ссылку
$('a[href^="#"]').on('click', function(event) {
    const target = $(this.getAttribute('href'));

    if (target.length) {
        event.preventDefault();
        $('html, body').animate({
            scrollTop: target.offset().top
        }, 1000);
    }
});