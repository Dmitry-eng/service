$(document).ready(function () {

    $("#buttonAddClient").click(function (event) {

 alert(new FormData($('#addClient')[0]))
        $.ajax({
            statusCode: {
                200: function (xhr) {
                    alert("Клиент успешно добавлен")
                    $('#addClient')[0].reset()
                },
                                400: function (xhr) {
                                    alert(JSON.stringify(xhr.responseText))
                                },

            },
            url: "/client",
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
});


function buildAddClientJSON() {
            var account = new Object();
            account.name = $("#name").val();
            account.phoneNumber = $("#phoneNumber").val();
            account.email = $("#email").val();
            return account;
}