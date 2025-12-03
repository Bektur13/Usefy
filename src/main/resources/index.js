function validateForm() {
    const username = document.forms[0].username.value;
    const password = document.forms[0].password.value;

    if(username.trim() === "" || password.trim() === "") {
        alert("Both fields are required");
        return false;
    }

    if(password.length < 6) {
        alert("Password must be at least 6 characters");
        return false;
    }

    return true;
}