

var ajaxCall = function(_url, _option, doneCallBack, failCallBack){
    $.ajax({
        url : _url,
        method : _option.method ? _option.method : 'get',
        data : _option.data? _option.data : {},
        contentType: _option.contentType ? _option.contentType : "application/json",
   }).done(function(data, textStatus, jqXHR) {
        if(jqXHR.status === 200 && jqXHR.statusText === "success"){
            doneCallBack(jqXHR)
        }
   }).fail(function(jqXHR, textStatus, errorThrown) {
       failCallBack(jqXHR);
   }).always(function() {
   });
};

var loginModule = function () {
    this.config = {
        apiKey: "AIzaSyDYR-dKMGjg2hAqqjijDie8ONylD4LZZCY",
        authDomain: "three-caliber.firebaseapp.com",
        databaseURL: "https://three-caliber.firebaseio.com",
        projectId: "three-caliber",
        storageBucket: "three-caliber.appspot.com",
        messagingSenderId: "440231935927"
    };

    this.firebase = firebase.initializeApp(this.config);
    this.email = null;
    this.userIdx = null;
    this.userNickName = null;
    this.getidEl = function(){
        return this.getEl
    }
};

loginModule.prototype.init = function(){
    this.firebase.auth().onAuthStateChanged(function(user) {
        var loginedEl = $('.login-profile');
        var logoutedEl = $('.logout-profile'); //
        if (user) {
            if(loginedEl.hasClass('off')){
                loginedEl.removeClass('off');
            }

            if(!logoutedEl.hasClass('off')){
                logoutedEl.addClass('off')
            }

            $('#login-id').text(user.email);

            // User is signed in.
            var displayName = user.displayName;
            var email = user.email;
            var emailVerified = user.emailVerified;
            var photoURL = user.photoURL;
            var isAnonymous = user.isAnonymous;
            var uid = user.uid;
            var providerData = user.providerData;

        } else {
            // User is signed out.
            if(logoutedEl.hasClass('off')){
                logoutedEl.removeClass('off')
            }
            if(!loginedEl.hasClass('off')){
                loginedEl.addClass('off')
            }
        }
    });
};
loginModule.prototype.logIn = function(_id,_pwd){
    var logoutedEl = $('.logout-profile');
    this.firebase
        .auth()
        .signInWithEmailAndPassword(_id, _pwd)
        .then(function (_sth) {
            if(!logoutedEl.hasClass('off')){
                logoutedEl.addClass('off')
            }
            console.log(_sth)
        })
        .catch(function (error) {
            console.log(error)
        });
};

loginModule.prototype.logOut = function(){
    var loginedEl = $('.login-profile');
    this.firebase
        .auth()
        .signOut()
        .then(function (sth) {
            alert("firebase 로그아웃 완료");
            if(!loginedEl.hasClass('off')){
                loginedEl.addClass('off')
            }
            console.log(sth)
        })
        .catch(function (error) {
            console.log(error)
        });
};

loginModule.prototype.signUp = function (_userData) {
    ajaxCall(
        "/user/signup",
        {
            method : "post",
            data : JSON.stringify(_userData)
        },
        function(jqXHR){
            alert("server save done")
            console.log(this)
            this.firebase
                .auth()
                .createUserWithEmailAndPassword(_userData.userId, _userData.password)
                .then(function (res) {
                    alert("firebase 회원가입 완료");
                    console.log("------firebaseReturn--------")
                    console.log(res);
                })
                .catch(function (error) {
                    if (error.code === "auth/invalid-email") {
                        alert("이메일이 올바르지 않습니다");
                    } else {
                        alert("firebase error");
                    }

                    console.log(error)
                });

        },
        function(jqXHR){
            alert("fail");
            console.log(jqXHR)
        })
};

var userModule = new loginModule();
userModule.init();

$(document).ready(function(){
    //$('#summernote').summernote();
    $('a[href="'+window.location.pathname+'"]').parent().addClass('active')
});