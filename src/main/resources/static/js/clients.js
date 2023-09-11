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
            "        <th scope=\"row\">" + getValueForBuild(data[i].id) + "</th>\n" +
              "        <td>" + getValueForBuild(data[i].name) + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='/page/client/" + data[i].id + "'\"  class=\"btn btn-success\" >Карточка клиента</button>  </td>\n" +
            "    </tr>");
    }
}

function Search(data) {
    $('tbody#show').empty();
    for (let i = 0; i < data.length; i++) {
        $('tbody#show').append("    <tr id=\"show\">\n" +
            "        <th scope=\"row\">" + getValueForBuild(data[i].id) + "</th>\n" +
              "        <td>" + getValueForBuild(data[i].name) + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='/page/client/" + data[i].id + "'\"  class=\"btn btn-success\" >Карточка клиента</button>  </td>\n" +
            "    </tr>");
    }
}

function list() {
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/client/all' + getValue(),
    }).done(function (data) {
        Search(data);
    })
}


function getValueForBuild(value) {
if(value == null) {
return ""
}
else {
return value;
}

}


function getValue() {
var s = $("#list").val();
if(s) {
return "/" + s;
} else{
return "";
}

}
