window.Contract = {

    API_URL: "http://localhost:8081/home/user/contract",

    getUnfinishedContracts: function () {
        $.ajax({
            url: Contract.API_URL + "/contracts",
            method: "GET"
        }).done(function (response) {
            // Contract.displayUnfinishedContracts(response);
            console.log(response);
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
                        window.location = '/contract';
                        User.getContractById(${contract.id});
                        document.getElementById('create-btn-div').style.visibility = 'hidden';
                        document.getElementById('display-search-btn').style.visibility = 'hidden';
                        document.getElementById('display-searched').style.visibility = 'hidden';" 
                style="border: none"><i class="fas fa-edit"></i></button></td>
        </tr>`
    },



    createWorkmanship: function (contractId) {

        let tbody = {
            name : "Inlocuit amortizoare fata",
            timing : "3",
            price : "75",
            carModel : "Ford",
            carType : "Transit"
        };

        $.ajax({
            url: User.API_URL+"/contract/" + id,
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(tbody)
        }).done(function (response) {
            console.log(response);
        })
    },

};
Contract.getUnfinishedContracts();