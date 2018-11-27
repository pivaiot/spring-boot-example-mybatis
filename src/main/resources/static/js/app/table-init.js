(function ($, et) {
    var token = $('meta[name="csrf-token"]').attr('content');
    var $table = $('#table'),
        $btnRemove = $('#btn-remove'),
        $btnConfirmOk = $('#btn-confirm-ok'),
        $dialogRemove = $('#dialog-confirm'),
        $dialogError = $('#dialog-error'),
        $dialogErrorContent = $('#dialog-error-content'),
        $rowsCount = $('#rows-count'),
        selections = [];

    // 获取选中的行
    function getIdSelections() {
        return $.map($table.bootstrapTable('getSelections'), function (row) {
            return row.id;
        });
    }

    $btnRemove.click(function () {
        $rowsCount.html(selections.length);
        $dialogRemove.modal();
        return false;
    });

    $btnConfirmOk.click(function () {
        // 调用接口删除
        var data = $.map(selections, function (id) {
            return et.delete.id_name + "=" + id;
        });

        data.push("_csrf=" + token);

        $.ajax({
            url: et.delete.url + '?' + data.join('&'),
            type: 'DELETE'
        }).done(function () {
            if (et.delete.logic) {
                $table.bootstrapTable('refresh', {});
            } else {
                $table.bootstrapTable('remove', {
                    field: 'id',
                    values: selections
                });
            }
            $btnRemove.prop('disabled', true);
        }).fail(function ($xhr) {
            var data = $xhr.responseJSON;
            $dialogErrorContent.empty().html(data.msg);
            $dialogError.modal();
        });

        $dialogRemove.modal('hide');
    });

    $table.bootstrapTable(et.bootstrapTable);

    // 选中行后，设置删除按钮为可用, 并记录前页选中删除项
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table',
        function () {
            $btnRemove.prop('disabled', !$table.bootstrapTable('getSelections').length);
            selections = getIdSelections();
        });

})(jQuery, tableDef);
