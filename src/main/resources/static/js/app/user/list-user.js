(function ($, w) {
    var statusMap = {
        active: '正常',
        blocked: '<span style="color:red">停用</span>'
    };

    var $table = $('#table'),
        $btnQuery = $('#btn-query'),
        $btnSave = $('#btn-save');

    var api = {
        updateStatus: function (userId, status, callback) {
            $.request(
                'PUT',
                '/api/users/' + userId + "/status?status=" + status, {},
                callback
            );
        }
    };

    $.extend($.fn.bootstrapTable.defaults, {
        showFilter: true,
        queryParams: function (params) {
            return params;
        }
    });

    w.tableDef = {
        delete: {
            id_name: "id",
            url: "/api/users",
            logic: true
        },
        bootstrapTable: {
            url: '/api/users',
            columns: [{
                    checkbox: true
                },
                {
                    title: "编码",
                    field: "id",
                },
                {
                    title: "用户名",
                    formatter: function (data, row, index) {
                        return row.user_name == null ? '' : row.user_name;
                    }
                },
                {
                    title: '邮箱',
                    formatter: function (data, row, index) {
                        return row.email == null ? '' : row.email;
                    }
                },
                {
                    title: "手机号",
                    formatter: function (data, row, index) {
                        return row.mobile == null ? '' : row.mobile;
                    }
                },
                {
                    title: "注册日期",
                    formatter: function (data, row, index) {
                            return formatDateTime(row.join_time);
                    }
                },
                {
                    title: "昵称",
                    formatter: function (data, row, index) {
                        return row.profile.display_name;
                    }
                },
                {
                    title: "状态",
                    formatter: function (data, row, index) {
                        return statusMap[row.status];
                    }
                },
                {
                    title: "操作",
                    formatter: function (data, row, index) {
                        return [
                            '<a class="edit" href="javascript:void(0)" style="margin-right:10px">' + (row.status == 'active' ? '停用' : '启用') + '</a>'
                        ].join(' ');
                    },
                    events: {
                        'click .edit': function (e, value, row, index) {
                            api.updateStatus(row.id, row.status == 'active' ? "blocked" : "active", function() {
                                    $table.bootstrapTable('refresh', {});
                            });
                        }
                    }
                }
            ]
        }
    };

    $btnQuery.click(function() {
        $table.bootstrapTable('refresh', {});
        return false;
    });

})(jQuery, window);
