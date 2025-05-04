$(document).ready(function () {
    $('body').on('click', '.delete-btn', function (e) {
        e.preventDefault();

        const button = $(this);
        const row = button.closest('tr');
        const ingredientId = $(this).data('id');
        const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
        const csrfToken  = $('meta[name="_csrf"]').attr('content');

        $.ajax({
            url: `/api/ingredients/${ingredientId}`,
            type: "DELETE",
            headers: {
                [csrfHeader]: csrfToken
            },
            success: function (result) {
                row.remove(); 
            },
            error: function (err) {
                console.error("Error:", err);
                alert("Something went wrong.");
            }
        });
        
    });

//update button logic  
    $('body').on('click', '.update-btn', function (e) {
        e.preventDefault(); 

        const ingredientId = $(this).data('id');
        const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
        const csrfToken  = $('meta[name="_csrf"]').attr('content');
        const button = $(this);
        const row = button.closest('tr');
        const isEditing = row.attr('data-editing') === 'true';

    if (isEditing) {
        row.find('.editable').attr('contenteditable', 'false');
        row.attr('data-editing', 'false');
        button.text('Update');

        const id = row.data('id');
        const amount = row.find('.amount-cell').text().trim();
        const unitName = row.find('.unitName-cell').text().trim();
        const ingredientName = row.find('.ingredientName-cell').text().trim();

        const payload = { id, amount, unitName, ingredientName};

        $.ajax({
            url: `/api/ingredients/${ingredientId}`,
            type: "PUT",
            contentType: 'application/json',
            headers: {
                [csrfHeader]: csrfToken
            },
            data: JSON.stringify(payload),
            success: function (response) {
                //console.log('Success:', response);
                row.css('background-color', '#d4f7d4');
            },
            error: function (xhr, status, error) {
                //console.error('Error:', error);
                row.css('background-color', '#f8d7da');
            }
        });

    } else {
        row.find('.editable').attr('contenteditable', 'true');
        row.attr('data-editing', 'true');
        button.text('Done');
    }
    
    });

    // save a new ingredient!
    $('body').on('click','.addIngredientBtn', function(e) {
        e.preventDefault(); 

        //crsf tokens
        const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
        const csrfToken  = $('meta[name="_csrf"]').attr('content');
        //json ingredient
        const recipeId = $('#recipeId').val();
        const ingredientName = $('#ingredientName').val();
        const amount = $('#amount').val();
        const unitName = $('#unitName').val();
        const payload = {ingredientName, amount, unitName}; 
        //table
        const $table = $('#ingredientsTable');
        
    $.ajax({
        url: `/api/ingredients/${recipeId}`,
        type: "POST",
        contentType: 'application/json',
        beforeSend(xhr) { xhr.setRequestHeader(csrfHeader, csrfToken); },
        data: JSON.stringify(payload),
        success: function (response) {
             //get the new ingredient and add it to the table
             //console.log(response);
             addIngredientRow(response.id, response.ingredientName, response.amount, response.unitName);
            },
        error: function (err) {
            console.error('Error:', err);
            alert('Something went wrong.');
        }
    });


});


function addIngredientRow(id, ingredientName, amount, unitName) {
    var row = '<tr>' +
    '<td class="editable amount-cell">' + amount + '</td>' +
    '<td class="editable unitName-cell">' + unitName + '</td>' +
    '<td class="editable ingredientName-cell">' + ingredientName + '</td>' +
    '<td>' + '<button class="btn btn-primary update-btn"data-id="' + id + '">' + 'Update </button></td>' +
    '<td>' + '<button class="btn btn-danger delete-btn" data-id="' + id + '">' + 'Delete </button></td>' 
    '</tr>';
    $('#ingredientsTable tbody').append(row);
            }
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
                                '<td class="editable amount-cell">' + i.amount + '</td>' +
                                '<td class="editable unitName-cell">' + i.unitName + '</td>' +
                                '<td class="editable ingredientName-cell">' + i.ingredientName + '</td>' +
                                '<td>' + '<button class="btn btn-primary update-btn"data-id="' + i.id + '">' + 'Update </button></td>' +
                                '<td>' + '<button class="btn btn-danger delete-btn" data-id="' + i.id + '">' + 'Delete </button></td>' 
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
