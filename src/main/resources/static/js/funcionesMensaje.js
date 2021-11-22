const BASE_URL = "http://localhost:8080/"
const URL_MESSAGE = BASE_URL + "api/Message/"
const URL_CINEMA = BASE_URL + "api/Cinema/"

function postMessage(){

    $.ajax({
        url :  URL_MESSAGE + "save",
        type:   "POST",
        data:   JSON.stringify({
            messageText: $("#messageText").val(),
            cinema: {id: $("#idCinema").val()},
            client: {idClient: 1}
        }),
        contentType:"application/JSON",
        datatype: "JSON",
        success:() => {
            alert("Mensaje guardado exitosamente")
            getMessage()
        }
    });
}


function loadOptions(){

    $.ajax({
        url :   URL_CINEMA + "all",
        type:   "GET",
        datatype:   "JSON",
        success:(response) => {
            response.forEach(element => {
                let option = document.createElement("option")
                option.innerHTML = element.name
                option.value = element.id
                $("#idCinema").append(option)
            });
        }
    });
}

function getMessage(){

    $.ajax({
        url :   URL_MESSAGE + "all",
        type:   "GET",
        datatype:   "JSON",
        success:(response) => {
            loadMessage(response)
        }
    });
}

function loadMessage(items){
    let myTable = document.getElementsByTagName("loadMessages")

    for(let i = 0; i < items.length; i++){

        myTable+="<tr>";
        myTable+="<td>"+items[i].messageText+"</td>";
        myTable+="<td>"+items[i].cinema.name+"</td>";
        myTable+="<td>"+items[i].client.name+"</td>";
        myTable+="</tr>";

    }
    myTable+="</tbody>";
    $("#loadMessages").empty()
    $("#loadMessages").append(myTable);
}


$('#postMessage').click(function(){
    postMessage()
});

$('#getMessages').click(function(){
    getMessage()
});

document.addEventListener("DOMContentLoaded", loadOptions())