function renderTable() {
    let person = getPerson()
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
    if (!person.requested) {
        $("#adminBtn").empty()
            .append("<button class=\"btn-primary\" value=\"I want to be an Admin\"  onclick=\"switchRole(" + person.id + ")\">I want to be an Admin</button>")
    } else {
        $("#adminBtn").empty()
            .append("<button class=\"btn-secondary\" disabled value=\"Pending...\"  >Pending...</button>")
    }
    $("#userTableBody").append("</tr></td>")

}

function switchRole(id) {
    $.ajax({
        method: 'get',
        url: "/persons/admin-role?id=" + id,
        async: false,
        contentType: 'application/json',
        success: function () {
            $("#adminBtn").empty()
                .append("<button class=\"btn-secondary\" disabled value=\"Pending...\"  >Pending...</button>")
        }
    })
}

function getPerson() {
    const QueryString = window.location.search;
    const urlParams = new URLSearchParams(QueryString);
    let id = urlParams.get('id')
    let person
    $.ajax({
        method: 'get',
        url: "/persons/show?id=" + id,
        async: false,
        contentType: 'application/json',
        success: function (response) {
            person = response
        }
    })
    return person
}

window.onload = renderTable()