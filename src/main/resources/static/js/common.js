var getHomePath = function () {
    return '/heresy/home';
};

var getMidPath = function(){
    var url = new URL(location.href);
    var midPath = url.pathname.substring(0, url.pathname.lastIndexOf('/')+1);
    return midPath;
};

var getUrlParameter = function(key){
    var url = new URL(window.location.href);
    var value = url.searchParams.get(key);
    return value;
};

var makeElement =function(el, attr){
    if(!attr){
        attr = {}
    }
    return $(document.createElement(el)).attr(attr)
};

var parseDate = function(_millisecondTime){
    var time = new Date(_millisecondTime);
    var datePart = time.toLocaleDateString('ko-KR').replace(/ /gi,'').split('\.');
    var parsedDate = datePart[0] + (datePart[1] < 10 ? '-0' : '-')
        + datePart[1] + (datePart[2] < 10 ? '-0' : '-')
        + datePart[2];
    return parsedDate;
};

var parseDateWithTime = function(_millisecondTime){
    var time = new Date(_millisecondTime);
    var parsedDateWithTime = time.toLocaleString('ko-KR');
    return parsedDateWithTime;
};

var ajaxCall = function(_url, _option, doneCallBack, failCallBack){
    $.ajax({
        url : _url,
        method : _option.method ? _option.method : 'get',
        data : _option.data? _option.data : {},
        contentType: _option.contentType ? _option.contentType : "application/json",
        beforeSend : function(jqXHR, settings){
            jqXHR.setRequestHeader('idToken', _option.idToken);
            console.log(jqXHR, settings)
        }
    }).done(function(data, textStatus, jqXHR) {
        if(jqXHR.status === 200 && jqXHR.statusText === "success"){
            doneCallBack(data, jqXHR)
        }
    }).fail(function(jqXHR, textStatus, errorThrown) {
        failCallBack(jqXHR);
    }).always(function() {
    });
};

var userModule = function () {
    this.config = {
        apiKey: "AIzaSyDYR-dKMGjg2hAqqjijDie8ONylD4LZZCY",
        authDomain: "three-caliber.firebaseapp.com",
        databaseURL: "https://three-caliber.firebaseio.com",
        projectId: "three-caliber",
        storageBucket: "three-caliber.appspot.com",
        messagingSenderId: "440231935927"
    };
    this.firebase = firebase.initializeApp(this.config);
};

userModule.prototype.init = function(pageView){
    return new Promise(function(resolve, reject){
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
                $('#login-id').text(user.displayName);
                resolve(user);
            } else {
                // User is signed out.
                if(logoutedEl.hasClass('off')){
                    logoutedEl.removeClass('off')
                }
                if(!loginedEl.hasClass('off')){
                    loginedEl.addClass('off')
                }
                resolve("no user logined")
            }
            pageView(user);
        })});
};
userModule.prototype.logIn = function(_id, _pwd){
    var _this = this;
    var logoutedEl = $('.logout-profile');
    _this.firebase.auth().setPersistence(firebase.auth.Auth.Persistence.SESSION)
        .then(function() {
            return _this.firebase
                .auth()
                .signInWithEmailAndPassword(_id, _pwd)
                .then(function (response) {
                    $('#loginModal').modal('hide');
                    if (!logoutedEl.hasClass('off')) {
                        logoutedEl.addClass('off')
                    }
                    console.log(response)
                })
                .catch(function (error) {
                    alert("firebase error" + error)
                });
        })
        .catch(function(error) {
            var errorCode = error.code;
            var errorMessage = error.message;
        });

};

userModule.prototype.logOut = function(){
    var loginedEl = $('.login-profile');
    this.firebase
        .auth()
        .signOut()
        .then(function () {
            alert("로그아웃 되었습니다");
            if(!loginedEl.hasClass('off')){
                loginedEl.addClass('off')
            }
        })
        .catch(function (error) {
            console.log(error)
        });
};

userModule.prototype.signUp = function (_userData) {
    ajaxCall(
        "/user/signup",
        {
            method : "post",
            data : JSON.stringify(_userData)
        },
        function(data,jqXHR){
            alert("회원가입 완료되었습니다");
            //location.href = getHomePath();
            /*this.firebase
                .auth()
                .createUserWithEmailAndPassword(_userData.userId, _userData.password)
                .then(function (res) {
                    var user = firebase.auth().currentUser;
                    user.updateProfile({
                        displayName: _userData.userNickName
                    }).then(function() {
                        alert("firebase 회원가입 완료");
                    }).catch(function(error) {
                    });
                })
                .catch(function (error) {
                    if (error.code === "auth/invalid-email") {
                        alert("이메일이 올바르지 않습니다");
                    } else {
                        alert("firebase error");
                    }

                    console.log(error)
                });*/

        },
        function(jqXHR){
            alert("회원가입 에러")
            console.log(jqXHR)
        })
};

var userModule = new userModule();

$(document).ready(function(){
    var currentUrlPart = new URL(window.location.href).pathname.split('/');
    var headers = $('#headNav').find('a');

    headers.toArray().forEach(function(item){
        if(item.href.indexOf(currentUrlPart[2]) > 0){
            $(item).parent().addClass('active')
        }
    });

});