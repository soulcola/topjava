const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: updateTable
};

// $(document).ready(function () {
$(function () {
    $(document).on("click", ":input:checkbox", function () {
        let checkbox = $(this);
        let row = checkbox.closest('tr');
        let id = row.attr("id");
        let enabled = checkbox.is(":checked");
        $.ajax({
            contentType: "application/json; charset=UTF-8",
            type: "POST",
            url: ctx.ajaxUrl + id,
            data: JSON.stringify(enabled)
        }).done(function () {
            row.attr("enabled", enabled);
            successNoty("User " + id + (enabled ? " enabled" : " disabled"))
        }).fail(function () {
            checkbox.prop("checked", !enabled);
        });
    });

    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "roles"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});
