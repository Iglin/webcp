/**
 * Created by user on 08.04.2016.
 */

document.getElementById("save_btn").onclick = function () {
    var nameInput = document.getElementById('input_name');
    var picInput = document.getElementById('input_pic');
    if (nameInput.value == "") {
        alert('You must enter the name!');
        return false;
    }
    if (picInput.value == "") {
        alert('You must enter the picture URL!');
        return false;
    }
    return true;
};