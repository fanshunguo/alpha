function tabs(state, control, show) {
    $(control + "> a").on(state, function () {
        $(this).addClass("on").siblings().removeClass("on");
        var index = this.index();
        $(show + "> div").eq(index).show().siblings().hide();
    });
    $(control + "> a").eq(0).attr("class", "on");
    $(show + "> div").eq(0).show();
}