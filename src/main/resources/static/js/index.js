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
              "        <td>" + getValueForBuild(data[i].dateCreated) + "</td>\n" +
            "        <td>" + getValueForBuild(data[i].shortDescription) + "</td>\n" +
            "        <td>" + getValueForBuild(data[i].executor) + "</td>\n" +
            "        <td>" + getValueForBuild(data[i].status) + "</td>\n" +
            "        <td>" + getValueForBuild(data[i].completionDate) + "</td>\n" +
            "        <td>  <button type=\"button\" onclick=\"window.location.href='/page/repair/" + data[i].id + "'\"  class=\"btn btn-success\" >Открыть</button>  </td>\n" +
            "    </tr>");
    }
}

function list() {
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/repair/all' + getValue(),
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

