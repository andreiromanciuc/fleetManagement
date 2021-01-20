window.Home = {
    API_URL: "http://localhost:8081/home",

    getCurrentUser: function () {
        $.ajax({
            url: Home.API_URL + "/user",
            method: "GET"
        }).done(function (response) {
            // console.log(response);
            $("#user-info").html(Home.displayUser(response));
            $(".personal-data").html(Home.displayUserInNav(response));
        })
    },

    displayUser: function (response) {
        return `${response.username}`
    },

    displayUserInNav: function (user) {
        return `logged in as:
            ${user.username}
            ${user.id}`
    },
};

Home.getCurrentUser();