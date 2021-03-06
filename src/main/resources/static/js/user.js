window.User = {

    API_URL: "http://localhost:8081/home/user",

    //From here User functions
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



    //From here Contract functions
    getUnfinishedContractsByUser: function () {
        $.ajax({
            url: "http://localhost:8081/home/user/contract/contracts",
            method: "GET"
        }).done(function (response) {
            $("#display-requests").html(User.displayTable());
            User.displayUnfinishedContracts(response);
        })
    },

    displayUnfinishedContracts: function (contracts) {
        let tableBody = '';
        contracts.forEach(contract => tableBody += User.getContractRow(contract));
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
                        $('#display-requests').html(User.getContractById(${contract.id}));
                        document.getElementById('display-search-btn').style.visibility = 'hidden';
                        document.getElementById('display-searched').style.visibility = 'hidden';
                        document.getElementById('create-btn-div').style.visibility = 'hidden';
                        document.getElementById('contract-table').style.visibility = 'visible';">
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
            url: "http://localhost:8081/home/user/contract/" + id,
            method: "GET"
        }).done(function (response) {
            $("#display-requests").html(User.displayDataOfContract(response));
            // let manopera = response.workmanshipList;
            // let sumManopera = 0;
            // for (let i = 0; i < manopera.length; i++) {
            //
            // }
        })
    },

    createWorkmanship: function (contractId) {
        let id =  contractId;
        console.log(id);
        // let workName = $("#workmanship-name").val();
        // console.log(workName);
        // let tbody = {
        //     name: "Inlocuit amortizoare fata",
        //     timing: "3",
        //     price: "75",
        //     carModel: "Ford",
        //     carType: "Transit"
        // };
        //
        // $.ajax({
        //     url: User.API_URL + "/contract/" + id,
        //     method: "POST",
        //     contentType: "application/json",
        //     data: JSON.stringify(tbody)
        // }).done(function (response) {
        //     console.log(response);
        // })
    },

    displayDataOfContract: function (contract) {
        return `<table class="table table-bordered table-dark">
            <thead>
                <tr>
                    <th scope="col">Contract ID</th>
                    <th scope="col">Nr de inmatriculare</th>
                    <th scope="col">Nume client</th>
                    <th scope="col">Nume partener</th>
                    <th scope="col">Creat in data</th>
                    <th scope="col">Status piese</th>
                    <th scope="col">Facturat</th>
                    <th scope="col">Edit</button></th>
                    <th scope="col">Printare</button></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="col">${contract.id}</th>
                    <th scope="col">${contract.car.plateNumber}</th>
                    <th scope="col">${contract.customer.name}</th>
                    <th scope="col">${contract.partner.name}</th>
                    <th scope="col">${contract.startDate}</th>
                    <th scope="col">${contract.orderedParts}</th>
                    <th scope="col">${contract.finishDate}</th>
                    <th scope="col"><button style="border: none"><i class="fas fa-edit"></i></button>
                    <button style="border: none"><i class="fas fa-trash-alt"></i></button></th>
                    <th scope="col"><button style="border: none"><i class="fas fa-file-pdf"></i></button></th>
                </tr>
            </tbody>
        </table>`
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



    //From here create Customer or Partner functions
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


    //From here bind events
    bindEvents: function () {
        $("#unfinished-contracts-btn").click(function (event) {
            event.preventDefault();
            User.getUnfinishedContractsByUser();
            document.getElementById("display-search-btn").style.visibility = 'hidden';
            document.getElementById("display-searched").style.visibility = 'hidden';
            document.getElementById("create-btn-div").style.visibility = 'hidden';
            document.getElementById("contract-table").style.visibility = 'hidden';
        });

        $("#new-contract-btn").click(function (event) {
            event.preventDefault();
            $("#display-requests").html(User.displayContractForm());
            document.getElementById("display-search-btn").style.visibility = 'visible';
            document.getElementById("display-searched").style.visibility = 'visible';
            document.getElementById("create-btn-div").style.visibility = 'visible';
            document.getElementById("contract-table").style.visibility = 'hidden';

        });

        $("#new-customer-btn").click(function (event) {
            event.preventDefault();
            $("#display-requests").html(User.displayCustomerForm());
            document.getElementById("create-btn-div").style.visibility = 'visible';
            document.getElementById("display-search-btn").style.visibility = 'hidden';
            document.getElementById("display-searched").style.visibility = 'hidden';
            document.getElementById("contract-table").style.visibility = 'hidden';

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

        $("#workmanship-save").click(function (event) {
            event.preventDefault();
            User.createWorkmanship();
        });

    }
};

User.bindEvents();
User.getCurrentUser();
