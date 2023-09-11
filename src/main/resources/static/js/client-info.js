$(document).ready(function () {
    list()
    clientInfo()
})




function getParams(){
return window.location.pathname.split('/')[3]
}


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


function getValueForBuild(value) {
if(value == null) {
return ""
}
else {
return value;
}

}


function list() {
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/repair/all/client/' + getParams(),
    }).done(function (data) {
        Search(data);
    })
}

function clientInfo() {
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/client/' + getParams(),
    }).done(function (data) {
        ClientInit(data);
    })
}

function ClientInit(data) {
    $('#name').val(data.name);
     $('#email').val(data.email);
     $('#phoneNumber').val(data.phoneNumber);
}


function updateClient() {
    $.ajax({

                                   url: "/client",
                                    type: 'PUT',
                                    contentType: "application/json",
                                    data: JSON.stringify(buildAddClientJSON()),
                                    dataType: 'JSON',
                                    cache: false,
                                    processData: false,
    }).done(function (data) {
       clientInfo()
    })
}

function buildAddClientJSON() {
            var client = new Object();
            client.id = getParams()
            client.name = $("#name").val();
            client.email = $("#email").val();
            client.phoneNumber = $("#phoneNumber").val();
            return client;
}
