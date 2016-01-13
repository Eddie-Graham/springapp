function getUsersStats(userId) {

    $.ajax({
        type: "GET",
        url: "getUsersStats.html",
        data: {"userId": userId},
        success: function (response) {

            $("#statsDiv").html(response);
        }
    });
}