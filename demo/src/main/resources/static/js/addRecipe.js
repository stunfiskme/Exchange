$(document).ready(function () {
    $('#description').on('input', function () {
      this.style.height = 'auto'; 
      this.style.height = (this.scrollHeight) + 'px';
    });


    $('#instructions').on('input', function () {
        this.style.height = 'auto'; 
        this.style.height = (this.scrollHeight) + 'px';
      });
  });
  