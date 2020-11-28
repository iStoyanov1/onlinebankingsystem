function selectSupplier(){
    let select = document.getElementById("bill-type")
    let selectOption = select.value;

    let supplierSelect = document.getElementById('supplier')

    if (selectOption === "Интернет"){
        $("#supplier").empty();
        $.each(internetSupplier, function(i, p) {
            $('#supplier').append($('<option></option>').val(p).html(p));
        });
    }else if (selectOption === "Образование"){
        $("#supplier").empty();
        $.each(educationSupplier, function(i, p) {
            $('#supplier').append($('<option></option>').val(p).html(p));
        });
    }else if (selectOption === "Телевизия"){
        $("#supplier").empty();
        $.each(tvSupplier, function(i, p) {
            $('#supplier').append($('<option></option>').val(p).html(p));
        });
    }else if (selectOption === "Телефон"){
        $("#supplier").empty();
        $.each(phoneSupplier, function(i, p) {
            $('#supplier').append($('<option></option>').val(p).html(p));
        });
    }else if (selectOption === "Кредит"){
        $("#supplier").empty();
        $.each(creditSupplier, function(i, p) {
            $('#supplier').append($('<option></option>').val(p).html(p));
        });
    }else if (selectOption === "Топлоснабдяване"){
        $("#supplier").empty();
        $.each(heatingSystemSupplier, function(i, p) {
            $('#supplier').append($('<option></option>').val(p).html(p));
        });
    }else if (selectOption === "Изберете"){
        $("#supplier").empty();
        let option = document.createElement("option")
        option.text = "Изберете";
        supplierSelect.add(option);
        $('#supplier').prop('disabled', true);
    }

}

let internetSupplier = ['Макс Телеком', 'Варна НЕТ', 'Макс Телеком', 'НЕТ-СЪРФ', 'ФАЙБЪР 1 ООД', 'НЕТ 1' ];
let educationSupplier = ['ТУ-ВАРНА'];
let tvSupplier = ['Булсатком', 'Нет БГ', 'А1 Телевизия Близу', 'Видеосат 21', 'НЕТ Системи', 'НЕТ 1' ];
let phoneSupplier = ['Виваком', 'Теленор', 'А1', 'Новаком'];
let creditSupplier = ['Вива Кредит', 'BNP Paribas', 'TBI Bank', 'UniCredit Bank'];
let heatingSystemSupplier = ['Топлофикация-София', 'Топлофикация-Варна', 'Топлофикация-Бургас', 'ЕВН Топлофикация-Пловдив','Топлофикация-Плевен'];
