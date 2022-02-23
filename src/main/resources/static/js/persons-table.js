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
            .append("<td>" + person.email + "</td>")
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
        $("#tableBody").append("</tr>")
    }
}

function findById(id) {
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
    return person
}

function modalShow(id) {
    $("#form-group").empty()
    let personAdmin = false
    let person = findById(id)

    document.getElementById("idEdit").value = person.id
    document.getElementById("usernameEdit").value = person.username
    document.getElementById("emailEdit").value = person.email
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
    let email = $("#emailEdit").val();
    let password = $("#passwordEdit").val();
    let name = $("#nameEdit").val();
    let surname = $("#surnameEdit").val();
    let age = $("#ageEdit").val();
    let role
    if (document.getElementById("roleEdit").getAttributeNames().includes("checked")) {
        role = "1"
    } else role = "2"

    let person = {
        id: id,
        username: username,
        email: email,
        password: password,
        name: name,
        surname: surname,
        age: age
    }
    editRole(person, role)
}

function editRole(person, role) {
    $.ajax({
        method: 'POST',
        url: "/admin/edit?role=" + role,
        contentType: "application/json; charset=utf-8",
        async: false,
        data: JSON.stringify(person),
    })
    renderTable()
}

function selectSave(value) {
    if (value) {
        $("#admin1").empty().append("<input id=\"roleEdit\" type=\"checkbox\" onclick='selectSave(false)' name=\"adminRole\" checked value=\"1\">")
            .append("<label for=\"admin1\">ADMINISTRATOR</label>")
    } else $("#admin1").empty().append("<input id=\"roleEdit\" type=\"checkbox\" onclick='selectSave(true)' name=\"adminRole\" value=\"1\">")
        .append("<label for=\"admin1\">ADMINISTRATOR</label>")
}

function send() {

    let username = $("#usernameNew").val();
    let email = $("#emailNew").val();
    let password = $("#passwordNew").val();
    let name = $("#nameNew").val();
    let surname = $("#surnameNew").val();
    let age = $("#ageNew").val();

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
    renderTable()
    renderForm()
}

function renderForm() {
    document.getElementById("newUserForm").reset()
}

function renderRequest() {
    $("#tableBody-request").empty()
    let persons = getAllPersons()
    let requests = getRequests()
    for (request of requests) {
        let person = persons.find(p => p.id === request.personId)
        $("#tableBody-request")
            .append("<tr>")
            .append("<td>" + person.id + "</td>")
            .append("<td>" + person.username + "</td>")
            .append("<td>" + person.email + "</td>")
            .append("<td>" + person.name + "</td>")
            .append("<td>" + person.surname + "</td>")
            .append("<td>" + person.age + "</td>")
            .append("<td><tr>")
        $.each(person.roles, function (index, r) {
            $("#tableBody-request").append(r.role.substring(5) + " ")
        })
        $("#tableBody-request").append("</tr></td>")
            .append("<td>" + "<button type=\"button\" class=\"btn btn-primary\" style='background-color: #0d6efd!important;' id=\"buttonAccept\" onClick=\"acceptAdminRole(" + person.id + ", 1)\">Accept</button>" + "</td>")
            .append("<td>" + "<a class=\"btn btn-danger\" style='background-color: #dc3545!important;' onClick='acceptAdminRole(" + person.id + ", 2)'>Decline</a>" + "</td>")
            .append("<td>")
        $("#tableBody-request").append("</td></tr>")
    }
}

function acceptAdminRole(id, role) {
    let person = findById(id)
    let personFormat = {
        id: person.id,
        username: person.username,
        email: person.email,
        name: person.name,
        surname: person.surname,
        password: person.password,
        age: person.age
    }
    editRole(personFormat, role)
    renderTable()
    renderRequest()
}

function renderUserTable() {
    let person
    $.ajax({
        method: 'get',
        url: "/admin/this",
        async: false,
        contentType: 'application/json',
        success: function (response) {
            person = response
        }
    })
    $("#userTableBody").empty()
        .append("<tr>")
        .append("<td>" + person.id + "</td>")
        .append("<td>" + person.username + "</td>")
        .append("<td>" + person.email + "</td>")
        .append("<td>" + person.name + "</td>")
        .append("<td>" + person.surname + "</td>")
        .append("<td>" + person.age + "</td>")
        .append("<td><tr>")
    $.each(person.roles, function (index, r) {
        $("#userTableBody").append(r.role.substring(5) + " ")
    })
}

function getRequests() {
    let requests
    $.ajax({
        method: 'get',
        url: "/admin/requests",
        async: false,
        contentType: 'application/json',
        success: function (response) {
            requests = response
        }
    })
    return requests
}