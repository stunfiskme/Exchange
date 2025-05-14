$(document).ready(function () {

//delete recipe  
$('body').on('click', '.delete-recipe-btn', function (e) {
    e.preventDefault();

    const button = $(this);
    const recipeId = $('#recipeId').val();
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    const csrfToken  = $('meta[name="_csrf"]').attr('content');

    $.ajax({
        url: `/api/recipes/${recipeId}`,
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
//update instructions  
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
        url: `/api/recipes/instructions/${recipeId}`,
        type: "PATCH",
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
    row.attr('contenteditable', 'true'); 
    row.attr('data-editing', 'true');
    button.text('Done');
}

});

//update description
$('body').on('click', '.description-update-btn', function (e) {
    e.preventDefault(); 

    const recipeId = $('#recipeId').val();
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    const csrfToken  = $('meta[name="_csrf"]').attr('content');
    const button = $(this);
    const row = $('#descriptionRow');
    const isEditing = row.attr('data-editing') === 'true';

if (isEditing) {
    row.attr('contenteditable', 'false'); 
    row.attr('data-editing', 'false');
    button.text('Update');

    const description = row.text().trim();
    const payload = {description};

    $.ajax({
        url: `/api/recipes/description/${recipeId}`,
        type: "PATCH",
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
           // console.error('Error:', error);
            row.css('background-color', '#f8d7da');
        }
    });

} else {
    row.attr('contenteditable', 'true'); 
    row.attr('data-editing', 'true');
    button.text('Done');
}

});

});