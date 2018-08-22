$(document).ready(function(){
   $('#summernote').summernote();
});

var ajaxCall = function(_url, _option){
   $.ajax({
       url : _url,
       method : _option.method ? _option.method : 'get',
       contentType: _option.contentType ? _option.contentType : "application/json; charset=utf-8"

   }).done(function() {
       alert( "success" );
   }).fail(function() {
       alert( "error" );
   }).always(function() {
       alert( "complete" );
   });
};