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
        return `
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
        </div>`
    },

    createNewContract: function (carResponse, customerResponse, partnerResponse, branchResponse) {
        let carId = localStorage.getItem("car");
        localStorage.removeItem("car");

        let customerId = localStorage.getItem("customer");
        localStorage.removeItem("customer");

        let partnerId = localStorage.getItem("partner");
        localStorage.removeItem("partner");

        let branch = $("#inputBranch").val();
        let tbody ={
            finished : false,
            carId : carId,
            customerId : customerId,
            partnerId : partnerId,
            branch: branch
        };

        $.ajax({
            url: User.API_URL + "/contract",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(tbody)
        }).done(function (response) {
            location.reload();
        })
    },

    searchCarForContract: function () {
        let car = $("#inputCar").val();
        $.ajax({
            url: User.API_URL + "/car?plateNumber=" + car,
            method: "GET"
        }).done(function (response) {
            $('#display-car').html(User.displayFoundCar(response));
            localStorage.setItem("car", response.id.toString());
        })
    },

    displayFoundCar: function (car) {
        return `id: ${car.id}, ${car.plateNumber}, ${car.vinNumber}`
    },

    searchCustomerForContract: function () {
        let customer = $("#inputCustomer").val();
        $.ajax({
            url: User.API_URL + "/customer?name=" + customer,
            method: "GET"
        }).done(function (response) {
            $('#display-customer').html(User.displayFoundCustomer(response));
            localStorage.setItem("customer", response.id.toString());
        })
    },

    displayFoundCustomer: function (customer) {
        return `id: ${customer.id}, ${customer.name}`
    },

    searchPartnerForContract: function () {
        let partner = $("#inputPartner").val();
        $.ajax({
            url: User.API_URL + "/partner?name=" + partner,
            method: "GET"
        }).done(function (response) {
            $('#display-partner').html(User.displayFoundPartner(response));
            localStorage.setItem("partner", response.id.toString());
        })
    },

    displayFoundPartner: function (partner) {
        return`id: ${partner.id}, ${partner.name}`
    },

    displayCustomerForm: function () {
        return `
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
        <p>Va rugam selectati tipul user-ului:</p>
            <input type="radio" id="customer-radio" name="user-type" value="customer-radio">
            <label for="customer-radio">Client</label><br>
            <input type="radio" id="partner-radio" name="user-type" value="partner-radio">
            <label for="partner-radio">Partener</label><br>`
    },

    createCustomer: function () {
        let radioButton =  $('input[name="user-type"]:checked').val();

        let customerName = $("#inputName").val();
        let customerEmail = $("#inputEmail").val();
        let customerPassword = $("#inputPassword").val();
        let customerPhone = $("#inputPhone").val();
        let customerCif = $("#inputCif").val();
        let customerBankAccount = $("#inputBankAccount").val();
        let customerAddress = $("#inputAddress").val();
        let customerContactPerson = $("#inputContactPerson").val();
        let tbody = {
            name : customerName,
            email : customerEmail,
            password : customerPassword,
            phoneNumber : customerPhone,
            cif : customerCif,
            bankAccount : customerBankAccount,
            address : customerAddress,
            contactPerson : customerContactPerson
        };

        if (radioButton === "customer-radio") {
            $.ajax({
                url: User.API_URL + "/customer",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(tbody)
            }).done(function (response) {
                location.reload();
            })
        } else if (radioButton === "partner-radio") {
            $.ajax({
                url: User.API_URL + "/partner",
                method: "POST",
                contentType: "application/json",
                data: JSON.stringify(tbody)
            }).done(function (response) {
                location.reload();
            })
        }
    },

    displayUnfinishedContracts: function (contracts) {
        let tableBody = '';
        contracts.forEach(contract => tableBody += User.getContractRow(contract));
        $("#tbody").html(tableBody);
    },

    bindEvents: function () {
        $("#unfinished-contracts-btn").click(function (event) {
            event.preventDefault();
            document.getElementById("create-btn-div").style.visibility = 'hidden';
            document.getElementById("display-search-btn").style.visibility = 'hidden';
            document.getElementById("display-searched").style.visibility = 'hidden';
            $("#display-requests").html(User.displayTable());
            User.getUnfinishedContracts();
        });

        $("#new-contract-btn").click(function (event) {
            event.preventDefault();
            $("#display-requests").html(User.displayContractForm());
            document.getElementById("display-search-btn").style.visibility = 'visible';
            document.getElementById("display-searched").style.visibility = 'visible';
            document.getElementById("create-btn-div").style.visibility = 'visible';
        });

        $("#new-customer-btn").click(function (event) {
            event.preventDefault();
            $("#display-requests").html(User.displayCustomerForm());
            document.getElementById("create-btn-div").style.visibility = 'visible';
            document.getElementById("display-search-btn").style.visibility = 'hidden';
            document.getElementById("display-searched").style.visibility = 'hidden';
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
            event.preventDefault();
            User.searchPartnerForContract();
        });

        $("#create-btn").click(function (event) {
            event.preventDefault();
            User.createNewContract();
            User.createCustomer();
        });

    }
};

User.bindEvents();
User.getCurrentUser();
