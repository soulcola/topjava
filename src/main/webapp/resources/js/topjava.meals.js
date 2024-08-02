const mealsAjaxUrl = "profile/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealsAjaxUrl,
    updateTable:updateMealTable
};

function clearFilter() {
    updateTable();
}

function updateMealTable() {
    $.ajax({
        url: ctx.ajaxUrl + "filter",
        type: "GET",
        data: {
            startDate: $("#startDate").val(),
            endDate: $("#endDate").val(),
            startTime: $("#startTime").val(),
            endTime: $("#endTime").val()
        },
        success: function (data) {
            response(data);
        }
    });
}

$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
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


