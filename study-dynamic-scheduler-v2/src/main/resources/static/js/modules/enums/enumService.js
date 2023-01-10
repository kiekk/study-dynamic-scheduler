var enumsService = (function () {

    const API_RESOURCE_URL = '/api/enums';

    function executeType(callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/execute-type`,
            type: 'GET',
            dataType: 'JSON',
            success(result) {
                if (callback) {
                    callback(result);
                }
            },
            error(e) {
                if (error) {
                    error(e.responseJSON);
                }
            }
        });
    }

    return {executeType}

})();