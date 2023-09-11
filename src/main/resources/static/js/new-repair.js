$(document).ready(function () {
    event.preventDefault();

    window.onload = function () {
        list()
    }



     $("#buttonAddRepair").click(function (event) {
     alert(new FormData($('#formNewRepair')[0]))
            $.ajax({
                statusCode: {
                    200: function (xhr) {
                        alert("Заявка на ремонт создана")
                        $('#formNewRepair')[0].reset()
                    },
                                    400: function (xhr) {
                                        alert(JSON.stringify(xhr.responseText))
                                    },

                },
                            url: "/repair",
                            type: 'POST',
                            contentType: "application/json",
                            data: JSON.stringify(buildAddClientJSON()),
                            dataType: 'JSON',
                            cache: false,
                            processData: false,
            })
                .done(function () {
                });
        });






})

function Search(data) {
    $('#clientId').empty()
var objSel = document.getElementById("clientId");
objSel.options[0] = new Option();
    for (let i = 0; i < data.length; i++) {
    objSel.options[i+1] = new Option(data[i].name, data[i].id);
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


function getValue() {
var s = $("#list").val();
if(s) {
return "/" + s;
} else{
return "";
}

}

function buildAddClientJSON() {
            var repair = new Object();
            repair.shortDescription = $("#shortDescription").val();
            repair.clientId = $("#clientId").val();
            repair.contactPhoneNumber = $("#contactPhoneNumber").val();
            repair.address = $("#address").val();
            return repair;
}