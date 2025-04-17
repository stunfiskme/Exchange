$(document).ready(function () {
    $('body').on('click', '.delete-btn', function (e) {
        e.preventDefault();

        const button = $(this);
        const row = button.closest('tr');
        const ingredientId = $(this).data('id');
        let csrfHeader = $("#csrfHeader").val(); // ✅ Get the string like "X-CSRF-TOKEN"
        let csrfToken = $("#csrfToken").val();

        console.log("Ingredient ID:", ingredientId);

        $.ajax({
            url: `/recipe/delete/${ingredientId}`,
            type: "DELETE",
            headers: {
                [csrfHeader]: csrfToken
            },
            success: function (result) {
                console.log("Deleted:", result);
                row.remove(); // or refresh if you want
            },
            error: function (err) {
                console.error("Error:", err);
                alert("Something went wrong.");
            }
        });
        
    });
});
