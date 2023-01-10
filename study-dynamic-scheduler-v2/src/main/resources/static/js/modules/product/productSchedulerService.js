var productSchedulerService = (function () {

    const API_RESOURCE_URL = '/api/product-scheduler';

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

    function fetch(productSchedulerId, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productSchedulerId}`,
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

    function update({productSchedulerId, params}, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productSchedulerId}`,
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

    function remove(productSchedulerId, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productSchedulerId}`,
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

    function resume(productSchedulerId, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productSchedulerId}/resume`,
            type: 'GET',
            success() {
                if (callback) {
                    callback();
                }
            },
            error(e) {
                if (error) {
                    error(e.responseJSON);
                }
            }
        });
    }

    function pause(productSchedulerId, callback, error) {
        $.ajax({
            url: `${API_RESOURCE_URL}/${productSchedulerId}/pause`,
            type: 'GET',
            success() {
                if (callback) {
                    callback();
                }
            },
            error(e) {
                if (error) {
                    error(e.responseJSON);
                }
            }
        });
    }

    return {search, fetch, insert, update, delete: remove, resume, pause};

})();