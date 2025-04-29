$(document).ready(function () {
//delete button 
$('body').on('click', '.delete-recipe-btn', function (e) {
    e.preventDefault();

    const button = $(this);
    const recipeId = $('#recipeId').val();
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    const csrfToken  = $('meta[name="_csrf"]').attr('content');

    $.ajax({
        url: `/recipe/delete/${recipeId}`,
        type: "DELETE",
        headers: {
            [csrfHeader]: csrfToken
        },
        success: function (result) {
            window.location.href = "/recipes";
        },
        error: function (err) {
            console.error("Error:", err);
            alert("Something went wrong.");
        }
    });
    
});
//update button logic  
$('body').on('click', '.instruction-update-btn', function (e) {
    e.preventDefault(); 

    const recipeId = $('#recipeId').val();
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    const csrfToken  = $('meta[name="_csrf"]').attr('content');
    const button = $(this);
    const row = $('#instructionRow');
    const isEditing = row.attr('data-editing') === 'true';

if (isEditing) {
    row.attr('contenteditable', 'false'); 
    row.attr('data-editing', 'false');
    button.text('Update');

    const instructions = row.text().trim();
    const payload = {instructions};
    console.log(payload);

    $.ajax({
        url: `/recipe/update/${recipeId}`,
        type: "PATCH",
        contentType: 'application/json',
        headers: {
            [csrfHeader]: csrfToken
        },
        data: JSON.stringify(payload),
        success: function (response) {
            console.log('Success:', response);
            row.css('background-color', '#d4f7d4');
        },
        error: function (xhr, status, error) {
            console.error('Error:', error);
            row.css('background-color', '#f8d7da');
        }
    });

} else {
    row.attr('contenteditable', 'true'); 
    row.attr('data-editing', 'true');
    button.text('Done');
}

});

//get instructions
function loadInstructions() {

    //id for recipe
    const recipeId = $('#recipeId').val();
    //table
    const $div = $("#instruction-div");

    $.ajax({
        url: `/recipe/get/instructions/${recipeId}`,
        type: "GET",
        dataType: 'text',
        success: function (response) {
            $div.find('#instructionRow').remove();
                let row = '<pre class="editable instructions-cell" id="instructionRow">' +  response   + '</pre>'  
                $('#instruction-div').append(row);
            },
        error: function (err) {
            console.error('Error:', err);
            alert('Something went wrong.');
        }
    });
}
loadInstructions();
});