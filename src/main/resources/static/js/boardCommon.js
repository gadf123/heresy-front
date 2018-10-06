/*
*   return : 페이지네이션 구조
* */
var makePagination = function(pageData, currentPage){
    var pages = new Array();
    var url = new URL(window.location.href).pathname;

    var arrowSpan = $(document.createElement('span')).attr({
        'aria-hidden' : "true"
    });
    var labelSpan = $(document.createElement('span')).attr({
        'class' : "sr-only"
    });

    if(Number(currentPage) !== 1){
        var prev= $(document.createElement('li'))
            .attr('class','page-item prev')
            .append($(document.createElement('a')).attr(
                {
                    class : "page-link text-dark",
                    href : url +'?page='+(Number(currentPage)-1),
                    'aria-label' : "Previous"
                }
            ).append(arrowSpan.html('&laquo;')).append(labelSpan.text('Previous')));
        pages.push(prev)
    }

    for(var i=1; i<= pageData.totalPage; i++){
        var page= $(document.createElement('li'))
            .attr('class','page-item')
            .append($(document.createElement('a')).attr(
                {
                    class : "page-link text-dark",
                    href : url +'?page='+i
                }
            ).text(i));
        pages.push(page);
    }

    if(Number(currentPage) < pageData.totalPage){
        var next= $(document.createElement('li'))
            .attr('class','page-item prev')
            .append($(document.createElement('a')).attr(
                {
                    class : "page-link text-dark",
                    href : url +'?page='+(Number(currentPage)+1),
                    'aria-label' : "Next"
                }
            ).append(arrowSpan.html('&raquo;')).append(labelSpan.text('Next')));
        pages.push(next)
    }

    return pages;
};


/*
*   return : 테이블 기본 구조
* */
var makeTableRow = function(rowItem){

    var rows = rowItem.map(function (item, index) {
        var row = document.createElement('tr');
        row.setAttribute('class','list_link_to');
        row.setAttribute('id',item.articleIdx);
        $('#tableHead').children().toArray().forEach(function(columnName, index){
            var column = document.createElement('td');

            if(columnName.id === 'articleIdx'){
                $(column).addClass('tcb-board-no')
            }else if(columnName.id === 'title'){
                $(column).addClass('text-left')
            }

            column.setAttribute('id', columnName+''+item.articleIdx);
            column.appendChild(document.createTextNode(
                columnName.id === 'createDate' ? parseDate(item[columnName.id]) : item[columnName.id])
            );
            row.appendChild(column)
        });
        return row;
    });

    return rows;
};

/*
*  게시판 글 데이터 바인딩
* */
var makeArticleView = function (articleData) {

    $('#articleTitle').text(articleData.title);
    $('#articleTime').text(parseDateWithTime(articleData.createDate));

    var content = $.parseHTML(articleData.content);
    $('#articleContent').append(content);
};

/*
*  게시판 글 데이터 재작성 바인딩
* */
var bindRewriteArticleView = function (articleData) {

    $('#articleTitle').val(articleData.title);
    $('#summernote').summernote('code', articleData.content);
};


/*
*  게시판 테이블 데이터 호출
* */
var drawTable = function(requestUrl){

    ajaxCall(
        requestUrl
        , {
            data : {
                pageSize : 20,
                currentPage : getUrlParameter('page')
            }
        }
        , function (data, jqXHR) {
            if($('#tableContent').children()){
                $('#tableContent').empty();
            }

            var rows = makeTableRow(data.contentList);
            rows.map(function (row) {
                $('#tableContent').append(row);
            });

            var pagination = makePagination(data.pagination, getUrlParameter('page'));
            $('#pagination').append(pagination);

            var linkTo = function(event){
                location.href = getMidPath()+"view?id=" + $(this).attr('id');
            };
            $('.list_link_to').click(linkTo)
        }
        , function (jqXHR) {
            console.log(jqXHR);
        }
    );
};

