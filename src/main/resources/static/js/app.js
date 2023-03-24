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


const scrollBtn = document.getElementById('scroll');

scrollBtn.addEventListener('click', () => {
    const scrollHeight = window.innerHeight;
    window.scrollBy({
        top: scrollHeight,
        behavior: 'smooth'
    });
});



//Смена темы
function setTheme(theme) {
    const body = document.querySelector('body');
    body.classList.remove('light-theme', 'dark-theme');
    body.classList.add(theme + '-theme');
    localStorage.setItem('theme', theme);
}

// При загрузке страницы проверяем, какая тема была выбрана ранее
const savedTheme = localStorage.getItem('theme');
if (savedTheme) {
    setTheme(savedTheme);
}

// function setCookie(name, value, days) {
//     var expires = "";
//     if (days) {
//         var date = new Date();
//         date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
//         expires = "; expires=" + date.toUTCString();
//     }
//     document.cookie = name + "=" + (value || "") + expires + "; path=/";
// }
//
// setCookie("theme", "dark", 365);