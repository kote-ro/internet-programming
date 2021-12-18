$(document).on('click', '#button', function(e){
    $.ajax({
        url: "https://catfact.ninja/fact",
        contentType: "application/json",
        dataType: 'json',
        success: function (result) {
            //console.log(result.fact);
            $("#exampleFormControlTextarea1").val(result.fact);
        }
    });
});