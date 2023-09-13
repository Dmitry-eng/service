function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;
    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
    return false;
};


function openFormCreateRepair() {
    var lefto = screen.availWidth/2-150;
    var righto = screen.availHeight/2-170;
    window.open("/repair/create", "INFO", "width=450, height=400, left=" + lefto + ", top="+righto+"");
}

function openFormCreateClient() {
    var lefto = screen.availWidth/2-150;
    var righto = screen.availHeight/2-170;
    window.open("/client/create", "INFO", "width=450, height=400, left=" + lefto + ", top="+righto+"");
}

function openFormCreateAccount() {
    var lefto = screen.availWidth/2-150;
    var righto = screen.availHeight/2-170;
    window.open("/account/create", "INFO", "width=450, height=400, left=" + lefto + ", top="+righto+"");
}