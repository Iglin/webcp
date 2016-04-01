/**
 * Created by user on 01.04.2016.
 */
document.getElementById("input_rating").onchange = function () {
    if (parseFloat(this.value) < 0) {
        this.value = 0;
    } 
    if (parseFloat(this.value) > 10) {
        this.value = 10;
    }
};

document.getElementById("input_age").onchange = function () {
    if (parseFloat(this.value) < 0) {
        this.value = 0;
    }
    if (parseFloat(this.value) > 18) {
        this.value = 18;
    }
};

document.getElementById("input_duration").onchange = function () {
    if (parseFloat(this.value) < 0) {
        this.value = 0;
    }
    if (parseFloat(this.value) > 500) {
        this.value = 500;
    }
};

document.getElementById("save_btn").onclick = function () {
    var fields = new Array();
    fields[0] = document.getElementById("input_title");
    fields[1] = document.getElementById("input_poster");
    fields[2] = document.getElementById("text_descr");
    for (var i = 0; i < fields.length; i++) {
        if (fields[i].value == "") {
            alert("Please, enter the " + fields[i].name);
            return false;
        }
    }
    return true;
};