/*
*  게시판 댓글 정보 조회
* */
var getCommentList = function(requestUrl){
    ajaxCall(
        requestUrl
        , {data:{articleIdx : getUrlParameter('id')}}
        , function (data, jqXHR) {
            var commentsView = $('#commentsView');
            makeCommentView(data, commentsView);

        }
        , function (jqXHR) {
            alert("server comment return error");
        }
    );
};

/*
*  게시판 댓글 작성
* */
var writeComment = function(requestUrl, commentData, commentRequestUrl){
    if(commentData.comment.trim() === ''){
        alert('댓글을 입력해주세요');
        return false;
    }
    userModule
        .firebase
        .auth()
        .currentUser
        .getIdToken(true)
        .then(function(firebaseIdToken) {
            console.log(firebaseIdToken)
            ajaxCall(
                requestUrl
                ,{
                    method : 'post',
                    data : JSON.stringify(commentData),
                    idToken : firebaseIdToken
                }
                ,function(data,jqXHR){
                    getCommentList(commentRequestUrl)
                }
                ,function(jqXHR){
                    console.log(jqXHR);
                    alert("server error")
                }
            )
        })
        .catch(function(error) {
            // Handle error
            alert('firebase token getting error')
        });
};

/*
*  게시판 대댓글 작성
* */
var reWriteComment = function(){
    var recommentData = {
        articleIdx : getUrlParameter('id'),
        comment : $(this).parent().find('input').val(),
        groupNum : $(this).parent().data('groupNum'),
        depth: $(this).parent().data('depth')+1,
        sequence : $(this).parent().data('sequence')
    };
    var _requestUrl = this.requestUrl;

    if(recommentData.comment.trim() === ''){
        alert('댓글을 입력해주세요');
        return false;
    }

    userModule
        .firebase
        .auth()
        .currentUser
        .getIdToken(true)
        .then(function(firebaseIdToken) {
            console.log(firebaseIdToken)
            ajaxCall(
                _requestUrl
                ,{
                    method : 'post',
                    data : JSON.stringify(recommentData),
                    idToken : firebaseIdToken
                }
                ,function(data,jqXHR){
                    getCommentList('/basicComment/selectAll');
                }
                ,function(jqXHR){
                    console.log(jqXHR);
                    alert("server error")
                }
            )
        })
        .catch(function(error) {
            // Handle error
            alert('firebase token getting error')
        });
};

/*
*  게시판 댓글뷰 생성
* */
var makeCommentView = function (data, commentsView) {
    if(commentsView.children()){
        commentsView.empty();
    }

    data.map(function (comment) {

        var li = $(document.createElement('li')).attr({
            'class': comment.depth === 0 ? 'comment' : 'comment level-' + (comment.depth + 1)
        });

        var photo = $(document.createElement('a')).attr({
            href: 'javascript:void(0)',
            title: 'profile',
            class: 'photo'
        });

        var img = $(document.createElement('img')).attr({
            src: 'https://placehold.it/32x32',
            alt: comment.userIdx
        });
        photo.append(img);

        var meta = $(document.createElement('div')).attr({
            class: 'meta'
        }).text(comment.userIdx + ' | ' + parseDateWithTime(comment.createDate));

        var reply = $(document.createElement('a')).attr({
            class: 'reply'
        }).text('Reply');
        //meta.append(reply);

        var body = $(document.createElement('div')).attr({
            class: 'body'
        }).text(comment.comment);

        var reWriteArea = $(document.createElement('div')).attr({
            style: 'margin-top: 20px;'
        }).data({
            'groupNum': comment.groupNum,
            'depth': comment.depth,
            'sequence': comment.sequence
        });

        var reWriteInput = $(document.createElement('input')).attr({
            type: 'text',
            style: 'width:85%; display: inline-block;',
            class: 'form-control',
            id: 'recommentContent',
            'aria-describedby': 'recomment',
            placeholder: '대댓글을 입력하세요'
        }).keypress(function (e) {
            if (e.which === 13) {
                //this --> inputElement
                this.requestUrl = '/basicComment/Rewrite';
                reWriteComment.call(this);
                return false;
            }
        });

        var reWriteButton = $(document.createElement('button')).attr({
            class: 'articleBtn btn btn-dark',
            type: 'button'
        }).text('작성').click(function(){
            this.requestUrl = '/basicComment/Rewrite';
            reWriteComment.call(this);
        });
        reWriteArea.append(reWriteInput).append(reWriteButton);

        commentsView.append(li.append(photo).append(meta).append(body).append(reWriteArea));
    });
};

