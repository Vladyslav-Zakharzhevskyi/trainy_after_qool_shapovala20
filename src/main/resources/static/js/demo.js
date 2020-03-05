
function saveEntityAData (){
    //send ajax call to save data inserted in input to custom database
    let data = $('#entitya-data').val();
    $.ajax({
        type: 'POST',
        url: 'api/entitya?data='+data,
        success: function (msg) {
            alert(msg.status);
        },
        error: function (msg) {
            alert(msg.status);
        }
    });
}