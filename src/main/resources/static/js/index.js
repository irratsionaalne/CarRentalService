function registrationValidation() {

    var allLetters = /^[a-zA-Z]+$/;
    var letter = /[a-zA-Z]/;
    var number = /[0-9]/;

    var first_name = document.getElementById('first_name').innerText;
    var last_name = document.getElementById('last_name').innerText;
    var email = document.getElementById('email').innerText;
    var password = document.getElementById('password').innerText;
    // var confirm_password = document.order_form.confirm_password.value;

    var invalid = [];

    if (!allLetters.test(first_name)) {
        invalid.push("*First Name");
    }

    if (!allLetters.test(last_name)) {
        invalid.push("*Surname Name");
    }

    if (email.indexOf("@") < 1 || email.lastIndexOf(".") < email.indexOf("@") + 2 || email.lastIndexOf(".") + 2 >= email.length) {
        invalid.push("*Email");
    }

    if (password.length < 4 || !letter.test(password) || !number.test(password)) {
        invalid.push("*Password");
    }

  /*  if (confirm_password.length < 4 || !letter.test(confirm_password) || !number.test(confirm_password)) {
        invalid.push("*Confirm Password");
    }*/

    if (invalid.length !== 0) {
        alert("Please provide response: \n" + invalid.join("\n"));
        return false;
    }
    return true;
}
