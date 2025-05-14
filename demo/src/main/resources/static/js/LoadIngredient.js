$(document).ready(function () {
//get all ingredients when page is loaded
function loadTable() {

    //id for recipe
    const recipeId = $('#recipeId').val();
    //table
    const $table = $('#ingredientsTable');

    $.ajax({
        url: `/api/ingredients/${recipeId}`,
        type: "GET",
        dataType: "json",
        contentType: 'application/json',
        success: function (response) {
            response.forEach(function(i) {
                var row = '<tr>' +
                                '<td>' + i.amount + '</td>' +
                                '<td>' + i.unitName + '</td>' +
                                '<td>' + i.ingredientName + '</td>' +
                                '</tr>';
                                $('#ingredientsTable tbody').append(row);
            })
            },
        error: function (err) {
            console.error('Error:', err);
            alert('Something went wrong.');
        }
    });
}
loadTable();
 });