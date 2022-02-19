function send() {

    let username = $("#username").val();
    let password = $("#password").val();
    let name = $("#name").val();
    let surname = $("#surname").val();
    let age = $("#age").val();

    let person = {
        username: username,
        password: password,
        name: name,
        surname: surname,
        age: age
    }

    $.ajax({
        method: 'POST',
        url: "/",
        contentType: "application/json",
        async: false,
        data: JSON.stringify(person),
        error: function(erorr) {
            console.log(error)
        }
    })

}
