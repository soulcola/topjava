const userAjaxUrl = "admin/users/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl,
};

// $(document).ready(function () {
$(function () {
    $(document).on("click", ":input:checkbox", function() {
        let id = $(this).closest('tr').attr("id")
        $.ajax({
            contentType: "application/json; charset=UTF-8",
            type: "POST",
            url: ctx.ajaxUrl + id,
            data: JSON.stringify($(this).is(":checked"))
        }).done(function () {
            updateTable();
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
