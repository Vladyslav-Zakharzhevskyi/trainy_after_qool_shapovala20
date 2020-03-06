
function doActivity() {
    $.ajax({
        type: 'POST',
        url: 'api/initactivity',
        success: function (msg) {
            alert(msg);
        },
        error: function (msg) {
            alert(msg.status);
        }
    });
}