$(document).ready(function () {

    $("#buttonAddAccount").click(function (event) {

        $.ajax({
            statusCode: {
                200: function (xhr) {
                    alert("Пользователь успешно добавлен")
                    $('#buttonAddAccount')[0].reset()
                },
                                400: function (xhr) {
                                    alert(JSON.stringify(xhr.responseText))
                                },

            },
            url: "/account",
                        type: 'POST',
                        contentType: "application/json",
                        data: JSON.stringify(buildAddAccountJSON()),
                        dataType: 'JSON',
                        cache: false,
                        processData: false,
        })
            .done(function () {
            });
    });
});


function buildAddAccountJSON() {
            var account = new Object();
            account.name = $("#name").val();
            account.login = $("#login").val();
            account.password = $("#password").val();
             account.email = $("#email").val();
            return account;
}

