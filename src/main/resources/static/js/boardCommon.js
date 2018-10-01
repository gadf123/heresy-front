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
                    href : url +'?page='+(Number(currentPage)-1),
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

