const BASE_URL = "http://localhost:8080/"
const URL_CATEGORY = BASE_URL + "api/Category/"
const URL_CINEMA = BASE_URL + "api/Cinema/"

function loadOptions(){

    $.ajax({
        url :   URL_CATEGORY + "all",
        type:   "GET",
        datatype:   "JSON",
        success:(response) => {
            response.forEach(element => {
                let option = document.createElement("option")
                option.innerHTML = element.name
                option.value = element.id
                $("#category").append(option)
            });
        }
    });
}

function postCinema(){

    $.ajax({
        url :  URL_CINEMA + "save",
        type:   "POST",
        data:   JSON.stringify({
            owner: $("#owner").val(),
            capacity: $("#capacity").val(),
            name: $("#name").val(),
            category: {id: $("#category").val()},
            description: $("#description").val()
        }),
        contentType:"application/JSON",
        datatype: "JSON",
        success:() => {
            alert("Cine guardado exitosamente")
            getCinemas()
        }
    });
}

function getCinemas(){

    $.ajax({
        url :   URL_CINEMA + "all",
        type:   "GET",
        datatype:   "JSON",
        success:(response) => {
            loadCinemas(response)
        }
    });
}

function loadCinemas(items){
    let myTable = document.getElementsByTagName("loadCinemas")

    for(let i = 0; i < items.length; i++){

        myTable+="<tr>";
        myTable+="<td>"+items[i].owner+"</td>";
        myTable+="<td>"+items[i].capacity+"</td>";
        myTable+="<td>"+items[i].category.name+"</td>";
        myTable+="<td>"+items[i].name+"</td>";
        myTable+="<td>"+items[i].description+"</td>";
        myTable+="</tr>";
    }
    myTable+="</tbody>";
    $("#loadCinemas").empty()
    $("#loadCinemas").append(myTable);
}

$('#postCinema').click(function(){
    postCinema()
});

$('#getCinemas').click(function(){
    getCinemas()
});


document.addEventListener("DOMContentLoaded", loadOptions())