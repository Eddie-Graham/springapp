$(document).ready(function () {

    // Dynamic css assignment for responsiveness across devices
    var divHeight = $("#postSubmitDiv").height();
    // Total padding is 64px (32px*2)
    divHeight += 64;
    $("#postsDiv").css('top', divHeight);

    divHeight = $("#filter").height() + 1;	// +1 to allow for border
    $("#postsList").css('top', divHeight);

    refreshPostsByTimestamp(undefined, true);
});

function submitPost() {

    var postText = $("#postText").val();
    postText = postText.trim();

    if (postText == "")
        return;

    $.ajax({
        type: "POST",
        url: "submitPost.html",
        data: {"postText": postText},
        success: function (response) {

            $("#postText").val('');
            // submitPost.html refreshes by timestamp (change?)
            $("#menuLink1").html("Timestamp");

            $("#postsList").fadeOut(400, function () {

                $("#postsList").html(response);
                $("#postsList").fadeIn(400);
            });
        }
    });
}