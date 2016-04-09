/**
 * Created by user on 08.04.2016.
 */
document.getElementById("save_btn").onclick = function () {
    var nameInput = document.getElementById('input_name');
    if (nameInput.value == "") {
        alert('Name is required!');
        return false;
    }
    return true;
};