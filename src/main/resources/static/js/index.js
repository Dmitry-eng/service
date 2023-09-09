$(document).ready(function () {
    event.preventDefault();


    window.onload = function () {
        list()
    }

    $('#search').click(function () {
        list()
    });

})

function Search(data) {
    $('tbody#show').empty();
    for (let i = 0; i < data.length; i++) {
        $('tbody#show').append("    <tr id=\"show\">\n" +
            "        <th scope=\"row\">" + data[i].id + "</th>\n" +
            "        <td>" + data[i].shortDescription + "</td>\n" +
            "        <td>" + data[i].executor + "</td>\n" +
            "        <td>" + data[i].status + "</td>\n" +
            "        <td>" + data[i].expectedCompletionDate + "</td>\n" +
            "        <td>  <button type=\"button\" onclick='deleteUser(" + data[i].id + ")' class=\"btn btn-success\">Удалить</button>  </td>\n" +
            "    </tr>");
    }
}

function list() {
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/repair/all/{value}' + $("#list").val(),
    }).done(function (data) {
        Search(data);
    })
}

