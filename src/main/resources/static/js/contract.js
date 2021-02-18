window.Contract = {

    API_URL: "http://localhost:8081/home/user/contract",

    getCurrentUser: function () {
        $.ajax({
            url: "http://localhost:8081/home/user",
            method: "GET"
        }).done(function (response) {
            $(".personal-data").html(Contract.displayUser(response));

        })
    },

    displayUser: function (user) {
        return `logged in as:
            ${user.username}
            ${user.id}`
    },

    getUnfinishedContractsByUser: function () {
        $.ajax({
            url: Contract.API_URL + "/contracts",
            method: "GET"
        }).done(function (response) {
            $("#display-requests").html(Contract.displayTable());
            Contract.displayUnfinishedContracts(response);
        })
    },

    displayUnfinishedContracts: function (contracts) {
        let tableBody = '';
        contracts.forEach(contract => tableBody += Contract.getContractRow(contract));
        $("#tbody").html(tableBody);
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
            <td><button id="edit-contract" onclick="
                        Contract.getContractById(${contract.id})">
                        <i class="fas fa-edit"></i></button></td>
        </tr>`
    },

    displayTable: function () {
        return `
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

    getContractById: function (id) {
        $.ajax({
            url: Contract.API_URL + "/" + id,
            method: "GET"
        }).done(function (response) {
            console.log(response);
            // $("#display-requests").html(User.displayDataOfContract(response));
            // let manopera = response.workmanshipList;
            // let sumManopera = 0;
            // for (let i = 0; i < manopera.length; i++) {
            //
            // }
        })
    },

    createWorkmanship: function (contractId) {

        let tbody = {
            name: "Inlocuit amortizoare fata",
            timing: "3",
            price: "75",
            carModel: "Ford",
            carType: "Transit"
        };

        $.ajax({
            url: User.API_URL + "/contract/" + id,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(tbody)
        }).done(function (response) {
            console.log(response);
        })
    },

    bindEvents: function () {
        $("#unfinished-contracts-btn").click(function (event) {
            event.preventDefault();
            Contract.getUnfinishedContractsByUser();
        });
    }

};

Contract.bindEvents();
Contract.getCurrentUser();

// Contract.getUnfinishedContractsByUser();
