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

// 1. Show the upload section
$('body').on('click', '.recipeImage-show-upload', function (e) {
    e.preventDefault();
    const container = document.getElementById('uploadContainer');
    container.style.display = container.style.display === 'none' ? 'block' : 'none';
});

// 2. Upload the image
$('body').on('click', '.recipeImage-upload-btn', function (e) {
    e.preventDefault();

    const recipeId = $('#recipeId').val();
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    const csrfToken  = $('meta[name="_csrf"]').attr('content');
    const fileInput = document.getElementById('file');
    const file = fileInput.files[0];

    if (!file) {
        alert("Please select an image.");
        return;
    }

    const formData = new FormData();
    formData.append("file", file);

    $.ajax({
        url: `/api/recipes/recipeImage/${recipeId}`,
        type: "PATCH",
        data: formData,
        processData: false, 
        contentType: false, 
        headers: {
            [csrfHeader]: csrfToken
        },
        success: function (response) {
            alert("Upload successful");
            // Optionally hide the upload section or refresh image
        },
        error: function (xhr, status, error) {
            alert("Upload failed");
        }
    });
});
});