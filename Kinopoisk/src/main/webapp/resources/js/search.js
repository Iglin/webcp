var moviesFlag = false;
var actorsFlag = false;
var dirsFlag = false;

document.getElementById("want_dir").onclick = function () {
    dirsFlag = ! dirsFlag;
    this.checked = dirsFlag;
};
document.getElementById("want_movie").onclick = function () {
    moviesFlag = ! moviesFlag;
    this.checked = moviesFlag;
};
document.getElementById("want_actor").onclick = function () {
    actorsFlag = ! actorsFlag;
    this.checked = actorsFlag;
};

