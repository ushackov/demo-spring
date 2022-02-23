function send() {

    let username = $("#username").val();
    let email = $("#email").val();
    let password = $("#password").val();
    let name = $("#name").val();
    let surname = $("#surname").val();
    let age = $("#age").val();

    let person = {
        username: username,
        email: email,
        password: password,
        name: name,
        surname: surname,
        age: age
    }

    $.ajax({
        type: 'POST',
        url: "/",
        datatype: 'json',
        contentType: "application/json",
        async: false,
        data: JSON.stringify(person),
    })
    window.location.replace("/login")

}
