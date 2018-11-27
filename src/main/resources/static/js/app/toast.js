;(function (w) {

    var black = "rgba(23, 30, 43, 0.81)";
    var green = "rgba(106, 213, 76, 0.81)";

    var init = function() {
        var elm = document.createElement('div');
        elm.style.zIndex = '999999';
        elm.style.color = '#F5F5F5';
        elm.style.backgroundColor = "rgba(23, 30, 43, 0.81)";
        elm.style.position = "fixed";
        elm.style.padding = "15px";
        elm.style.borderRadius = "5px";
        elm.style.width = "260px";
        elm.style.height= "20px";
        elm.style.textAlign = "center";
        elm.style.top = '300px';
        elm.style.left = '0';
        elm.style.bottom = '0';
        elm.style.right = '0';
        elm.style.margin = '0 auto';
        elm.style.display = 'none';
        document.body.appendChild(elm);
        return elm;
    };

    var elm = init();

    var showToastText = function (msg) {
        showColorfulToastText(msg, black);
    };

    var showSuccessToastText = function (msg) {
        showColorfulToastText(msg, green);
    };

    var showColorfulToastText = function (msg, color) {
        elm.innerText = msg;
        elm.style.display = '';
        elm.style.backgroundColor = color;

        setTimeout(function () {
            elm.style.display = 'none';
        }, 3000);
    };

    w.showToastText = showToastText;
    w.showSuccessToastText = showSuccessToastText;
    w.showColorfulToastText = showColorfulToastText;

})(window);