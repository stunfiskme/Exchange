$(document).ready(function () {
//get image
function getImage(){
    const recipeId = $('#recipeId').val();
    const csrfHeader = $('meta[name="_csrf_header"]').attr('content');
    const csrfToken  = $('meta[name="_csrf"]').attr('content');
    $.ajax({
        url: `/api/recipes/recipeImage/${recipeId}`,
        type: "GET",
        xhrFields: {
            responseType: 'blob' 
        },
        headers: {
            [csrfHeader]: csrfToken
        },
        success: function (blob) {
            const url = URL.createObjectURL(blob);
            $('#recipe-img').attr('src', url);
        },
        error: function (xhr, status, error) {
           console.error('Error:', error);
        }
    });
}
getImage();
 });