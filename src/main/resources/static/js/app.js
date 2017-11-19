
function loadHome()
{
        $("#content").load('main.html');
}

$("#home").click(function()
{
    loadHome();
    $(".nav li").removeClass("active");
            $(this).parent().addClass('active');
});
$("#info").click(function()
{
    var $this = $(this);
    $("#content").load('info.html');
    $(".nav li").removeClass("active");
    $this.parent().addClass('active');
});
$("#about").click(function()
{
    var $this = $(this);
    $("#content").load('about.html');
    $(".nav li").removeClass("active");
    $this.parent().addClass('active');
});

$(document).ready(function(){
 loadHome();
});