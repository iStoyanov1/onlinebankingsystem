/*
                    <td style="border-color: dodgerblue" th:text="${cost.getDate()}"></td>
                                    <td style="border-color: dodgerblue" th:text="${cost.getDetails()}"></td>
                                    <td style="border-color: dodgerblue; color: red"
                                        th:text="${cost.getQuantity()}"></td>
                                    <td style="border-color: dodgerblue" th:text="${cost.getRecipient()}"></td>
                                    <td style="border-color: dodgerblue"
                                        th:text="${cost.getSender().getAccountNumber()}"></td>

                                        <tr>
                                        <td style="border-color: dodgerblue">${item.date}</td>
                                        <td style="border-color: dodgerblue">${item.}</td>
                                        <td style="border-color: dodgerblue; color: red">${item.details}</td>
                                        <td style="border-color: dodgerblue">${item.recipient}</td>
                                        <td style="border-color: dodgerblue">${item.getSender.getAccountNumber()}</td>
                                        </tr>

                                        * */

const UrlCosts = {
    items: '/api/transaction/cost'
};

const UrlIncomes = {
    items: '/api/transaction/income'
}

const costTable = ({date, details, quantity, recipient, sender}) =>
    ` 
                     <tr>
                     <td style="border-color: dodgerblue">${date}</td>
                      <td style="border-color: dodgerblue">${details}</td>
                      <td style="border-color: dodgerblue; color: red">${quantity.toFixed(2)}</td>
                      <td style="border-color: dodgerblue">${recipient}</td>
                      <td style="border-color: dodgerblue">${sender.accountNumber}</td>
                  </tr>`

const incomeTable = ({date, details, quantity, sender, recipient}) =>
    ` 
                     <tr>
                     <td style="border-color: dodgerblue">${date}</td>
                      <td style="border-color: dodgerblue">${details}</td>
                      <td style="border-color: dodgerblue; color: green">${quantity.toFixed(2)}</td>
                      <td style="border-color: dodgerblue">${recipient.accountNumber}</td>
                      <td style="border-color: dodgerblue">${sender}</td>
                  </tr>`



function showCosts() {
    fetch(UrlCosts.items)
        .then(response => response.json())
        .then(items => {
            let result = '';
            items.forEach(item => {
                const itemTable = costTable(item);
                result += itemTable;

            });
            document.getElementById('transaction-table')
                .innerHTML = result;

        });
}



function costsBetweenDates() {
        $('#submit-button').on('submit', function (ev){

        fetch('/api/transaction/cost', {
            method: "post",
            headers : {
                'Accept' : 'application/json',
                'Content-Type' : 'application/json'
            },
        })
            .then(resp => resp.json())
            .then(items => {
                let result = '';
                items.forEach(item => {
                    const itemTable = costTable(item);
                    result += itemTable;
                });
                document.getElementById('transaction-table')
                    .innerHTML = result;
            });



        ev.preventDefault();

        return false;
    });
}

function showIncomes() {
    fetch(UrlIncomes.items)
        .then(response => response.json())
        .then(items => {
            let result = '';
            items.forEach(item => {
                const itemTable = incomeTable(item);
                result += itemTable;

            });
            document.getElementById('transaction-table')
                .innerHTML = result;

        });
}

function displayTable() {
    let option = document.getElementById('transactionType').value;

    if (option === 'costs') {
        showCosts();
    } else if (option === 'incomes') {
        showIncomes();
    }
}

document.getElementById('transactionType').addEventListener("change", displayTable)



