// Camel to Snake
String.prototype.toSnake = function () {
    return this.replace(/([A-Z])/g, function ($1) {
        return "_" + $1.toLowerCase();
    });
};

// Snake to Camel
String.prototype.toCamel = function () {
    return this.replace(/(_[a-z])/g, function ($1) {
        return $1.toUpperCase().replace('_', '');
    });
};

var extend = function (own, options) {
    $.each(typeof options === 'undefined' ? {} : options, function (k, v) {
        own[k] = v;
    });
    return own;
};


;
(function (w) {
    var entityMap = {
        '&': '&amp;',
        '<': '&lt;',
        '>': '&gt;',
        '"': '&quot;',
        "'": '&#39;',
        '/': '&#x2F;',
        '`': '&#x60;',
        '=': '&#x3D;'
    };

    w.escapeHtml = function (string) {
        return String(string).replace(/[&<>"'`=\/]/g, function (s) {
            return entityMap[s];
        });
    }
})(window);


var formatDate = function (data) {
    if (typeof data === 'undefined')
        return '';
    return moment(data).format('YYYY-MM-DD');
};

var formatDateTime = function (data) {
    if (typeof data === 'undefined')
        return '';
    return moment(data).format('YYYY-MM-DD hh:mm:ss');
};

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

$.request = function (type, url, data, onSuccess) {
    var token = $('meta[name="csrf-token"]').attr('content');
    $.ajax({
        type: type,
        url: url,
        headers: {
            "X-CSRF-TOKEN": token
        },
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data)
    }).done(function (resp) {
        onSuccess(resp);
    }).fail(function ($xhr) {
        var data = $xhr.responseJSON;
        $('#dialog-error-content').empty().html(data.code);
        $('#dialog-error').modal();
    });
}

var disableValidator = function (formSelector) {
    $(formSelector).validator('destroy');
};

var updateValidator = function (formSelector) {
    $(formSelector).validator('update');
};

var validateForm = function (formSelector) {
    // Validate
    $(formSelector).validator('destroy');
    var valid = true;
    var res = $(formSelector).on('invalid.bs.validator', function (e) {
        valid = false;
    }).validator('validate');
    return valid;
};

var clearForm = function (formSelector) {
    $(formSelector).find(':input').each(function (idx, elm) {
        $(elm).val('');
    });
};

var clearFormWithoutHidden = function (formSelector) {
    $(formSelector).find(':input:not([type="hidden"])').each(function (idx, elm) {
        $(elm).val('');
    });
};

var fillForm = function (formSelector, data) {
    $(formSelector).find(':input').each(function (idx, elm) {
        var id = $(elm).attr('property');
        console.log(id);
        if (id !== 'undefined') {
            $(elm).val(data[id] != null ? data[id] : '');
            return;
        }
    });
};

var fillFormWithoutIdLike = function (formSelector, data, idLike) {
    $(formSelector).find(':input').each(function (idx, elm) {
        var id = $(elm).attr('property');
        if (id.startsWith(idLike)) {
            return;
        }
        if (id !== 'undefined') {
            $(elm).val(data[id] != null ? data[id] : '');
            return;
        }
    });
};

var abbreviate = function (content, length) {
    var text = content.substring(0, length);
    if (content.length > length)
        return text + "..."
    return text;
};

;
(function ($, w) {
    $dialogBody = $('#dialog-info .modal-body');
    $dialog = $('#dialog-info');
    w.showDialogInfo = function (html) {
        $dialogBody.html(html);
        $dialog.modal();
    }
})(jQuery, window);

var getFormDataAsObject = function (formSelector) {
    var data = {};
    $.each($(formSelector).serializeArray(), function (idx, elm) {
        if (data[elm.name] != null) {
            if (!$.isArray(data[elm.name])) {
                data[elm.name] = [data[elm.name]];
            }
            data[elm.name].push(elm.value)
        } else {
            data[elm.name] = elm.value;
        }
    });

    return data;
};

var uploadfile = function (url, data, onSuccess) {
    $.ajax({
        method: "POST",
        dataType: 'json',
        url: url,
        data: data,
        contentType: false,
        processData: false,
    }).done(function (response) {
        onSuccess(response);
    });;
};
