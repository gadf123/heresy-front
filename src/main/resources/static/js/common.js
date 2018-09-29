var parseTime = function(_millisecondTime){
    var time = new Date(_millisecondTime);
    var parsedTime = time.toISOString().split('.')[0]
        .replace(/-/gi,'.')
        .replace('T',' ');
    return parsedTime;
};

var makePagination = function(pageData, currentPage){
    var pages = new Array();
    var url = new URL(window.location.href).pathname;

    if(Number(currentPage) !== 1){
        var prev= $(document.createElement('li'))
                .attr('class','page-item prev')
                .append($(document.createElement('a')).attr(
                    {
                        class : "page-link",
                        href : url +'?page='+(Number(currentPage)-1)
                    }
                ).text('이전'));
        pages.push(prev)
    }

    for(var i=1; i<= pageData.totalPage; i++){
        var page= $(document.createElement('li'))
            .attr('class','page-item')
            .append($(document.createElement('a')).attr(
                {
                    class : "page-link",
                    href : url +'?page='+i
                }
            ).text(i));
        pages.push(page);
    }

    if(Number(currentPage) < pageData.totalPage){
        var next= $(document.createElement('li'))
            .attr('class','page-item next')
            .append($(document.createElement('a')).attr(
                {
                    class : "page-link",
                    href : url +'?page='+(Number(currentPage)+1)
                }
            ).text('다음'));
        pages.push(next)
    }

    return pages;
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
            alert("firebase 로그아웃 완료");
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
            alert("server save done")
            console.log(this)
            this.firebase
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
                });

        },
        function(jqXHR){
            alert("server save fail")
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