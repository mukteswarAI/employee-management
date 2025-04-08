// script.js
document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get the values from the form
    let email = document.getElementById('email').value;
    let password = document.getElementById('password').value;

    // In a real application, you would send this data to a server
    // For this example, we'll just log the data to the console
    console.log('Email:', email);
    console.log('Password:', password);

    // You can add your login logic here
    // For example, you might want to check if the email and password are valid
    // and then redirect the user to a different page

    // Optionally, you can clear the form after submission
    document.getElementById('email').value = '';
    document.getElementById('password').value = '';
});
