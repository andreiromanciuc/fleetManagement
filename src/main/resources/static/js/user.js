window.User = {

    API_URL: "http://localhost:8081/home/user",

    getCurrentUser: function () {
        $.ajax({
        url: User.API_URL,
        method: "GET"
        }).done(function (response) {
            $(".personal-data").html(User.displayUser(response))
        })
    },

    displayUser: function (user) {
        return `logged in as:
            ${user.username}
            ${user.id}`
    },

    getUnfinishedContracts: function () {
        $.ajax({
            url: User.API_URL + "/contracts",
            method: "GET"
        }).done(function (response) {
            console.log(response);
        })
    },

    createNewContract: function () {
        $.ajax({
            url: User.API_URL + "/contract",
            method: "POST"
        }).done(function (response) {
        })
    },

    bindEvents: function () {
        $("#unfinished-contracts-btn").click(function (event) {
            event.preventDefault();
            User.getUnfinishedContracts();
        });

        $("#new-contract-btn").click(function (event) {
            event.preventDefault();
            User.createNewContract();
        })
    }
};

User.bindEvents();
User.getCurrentUser();
