function renderTable() {

    let persons = getPerson()
    for (person of persons) {
        $("#userTableBody").append("<tr>")
            .append("<td>" + person.id + "</td>")
            .append("<td>" + person.username + "</td>")
            .append("<td>" + person.name + "</td>")
            .append("<td>" + person.surname + "</td>")
            .append("<td>" + person.age + "</td>")
            .append("<td><tr>")
        $.each(person.roles, function (index, r) {
            $("#userTableBody").append(r.role.substring(5) + " ")
        })
        $("#userTableBody").append("</tr></td>")
    }
}

function getPerson() {
    const QueryString = window.location.search;
    const urlParams = new URLSearchParams(QueryString);
    let id = urlParams.get('id')
    let persons
    $.ajax({
        method: 'get',
        url: "/persons/show?id=" + id,
        async: false,
        contentType: 'application/json',
        success: function (response) {
            persons = response
        }
    })
    return persons
}

window.onload = renderTable()