Toast = {
    success: function (message) {
        Swal.fire({
            position: 'top-center',
            icon: 'success',
            title: message,
            showConfirmButton: false,
            timer: 800
        })
    },

    error: function (message) {
        Swal.fire({
            position: 'top-end',
            icon: 'error',
            title: message,
            showConfirmButton: false,
            timer: 3000
        })
    },

    warning: function (message) {
        Swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: message,
            showConfirmButton: false,
            timer: 3000
        })
    }
};
