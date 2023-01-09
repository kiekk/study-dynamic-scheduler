Handlebars.getTemplate = function (name) {
    if (Handlebars.templates === undefined || Handlebars.templates[name] === undefined) {
        $.ajax({
            dataType: 'text',
            url: '/js/handlebars/template/' + name + '.hbs',
            success: function (data) {
                if (Handlebars.templates === undefined) {
                    Handlebars.templates = {};
                }
                Handlebars.templates[name] = Handlebars.compile(data);
            },
            async: false
        });
    }
    return Handlebars.templates[name];
}

Handlebars.getPaginationData = function (totalCount, p_page, p_pageSize, p_pagingSize) {
    var page = !isNaN(p_page) ? parseInt(p_page, 10) : 1;
    var pageSize = !isNaN(p_pageSize) ? parseInt(p_pageSize, 10) : 10;
    var pagingSize = !isNaN(p_pagingSize) ? parseInt(p_pagingSize, 10) : 10;

    var lastPage = Math.floor(totalCount / pageSize) + (totalCount % pageSize === 0 ? 0 : 1);

    var lastPaging = 1 + (Math.floor((lastPage - 1) / pagingSize) * pagingSize);

    var pagingStart = 1 + (Math.floor((page) / pagingSize) * pagingSize);

    var prevPaging = pagingStart - pagingSize < 0 ? 0 : pagingStart - pagingSize;
    var nextPaging = lastPaging <= (pagingStart + pagingSize) ? lastPaging : pagingStart + pagingSize;
    var pagingList = [];

    for (var p = pagingStart; p <= (pagingStart === nextPaging ? lastPage : nextPaging - 1); p++) {
        pagingList.push(p);
    }

    return {
        page: page + 1,
        pageSize: pageSize,
        pagingSize: pagingSize,
        firstPage: 0,
        lastPage: lastPage,
        prevPaging: prevPaging,
        nextPaging: nextPaging,
        pagingList: pagingList,
    };
}

Handlebars.registerHelper('ifCond', function (v1, operator, v2, options) {
    switch (operator) {
        case '==':
            return (v1 == v2) ? options.fn(this) : options.inverse(this);
        case '===':
            return (v1 === v2) ? options.fn(this) : options.inverse(this);
        case '!=':
            return (v1 != v2) ? options.fn(this) : options.inverse(this);
        case '!==':
            return (v1 !== v2) ? options.fn(this) : options.inverse(this);
        case '<':
            return (v1 < v2) ? options.fn(this) : options.inverse(this);
        case '<=':
            return (v1 <= v2) ? options.fn(this) : options.inverse(this);
        case '>':
            return (v1 > v2) ? options.fn(this) : options.inverse(this);
        case '>=':
            return (v1 >= v2) ? options.fn(this) : options.inverse(this);
        case '&&':
            return (v1 && v2) ? options.fn(this) : options.inverse(this);
        case '||':
            return (v1 || v2) ? options.fn(this) : options.inverse(this);
        default:
            return options.inverse(this);
    }
});

Handlebars.registerHelper('dateFormat', function (datetime, inFormat, outFormat) {
    if (moment) {
        return moment(datetime, inFormat).format(outFormat);
    }
    return datetime;
});

Handlebars.registerHelper('enumCodeToName', function (enums, code) {
    var filteredEnum = enums.filter(function (item) {
        return item.code === code;
    })
    if (1 === filteredEnum.length) {
        return filteredEnum[0].name;
    }
    return null;
});

Handlebars.registerHelper('isEmpty', function (value, options) {
    return value === undefined || value === null || value === '' ? options.fn(this) : options.inverse(this);
});

Handlebars.registerHelper('isNotEmpty', function (value, options) {
    return value !== undefined && value !== null && value !== '' ? options.fn(this) : options.inverse(this);
});

Handlebars.registerHelper('startWith', function (target, value, options) {
    return 0 === target.indexOf(value) ? options.fn(this) : options.inverse(this);
});

Handlebars.registerHelper('select', function (value, options) {
    var $select = $('<select />').html(options.fn(this));
    $('option[value="' + value + '"]', $select).attr('selected', 'selected');
    return $select.html();
});

Handlebars.registerHelper('addComma', function (value) {
    var number = isNaN(Number(value)) ? value : Number(value);
    if (!isNaN(number)) {
        return number.addComma();
    }
    return value;
});

Handlebars.registerHelper('decrement', function (value, amount) {
    var number = isNaN(Number(value)) ? value : Number(value);
    if (!isNaN(number)) {
        return value - amount;
    }
    return value;
});

Handlebars.registerHelper('increment', function (value) {
    return parseInt(value) + 1;
});

Handlebars.registerHelper({
    eq: (v1, v2) => v1 === v2,
    ne: (v1, v2) => v1 !== v2,
    lt: (v1, v2) => v1 < v2,
    gt: (v1, v2) => v1 > v2,
    lte: (v1, v2) => v1 <= v2,
    gte: (v1, v2) => v1 >= v2,
    and() {
        return Array.prototype.every.call(arguments, Boolean);
    },
    or() {
        return Array.prototype.slice.call(arguments, 0, -1).some(Boolean);
    }
});

Handlebars.registerHelper('subString', function(string, startIndex) {
    var theString = string.substring(startIndex);
    return new Handlebars.SafeString(theString)
});