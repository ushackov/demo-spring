function deletePerson(id) {
    $.ajax({
        method: 'get',
        url: "/admin/delete?id=" + id,
        async: false,
    })
    renderTable()
}

function getAllPersons() {
    let persons
    $.ajax({
        method: 'get',
        url: "/persons/all",
        async: false,
        contentType: 'application/json',
        success: function (response) {
            persons = response
        }
    })
    return persons
}

function renderTable() {

    $("#tableBody").empty()
    let persons = getAllPersons()
    for (person of persons) {
        $("#tableBody")
            .append("<tr>")
            .append("<td>" + person.id + "</td>")
            .append("<td>" + person.username + "</td>")
            .append("<td>" + person.name + "</td>")
            .append("<td>" + person.surname + "</td>")
            .append("<td>" + person.age + "</td>")
            .append("<td><tr>")
        $.each(person.roles, function (index, r) {
            $("#tableBody").append(r.role.substring(5) + " ")
        })
        $("#tableBody").append("</tr></td>")
            .append("<td>" + "<button type=\"button\" class=\"btn btn-primary\" style='background-color: #0d6efd!important;' id=\"buttonEdit\" data-bs-toggle=\"modal\" onClick=\"modalShow(" + person.id + ")\" data-bs-target=\"#editModal\">Edit</button>" + "</td>")
            .append("<td>" + "<a class=\"btn btn-danger\" style='background-color: #dc3545!important;' onClick='deletePerson(" + person.id + ")'>DELETE</a>" + "</td>")
            .append("</tr>")
    }
}

function modalShow(id) {
    $("#form-group").empty()
    let personAdmin = false
    let person
    $.ajax({
        method: 'get',
        url: "/admin/find?id=" + id,
        async: false,
        contentType: 'application/json',
        success: function (response) {
            person = response
        }
    })

    document.getElementById("idEdit").value = person.id
    document.getElementById("usernameEdit").value = person.username
    document.getElementById("nameEdit").value = person.name
    document.getElementById("surnameEdit").value = person.surname
    document.getElementById("ageEdit").value = person.age
    document.getElementById("passwordEdit").value = person.password
    document.getElementById("usernameEdit").value = person.username
    for (let i = 0; i < person.roles.length; i++) {
        if (person.roles[i].role === "ROLE_ADMIN") {
            personAdmin = true
            break
        }
    }
    if (personAdmin) {
        $("#admin1").empty().append("<input id=\"roleEdit\" type=\"checkbox\" onclick='selectSave(false)' name=\"adminRole\" checked value=\"1\">")
            .append("<label for=\"admin1\">ADMINISTRATOR</label>")
    } else $("#admin1").empty().append("<input id=\"roleEdit\" type=\"checkbox\" onclick=selectSave(true) name=\"adminRole\" value=\"1\">")
        .append("<label for=\"admin1\">ADMINISTRATOR</label>")
}

window.onload = renderTable()

function savePerson() {
    let id = $("#idEdit").val();
    let username = $("#usernameEdit").val();
    let password = $("#passwordEdit").val();
    let name = $("#nameEdit").val();
    let surname = $("#surnameEdit").val();
    let age = $("#ageEdit").val();
    let role
    if(document.getElementById("roleEdit").getAttributeNames().includes("checked")){
        role = "1"
    } else role = "2"

    let person = {
        id: id,
        username : username,
        password: password,
        name: name,
        surname: surname,
        age: age
    }

    $.ajax({
        method: 'POST',
        url: "/admin/edit?role=" + role,
        contentType: "application/json; charset=utf-8",
        async: false,
        data: JSON.stringify(person),
    })
    renderTable()
}

function selectSave(value){
    if (value) {
        $("#admin1").empty().append("<input id=\"roleEdit\" type=\"checkbox\" onclick='selectSave(false)' name=\"adminRole\" checked value=\"1\">")
            .append("<label for=\"admin1\">ADMINISTRATOR</label>")
    } else $("#admin1").empty().append("<input id=\"roleEdit\" type=\"checkbox\" onclick='selectSave(true)' name=\"adminRole\" value=\"1\">")
        .append("<label for=\"admin1\">ADMINISTRATOR</label>")
}