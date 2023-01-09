var productService = (function () {

    const API_RESOURCE_URL = '/api/product';

    function search(params, callback, error) {
        $.ajax({
            url: API_RESOURCE_URL,
            type: 'GET',
            data: params,
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

    function fetch(productId, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productId}`,
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

    function insert(params, callback, error) {
        $.ajax({
            url: API_RESOURCE_URL,
            type: 'POST',
            data: params,
            contentType: 'application/json',
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

    function update({productId, params}, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productId}`,
            type: 'PUT',
            data: params,
            contentType: 'application/json',
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

    function remove(productId, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productId}`,
            type: 'DELETE',
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

    return {search, fetch, insert, update, delete: remove};

})();