/*
*  게시판 글 조회
* */
var getOneArticle = function(requestUrl, commentRequestUrl){
    ajaxCall(
        requestUrl
        , {data:{articleIdx : getUrlParameter('id')}}
        , function (data, jqXHR) {
            if(new URL(location.href).pathname.indexOf('view')>0){
                makeArticleView(data);
                getCommentList(commentRequestUrl);
            }

            if(new URL(location.href).pathname.indexOf('rewrite')>0){
                bindRewriteArticleView(data);
            }
        }
        , function (jqXHR) {
            console.log("getOneArticle server view return error");
        }
    );
}

/*
*  게시판 글 작성
* */
var writeArticle = function(requestUrl, articleData){
    userModule
        .firebase
        .auth()
        .currentUser
        .getIdToken(true)
        .then(function(firebaseIdToken) {
            console.log(firebaseIdToken)
            ajaxCall(
                requestUrl
                ,{
                    method : 'post',
                    data : JSON.stringify(articleData),
                    idToken : firebaseIdToken
                }
                ,function(data,jqXHR){
                    alert("작성이 완료되었습니다.");
                    location.href = getMidPath() + 'board?page=1';
                }
                ,function(jqXHR){
                    console.log('writeArticle error', jqXHR);
                }
            )
            }).catch(function(error) {
                 console.log('writeArticle error', error)
                console.log('writeArticle error', error)
        });
};

/*
*  게시판 업로드 이미지
* */
var uploadImageArticle = function(requestUrl, data){
    return new Promise(function(resolve, reject){
        userModule
            .firebase
            .auth()
            .currentUser
            .getIdToken(true)
            .then(function(firebaseIdToken) {
                $.ajax({
                    url : requestUrl,
                    method : 'post',
                    data : data,
                    enctype: 'multipart/form-data',
                    contentType: false,
                    processData : false,
                    cache : false,
                    beforeSend : function(jqXHR, settings){
                        jqXHR.setRequestHeader('idToken', firebaseIdToken);
                    }
                }).done(function(data, textStatus, jqXHR) {
                    if(jqXHR.status === 200 && jqXHR.statusText === "success"){
                        resolve(data);
                    }
                }).fail(function(jqXHR, textStatus, errorThrown) {
                    console.log(jqXHR);
                }).always(function() {
                });
            }).catch(function(error) {
            console.log('uploadImageArticle error', error)
        });
    });
};

/*
*  게시판 글 지우기
* */
var deleteArticle = function (requestUrl) {
    var redirectUrl = getMidPath() + 'board?page=1';
    userModule
        .firebase
        .auth()
        .currentUser
        .getIdToken(true)
        .then(function(firebaseIdToken) {
            ajaxCall(
                requestUrl
                ,{
                    method : 'post',
                    data : JSON.stringify({articleIdx : getUrlParameter('id')}),
                    idToken : firebaseIdToken
                }
                ,function(data,jqXHR){
                    alert("삭제가 완료되었습니다");
                    location.href = redirectUrl
                }
                ,function(jqXHR){
                    console.log('deleteArticle error', jqXHR);
                }
            )
        }).catch(function(error) {
            console.log('deleteArticle error', error)
        });
};



