$(document).ready(function () {

$('#price').bind("change keyup input click", function() {
if (this.value.match(/[^0-9\.]/g)) {
this.value = this.value.replace(/[^0-9\.]/g, '');
}
});


findAllAccount()
    init()



          $('#buttonFullDescription').click(function () {
              buttonFullDescriptionEvent()
          });



})

function init() {
    $.ajax({
    statusCode: {

                                                        500: function (xhr) {
//                                                            alert(JSON.stringify(xhr.responseText))
                                                        },
                                    400: function (xhr) {
//                                        alert(JSON.stringify(xhr.responseText))
//                                        initTest()
                                    }

                },
        type: "GET",
        dataType: 'JSON',
        url: '/repair/' + getParms(),
    }).done(function (data) {
    Search(data)
    })
}

function Search(data) {

if(data) {
} else {
return;
}


$('#agreementId').val(data.id)
$('#shortDescription').val(data.shortDescription)
$('#fullDescription').val(data.fullDescription)
$('#dateCreated').val(data.dateCreated)
$('#completionDate').val(data.completionDate)
$('#status').val(data.status)
$('#address').val(data.address)
$('#email').val(data.client.email)

if(data.contactPhoneNumber) {
$('#contactPhoneNumber').val(data.contactPhoneNumber)
} else {
$('#contactPhoneNumber').val(data.client.phoneNumber)
}

if(data.comments) {
buildComment(data.comments)
}

$('#clientName').val(data.client.name)
$('#price').val(data.price)
window.globalVar = data.client.id;

if(data.executor) {
$('#executor').val(data.executor.id)
}

if(data.status == 'Оценка стоимости ремонта') {
$('#price').prop('disabled', false);
} else {
$('#price').prop('disabled', true);
}

if(data.status == 'Завершен' || data.status == 'Отклонен') {
$('#status').prop('disabled', true);
$('#executor').prop('disabled', true);
$('#fullDescription').prop('disabled', true);
$('#buttonFullDescription').prop('disabled', true);
}


}

function buttonFullDescriptionEvent() {
    $.ajax({
    statusCode: {
                    200: function (xhr) {
                        init()
                    },
                                                        500: function (xhr) {
                                                            alert(JSON.stringify(xhr.responseText))
                                                        },
                                    400: function (xhr) {
                                        alert(JSON.stringify(xhr.responseText))
                                        initTest()
                                    },

                },
                        url: "/repair/updateDescription",
                        type: 'PUT',
contentType: "application/json",
                        data:  JSON.stringify(buildFullDescriptionJson()),
                        dataType: 'JSON',
                        cache: false,
                        processData: false,

    }).done(function () {
     executorInit()
       init()

    })
}


function buildFullDescriptionJson(){
            var repair = new Object();
            repair.id = $("#agreementId").val();
            repair.fullDescription = $("#fullDescription").val();
            return repair;
}


function findAllAccount() {
    $.ajax({
        type: "GET",
        dataType: 'JSON',
        url: '/account/all',
    }).done(function (data) {
    executorInit(data)
    })
}

function executorInit(data) {

    $('#executor').empty()
var objSel = document.getElementById("executor");
    for (let i = 0; i < data.length; i++) {
    objSel.options[i+1] = new Option(data[i].name, data[i].id);
    }



}


function updateStatus() {
    $.ajax({
       statusCode: {
         200: function (xhr) {
                                                   initTest()
                                               },
                                        400: function (xhr) {
                                            alert(JSON.stringify(xhr.responseText))
                                            initTest()
                                        },

                    },
                        url: "/repair/updateStatus",
                        type: 'PUT',
contentType: "application/json",
                        data:  JSON.stringify(buildUpdateStatusJson()),
                        dataType: 'JSON',
                        cache: false,
                        processData: false,

    }).done(function () {

    initTest()

    })
}

function updateExecutor() {
    $.ajax({
           statusCode: {
                                            400: function (xhr) {
                                                alert(JSON.stringify(xhr.responseText))
                                                initTest()
                                            },

                        },
                        url: "/repair/updateExecutor",
                        type: 'PUT',
contentType: "application/json",
                        data:  JSON.stringify(buildUpdateExecutorJson()),
                        dataType: 'JSON',
                        cache: false,
                        processData: false,

    }).done(function () {
           executorInit()
       init()
    })
}

function updatePrice() {
    $.ajax({
           statusCode: {
                                            400: function (xhr) {
                                                alert(JSON.stringify(xhr.responseText))
                                                initTest()
                                            },

                        },
                        url: "/repair/updatePrice",
                        type: 'PUT',
contentType: "application/json",
                        data:  JSON.stringify(buildUpdatePriceJson()),
                        dataType: 'JSON',
                        cache: false,
                        processData: false,

    }).done(function () {
           executorInit()
       init()
    })
}

function buildUpdateExecutorJson(){
            var repair = new Object();
            repair.id = $("#agreementId").val();
            repair.executor = $("#executor").val();
            return repair;
}

function buildUpdateStatusJson(){
            var repair = new Object();
            repair.id = $("#agreementId").val();
            repair.repairStatus = $("#status").val();
            return repair;
}

function buildUpdatePriceJson(){
            var repair = new Object();
            repair.id = $("#agreementId").val();
            repair.price = $("#price").val();
            return repair;
}

function initTest(){
findAllAccount()
    init()
}

function addComment() {
    $.ajax({
           statusCode: {
             200: function (xhr) {
             $("#comment").val('')
                                                           initTest()
                                                       },
                                            400: function (xhr) {
                                                alert(JSON.stringify(xhr.responseText))
                                                initTest()
                                            },

                        },
                        url: "/repair/comment",
                        type: 'POST',
contentType: "application/json",
                        data:  JSON.stringify(buildAddCommentJson()),
                        dataType: 'JSON',
                        cache: false,
                        processData: false,

    }).done(function () {

           executorInit()
       initTest()
    })
}

function buildAddCommentJson(){
            var repair = new Object();
            repair.repairId = $("#agreementId").val();
            repair.value = $("#comment").val();
            return repair;
}


function buildComment(comments) {
$('#comments').empty()
    for (let l = 0; l < comments.length; l++) {
        $('#comments').append("<div class=\"message-item\"> <div class=\"message-inner\"> <div class=\"message-head clearfix asker-meta\"> <div class=\"user-detail\"> <div class=\"post-meta\"> <div class=\"asker-meta\"> <span class=\"qa-message-what\"></span> <span class=\"qa-message-when\"> <span class=\"qa-message-when-data\">"+ comments[l].dateCreate +"</span> </span> <span class=\"qa-message-who\"> <span class=\"qa-message-who-pad\">by </span> <span class=\"qa-message-who-data\"> "+ comments[l].account.name +"</span> </span> </div> </div> </div> </div> <div class=\"qa-message-when-data\" style=\"padding-left: 15px\"    >"+comments[l].value+"</div> </div> </div>");
    }
}

function getParms(){
return window.location.pathname.split('/')[3]
}

function openClientInfo(){
    window.open("/page/client/" + window.globalVar);
}