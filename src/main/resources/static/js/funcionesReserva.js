const BASE_URL = "http://localhost:8080/"
const URL_RESERVATION = BASE_URL + "api/Reservation/"
const URL_CINEMA = BASE_URL + "api/Cinema/"

function postReservation(){

    $.ajax({
        url :  URL_RESERVATION + "save",
        type:   "POST",
        data:   JSON.stringify({
            startDate: document.getElementById("startDate").value,
            devolutionDate: document.getElementById("startDate").value,
            cloud: {id: $("#idCinema").val()},
            client: {idClient: 1}
        }),
        contentType:"application/JSON",
        datatype: "JSON",
        success:() => {
            alert("Reserva creada exitosamente")
            getReservations()
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

function getReservations(){

    $.ajax({
        url :   URL_RESERVATION + "all",
        type:   "GET",
        datatype:   "JSON",
        success:(response) => {
            loadReservation(response)
        }
    });
}

function loadReservation(items){
    let myTable = document.getElementsByTagName("loadReservations")

    for(let i = 0; i < items.length; i++){

        myTable+="<tr>";
        myTable+="<td>"+items[i].idReservation+"</td>";
        myTable+="<td>"+items[i].cinema.name+"</td>";
        myTable+="<td>"+items[i].client.idClient+"</td>";
        myTable+="<td>"+items[i].client.name+"</td>";
        myTable+="<td>"+items[i].client.email+"</td>";
        myTable+= items[i].client.score == null ? "<td>No tiene calificación</td>" : "<td>"+items[i].client.score+"</td>";
        myTable+="</tr>";
    }
    myTable+="</tbody>";
    $("#loadReservations").empty()
    $("#loadReservations").append(myTable);
}

$('#postReservation').click(function(){
    postReservation()
});

$('#getReservations').click(function(){
    getReservations()
});

document.addEventListener("DOMContentLoaded", loadOptions())