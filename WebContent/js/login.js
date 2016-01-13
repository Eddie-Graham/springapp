function checkRegistrationInput() {

    var username = $('#username').val();
    username = username.trim();

    var email = $('#email').val();
    email = email.trim();

    var password = $('#password').val();
    var confirmPassword = $('#confirmPassword').val();

    var waitingForUsernameResponse = false;
    var waitingForEmailResponse = false;
    var finishedMethod = false;

    //////////////////////////////////////
    // USERNAME CHECK                   //
    //////////////////////////////////////

    if (username == '') {
        alert("Please enter username.");
        return false;
    }

    if (username.length < 4) {
        alert("Username must be atleast 4 characters long.");
        return false;
    }

    if (username.length > 14) {
        alert("Username must not exceed 14 characters.");
        return false;
    }

    waitingForUsernameResponse = true;

    $.ajax({
        type: "GET",
        url: "checkUniqueUsername.html",
        data: {"username": username},

        success: function (response) {

            if (response == 'NOT UNIQUE') {
                alert("Username already taken, please use another one.");
                return false;
            }

            waitingForUsernameResponse = false;

            if (finishedMethod && !waitingForUsernameResponse && !waitingForEmailResponse) {
                $("#registerForm").submit();
            }
        }
    });

    //////////////////////////////////////
    // EMAIL CHECK                      //
    //////////////////////////////////////

    if (email == '') {
        alert("Please enter email.");
        return false;
    }

    if (email.indexOf("@") < 0) {
        alert("Please enter valid email.");
        return false;
    }

    if (email.indexOf(" ") > -1) {
        alert("Please enter valid email.");
        return false;
    }

    waitingForEmailResponse = true;

    $.ajax({
        type: "GET",
        url: "checkUniqueEmail.html",
        data: {"email": email},

        success: function (response) {

            if (response == 'NOT UNIQUE') {
                alert("Email already taken, please use another one.");
                return false;
            }

            waitingForEmailResponse = false;

            if (finishedMethod && !waitingForUsernameResponse && !waitingForEmailResponse) {
                $("#registerForm").submit();
            }
        }
    });

    //////////////////////////////////////
    // PASSWORDS CHECK                  //
    //////////////////////////////////////

    if (password == '') {
        alert("Please enter password.");
        return false;
    }

    if (password.length < 8) {
        alert("Password must be atleast 8 characters long.");
        return false;
    }

    if (confirmPassword == '') {
        alert("Please confirm password.");
        return false;
    }

    if (confirmPassword.length < 8) {
        alert("Password must be atleast 8 characters long.");
        return false;
    }

    if (password != confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }

    var finishedMethod = true;

    if (finishedMethod && !waitingForUsernameResponse && !waitingForEmailResponse) {
        $("#registerForm").submit();
    }
}

$(document).ready(function () {

    //////////////////////////////////////
    // LOGIN FORM SUBMIT                //
    //////////////////////////////////////

    $("#loginForm").submit(function (e) {

        e.preventDefault();

        var loginUsername = $('#loginUsername').val();
        loginUsername = loginUsername.trim();
        var loginPassword = $('#loginPassword').val();

        if (loginUsername == "") {
            alert("Please enter username.");
            return false;
        }

        if (loginPassword == "") {
            alert("Please enter password.");
            return false;
        }

        // ajax call to spring security
        $.ajax({
            type: "POST",
            url: 'login.html',
            data: $("#loginForm").serialize(),
            success: function (response) {

                if (response == "FAILED") {
                    alert("Invalid username/password combination.");
                    return false;
                }

                if (response == "SUCCESS") {
                    window.location = "home.html";
                }
            }
        });

        return false;
    });

    //////////////////////////////////////
    // REGISTER FORM SUBMIT             //
    //////////////////////////////////////

    $('#registerForm').on('submit', function (e) {

        e.preventDefault();

        $.ajax({
            type: "POST",
            url: 'submitRegistration.html',
            data: $("#registerForm").serialize(),
            success: function (response) {

                if (response == 'SUCCESS') {
                    alert("You have successfully registered, please sign in!")

                    $("#username").prop('disabled', true);
                    $("#email").prop('disabled', true);
                    $("#password").prop('disabled', true);
                    $("#confirmPassword").prop('disabled', true);
                }
            }
        });
    });
});
