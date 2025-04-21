$(document).ready(function () {
    $('body').on('click', '.delete-btn', function (e) {
        e.preventDefault();

        const button = $(this);
        const row = button.closest('tr');
        const ingredientId = $(this).data('id');
        let csrfHeader = $("#csrfHeader").val();
        let csrfToken = $("#csrfToken").val();

        $.ajax({
            url: `/recipe/delete/${ingredientId}`,
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

  
    $('body').on('click', '.update-btn', function (e) {
        e.preventDefault(); 

        const ingredientId = $(this).data('id');
        let csrfHeader = $("#csrfHeader").val();
        let csrfToken = $("#csrfToken").val();
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
            url: `/ingredient/update/${ingredientId}`,
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
});
