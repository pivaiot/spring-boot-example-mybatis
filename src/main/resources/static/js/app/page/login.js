;
(function ($, w) {

    $('#btn-login').click(function () {
        $that = $(this);
        var validator = new Validator(
            'form-login', [{
                name: 'login_name',
                display: "请填写手机号/公司邮箱/用户名|用户名太长|用户名太短",
                rules: 'required|max_length(50)|min_length(2)'
            }, {
                name: 'password',
                display: '请填写密码|密码长度6-24,包括大小写字母和下划线',
                regexp_pwd: /^[A-Za-z_0-9]{6,24}$/,
                rules: 'required|regexp_pwd'
            }],
            function (obj, evt) {
                if (obj.errors.length > 0) {
                    console.log(obj.errors[0].message);
                    return false;
                }

                var data = {
                    login_name: $("#form-login input[name=login_name]").val(),
                    password: $("#form-login input[name=password]").val()
                };

                $.ajax({
                    method: 'POST',
                    url: '/api/login',
                    contentType: "application/json; charset=utf-8",
                    data: JSON.stringify(data)
                }).done(function (response) {
                    console.log(response);
                    window.location.href = "/";
                }).fail(function ($xhr) {
                    var data = $xhr.responseJSON;
                    console.log(data);
                });
            }
        );
        validator.validate();

        return false;
    });

})(jQuery, window);
