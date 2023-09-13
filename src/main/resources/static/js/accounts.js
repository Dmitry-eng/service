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
            "        <td>  <input id=\""+data[i].id+"\"  "+ getChecked(data[i]) +"  value=\""+data[i].id+"\"  onclick=\"boxDisable($(this))\" type=\"checkbox\">  </td>\n" +
            "    </tr>");

    }
}

function getChecked(obj) {
        if(obj.activated) {
        return 'checked';
        }
}

function list() {
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/account/all' + getValue(),
    }).done(function (data) {
    Search(data)
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

function updateAccount(values) {
      $.ajax({
                statusCode: {
                    200: function (xhr) {
                        list()
                    },
                                    400: function (xhr) {
                                        alert(JSON.stringify(xhr.responseText))
                                    },

                },
                            url: "/account",
                            type: 'PUT',
                            contentType: "application/json",
                            data: JSON.stringify(values),
                            dataType: 'JSON',
                            cache: false,
                            processData: false,
            })
                .done(function () {
                });

}

function boxDisable(t) {
    var account = new Object();
    account.id = t.val()
    account.activated = t.is(':checked')
updateAccount(account)
//    if () {
//
//    var account = new Object();
//    account.id = t.val()
//    account.activated = t.is(':checked')
//
//    } else {
//      $(e).find('input').removeAttr('disabled');
//    }
//


}