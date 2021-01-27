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

    displayContractForm: function () {
        return `<form>
        <div class="row mb-3">
            <label for="inputCar" class="col-sm-2 col-form-label">Masina</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputCar">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputCustomer" class="col-sm-2 col-form-label">Client</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputCustomer">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputPartner" class="col-sm-2 col-form-label">Partener</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPartner">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputBranch" class="col-sm-2 col-form-label">Livrare din filiala: (R)</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputBranch">
            </div>
        </div>

        <button type="submit" class="btn btn-primary" id="create-contract-btn">Creare contract</button>
    </form>`
    },

    searchCarForContract: function () {
        let car = $("#inputCar").val();
        $.ajax({
            url: User.API_URL + "/car?plateNumber=" + car,
            method: "GET"
        }).done(function (response) {
            $('#display-car').html(User.displayFoundCar(response));
        })
    },

    displayFoundCar: function (car) {
        return `id:${car.id}, ${car.plateNumber}, ${car.vinNumber}`
    },

    searchCustomerForContract: function () {
        let customer = $("#inputCustomer").val();
        $.ajax({
            url: User.API_URL + "/customer?name=" + customer,
            method: "GET"
        }).done(function (response) {
            $('#display-customer').html(User.displayFoundCustomer(response));
        })
    },

    displayFoundCustomer: function (customer) {
        return `id:${customer.id}, ${customer.name}`
    },

    displayCustomerForm: function () {
        return `<form style="margin-top: 20px; padding-right: 20px; padding-left: 20px; width: 30%;">
        <div class="row mb-3">
            <label for="inputName" class="col-sm-2 col-form-label">Nume companie</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputName">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputEmail" class="col-sm-2 col-form-label">Email companie</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputPassword" class="col-sm-2 col-form-label">Parola noua</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassword">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputPhone" class="col-sm-2 col-form-label">Nr. Telefon</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPhone">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputCif" class="col-sm-2 col-form-label">CIF</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputCif">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputBankAccount" class="col-sm-2 col-form-label">Cont bancar</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputBankAccount">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputAddress" class="col-sm-2 col-form-label">Adresa</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputAddress">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputContactPerson" class="col-sm-2 col-form-label">Persoana de contact</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputContactPerson">
            </div>
        </div>

        <button type="submit" class="btn btn-primary" id="create-customer-btn">Creare client</button>
    </form>`
    },

    displayPartnerForm: function () {
        return `<form style="margin-top: 20px; padding-right: 20px; padding-left: 20px; width: 30%;">
        <div class="row mb-3">
            <label for="inputName" class="col-sm-2 col-form-label">Nume companie</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputName">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputEmail" class="col-sm-2 col-form-label">Email companie</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputPassword" class="col-sm-2 col-form-label">Parola noua</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPassword">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputPhone" class="col-sm-2 col-form-label">Nr. Telefon</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputPhone">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputCif" class="col-sm-2 col-form-label">CIF</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputCif">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputBankAccount" class="col-sm-2 col-form-label">Cont bancar</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputBankAccount">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputAddress" class="col-sm-2 col-form-label">Adresa</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputAddress">
            </div>
        </div>
        <div class="row mb-3">
            <label for="inputContactPerson" class="col-sm-2 col-form-label">Persoana de contact</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="inputContactPerson">
            </div>
        </div>

        <button type="submit" class="btn btn-primary" id="create-customer-btn">Creare partener</button>
    </form>`
    },

    displayUnfinishedContracts: function (contracts) {
        let tableBody = '';
        contracts.forEach(contract => tableBody += User.getContractRow(contract));
        $("#tbody").html(tableBody);
    },

    //todo: to finish below method
    createNewContract: function () {
        let tbody ={
            finished : false,
            carId : $("#inputCar").val(),
            customerId : $("#inputCustomer").val(),
            partnerId : $("#inputPartner").val(),
            branch : "R59"
        };

        $.ajax({
            url: User.API_URL + "/contract",
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
            $("#display-requests").html(User.displayTable());
            User.getUnfinishedContracts();

        });

        $("#new-contract-btn").click(function (event) {
            event.preventDefault();
            $("#display-requests").html(User.displayContractForm());
            document.getElementById("display-search-btn").style.visibility = 'visible';
            document.getElementById("display-searched").style.visibility = 'visible';


            // User.createNewContract();
        });

        $("#new-customer-btn").click(function (event) {
            event.preventDefault();
            $("#display-requests").html(User.displayCustomerForm());
            document.getElementById("display-search-btn").style.visibility = 'visible';
            document.getElementById("display-searched").style.visibility = 'visible';

        });

        $("#new-partner-btn").click(function (event) {
            event.preventDefault();
            $("#display-requests").html(User.displayPartnerForm());
            document.getElementById("display-search-btn").style.visibility = 'visible';
            document.getElementById("display-searched").style.visibility = 'visible';

        });

        $("#search-car-btn").click(function (event) {
            event.preventDefault();
            User.searchCarForContract();
        });

        $("#search-customer-btn").click(function (event) {
            event.preventDefault();
            User.searchCustomerForContract();
        });

        $("#search-partner-btn").click(function (event) {
            // event.preventDefault();
            // User.searchCustomerForContract();
            console.log("clicked");
        });



    }
};

User.bindEvents();
User.getCurrentUser();
