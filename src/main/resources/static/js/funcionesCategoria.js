const BASE_URL = "http://localhost:8080/"
const URL_CATEGORY = BASE_URL + "api/Category/"

function postCategory(){
    $.ajax({
        url :  URL_CATEGORY + "save",
        type:   "POST",
        data:   JSON.stringify({
            name: $("#name").val(),
            description: $("#description").val()
        }),
        contentType:"application/JSON",
        datatype: "JSON",
        success:() => {
            alert("Categoria guardada")
            getCategory()
        }
    });
}

function getCategory(){

    $.ajax({
        url :   URL_CATEGORY + "all",
        type:   "GET",
        datatype:   "JSON",
        success:(response) => {
            loadCategories(response)
        }
    });
}

function loadCategories(items){
    let myTable = document.getElementsByTagName("loadCategory")

    for(let i = 0; i < items.length; i++){
        myTable+="<tr>";
        myTable+="<td>"+items[i].name+"</td>";
        myTable+="<td>"+items[i].description+"</td>";
        myTable+="<td><ul>"
        items[i].cinemas.forEach(element => {
            myTable += `<li>${element.owner} -- ${element.capacity} -- ${element.name} -- ${element.description}</li>`;
        });
        myTable+="</ul></td>";
        myTable+="</tr>";

    }
    myTable+="</tbody>";
    $("#loadCategories").empty()
    $("#loadCategories").append(myTable);
}

$('#postCategory').click(function(){
    postCategory()
});

$('#getCategories').click(function(){
    getCategory()
});