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
            User.displayUnfinishedContracts(response);
        })
    },

    displayTable: function () {
        return`
            <table class="table" style="margin-top: 10px">
    <thead>
         <tr>
            <th>ID</th>
            <th>Creat de:</th>
            <th>Creat in data de:</th>
            <th>Client</th>
            <th>Nr. masina</th>
            <th>VIN masina</th>
            <th>Data programarii</th>
            <th>Status comanda piese</th>
            <th>Comandate in filiala</th>
            <th>Partener</th>
            <th>Status contract</th>
        </tr>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>`
    },

    getContractRow: function (contract) {
        return `
        <tr>
            <th scope="row">${contract.id}</th>
            <td>${contract.createdBy}</td>
            <td>${contract.startDate}</td>
            <td>${contract.customer.name}</td>
            <td>${contract.car.plateNumber}</td>
            <td>${contract.car.vinNumber}</td>
            <td>${contract.startFixCarDate}</td>
            <td>${contract.orderedParts}</td>
            <td>${contract.branch}</td>
            <td>${contract.partner.name}</td>
            <td>${contract.finished}</td>
        </tr>`
    },

    displayUnfinishedContracts: function (contracts) {
        let tableBody = '';
        contracts.forEach(contract => tableBody += User.getContractRow(contract));
        $("#tbody").html(tableBody);
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
            $("#table-display").html(User.displayTable());
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
