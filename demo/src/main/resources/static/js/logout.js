document.addEventListener('DOMContentLoaded', function () {
  const logoutLink = document.getElementById('logoutLink');
  if (logoutLink) {
    logoutLink.addEventListener('click', function (e) {
      e.preventDefault();
      document.getElementById('logoutForm').submit();
    });
  }
});
