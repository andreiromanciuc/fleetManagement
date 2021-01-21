window.Customer = {

    API_URL: "http://localhost:8081/home/customer",

    getCurrentUser: function () {
        $.ajax({
            url: "http://localhost:8081/home/user",
            method: "GET"
        }).done(function (response) {
            // console.log(response);
            $(".personal-data").html(Customer.displayUserInNav(response));
        })
    },

    displayUserInNav: function (user) {
        return `logged in as:
            ${user.username}
            ${user.id}`
    },
};

Customer.getCurrentUser();