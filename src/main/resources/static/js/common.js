$(document).ready(function(){
   $('#summernote').summernote();
});

var ajaxCall = function(_url, _option, doneCallBack, failCallBack){
    console.log(window)
    $.ajax({
        url : _url,
        method : _option.method ? _option.method : 'get',
        data : _option.data? _option.data : {},
        contentType: _option.contentType ? _option.contentType : "application/json; charset=utf-8"
   }).done(function(data, textStatus, jqXHR) {
        if(jqXHR.status === 200 && jqXHR.statusText === "success"){
            doneCallBack(jqXHR)
        }
   }).fail(function(jqXHR, textStatus, errorThrown) {
       failCallBack(jqXHR);
       alert('error occur')
   }).always(function() {
   });
};